package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.enums.StyleName;
import com.paintingscollectors.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paintings")

public class Painting {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String author;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "painting_id", referencedColumnName = "id")
    private Style style;
    
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
    
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    
    @Column(name = "is_favorite", nullable = false)
    private boolean isFavorite;
    
    @Column(nullable = false)
    private long votes;
    
    public Painting() {
    }
    
    public Painting(String name, 
                    String author, 
                    String imageUrl,
                    User owner,
                    Style style) {
        this.name = name;
        this.author = author;
        this.imageUrl = imageUrl;
        this.isFavorite = false;
        this.votes = 0;
        this.owner = owner;
        this.style = style;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    
    public User getOwner() {
        return owner;
    }
    
    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public boolean isFavorite() {
        return isFavorite;
    }
    
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    
    public long getVotes() {
        return votes;
    }
    
    public void setVotes(long votes) {
        this.votes = votes;
    }
    
    public Style getStyle() {
        return style;
    }
    
    public void setStyle(Style style) {
        this.style = style;
    }
}
