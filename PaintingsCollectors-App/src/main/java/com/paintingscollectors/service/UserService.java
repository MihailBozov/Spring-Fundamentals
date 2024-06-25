package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.UserLoginDto;
import com.paintingscollectors.model.dto.UserRegisterDto;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.session.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private UserSession userSession;
    private ModelMapper modelMapper;
    
    @Autowired
    public UserService(UserRepository userRepository, UserSession userSession, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }
    
    
    public User register(UserRegisterDto registerDto) {
        User user = this.modelMapper.map(registerDto, User.class);
        this.userRepository.saveAndFlush(user);
        return user;
    }
    
    public User login(UserLoginDto loginDto) {
        User user = this.userRepository.getByUsername(loginDto.getUsername()); 
        this.userSession.login(user);
        return user;
    }
    
    public void logout() {
        this.userSession.logout();
    }
    
   
}
