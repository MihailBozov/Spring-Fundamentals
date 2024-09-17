package com.bonappetit.service;

import com.bonappetit.model.dto.LoginDto;
import com.bonappetit.model.dto.RegisterDto;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.util.UserSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserSession userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
    }

    public void register(RegisterDto registerDto) {
        User user = this.modelMapper.map(registerDto, User.class);
        this.userRepository.saveAndFlush(user);
    }

    public void login(LoginDto loginDto) {
        Optional<User> user = this.userRepository.findUserByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        user.ifPresent(this.userSession::login);
    }

    public void logout() {
        this.userSession.logout();
    }
    
    public boolean isLoggedIn() {
        return this.userSession.isLoggedIn();
    }
}
