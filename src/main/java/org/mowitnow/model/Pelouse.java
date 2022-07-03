package org.mowitnow.model;

import lombok.Data;

@Data
public class Pelouse {
    private Coordonnees positionInitiale;
    private Coordonnees positionMaximale;

    public Pelouse() {
        positionInitiale = new Coordonnees(0, 0);
    }

    public boolean isHorsPelouse(Coordonnees coordonnees) {
        return coordonnees.getX() >= 0
                && coordonnees.getY() >= 0
                && coordonnees.getX() <= this.getPositionMaximale().getX()
                && coordonnees.getY() <= this.getPositionMaximale().getY();
    }
}
