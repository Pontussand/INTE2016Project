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

public class RepeatFromHistoryTest {

    private CommandPrompt commandPrompt;
    private RepeatFromHistory repeatFromHistory;
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
        repeatFromHistory = new RepeatFromHistory(commandPrompt);
        commandHistory.clear();
    }

    @Test
    public void doCommand_empty() {
        assertEquals(RepeatLast.NO_PREVIOUS_COMMAND, repeatFromHistory.doCommand(currentDir, "3"));
    }

    @Test
    public void doCommand_repeatHistory() {
        commandHistory.add("history");
        assertEquals("0 history\n1 history", repeatFromHistory.doCommand(currentDir, "0"));
    }

    @Test
    public void doCommand_notANumber() {
        commandHistory.add("ls");
        assertEquals("not a number is not a number, try again!", repeatFromHistory.doCommand(currentDir, "not a number"));
    }

    @Test
    public void doCommand_outOfBounds() {
        commandHistory.add("ls");
        assertEquals("You haven't made that many commands, try again!", repeatFromHistory.doCommand(currentDir, "6"));
    }

    @Test
    public void equals_symetric() {
        RepeatFromHistory rep1 = new RepeatFromHistory(commandPrompt);
        RepeatFromHistory rep2 = new RepeatFromHistory(commandPrompt);

        assertTrue(rep1.equals(rep2) && rep2.equals(rep1));
    }

    @Test
    public void hashCode_symetric() {
        RepeatFromHistory rep1 = new RepeatFromHistory(commandPrompt);
        RepeatFromHistory rep2 = new RepeatFromHistory(commandPrompt);

        assertTrue(rep1.hashCode() == rep2.hashCode());
    }


}
