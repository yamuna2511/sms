package com.sa.socialcoding.sms.service.impl;

import com.sa.socialcoding.sms.assembler.UserAssembler;
import com.sa.socialcoding.sms.dto.UserDTO;
import com.sa.socialcoding.sms.model.User;
import com.sa.socialcoding.sms.model.UserCredentials;
import com.sa.socialcoding.sms.repository.UserRepository;
import com.sa.socialcoding.sms.repository.impl.UserCredentialRepository;
import com.sa.socialcoding.sms.repository.impl.UserCustomRepository;
import com.sa.socialcoding.sms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCustomRepository userCustomRepository;
    @Autowired
    private UserAssembler userAssembler;
    @Autowired
    private UserCredentialRepository userCredentialRepo;

    @Override
    public List<UserDTO> getUsers(Integer userId, String userType, String firstName) {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> users = userCustomRepository.getUsers(userId, userType, firstName);
        for(User user : users){
            UserDTO userDTO = userAssembler.fromUserEntityToDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public void submit(UserDTO userRequest) {
        log.info("Creating new user", userRequest.getDob());
        User user = userAssembler.fromUserDTOToEntity(userRequest);
        user.setCreatedOn(new Date());

        String email = userRequest.getMailId();
        String[] arr = email.split("@");
        //setting user credential
        UserCredentials userCredential = new UserCredentials();
        userCredential.setUserName(arr[0].toUpperCase());
        userCredential.setPassword("abcd");
        userCredential.setUserStatus("NEW");
        userCredential.setUser(user);
        user.setUserCredential(userCredential);
        userRepository.save(user);
    }

    @Override
    public Boolean validateLogin(String userName, String password) {
        log.info("Validating user");
        UserCredentials userCred = userCredentialRepo.findByUserName(userName);
        return userCred.getPassword().contentEquals(password) ? true : false;
    }
}
