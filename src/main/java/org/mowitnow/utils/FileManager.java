package org.mowitnow.utils;

import org.mowitnow.exceptions.FileFormatValidationException;
import org.mowitnow.model.Action;
import org.mowitnow.model.InputParameters;
import org.mowitnow.model.Pelouse;
import org.mowitnow.model.Tondeuse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class FileManager {
    /**
     * extractParameters est la méthode qui permet la lecture du fichier d'entrée et l'appel aux méthodes extractant les
     * paramètres nécessaires
     * @param file
     * @return InputParameters: les paramètres extraits à partir du fichier d'entrée
     * @throws FileFormatValidationException
     * @throws FileNotFoundException
     */
    public static InputParameters extractParameters(File file) throws FileFormatValidationException, FileNotFoundException {
        int id = 0;
        InputParameters inputParameters = new InputParameters();
        List<Tondeuse> tondeuseList = new ArrayList<>();
        Map<Integer, List<Action>> actionsByTondeuseIdMap = new HashMap<>();
        Scanner scanner = new Scanner(file);
        if (scanner.hasNext()) {
            Pelouse pelouse = new Pelouse();
            pelouse.setPositionMaximale(FileLineParser.parsePelouseMaxPositionLine(scanner.nextLine()));
            inputParameters.setPelouse(pelouse);
        }
        while (scanner.hasNext()) {
            Tondeuse tondeuse = FileLineParser.parseTondeuseParametersLine(scanner.nextLine());
            tondeuse.setId(id++);
            tondeuseList.add(tondeuse);
            if (scanner.hasNext()) {
                List<Action> actionList = FileLineParser.parseActionsLine(scanner.nextLine());
                actionsByTondeuseIdMap.put(tondeuse.getId(), actionList);
            }
        }
        inputParameters.setTondeuseList(tondeuseList);
        inputParameters.setActionsByTondeuseIdMap(actionsByTondeuseIdMap);

        return inputParameters;
    }
}
