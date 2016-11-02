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

public class PwdTest {

    private Pwd pwd, pwd2;
    private Mkdir mkdir;
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
        pwd2 = new Pwd(commandPrompt);
        mkdir = new Mkdir(commandPrompt);

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
    public void equals_null() {
        assertFalse(pwd.equals(null));
    }

    @Test
    public void equals_itself() {
        assertTrue(pwd.equals(pwd));
    }

    @Test
    public void equals_symetric() {
        assertTrue(pwd.equals(pwd2) && pwd2.equals(pwd));
    }

    @Test
    public void hashCode_symetric() {
        assertTrue(pwd.hashCode() == pwd2.hashCode());
    }

    @Test
    public void equals_unsymetric() {
        assertFalse(pwd.equals(mkdir) && mkdir.equals(pwd));
    }

    @Test
    public void hashCode_unsymetric() {
        assertFalse(pwd.hashCode() == mkdir.hashCode());
    }
}
