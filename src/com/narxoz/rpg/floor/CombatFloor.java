package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import java.util.List;

public class CombatFloor extends TowerFloor {
    private Monster monster;

    @Override
    protected String getFloorName() { return "Goblin room"; }

    @Override
    protected void setup(List<Hero> party) {
        monster = new Monster("Angry Goblin", 50, 15);
        System.out.println(" Monster appeared: " + monster.getName() + " (" + monster.getHp() + " HP)");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int totalDamageTaken = 0;

        while (monster.isAlive() && isPartyAlive(party)) {
            for (Hero hero : party) {
                if (hero.isAlive()) {
                    hero.getState().onTurnStart(hero);

                    if (hero.isAlive() && hero.getState().canAct()) {
                        int damage = hero.getState().modifyOutgoingDamage(hero.getAttackPower());
                        monster.takeDamage(damage);
                        System.out.println(" " + hero.getName() + " hits monster on  " + damage + " damage!");
                    }

                    hero.getState().onTurnEnd(hero);
                }
                if (!monster.isAlive()) break;
            }

            if (monster.isAlive()) {
                for (Hero hero : party) {
                    if (hero.isAlive()) {
                        int rawDamage = monster.getAttackPower();
                        int actualDamage = hero.getState().modifyIncomingDamage(rawDamage);
                        hero.takeDamage(actualDamage);
                        totalDamageTaken += actualDamage;
                        System.out.println(" Monster hits " + hero.getName() + " on " + actualDamage + " damage!");
                    }
                }
            }
        }

        boolean cleared = !monster.isAlive();
        return new FloorResult(cleared, totalDamageTaken, cleared ? "Monster defeated!" : "Squad was eliminated in the battle.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println(" Heroes found 100 golden coin in a goblin room.");
    }

    private boolean isPartyAlive(List<Hero> party) {
        for (Hero h : party) { if (h.isAlive()) return true; }
        return false;
    }
}