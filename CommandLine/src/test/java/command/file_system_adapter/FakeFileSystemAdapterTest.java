package command.file_system_adapter;

import command.file_system_adapter.FakeFileSystemAdapter;
import org.junit.*;

public class FakeFileSystemAdapterTest {
	FakeFileSystemAdapter sa;

	@Before
	public void before(){
		sa = new FakeFileSystemAdapter();
	}
}
