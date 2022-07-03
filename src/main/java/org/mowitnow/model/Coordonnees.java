package org.mowitnow.model;

import lombok.Data;

@Data
public class Coordonnees {
    private int x;
    private int y;

    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
