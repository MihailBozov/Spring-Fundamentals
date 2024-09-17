package com.philately.service;

import com.philately.model.PaperName;
import com.philately.model.dto.StampDto;
import com.philately.model.entity.Paper;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.PaperRepository;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import com.philately.util.UserSession;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.PreRemove;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StampService {

    private final StampRepository stampRepository;
    private final PaperRepository paperRepository;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final ModelMapper modelMapper;

    @Autowired
    public StampService(StampRepository stampRepository, ModelMapper modelMapper, PaperRepository paperRepository, UserRepository userRepository, UserSession userSession) {
        this.stampRepository = stampRepository;
        this.modelMapper = modelMapper;
        this.paperRepository = paperRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public void addStamp(StampDto stampDto) {
        Stamp stamp = this.modelMapper.map(stampDto, Stamp.class);

        Optional<Paper> paper = this.paperRepository.findPaperByName(PaperName.valueOf(stampDto.getPaper()));
        Optional<User> user = this.userRepository.findById(this.userSession.getId());

        if (paper.isPresent() && user.isPresent()) {
            stamp.setPaper(paper.get());
            stamp.setOwner(user.get());
            stampRepository.saveAndFlush(stamp);
        }
    }

    public List<Stamp> getMyStamps() {
        return this.stampRepository.findAllStampsForCurrentUser(this.userSession.getId());
    }

    public List<Stamp> getForeignStamps() {
        return this.stampRepository.findAllForeignStamps(this.userSession.getId());
    }

    @Transactional
    public void addToWishList(Long id) {
        Optional<User> user = this.userRepository.findById(this.userSession.getId());
        Optional<Stamp> stamp = this.stampRepository.findById(id);

        if (user.isPresent() && stamp.isPresent()) {
            if (!user.get().getWishedStamps().contains(stamp.get())) {
                user.get().getWishedStamps().add(stamp.get());
            }
            this.userRepository.saveAndFlush(user.get());
        }
    }

    @Transactional
    public void deleteWishedStamps() {
        Optional<User> user = this.userRepository.findById(this.userSession.getId());

        if (user.isPresent()) {
            user.get().getWishedStamps().clear();
            this.userRepository.saveAndFlush(user.get());
        }
    }

    public List<Stamp> getWishedStamps() {
        return this.userRepository.findAllWishedStamps(this.userSession.getId());
    }

    @Transactional
    public void removeFromFavorites(Long stampId) {
        Optional<User> user = this.userRepository.findById(this.userSession.getId());
        Optional<Stamp> stamp = this.stampRepository.findById(stampId);

        if (user.isPresent() && stamp.isPresent()) {
            user.get().getWishedStamps().remove(stamp.get());
            this.userRepository.saveAndFlush(user.get());
        }
    }

    public List<Stamp> getAllPurchasedStamps() {
        return this.userRepository.findAllPurchasedStamps(this.userSession.getId());
    }
    
    @Transactional
    public void buyAll() {
        Optional<User> user = this.userRepository.findById(this.userSession.getId());

        if (user.isPresent()) {
            List<Stamp> wishedStamps = this.getWishedStamps();
            List<Stamp> purchasedStamps = this.getAllPurchasedStamps();
            for (Stamp stamp : wishedStamps) {
                user.get().getPurchasedStamps().add(stamp);
                user.get().getWishedStamps().remove(stamp);
            }
            this.userRepository.saveAndFlush(user.get());
        }

    }
}
