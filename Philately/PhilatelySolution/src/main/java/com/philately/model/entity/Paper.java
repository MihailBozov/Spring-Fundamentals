package com.philately.model.entity;

import com.philately.model.PaperName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "papers")
public class Paper {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperName name;
    
    @Column(nullable = false)
    private String description;
    
    public Paper(PaperName name, String description) {
        this.name = name;
        this.description = description;
    }
}
