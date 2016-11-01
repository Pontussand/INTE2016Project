package file_system_adapter.fake_FSO;

import file_system_adapter.FakeFSAdapter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FakeFSA_deleteFSO {
	FakeFSAdapter fakeAdapter;
	FakeDirectory root;

	@Before
	public void before() {
		fakeAdapter = new FakeFSAdapter();
		root = new FakeDirectory("root");
		fakeAdapter.setRoot(root);
	}

	@Test
	public void deleteFSO_noSuchFSO(){
		assertFalse(fakeAdapter.deleteFSO("/path/to/FSO"));
	}

	@Test
	public void deleteFSO_rootFSO(){
		root.addFSO(new FakeDirectory("content"));
		assertTrue(fakeAdapter.deleteFSO("/content"));
		assertEquals(0, root.getContent().length);
	}

	@Test
	public void deleteFSO_subFSO(){
		FakeDirectory sub = new FakeDirectory("sub");
		sub.addFSO(new FakeFile("content.txt", ""));
		root.addFSO(sub);

		assertTrue(fakeAdapter.deleteFSO("/sub/content.txt"));
		assertEquals(1, root.getContent().length);
		assertEquals(0, sub.getContent().length);
	}
}
