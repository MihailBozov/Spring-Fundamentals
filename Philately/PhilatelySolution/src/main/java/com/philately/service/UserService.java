package com.philately.service;

import com.philately.model.dto.LoginDto;
import com.philately.model.dto.RegisterDto;
import com.philately.model.entity.User;
import com.philately.repository.UserRepository;
import com.philately.util.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserSession userSession;
    private final StampService stampService;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserSession userSession, StampService stampService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
        this.stampService = stampService;
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
        this.stampService.deleteWishedStamps();
        this.userSession.logout();
    }
    
    public boolean isLoggedIn() {
        return this.userSession.isLoggedIn();
    }
}
