package me.lisacek.befarmer.upgrades;

import me.lisacek.befarmer.cons.Quest;

public class ReplantUpgrade {

    private final Quest quest;

    public ReplantUpgrade(Quest quest) {
        this.quest = quest;
    }

    public Quest getQuest() {
        return quest;
    }

}
