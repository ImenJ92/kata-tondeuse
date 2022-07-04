package org.mowitnow.utils;

import org.apache.commons.lang3.StringUtils;
import org.mowitnow.model.Action;

public class FileValidator {

    /**
     * valider si la ligne présentant la position maximale est bien valide
     * @param firstline
     * @return boolean
     */
    public static boolean pelouseMaxPositionLineValidation(String firstline) {
        return StringUtils.isNotBlank(firstline) && firstline.matches("^[0-9] [0-9]$");
    }

    /**
     * valider si la ligne présentant les paramètres de la Tondeuse sont bien valides
     * @param tondeuseLine
     * @return boolean
     */
    public static boolean tondeuseParametersLineValidation(String tondeuseLine) {
        return StringUtils.isNotBlank(tondeuseLine) && tondeuseLine.matches("^[0-9] [0-9] (N|S|W|E)$");
    }

    /**
     * valider si la ligne présentant la liste des actions est bien valide
     * @param actionsLine
     * @return boolean
     */
    public static boolean actionsLineValidation(String actionsLine) {
        return StringUtils.isNotBlank(actionsLine)
                && StringUtils.containsOnly(actionsLine,
                Action.AVANCER.getCode()
                , Action.DROITE.getCode()
                , Action.GAUCHE.getCode());
    }

}
