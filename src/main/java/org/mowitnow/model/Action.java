package org.mowitnow.model;

import lombok.Data;

public enum Action {
    DROITE('D', "Droite"),
    GAUCHE('G', "Gauche"),
    AVANCER('A', "Avancer");

    private char code;
    private String description;

    private Action(char code, String description) {
        this.code = code;
        this.description = description;
    }

    public char getCode() {
        return code;
    }

    public static Action getActionFromCode(char code) {
        for (Action action : Action.values()) {
            if (action.getCode() == code) {
                return action;
            }
        }
        return null;
    }
}
