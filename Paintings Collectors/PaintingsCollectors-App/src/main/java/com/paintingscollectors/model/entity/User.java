package com.paintingscollectors.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Painting> getPaintings() {
        return paintings;
    }
    
    public void setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
    }
    
    public List<Painting> getFavoritePaintings() {
        return favoritePaintings;
    }
    
    public void setFavoritePaintings(List<Painting> favoritePaintings) {
        this.favoritePaintings = favoritePaintings;
    }
    
    public List<Painting> getRatedPaintings() {
        return ratedPaintings;
    }
    
    public void setRatedPaintings(List<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
    }
}
















