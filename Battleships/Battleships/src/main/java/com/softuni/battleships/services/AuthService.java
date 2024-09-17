package com.softuni.battleships.services;

import com.softuni.battleships.models.dtos.LoginDto;
import com.softuni.battleships.models.dtos.UserRegistrationDto;
import com.softuni.battleships.models.entities.User;
import com.softuni.battleships.repositories.UserRepository;
import com.softuni.battleships.sessions.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    
    private UserRepository userRepository;
    private UserSession userSession;
    
   
    @Autowired
    public AuthService(UserRepository userRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }
    
    public boolean register(UserRegistrationDto registrationDto) {
        
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            return false;
        }
        
        Optional<User> byEmail = this.userRepository.findByEmail(registrationDto.getEmail());        
        if (byEmail.isPresent()) {
            return false;
        }
        
        Optional<User> byPassword = this.userRepository.findByPassword(registrationDto.getPassword());
        if (byEmail.isPresent()) {
            return false;
        }
        
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setFullName(registrationDto.getFullName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        
        this.userRepository.save(user);
        return true;
    }
    
    public boolean login(LoginDto loginDto) {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        
        if (user.isEmpty()) {
            return false;
        }
        this.userSession.login(user.get());
        return true;
    }
    
    public void logout() {
        this.userSession.logout();
    }
    
    public boolean isLogged() {
        return this.userSession.getId() > 0;
    }
    
    public long getLoggedUserId() {
        return this.userSession.getId();
    }
}
