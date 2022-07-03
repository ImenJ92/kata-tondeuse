package org.mowitnow;

import org.mowitnow.business.TondeuseManager;
import org.mowitnow.exceptions.FileFormatValidationException;
import org.mowitnow.model.Action;
import org.mowitnow.model.InputParameters;
import org.mowitnow.model.Tondeuse;
import org.mowitnow.utils.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {
    public static List<String> outputResults;

    public static void main(String... args) throws FileNotFoundException, FileFormatValidationException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Filename is missing");
        }
        File inputFile = new File(args[0]);
        System.out.println(inputFile.getAbsolutePath());
        if (!inputFile.isFile()) {
            throw new FileNotFoundException("File not exist");
        } else {
            outputResults = new ArrayList<>();
            InputParameters inputParameters = FileManager.extractParameters(inputFile);
            Map<Integer, List<Action>> actionsByTondeuseMap = inputParameters.getActionsByTondeuseIdMap();
            for (Tondeuse tondeuse : inputParameters.getTondeuseList()) {
                Tondeuse updatedTondeuse = TondeuseManager.applyAction(tondeuse, actionsByTondeuseMap.get(tondeuse.getId()), inputParameters.getPelouse());
                outputResults.add(TondeuseManager.outputDisplay(updatedTondeuse));
            }
            System.out.println(String.join(" ", outputResults));
        }

    }
}
