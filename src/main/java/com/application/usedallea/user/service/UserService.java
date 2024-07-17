package com.application.usedallea.user.service;

import com.application.usedallea.user.dto.UserModifyDto;
import com.application.usedallea.user.dto.UserRegisterDto;

public interface UserService{

    void registerUser(UserRegisterDto userRegisterDto);

    void updateUser(UserModifyDto userModifyDto);

    void deactivateUser(String userId);

    boolean loginUser(UserRegisterDto userRegisterDto);

    boolean checkDuplicatedUser(String userId);
}
