package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.enums.StyleName;
import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "style_name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleName styleName;
    
    @Column(nullable = false)
    private String description;
    
    public Style() {
    }
    
    public Style(StyleName styleName, String description) {
        this.styleName = styleName;
        this.description = description;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public StyleName getStyleName() {
        return styleName;
    }
    
    public void setStyleName(StyleName styleName) {
        this.styleName = styleName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
