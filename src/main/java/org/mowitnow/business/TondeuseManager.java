package org.mowitnow.business;

import org.mowitnow.model.Action;
import org.mowitnow.model.Orientation;
import org.mowitnow.model.Coordonnees;
import org.mowitnow.model.Pelouse;
import org.mowitnow.model.Tondeuse;

import java.util.List;

public class TondeuseManager {
    /**
     * appliquer l'ensemble des actions sur la Tondeuse et la retourner avec les attributs résultants
     * @param tondeuse
     * @param actions
     * @param pelouse
     * @return Tondeuse: la tondeuse avec ses nouveaux attributs
     */
    public static Tondeuse applyAction(Tondeuse tondeuse, List<Action> actions, Pelouse pelouse) {
        for (int i = 0; i < actions.size(); i++) {
            switch (actions.get(i)) {
                case AVANCER:
                    tondeuse.setCoordonneesActuelles(avancer(tondeuse, pelouse));
                    break;
                case DROITE:
                    tondeuse.setOrientation(tournerADroite(tondeuse.getOrientation()));
                    break;
                case GAUCHE:
                    tondeuse.setOrientation(tournerAGauche(tondeuse.getOrientation()));
                    break;
            }
        }
        return tondeuse;
    }

    /**
     * déterminer les nouveaux coordonnées suite à un avancement de la tondeuse
     * @param tondeuse
     * @param pelouse
     * @return Coordonnées: nouveaux coordonnées de la tondeuse
     */
    private static Coordonnees avancer(Tondeuse tondeuse, Pelouse pelouse) {
        int x = tondeuse.getCoordonneesActuelles().getX();
        int y = tondeuse.getCoordonneesActuelles().getY();
        switch (tondeuse.getOrientation()) {
            case NORTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y--;
                break;
            case WEST:
                x--;
                break;
        }
        Coordonnees nouveauxCoordonnees = new Coordonnees(x, y);
        if (nouveauxCoordonnees != null
                && pelouse.isHorsPelouse(nouveauxCoordonnees)) {
            return nouveauxCoordonnees;
        } else {
            return tondeuse.getCoordonneesActuelles();
        }
    }


    /**
     * déterminer la nouvelle orientation de la tondeuse suite à une pivotation a droite
     * @param orientation
     * @return Orientation
     */
    public static Orientation tournerADroite(Orientation orientation) {
        switch (orientation) {
            case NORTH:
                return Orientation.EAST;
            case EAST:
                return Orientation.SOUTH;
            case SOUTH:
                return Orientation.WEST;
            case WEST:
                return Orientation.NORTH;
        }
        return orientation;
    }

    /**
     * déterminer la nouvelle orientation de la tondeuse suite à une pivotation a gauche
     * @param orientation
     * @return Orientation
     */
    public static Orientation tournerAGauche(Orientation orientation) {
        switch (orientation) {
            case NORTH:
                return Orientation.WEST;
            case EAST:
                return Orientation.NORTH;
            case SOUTH:
                return Orientation.EAST;
            case WEST:
                return Orientation.SOUTH;
        }
        return orientation;
    }

    /**
     * retourner le résultat associé à une tondeuse sous le format attendu
     * @param tondeuse
     * @return String: le résultat attendu sous le format X Y O
     */
    public static String outputDisplay(Tondeuse tondeuse) {
        return new StringBuilder().append(tondeuse.getCoordonneesActuelles().getX()).append(" ").
                append(tondeuse.getCoordonneesActuelles().getY()).append(" ").
                append(tondeuse.getOrientation().getCode()).toString();
    }
}
