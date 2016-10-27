package prompt.command;

import file_system_adapter.FSAdapter;
import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;

public class TouchTest {

    private Touch touch;
    private FakeFSAdapter fakeAdapter;
    private FakeDirectory root;
    private PathContainer currentDir;

    private String nameLength(int length) {
        String fileName = "";
        for (int a = 0; a < length - 4; a++) {
            fileName += "a";
        }
        return fileName + ".txt";
    }

    @Before
    public void before() {
        root = new FakeDirectory("root");
        fakeAdapter = new FakeFSAdapter();
        fakeAdapter.setRoot(root);
        touch.setAdapter(fakeAdapter);
        currentDir = new PathContainer("");
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
        assertEquals(touch.INVALID_FILE_NAME, touch.doCommand(currentDir,nameLength(Command.maxFSOLength-4) + ".txt"));
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
        assertEquals(touch.INVALID_PATH, touch.doCommand(currentDir,nameLength(Command.maxFSOLength-4) + ".txt"));
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

    @Test
    public void doCommand_createFile_currentDir() {
        assertEquals("", touch.doCommand(currentDir,"fil.txt"));
    }

    @Test
    public void doCommand_createFile_createDirsAbsolutePath() {
        assertEquals("", touch.doCommand(currentDir,"/mapp/mapp2 fil.txt"));
    }

    @Test
    public void doCommand_createFile_createDirMaxLength() {
        assertEquals("", touch.doCommand(currentDir,nameLength(Command.maxFSOLength) + " a.txt"));
    }

    @Test
    public void doCommand_createFile_givenAbsolutePath() {
        FSAdapter adapter = Touch.getAdapter();
        adapter.mkdirs("/mapp/mapp2");
        assertEquals("", touch.doCommand(currentDir,"/mapp/mapp2 fil.txt"));
    }

    @Test
    public void doCommand_createFile_givenPath() {
        FSAdapter adapter = Touch.getAdapter();
        adapter.mkdirs("/mapp");
        assertEquals("", touch.doCommand(currentDir,"mapp fil.txt"));
    }

    @Test
    public void doCommand_createFileMaxLength_givenPath() {
        FSAdapter adapter = Touch.getAdapter();
        adapter.mkdirs("mapp");
        assertEquals("", touch.doCommand(currentDir,"mapp " + nameLength(Command.maxFSOLength-4) +".txt"));
    }
}
