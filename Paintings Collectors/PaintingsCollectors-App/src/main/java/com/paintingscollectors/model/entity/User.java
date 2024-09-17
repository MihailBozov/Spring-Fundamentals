package com.paintingscollectors.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToMany(mappedBy = "owner", targetEntity = Painting.class, fetch = FetchType.EAGER)
    private List<Painting> paintings;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "favorite_paintings",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "painting_id"))
    private List<Painting> favoritePaintings;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rated_paintings",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "painting_id"))
    private List<Painting> ratedPaintings;
    
    
    public User() {
        this.paintings = new ArrayList<>();
        this.favoritePaintings = new ArrayList<>();
        this.ratedPaintings = new ArrayList<>();
    }

}
















