package command;

/**
 * Created by pontus on 2016-10-13.
 */

import org.junit.*;
import static org.junit.Assert.*;

public class FileManager_InputControlTest {

   FileManager fm;

    @Before
   public void filemanager(){
       fm = new FileManager();
   }

    @Test
    public void correctFileName_Spaces(){
        assertEquals(false, fm.correctFileName("abc def"));
    }

    @Test
    public void correctFileName_noSpaces_rightFileType(){
        FileManager fm = new FileManager();
        assertEquals(true, fm.correctFileName("abcdef.txt"));
    }

    @Test
    public void correctFileName_wrongFileType(){
        FileManager fm = new FileManager();
        assertEquals(false, fm.correctFileName("abc.jpg"));
    }

    @Test
    public void correctFileName_rightFileType(){
        FileManager fm = new FileManager();
        assertEquals(true, fm.correctFileName("abc.txt"));
    }
}
