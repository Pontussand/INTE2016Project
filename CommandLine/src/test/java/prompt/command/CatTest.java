package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CatTest {
    private Cat cat;
    private CommandPrompt commandPrompt;
    private FakeFSAdapter fakeAdapter;
    private FakeDirectory root;
    private PathContainer currentDir;
    private String expectedText = "abc";

    @Before
    public void before() {

        root = new FakeDirectory("");
        fakeAdapter = new FakeFSAdapter();
        fakeAdapter.setRoot(root);
        currentDir = new PathContainer("");
        commandPrompt = new CommandPrompt(fakeAdapter);
        cat = new Cat(commandPrompt);
        FakeFile file = new FakeFile("testFakeTextFile.txt", "abc");
        root.addFSO(file);
    }

    @Test
    public void doCommand_CatReadExisting() {
        assertEquals(expectedText, cat.doCommand(currentDir, "testFakeTextFile.txt"));
    }

    @Test
    public void doCommand_CatReadNonexistent(){
        assertEquals(null, cat.doCommand(currentDir, "nonexistent.txt"));
    }

    @Test
    public void equals_Symmetric() {
        Cat cat1 = new Cat(commandPrompt);
        Cat cat2 = new Cat(commandPrompt);

        assertTrue(cat1.equals(cat2) && cat2.equals(cat1));
    }

    @Test
    public void hashCode_Symetric() {
        Cat cat1 = new Cat(commandPrompt);
        Cat cat2 = new Cat(commandPrompt);

        assertTrue(cat1.hashCode() == cat2.hashCode());
    }
}