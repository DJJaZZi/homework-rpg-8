package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;
import java.util.List;

public class TowerRunner {
    private final List<TowerFloor> floors;

    public TowerRunner(List<TowerFloor> floors) {
        this.floors = floors;
    }

    public TowerRunResult runTower(List<Hero> party) {
        System.out.println("\n=== BEGINNING OF THE ASSCENT TO THE TOWER ===");
        int floorsCleared = 0;

        for (TowerFloor floor : floors) {
            if (getSurvivingHeroesCount(party) == 0) {
                System.out.println("\n All heroes died. The ascent ended.");
                break;
            }

            FloorResult result = floor.explore(party);

            if (result.isCleared()) {
                floorsCleared++;
            } else {
                System.out.println("\n Squad could not go through the floor: " + result.getSummary());
                break;
            }
        }

        int survivors = getSurvivingHeroesCount(party);
        boolean reachedTop = floorsCleared == floors.size();

        return new TowerRunResult(floorsCleared, survivors, reachedTop);
    }

    private int getSurvivingHeroesCount(List<Hero> party) {
        int count = 0;
        for (Hero h : party) {
            if (h.isAlive()) count++;
        }
        return count;
    }
}