package org.mowitnow;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mowitnow.exceptions.FileFormatValidationException;
import org.mowitnow.utils.FileLineParser;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    final String PATH = "./src/test/resources/";

    @Test(expected = IllegalArgumentException.class)
    public void filename_is_missing_test() throws FileNotFoundException, FileFormatValidationException {
        Application.main(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void file_Not_Found_test() throws FileNotFoundException, FileFormatValidationException {
        Application.main("not_existing_file");
    }

    @Test
    public void first_line_erreur_test() throws FileNotFoundException, FileFormatValidationException {
        expectedEx.expect(FileFormatValidationException.class);
        expectedEx.expectMessage(FileLineParser.PELOUSE_MAX_POSITION_ERROR_MESSAGE);
        Application.main(PATH + "first_line_error_file.txt");
    }

    @Test
    public void tondeuse_line_erreur_test() throws FileNotFoundException, FileFormatValidationException {
        expectedEx.expect(FileFormatValidationException.class);
        expectedEx.expectMessage(FileLineParser.TENDEUSE_PARAMS_ERROR_MESSAGE);
        Application.main(PATH + "tondeuse_line_error_file.txt");
    }

    @Test
    public void actions_line_erreur_test() throws FileNotFoundException, FileFormatValidationException {
        expectedEx.expect(FileFormatValidationException.class);
        expectedEx.expectMessage(FileLineParser.ACTIONS_ERROR_MESSAGE);
        Application.main(PATH + "actions_line_error_file.txt");
    }

    @Test
    public void successTest() throws FileNotFoundException, FileFormatValidationException {
        Application.main(PATH + "sucess_file.txt");
        assertNotNull(Application.outputResults);
        assertEquals(Application.outputResults.size(), 2);
        System.out.println(Application.outputResults);
        assertEquals("1 3 N", Application.outputResults.get(0));
        assertEquals("5 1 E", Application.outputResults.get(1));
    }
}
