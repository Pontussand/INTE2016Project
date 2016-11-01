package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static prompt.command.Command.commandHistory;

public class RepeatLastTest {

    private CommandPrompt commandPrompt;
    private RepeatLast repeatLast;
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
        repeatLast = new RepeatLast(commandPrompt);
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
        commandPrompt.command("hello");
        assertEquals("0 history\n1 history", repeatLast.doCommand(currentDir, input));
    }

    @Test
    public void equals_symetric() {
        RepeatLast rep1 = new RepeatLast(commandPrompt);
        RepeatLast rep2 = new RepeatLast(commandPrompt);

        assertTrue(rep1.equals(rep2) && rep2.equals(rep1));
    }

    @Test
    public void hashCode_symetric() {
        RepeatLast rep1 = new RepeatLast(commandPrompt);
        RepeatLast rep2 = new RepeatLast(commandPrompt);

        assertTrue(rep1.hashCode() == rep2.hashCode());
    }
}
