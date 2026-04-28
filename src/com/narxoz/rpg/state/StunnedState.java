package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class StunnedState implements HeroState {
    private int duration = 1;

    @Override
    public String getName() { return "Stunned"; }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return 0;
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return rawDamage;
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println(" " + hero.getName() + " stunned and skips this move!");
    }

    @Override
    public void onTurnEnd(Hero hero) {
        duration--;
        if (duration <= 0) {
            System.out.println(" Hero " + hero.getName() + " came to after being stunned.");
            hero.setState(new NormalState());
        }
    }

    @Override
    public boolean canAct() {
        return false;
    }
}