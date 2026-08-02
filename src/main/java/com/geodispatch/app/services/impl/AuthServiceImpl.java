package com.geodispatch.app.services.impl;

import com.geodispatch.app.dto.DriverDto;
import com.geodispatch.app.dto.SignupDto;
import com.geodispatch.app.dto.UserDto;
import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.User;
import com.geodispatch.app.entities.enums.Role;
import com.geodispatch.app.exceptions.ResourceNotFoundException;
import com.geodispatch.app.exceptions.RuntimeConflictException;
import com.geodispatch.app.repositories.UserRepository;
import com.geodispatch.app.security.JWTService;
import com.geodispatch.app.services.AuthService;
import com.geodispatch.app.services.DriverService;
import com.geodispatch.app.services.RiderService;
import com.geodispatch.app.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) authentication.getPrincipal();

        return new String[]{
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        };
    }

    @Override
    public UserDto signup(SignupDto signupDto) {

        if (userRepository.findByEmail(signupDto.getEmail()).isPresent()) {
            throw new RuntimeConflictException(
                    "User already exists with email: " + signupDto.getEmail()
            );
        }

        User user = modelMapper.map(signupDto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.RIDER));

        User savedUser = userRepository.save(user);

        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        ));

        if (user.getRoles().contains(Role.DRIVER)) {
            throw new RuntimeConflictException(
                    "User is already registered as a driver."
            );
        }

        Driver driver = Driver.builder()
                .user(user)
                .vehicleId(vehicleId)
                .available(true)
                .rating(0.0)
                .build();

        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);

        Driver savedDriver = driverService.createNewDriver(driver);

        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {

        Long userId = jwtService.getUserIdFromToken(refreshToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        ));

        return jwtService.generateAccessToken(user);
    }
}