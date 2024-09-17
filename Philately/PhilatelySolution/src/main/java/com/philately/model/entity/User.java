package com.philately.model.entity;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Stamp> purchasedStamps;
    
    
    @ManyToMany
    @JoinTable(name = "users_wushed_stamps",
            joinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stamp_id", referencedColumnName = "id"))
    private List<Stamp> wishedStamps;
    
}
