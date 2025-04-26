package com.example.battle.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Platoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unitClass;
    private int soldierCount;

    public Platoon() {
        super();
    }

    public Platoon(Long id, String unitClass, int soldierCount) {
        this.id = id;
        this.unitClass = unitClass;
        this.soldierCount = soldierCount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitClass() {
        return unitClass;
    }

    public void setUnitClass(String unitClass) {
        this.unitClass = unitClass;
    }

    public int getSoldierCount() {
        return soldierCount;
    }

    public void setSoldierCount(int soldierCount) {
        this.soldierCount = soldierCount;
    }

    @Transient
    private static final Map<String, List<String>> ADVANTAGE_MAP = createAdvantageMap();

    public int effectiveStrengthAgainst(Platoon opponent) {
        if (ADVANTAGE_MAP.getOrDefault(this.unitClass, Collections.emptyList()).contains(opponent.unitClass)) {
            return this.soldierCount * 2;
        }
        return this.soldierCount;
    }

    public String battleOutcome(Platoon opponent) {
        int ownStrength = this.effectiveStrengthAgainst(opponent);
        if (ownStrength > opponent.soldierCount) {
            return "Win";
        } else if (ownStrength == opponent.soldierCount) {
            return "Draw";
        } else {
            return "Loss";
        }
    }

    private static Map<String, List<String>> createAdvantageMap() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Militia", Arrays.asList("Spearmen", "LightCavalry"));
        map.put("Spearmen", Arrays.asList("LightCavalry", "HeavyCavalry"));
        map.put("LightCavalry", Arrays.asList("FootArcher", "CavalryArcher"));
        map.put("HeavyCavalry", Arrays.asList("Militia", "FootArcher", "LightCavalry"));
        map.put("CavalryArcher", Arrays.asList("Spearmen", "HeavyCavalry"));
        map.put("FootArcher", Arrays.asList("Militia", "CavalryArcher"));
        return map;
    }

    @Override
    public String toString() {
        return unitClass + "#" + soldierCount;
    }
}