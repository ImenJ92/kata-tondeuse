package org.mowitnow.utils;

import org.mowitnow.exceptions.FileFormatValidationException;
import org.mowitnow.model.Action;
import org.mowitnow.model.Orientation;
import org.mowitnow.model.Coordonnees;
import org.mowitnow.model.Tondeuse;

import java.util.ArrayList;
import java.util.List;

/**
 * FileLineParser - class which offer methods to parse input file lines and return parameters needed to
 * move the Tondeuse
 */
public class FileLineParser {
    public static final String PELOUSE_MAX_POSITION_ERROR_MESSAGE = "First Line is not valid !";
    public static final String TENDEUSE_PARAMS_ERROR_MESSAGE = "Tondeuse Parameters Line is not valid !";
    public static final String ACTIONS_ERROR_MESSAGE = "Actions Line is not valid !";

    /**
     * permet d'extraire les coordonnées de la position maximale de la pelouse à partir de la première ligne
     * du fichier d'entrée
     * @param firstline
     * @return Coordonnees: cordonnées de la position maximale de la pelouse
     * @throws FileFormatValidationException
     */
    public static Coordonnees parsePelouseMaxPositionLine(String firstline) throws FileFormatValidationException {
        if (FileValidator.pelouseMaxPositionLineValidation(firstline)) {
            String[] pos = firstline.split(" ");
            return new Coordonnees(Integer.valueOf(pos[0]), Integer.valueOf(pos[1]));
        }
        throw new FileFormatValidationException(PELOUSE_MAX_POSITION_ERROR_MESSAGE);
    }

    /**
     * permet d'extraire les attributs de la Tondeuse : les coordonnées de sa position initiale et sa direction initiale
     * à partir de la deuxième ligne
     * @param tondeuseLine
     * @return Tondeuse: la tondeuse initialisée avec les paramètres d'entrées
     * @throws FileFormatValidationException
     */
    public static Tondeuse parseTondeuseParametersLine(String tondeuseLine) throws FileFormatValidationException {
        if (FileValidator.tondeuseParametersLineValidation(tondeuseLine)) {
            String[] split = tondeuseLine.split(" ");
            Tondeuse tondeuse = new Tondeuse();
            tondeuse.setCoordonneesActuelles(new Coordonnees(Integer.valueOf(split[0]), Integer.valueOf(split[1])));
            tondeuse.setOrientation(Orientation.getOrientationFromCode(split[2].charAt(0)));
            return tondeuse;
        }
        throw new FileFormatValidationException(TENDEUSE_PARAMS_ERROR_MESSAGE);
    }

    /**
     *permet d'extraire la liste des actions à appliquer par la Tondeuse pour la faire bouger à partir de la ligne
     * qui suit la ligne de la Tondeuse
     * @param actionsLine
     * @return List<Action>: la liste des actions extraites
     * @throws FileFormatValidationException
     */
    public static List<Action> parseActionsLine(String actionsLine) throws FileFormatValidationException {
        if (FileValidator.actionsLineValidation(actionsLine)) {
            List<Action> actions = new ArrayList<Action>();
            char[] actionsArray = actionsLine.toCharArray();
            for (char c : actionsArray) {
                actions.add(Action.getActionFromCode(c));
            }
            return actions;
        }
        throw new FileFormatValidationException("Actions Line is not valid !");
    }


}
