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

public class RepeatLastTest {

    private CommandPrompt commandPrompt;
    private RepeatLast repeatLast, repeatLast2;
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
        repeatLast = new RepeatLast(commandPrompt);
        commandHistory.clear();
        repeatLast2 = new RepeatLast(commandPrompt);
        mkdir = new Mkdir(commandPrompt);
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
    public void equals_itself() {
        assertTrue(repeatLast.equals(repeatLast));
    }

    @Test
    public void equals_null() {
        assertFalse(repeatLast.equals(null));
    }

    @Test
    public void equals_symetric() {
        assertTrue(repeatLast.equals(repeatLast2) && repeatLast2.equals(repeatLast));
    }

    @Test
    public void hashCode_symetric() {
        assertTrue(repeatLast.hashCode() == repeatLast2.hashCode());
    }


    @Test
    public void equals_unsymetric() {
        assertFalse(repeatLast.equals(mkdir) && mkdir.equals(repeatLast));
    }

    @Test
    public void hashCode_unsymetric() {
        assertFalse(repeatLast.hashCode() == mkdir.hashCode());
    }
}
