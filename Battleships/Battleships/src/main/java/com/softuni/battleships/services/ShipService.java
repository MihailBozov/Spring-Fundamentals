package com.softuni.battleships.services;

import com.softuni.battleships.models.dtos.BattleShipDto;
import com.softuni.battleships.models.dtos.ShipDto;
import com.softuni.battleships.models.dtos.StartBattleDto;
import com.softuni.battleships.models.entities.Ship;
import com.softuni.battleships.models.entities.User;
import com.softuni.battleships.models.entities.enums.ShipType;
import com.softuni.battleships.repositories.CategoryRepository;
import com.softuni.battleships.repositories.ShipRepository;
import com.softuni.battleships.repositories.UserRepository;
import com.softuni.battleships.sessions.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {
    
    private UserSession userSession;
    
    private ShipRepository shipRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    
    @Autowired
    public ShipService(UserSession userSession, ShipRepository shipRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.userSession = userSession;
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
    
    public boolean create(ShipDto shipDto) {
        Optional<Ship> shipOpt = this.shipRepository.findByName(shipDto.getName());
        if (shipOpt.isPresent()) {
            return false;
        }
        
        Optional<User> userOpt = this.userRepository.findById(this.userSession.getId());
        if (userOpt.isEmpty()) {
            return false;
        }
        
        ShipType type = switch (shipDto.getCategory()) {
            case 1 -> ShipType.BATTLE;
            case 2 -> ShipType.CARGO;
            case 3 -> ShipType.PATROL;
            default -> ShipType.BATTLE;
        };

//        String shipName = ShipType.values()[shipDto.getCategory()].name();
        
        Ship ship = new Ship();
        ship.setName(shipDto.getName());
        ship.setCreated(shipDto.getCreated());
        ship.setCategory(this.categoryRepository.findByName(type));
//        ship.setCategory(this.categoryRepository.findByName(shipName));
        ship.setUser(userOpt.get());
        
        
        this.shipRepository.saveAndFlush(ship);
        return true;
    }
    
    
    public List<BattleShipDto> getShipsOwnedBy(long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
                .stream()
                .map(ship -> new BattleShipDto(ship))
                .collect(Collectors.toList());
    }
    
    public List<BattleShipDto> getShipsNotOwnedBy(long ownedId) {
        return this.shipRepository.findByUserIdNot(ownedId)
                .stream()
                .map(ship -> new BattleShipDto(ship))
                .collect(Collectors.toList());
        
    }
    
    public List<BattleShipDto> getAllSorted() {
        return this.shipRepository.findAllByOrderByNameAscHealthAscPower()
                .stream()
                .map(ship -> new BattleShipDto(ship))
                .collect(Collectors.toList());
        
    }
}
