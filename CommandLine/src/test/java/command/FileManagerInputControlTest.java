package command;

/**
 * Created by pontus on 2016-10-13.
 */

import org.junit.*;
import static org.junit.Assert.*;

public class FileManagerInputControlTest {

    @Test
    public void correctFileName_noSpaces(){
        Filemanager fm = new Filemanager();
        assertEquals(false, fm.correctFileName("abc def"));
    }


}
