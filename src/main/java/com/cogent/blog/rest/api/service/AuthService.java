package com.cogent.blog.rest.api.service;

import com.cogent.blog.rest.api.payload.LoginDto;
import com.cogent.blog.rest.api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
