package com.softuni.battleships.services;

import com.softuni.battleships.models.dtos.StartBattleDto;
import com.softuni.battleships.models.entities.Ship;
import com.softuni.battleships.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {
    
    private ShipRepository shipRepository;
    
    @Autowired
    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }
    
    public void attack(StartBattleDto battleDto) {
        Optional<Ship> attackerOpt = this.shipRepository.findById((long) battleDto.getAttackerId());
        Optional<Ship> defenderOpt = this.shipRepository.findById((long) battleDto.getDefenderId());
        
        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        Ship attacker = attackerOpt.get();
        Ship defender = defenderOpt.get();
        
        long newDefenderHealth = defender.getHealth() - attacker.getPower();
        
        if (newDefenderHealth <= 0) {
            this.shipRepository.delete(defender);
        } else {
            defender.setHealth(newDefenderHealth);
            this.shipRepository.saveAndFlush(defender);
            
        }
        
    }
}
