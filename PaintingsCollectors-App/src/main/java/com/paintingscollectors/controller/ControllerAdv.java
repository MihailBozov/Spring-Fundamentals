package com.paintingscollectors.controller;

import com.paintingscollectors.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerAdv {
    private UserSession userSession;
    
    @Autowired
    public ControllerAdv(UserSession userSession) {
        this.userSession = userSession;
    }
    
    @ModelAttribute("userSession")
    public UserSession getSession() {
        return this.userSession;
    }
}
