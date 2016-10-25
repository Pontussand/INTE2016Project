package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;

public class TouchTest {

    private Touch touch;
    private FakeFileSystemAdapter fakeAdapter;
    private FakeDirectory root;
    private PathContainer currentDir;
    private String input;

    private String nameLength(int length) {
        String fileName = "";
        for (int a = 0; a < length; a++) {
            fileName += "a";
        }
        return fileName;
    }

    @Before
    public void before() {
        root = new FakeDirectory("root");
        fakeAdapter = new FakeFileSystemAdapter();
        fakeAdapter.setRoot(root);
        touch.setAdapter(fakeAdapter);
        currentDir = new PathContainer("/Folder1/Folder2/Folder3");
        input = null;
        touch = new Touch(new CommandPrompt(fakeAdapter));
    }

    @Test
    public void doCommand_fileAlreadyExists() {
        assertEquals(touch.FILE_EXISTS, touch.doCommand(currentDir,"fil.txt"));
    }

    @Test
    public void doCommand_wrongFileType() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"fil"));
    }

    @Test
    public void doCommand_fileName_toLong() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,nameLength(254)+".txt"));
    }

    @Test
    public void doCommand_fileName_toShort() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,".txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharSmaller() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"<fil.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharSpace() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"f il.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharBigger() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,">fil.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharColon() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,":fil.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharQuote() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"\"fil.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharSlash() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"/fil.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharBar() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"|fil.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharQuestion() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"?fil.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharAsterisk() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"*fil.txt"));
    }

    @Test
    public void doCommand_fileName_invalidCharBackslash() {
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,"\\fil.txt"));
    }

    @Test
    public void doCommand_path_toLong() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,nameLength(256) + " fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharSmaller() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma<pp fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharSpace() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma pp fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharBigger() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma>pp fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharColon() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma:pp fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharQuote() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma\"pp fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharBar() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma|pp fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharQuestion() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma?pp fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharAsterisk() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma*pp fil.txt"));
    }

    @Test
    public void doCommand_path_invalidCharBackslash() {
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,"ma\\\\pp fil.txt"));
    }

}
