package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.PoisonedState;
import java.util.List;

public class TrapFloor extends TowerFloor {
    @Override
    protected String getFloorName() { return "Corridor of Poisoned darts"; }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("️ You hear the sound of the pressure plate... ");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int totalDamage = 0;
        for (Hero hero : party) {
            if (hero.isAlive()) {
                System.out.println(" " + hero.getName() + " hit by dart!");
                hero.takeDamage(10);

                hero.setState(new PoisonedState());
                totalDamage += 10;
            }
        }
        return new FloorResult(true, totalDamage, "Squad got through the trap, but was poisoned.");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
    }
}