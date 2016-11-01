package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PwdTest {

    private Pwd pwd;
    private FakeFSAdapter fakeAdapter;
    private FakeDirectory root;
    private PathContainer currentDir;
    private String input;
    private CommandPrompt commandPrompt;

    @Before
    public void before() {
        root = new FakeDirectory("root");
        fakeAdapter = new FakeFSAdapter();
        fakeAdapter.setRoot(root);
        pwd.setAdapter(fakeAdapter);
        currentDir = new PathContainer("/Folder1/Folder2/Folder3");
        input = null;
        commandPrompt = new CommandPrompt(fakeAdapter);
        pwd = new Pwd(commandPrompt);
    }

    @Test
    public void doCommand_returnPath() {
        assertEquals("/Folder1/Folder2/Folder3", pwd.doCommand(currentDir,""));
    }

    @Test
    public void equals_symetric() {
        Pwd pwd1 = new Pwd(commandPrompt);
        Pwd pwd2 = new Pwd(commandPrompt);

        assertTrue(pwd1.equals(pwd2) && pwd2.equals(pwd1));
    }

    @Test
    public void hashCode_symetric() {
        Pwd pwd1 = new Pwd(commandPrompt);
        Pwd pwd2 = new Pwd(commandPrompt);

        assertTrue(pwd1.hashCode() == pwd2.hashCode());
    }
}
