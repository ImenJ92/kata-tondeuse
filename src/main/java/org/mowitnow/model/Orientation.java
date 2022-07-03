package org.mowitnow.model;

import lombok.Data;


public enum Orientation {
    NORTH('N', "Nord"),
    EAST('E', "est"),
    WEST('W', "ouest"),
    SOUTH('S', "sud");

    private char code;
    private String description;


    private Orientation(char code, String description) {
        this.code = code;
        this.description = description;
    }

    public char getCode() {
        return code;
    }

    public static Orientation getOrientationFromCode(char c) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.getCode() == c) {
                return orientation;
            }
        }
        return null;
    }
}
