package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState {
    private int duration = 3;

    @Override
    public String getName() { return "Sent (" + duration + " move.)"; }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return (int) (basePower * 0.8);
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return (int) (rawDamage * 1.2);
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println(" Poison takes 5 damage to hero  " + hero.getName() + "!");
        hero.takeDamage(5);
    }

    @Override
    public void onTurnEnd(Hero hero) {
        duration--;
        if (duration <= 0) {
            System.out.println(" The effect of poison on the hero " + hero.getName() + " ended.");
            hero.setState(new NormalState());
        }
    }

    @Override
    public boolean canAct() {
        return true;
    }
}