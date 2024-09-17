package com.philately.repository;

import com.philately.model.entity.Stamp;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StampRepository extends JpaRepository<Stamp, Long> {
    @Transactional
    @Query("SELECT s FROM Stamp s JOIN s.owner AS so WHERE so.id = :id")
    List<Stamp> findAllStampsForCurrentUser(Long id);

    @Transactional
    @Query("SELECT s FROM Stamp s JOIN s.owner AS so WHERE so.id != :id")
    List<Stamp> findAllForeignStamps(Long id);
}
