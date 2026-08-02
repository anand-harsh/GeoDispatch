package com.geodispatch.app.services;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.SignupDto;
import com.geodispatch.app.dto.UserDto;

public interface AuthService {
  String[] login(String paramString1, String paramString2);
  
  UserDto signup(SignupDto paramSignupDto);
  
  DriverDto onboardNewDriver(Long paramLong, String paramString);
  
  String refreshToken(String paramString);
}
