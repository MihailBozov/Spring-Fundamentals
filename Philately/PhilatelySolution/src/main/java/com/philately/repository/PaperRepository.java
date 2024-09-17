package com.philately.repository;

import com.philately.model.PaperName;
import com.philately.model.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
    
    Optional<Paper> findPaperByName(PaperName name);
}
