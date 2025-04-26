package com.example.battle.service;

import com.example.battle.entity.Platoon;
import com.example.battle.repository.PlatoonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BattleService {

    private final PlatoonRepository platoonRepository;

    public BattleService(PlatoonRepository platoonRepository) {
        this.platoonRepository = platoonRepository;
    }

    public String findWinningOrder(List<Platoon> ownPlatoons, List<Platoon> opponentPlatoons) {
        List<List<Platoon>> allOrders = generatePermutations(ownPlatoons);
        for (List<Platoon> order : allOrders) {
            if (isWinningArrangement(order, opponentPlatoons)) {
                return formatOutput(order);
            }
        }
        return "There is no chance of winning";
    }

    private boolean isWinningArrangement(List<Platoon> ownOrder, List<Platoon> opponentOrder) {
        int wins = 0;
        for (int i = 0; i < ownOrder.size(); i++) {
            if (ownOrder.get(i).battleOutcome(opponentOrder.get(i)).equals("Win")) {
                wins++;
            }
        }
        return wins >= 3;
    }

    private List<List<Platoon>> generatePermutations(List<Platoon> platoons) {
        List<List<Platoon>> result = new ArrayList<>();
        permute(platoons, 0, result);
        return result;
    }

    private void permute(List<Platoon> platoons, int start, List<List<Platoon>> result) {
        if (start == platoons.size() - 1) {
            result.add(new ArrayList<>(platoons));
            return;
        }
        for (int i = start; i < platoons.size(); i++) {
            Collections.swap(platoons, start, i);
            permute(platoons, start + 1, result);
            Collections.swap(platoons, start, i);
        }
    }

    private String formatOutput(List<Platoon> platoons) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < platoons.size(); i++) {
            sb.append(platoons.get(i).toString());
            if (i < platoons.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
