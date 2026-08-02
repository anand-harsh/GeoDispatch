package com.geodispatch.app.controllers;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.LoginRequestDto;
import com.geodispatch.app.dto.LoginResponseDto;
import com.geodispatch.app.dto.OnboardDriverDto;
import com.geodispatch.app.dto.SignupDto;
import com.geodispatch.app.dto.UserDto;
import com.geodispatch.app.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody SignupDto signupDto) {

        UserDto user = authService.signup(signupDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/onBoardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onboardNewDriver(
            @PathVariable Long userId,
            @Valid @RequestBody OnboardDriverDto onboardDriverDto) {

        DriverDto driver = authService.onboardNewDriver(
                userId,
                onboardDriverDto.getVehicleId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(driver);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto,
            HttpServletResponse response) {

        String[] tokens = authService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );

        Cookie refreshTokenCookie = new Cookie("refreshToken", tokens[1]);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setSecure(false); // true in production with HTTPS
        refreshTokenCookie.setMaxAge(180 * 24 * 60 * 60);

        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok(
                new LoginResponseDto(tokens[0])
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(
            HttpServletRequest request) {

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() ->
                        new AuthenticationServiceException(
                                "Refresh token not found."
                        ));

        String accessToken = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(
                new LoginResponseDto(accessToken)
        );
    }
}