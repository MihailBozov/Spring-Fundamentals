package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.AddPaintingDto;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.model.enums.StyleName;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class PaintingService {
    private final UserService userService;
    private UserSession userSession;
    private PaintingRepository paintingRepository;
    private StyleRepository styleRepository;
    private UserRepository userRepository;
    
    @Autowired
    public PaintingService(UserSession userSession, PaintingRepository paintingRepository, StyleRepository styleRepository, UserRepository userRepository, UserService userService) {
        this.userSession = userSession;
        this.paintingRepository = paintingRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }
    
    public Painting createPainting(AddPaintingDto addPaintingDto) {
        
        Style style = this.styleRepository.findByStyleName(StyleName.valueOf(addPaintingDto.getStyle()));
        
        Painting painting = new Painting(
                addPaintingDto.getName(),
                addPaintingDto.getAuthor(),
                addPaintingDto.getImageUrl(),
                this.userSession.getUser(),
                style
        );
        this.paintingRepository.saveAndFlush(painting);
        return painting;
    }
    
    @Transactional(rollbackOn = Exception.class)
    public boolean deletePainting(Long id) {
        
        try {
            User user = this.userRepository.getById(userSession.getUser().getId());
            
            Optional<Painting> painting = this.paintingRepository.findById(id);
            
            if (painting.isPresent() && this.paintingRepository.notFavorite(painting.get())) {
                if (user.getRatedPaintings().contains(painting.get())) {
                    user.getRatedPaintings().remove(painting.get());
                    this.userRepository.saveAndFlush(user);
                }
                this.paintingRepository.delete(painting.get());
                return true;
            }
            return false;
            
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public List<Painting> getAddedPaintings() {
        
        Optional<List<Painting>> paintingsOpt = this.paintingRepository.findAllPaintingsByUser(this.userSession.getUser());
        
        return paintingsOpt.orElse(null);
    }
    
    public List<Painting> getFavoritePaintings() {
        Optional<List<Painting>> paintingsOpt = this.paintingRepository.findAllFavoritePaintingsByUser(this.userSession.getUser());
        return paintingsOpt.orElse(null);
    }
    
    public List<Painting> getOtherPaintings() {
        Optional<List<Painting>> paintingsOpt = this.paintingRepository.findAllPaintingsExceptFor(this.userSession.getUser());
        return paintingsOpt.orElse(null);
    }
    
    
    public List<Painting> getMostRatedPaintings() {
        Optional<List<Painting>> paintingsOpt = this.paintingRepository.findAllMostRatedPaintings();
        if (paintingsOpt.isPresent()) {
            return paintingsOpt
                    .get()
                    .stream()
                    .limit(2)
                    .toList();
        }
        return null;
    }
    
    @Transactional
    public boolean removeFromFavorite(Long id) {
        
        try {
            User user = this.userRepository.getById(userSession.getUser().getId());
            user.getFavoritePaintings().remove(this.paintingRepository.getById(id));
            this.userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean addToFavorite(Long id) {
        try {
            User user = this.userRepository.findById(userSession.getUser().getId()).get();
            Painting painting = this.paintingRepository.getById(id);
            
            
            if (!user.getFavoritePaintings().contains(painting)) {
                user.getFavoritePaintings().add(painting);
                userRepository.saveAndFlush(user);
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Transactional
    public long vote(Long id) {
        Painting painting = this.paintingRepository.getById(id);
        painting.setVotes(painting.getVotes() + 1);
        
        User user = painting.getOwner();
        
        if (!user.getRatedPaintings().contains(painting)) {
            user.getRatedPaintings().add(painting);
            userRepository.saveAndFlush(user);
        }
        else {
            userRepository.saveAndFlush(user);
        }
        return this.paintingRepository.getById(id).getVotes();
    }
}
