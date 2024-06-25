package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
    
    @Query("SELECT p from Painting AS p WHERE p.owner = :owner")
    Optional<List<Painting>> findAllPaintingsByUser(User owner);
    
    @Query("SELECT fp FROM User AS u join u.favoritePaintings AS fp WHERE u = :owner")
    Optional<List<Painting>> findAllFavoritePaintingsByUser(User owner);
    
    @Query("SELECT p FROM Painting AS p WHERE p.owner != :owner")
    Optional<List<Painting>> findAllPaintingsExceptFor(User owner); 
    
    @Query("SELECT p from User AS u JOIN u.ratedPaintings AS p ORDER BY p.votes DESC")
    Optional<List<Painting>> findAllMostRatedPaintings();
    
    @Query("SELECT COUNT(p) = 0 FROM User AS u JOIN u.favoritePaintings AS p WHERE p = :painting")
    boolean notFavorite(Painting painting);
    
    
    
    
}
