package fileSystemObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class fakeFileSystemObjectTest {
	FakeFSOPath root;
	String testDir1 = "testDir1";

	@Before
	public void setup(){
		root = new FakeFSOPath("/");
	}

	@Test
	public void addDirectory(){
		FakeFSOPath newDir = new FakeFSOPath("/"+testDir1);

		FakeFSOPath[] expectedListing = {new FakeFSOPath("testDir1")};
		FakeFSOPath[] listing = root.listFiles();
		assertEquals(expectedListing.length, listing.length);

		for(int i = 0; i < expectedListing.length; i++){
			assertEquals(expectedListing[i], listing[i]);
		}
	}
}
