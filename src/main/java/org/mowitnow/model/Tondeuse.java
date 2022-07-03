package org.mowitnow.model;

import lombok.Data;

@Data
public class Tondeuse {
    private int id;
    private Coordonnees coordonneesActuelles;
    private Orientation orientation;
}
