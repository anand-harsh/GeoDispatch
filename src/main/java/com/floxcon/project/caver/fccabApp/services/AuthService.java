package com.floxcon.project.caver.fccabApp.services;

import com.floxcon.project.caver.fccabApp.dto.DriverDto;
import com.floxcon.project.caver.fccabApp.dto.SignupDto;
import com.floxcon.project.caver.fccabApp.dto.UserDto;

public interface AuthService {
    String login(String email, String password); // return token
    UserDto signup(SignupDto signupDto);
    DriverDto onBoardDriver(Long userId);
}
