package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static prompt.command.Command.commandHistory;

public class HistoryTest {

    private CommandPrompt commandPrompt;
    private History history, history2;
    private Mkdir mkdir;
    private FakeFSAdapter fakeAdapter;
    private FakeDirectory root;
    private PathContainer currentDir;
    private String input = null;

    @Before
    public void before() {
        root = new FakeDirectory("root");
        fakeAdapter = new FakeFSAdapter();
        fakeAdapter.setRoot(root);
        Command.setAdapter(fakeAdapter);
        currentDir = new PathContainer("");
        commandPrompt = new CommandPrompt(fakeAdapter);
        history = new History(commandPrompt);
        commandHistory.clear();
        history2 = new History(commandPrompt);
        mkdir = new Mkdir(commandPrompt);
    }

    @Test
    public void doCommand_empty() {
        assertEquals("", history.doCommand(currentDir, input));
    }

    @Test
    public void doCommand_oneCommands() {
        history.addToHistory("ls");
        history.addToHistory("history");
        assertEquals("0 ls\n1 history", history.doCommand(currentDir,input));
    }

    @Test
    public void doCommand_twoCommands() {
        history.addToHistory("ls");
        history.addToHistory("mkdir folder");
        history.addToHistory("history");
        assertEquals("0 ls\n1 mkdir folder\n2 history", history.doCommand(currentDir, input));
    }

    @Test
    public void History_historyLimit() {
        history.addToHistory("ls");
        history.addToHistory("mkdir folder");
        history.addToHistory("mkdirs folder\\folder2");
        history.addToHistory("pwd");
        history.addToHistory("ls");
        history.addToHistory("mkdir folder");
        history.addToHistory("mkdirs folder\\folder2");
        history.addToHistory("pwd");
        history.addToHistory("touch file");
        history.addToHistory("touch file2");
        assertEquals("0 ls\n" +
                "1 mkdir folder\n" +
                "2 mkdirs folder\\folder2\n" +
                "3 pwd\n" +
                "4 ls\n" +
                "5 mkdir folder\n" +
                "6 mkdirs folder\\folder2\n" +
                "7 pwd\n" +
                "8 touch file\n" +
                "9 touch file2"
                , history.doCommand(currentDir, input)); // 10 commands executed
        history.addToHistory("touch file3");
        assertEquals("0 mkdir folder\n" +
                "1 mkdirs folder\\folder2\n" +
                "2 pwd\n" +
                "3 ls\n" +
                "4 mkdir folder\n" +
                "5 mkdirs folder\\folder2\n" +
                "6 pwd\n" +
                "7 touch file\n" +
                "8 touch file2\n" +
                "9 touch file3"
                , history.doCommand(currentDir, input)); // 11 commands executed
    }

    @Test
    public void equals_itself() {
        assertTrue(history.equals(history));
    }

    @Test
    public void equals_null() {
        assertFalse(history.equals(null));
    }

    @Test
    public void equals_symetric() {
        assertTrue(history.equals(history2) && history2.equals(history));
    }

    @Test
    public void hashCode_symetric() {
        assertTrue(history.hashCode() == history2.hashCode());
    }

    @Test
    public void equals_unsymetric() {
        assertFalse(history.equals(mkdir) && mkdir.equals(history));
    }

    @Test
    public void hashCode_unsymetric() {
        assertFalse(history.hashCode() == mkdir.hashCode());
    }
}

