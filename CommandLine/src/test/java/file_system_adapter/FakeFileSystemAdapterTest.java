package file_system_adapter;

import org.junit.*;
import static org.junit.Assert.*;

public class FakeFileSystemAdapterTest {
	FakeFileSystemAdapter sa;

	@Before
	public void before(){
		sa = new FakeFileSystemAdapter();
	}
}
