package com.sa.socialcoding.sms.assembler;

import com.sa.socialcoding.sms.dto.ParentDetailDTO;
import com.sa.socialcoding.sms.dto.UserDTO;
import com.sa.socialcoding.sms.model.ParentDetail;
import com.sa.socialcoding.sms.model.User;
import com.sa.socialcoding.sms.model.UserCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@Component
public class UserAssembler {

    public User fromUserDTOToEntity(UserDTO userDTO){
        User userEntity = new User();
        BeanUtils.copyProperties(userDTO, userEntity);
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            java.util.Date date = formatter.parse(userDTO.getDob());
            java.sql.Date dob = new java.sql.Date(date.getTime());
            userEntity.setDob(dob);
        }catch(Exception e){
            log.error("Exception in date conversion", e);
        }
        if(Objects.nonNull(userDTO.getParentDetailDTO())){
            fromParentEntityToDTO(userEntity, userDTO.getParentDetailDTO());
        }
        return userEntity;
    }

    public UserDTO fromUserEntityToDTO(User userEntity){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        userDTO.setDob(formatter.format(userEntity.getDob()));
        if(Objects.nonNull(userEntity.getParentDetail())){
            fromParentDTOToEntity(userDTO, userEntity.getParentDetail());
        }
        return userDTO;
    }

    private void fromParentDTOToEntity(UserDTO userDTO, ParentDetail parentDetail){
        ParentDetailDTO parentDetailDTO = new ParentDetailDTO();
        parentDetailDTO.setFirstName(parentDetail.getFirstName());
        parentDetailDTO.setMiddleName(parentDetail.getMiddleName());
        parentDetailDTO.setLastname(parentDetail.getLastname());
        parentDetailDTO.setRelationType(parentDetail.getRelationType());
        parentDetailDTO.setMobile(parentDetail.getMobile());
        parentDetailDTO.setMailId(parentDetail.getMailId());
        userDTO.setParentDetailDTO(parentDetailDTO);
    }

    private void fromParentEntityToDTO(User userEntity, ParentDetailDTO parentDetailDTO){
        ParentDetail parentDetail = new ParentDetail();
        parentDetail.setStudent(userEntity);
        parentDetail.setFirstName(parentDetailDTO.getFirstName());
        parentDetail.setMiddleName(parentDetailDTO.getMiddleName());
        parentDetail.setLastname(parentDetailDTO.getLastname());
        parentDetail.setRelationType(parentDetailDTO.getRelationType());
        parentDetail.setMobile(parentDetailDTO.getMobile());
        parentDetail.setMailId(parentDetailDTO.getMailId());
        userEntity.setParentDetail(parentDetail);
    }
}
