package com.philately.util;

import com.philately.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@Component
@SessionScope
public class UserSession {
    private Long id;
    private User user;
    
    public void login(User user) {
        this.id = user.getId();
        this.user = user;
        
    }
    
    public void logout(){
        this.user = null;
        
    }
    
    public boolean isLoggedIn() {
        return this.user != null;
    }
}
