package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.enums.StyleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
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

    
    public Style(StyleName styleName, String description) {
        this.styleName = styleName;
        this.description = description;
    }

}
