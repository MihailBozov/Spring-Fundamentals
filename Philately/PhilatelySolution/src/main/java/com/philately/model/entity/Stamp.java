package com.philately.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stamps")
public class Stamp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "paper_id", referencedColumnName = "id")
    private Paper paper;
    
    @Column(nullable = false)
    private int price;
    
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;
    
    
    
}
