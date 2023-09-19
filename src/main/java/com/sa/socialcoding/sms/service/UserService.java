package com.sa.socialcoding.sms.service;

import com.sa.socialcoding.sms.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers(Integer userId, String userType, String firstName);

    String submit(UserDTO userRequest);

    Boolean validateLogin(String userName, String password);
}
