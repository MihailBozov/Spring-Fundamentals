package com.paintingscollectors.session;

import com.paintingscollectors.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class UserSession {
    
    private Long id;
    
    private String email;
    
    private String username;
    
    private String firstName;
    
    private String lastName;
    
    private User user;
    
   public UserSession() {
       this.id = null;
       this.email = null;
       this.firstName = null;
       this.lastName = null;
       
   }
    
    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.user = user;
        
    }
    
    public void logout() {
        this.id = null;
        this.email = null;
        this.username = null;
        
    }
    
    public boolean isLoggedIn() {
       return this.id != null;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
