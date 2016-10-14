package command.test.file_system_adapter;

import org.junit.*;

public class FakeFileSystemAdapterTest {
	FakeFileSystemAdapter sa;

	@Before
	public void before(){
		sa = new FakeFileSystemAdapter();
	}
}
