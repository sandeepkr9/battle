package com.example.battle.controller;

import com.example.battle.entity.Platoon;
import com.example.battle.service.BattleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/battle")
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/simulate")
    public String simulateBattle(
            @RequestBody BattleRequest request
    ) {
        return battleService.findWinningOrder(request.getOwnPlatoons(), request.getOpponentPlatoons());
    }

    public static class BattleRequest {
        private List<Platoon> ownPlatoons;
        private List<Platoon> opponentPlatoons;

        public List<Platoon> getOwnPlatoons() {
            return ownPlatoons;
        }

        public void setOwnPlatoons(List<Platoon> ownPlatoons) {
            this.ownPlatoons = ownPlatoons;
        }

        public List<Platoon> getOpponentPlatoons() {
            return opponentPlatoons;
        }

        public void setOpponentPlatoons(List<Platoon> opponentPlatoons) {
            this.opponentPlatoons = opponentPlatoons;
        }
    }
}
