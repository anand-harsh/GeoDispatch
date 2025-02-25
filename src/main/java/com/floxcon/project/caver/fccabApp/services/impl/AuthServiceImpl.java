package com.floxcon.project.caver.fccabApp.services.impl;

import com.floxcon.project.caver.fccabApp.dto.DriverDto;
import com.floxcon.project.caver.fccabApp.dto.SignupDto;
import com.floxcon.project.caver.fccabApp.dto.UserDto;
import com.floxcon.project.caver.fccabApp.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        return null;
    }

    @Override
    public DriverDto onBoardDriver(Long userId) {
        return null;
    }
}
