package file_system_adapter;


import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class FakeFSA_lsDirTest {
    FakeFSAdapter fakeAdapter;
    FakeDirectory root;

    @Before
    public void before() {
        fakeAdapter = new FakeFSAdapter();
        root = new FakeDirectory("root");
        fakeAdapter.setRoot(root);
    }

    @Test
    public void ls_getListOfDirsFromRoot() {
        root.addFSO(new FakeDirectory("FD1"));
        root.addFSO(new FakeDirectory("FD2"));
        root.addFSO(new FakeFile("FF1.txt", "test"));
        root.addFSO(new FakeFile("FF2.txt", "test"));

        String[] expected = {"FD1", "FD2"};
        String[] actual = fakeAdapter.lsDir("");

        assertArrayEquals(expected, actual);
    }
}
