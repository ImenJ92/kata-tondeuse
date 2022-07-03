package org.mowitnow.utils;

import org.apache.commons.lang3.StringUtils;
import org.mowitnow.model.Action;

public class FileValidator {

    public static boolean pelouseMaxPositionLineValidation(String firstline) {
        return StringUtils.isNotBlank(firstline) && firstline.matches("^[0-9] [0-9]$");
    }

    public static boolean tondeuseParametersLineValidation(String tondeuseLine) {
        return StringUtils.isNotBlank(tondeuseLine) && tondeuseLine.matches("^[0-9] [0-9] (N|S|W|E)$");
    }

    public static boolean actionsLineValidation(String actionsLine) {
        return StringUtils.isNotBlank(actionsLine)
                && StringUtils.containsOnly(actionsLine,
                Action.AVANCER.getCode()
                , Action.DROITE.getCode()
                , Action.GAUCHE.getCode());
    }

}
