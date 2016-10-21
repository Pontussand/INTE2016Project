package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static prompt.command.Command.commandHistory;

public class RepeatLastTest {

    private CommandPrompt prompt;
    private RepeatLast repeatLast;
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
        repeatLast = new RepeatLast(prompt = new CommandPrompt(fakeAdapter));
        commandHistory.clear();
    }

    @Test
    public void doCommand_empty() {
        assertEquals(RepeatLast.NO_PREVIOUS_COMMAND, repeatLast.doCommand(currentDir, input));
    }

    @Test
    public void doCommand_repeatHistory() {
        commandHistory.add("history");
        assertEquals("0 history\n1 history", repeatLast.doCommand(currentDir, input));
    }

    @Test
    public void doCommand_repeatInvalidCommandNotExecuted() {
        commandHistory.add("history");
        prompt.command("hello");
        assertEquals("0 history\n1 history", repeatLast.doCommand(currentDir, input));
    }
}
