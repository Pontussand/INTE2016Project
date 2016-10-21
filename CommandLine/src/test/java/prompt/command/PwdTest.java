package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;

public class PwdTest {

    private Pwd pwd;
    private FakeFileSystemAdapter fakeAdapter;
    private FakeDirectory root;
    private PathContainer currentDir;
    private String input;

    @Before
    public void before() {
        root = new FakeDirectory("root");
        fakeAdapter = new FakeFileSystemAdapter();
        fakeAdapter.setRoot(root);
        pwd.setAdapter(fakeAdapter);
        currentDir = new PathContainer("/Folder1/Folder2/Folder3");
        input = null;
        pwd = new Pwd(new CommandPrompt(fakeAdapter));
    }

    @Test
    public void doCommand_contact() {
        assertEquals("/Folder1/Folder2/Folder3", pwd.doCommand(currentDir,""));
    }
}
