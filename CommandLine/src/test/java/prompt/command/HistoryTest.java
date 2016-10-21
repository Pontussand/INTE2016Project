package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;

public class HistoryTest {

    private CommandPrompt cp;
    private History history;
    private FakeFileSystemAdapter fakeAdapter;
    private FakeDirectory root;
    private PathContainer currentDir;

    @Before
    public void before() {
        root = new FakeDirectory("root");
        fakeAdapter = new FakeFileSystemAdapter();
        fakeAdapter.setRoot(root);
        Command.setAdapter(fakeAdapter);
        currentDir = new PathContainer("\\folder");
        history = new History(cp = new CommandPrompt(fakeAdapter));
    }

    @Test
    public void empty() {
        assertEquals("0 history", history.doCommand(currentDir, "history"));
    }

    @Test
    public void ls() {
        cp.command("ls");
        assertEquals("0 ls\n1 history", history.doCommand(currentDir,"history"));
    }

    @Test
    public void ls2() {
        cp.command("ls");
        cp.command("ls");
        assertEquals("0 ls\n1 ls\n2 history", history.doCommand(currentDir, "history"));
    }

    @Test
    public void History_historyLimit() {
        cp.command("ls");
        cp.command("mkdir folder");
        cp.command("mkdirs folder\\folder2");
        cp.command("pwd");
        cp.command("ls");
        cp.command("mkdir folder");
        cp.command("mkdirs folder\\folder2");
        cp.command("pwd");
        cp.command("touch file");
        assertEquals("0 ls\n" +
                "1 mkdir folder\n" +
                "2 mkdirs folder\\folder2\n" +
                "3 pwd\n" +
                "4 ls\n" +
                "5 mkdir folder\n" +
                "6 mkdirs folder\\folder2\n" +
                "7 pwd\n" +
                "8 touch file\n" +
                "9 history"
                , history.doCommand(currentDir, "history")); // 10 commands executed
        assertEquals("0 mkdir folder\n" +
                "1 mkdirs folder\\folder2\n" +
                "2 pwd\n" +
                "3 ls\n" +
                "4 mkdir folder\n" +
                "5 mkdirs folder\\folder2\n" +
                "6 pwd\n" +
                "7 touch file\n" +
                "8 history\n" +
                "9 history"
                , history.doCommand(currentDir, "history")); // 11 commands executed
    }
}

