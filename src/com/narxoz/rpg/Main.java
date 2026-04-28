package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.CombatFloor;
import com.narxoz.rpg.floor.RestFloor;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.floor.TrapFloor;
import com.narxoz.rpg.state.NormalState;
import com.narxoz.rpg.state.StunnedState;
import com.narxoz.rpg.tower.TowerRunResult;
import com.narxoz.rpg.tower.TowerRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero("Knight Leon", 100, 20, 10);
        hero1.setState(new NormalState());

        Hero hero2 = new Hero("Mage Elliot", 70, 35, 5);
        hero2.setState(new StunnedState());

        List<Hero> party = Arrays.asList(hero1, hero2);

        List<TowerFloor> floors = new ArrayList<>();
        floors.add(new TrapFloor());
        floors.add(new CombatFloor());
        floors.add(new RestFloor());
        floors.add(new CombatFloor());

        TowerRunner runner = new TowerRunner(floors);
        TowerRunResult result = runner.runTower(party);

        System.out.println("\n=== ASCENT SUMMARY ===");
        System.out.println("Floors passed: " + result.getFloorsCleared() + " out of " + floors.size());
        System.out.println("Alive heroes: " + result.getHeroesSurviving());
        System.out.println("Tower was defeated: " + (result.isReachedTop() ? "Yes!" : "No..."));
    }
}