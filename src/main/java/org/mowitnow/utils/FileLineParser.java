package org.mowitnow.utils;

import org.mowitnow.exceptions.FileFormatValidationException;
import org.mowitnow.model.Action;
import org.mowitnow.model.Orientation;
import org.mowitnow.model.Coordonnees;
import org.mowitnow.model.Tondeuse;

import java.util.ArrayList;
import java.util.List;

public class FileLineParser {
    public static final String PELOUSE_MAX_POSITION_ERROR_MESSAGE = "First Line is not valid !";
    public static final String TENDEUSE_PARAMS_ERROR_MESSAGE = "Tondeuse Parameters Line is not valid !";
    public static final String ACTIONS_ERROR_MESSAGE = "Actions Line is not valid !";

    public static Coordonnees parsePelouseMaxPositionLine(String firstline) throws FileFormatValidationException {
        if (FileValidator.pelouseMaxPositionLineValidation(firstline)) {
            String[] pos = firstline.split(" ");
            return new Coordonnees(Integer.valueOf(pos[0]), Integer.valueOf(pos[1]));
        }
        throw new FileFormatValidationException(PELOUSE_MAX_POSITION_ERROR_MESSAGE);
    }

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
