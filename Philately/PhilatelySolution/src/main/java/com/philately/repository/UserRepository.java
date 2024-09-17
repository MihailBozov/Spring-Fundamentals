package com.philately.repository;

import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);
    
    @Transactional
    @Query("SELECT ws FROM User u JOIN u.wishedStamps ws WHERE u.id = :userId")
    List<Stamp> findAllWishedStamps(Long userId);
    
    @Transactional
    @Query("SELECT up FROM User u JOIN u.purchasedStamps up WHERE u.id = :userId")
    List<Stamp> findAllPurchasedStamps(Long userId);
}
