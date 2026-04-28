package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import java.util.List;

public class RestFloor extends TowerFloor {
    @Override
    protected String getFloorName() { return "Holy Source"; }

    @Override
    protected void announce() {
        System.out.println("\n You entered to quiet and calm place... It is " + getFloorName() + ".");
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println(" Heroes are making fire and securely rest.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) {
                hero.heal(20);
                System.out.println(" " + hero.getName() + " drinks from the source and regains 20 HP.");
            }
        }
        return new FloorResult(true, 0, "Squad successfully rested.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println(" Heroes found some supplies on the edge of the source.");
    }
}