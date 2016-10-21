package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static prompt.command.Command.commandHistory;

public class HistoryTest {

    private CommandPrompt prompt;
    private History history;
    private FakeFileSystemAdapter fakeAdapter;
    private FakeDirectory root;
    private PathContainer currentDir;
    private String input = null;

    @Before
    public void before() {
        root = new FakeDirectory("root");
        fakeAdapter = new FakeFileSystemAdapter();
        fakeAdapter.setRoot(root);
        Command.setAdapter(fakeAdapter);
        currentDir = new PathContainer("");
        history = new History(prompt = new CommandPrompt(fakeAdapter));
        commandHistory.clear();
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
}

