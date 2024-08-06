package com.cogent.blog.rest.api.service.impl;

import com.cogent.blog.rest.api.entity.Role;
import com.cogent.blog.rest.api.entity.User;
import com.cogent.blog.rest.api.payload.LoginDto;
import com.cogent.blog.rest.api.payload.RegisterDto;
import com.cogent.blog.rest.api.repository.RoleRepository;
import com.cogent.blog.rest.api.repository.UserRespository;
import com.cogent.blog.rest.api.security.JwtTokenProvider;
import com.cogent.blog.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRespository userRespository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRespository.existsByUserName(registerDto.getUserName())){
            throw new RuntimeException("Username already exists");
        }
        if(userRespository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException("email already exists");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode( registerDto.getPassword() ));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRespository.save(user);

        return "user created successfully";
    }
}
