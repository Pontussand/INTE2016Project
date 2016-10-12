package fileSystemObject;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class fakeFileSystemObjectTest {
	FakeFSO root;
	String testDir1 = "testDir1";

	@Before
	public void setup(){
		root = new FakeFSO("/");
	}

	@Test
	public void addDirectory(){
		FakeFSO newDir = new FakeFSO("/"+testDir1);

		FakeFSO[] expectedListing = {new FakeFSO("testDir1")};
		FakeFSO[] listing = root.listFiles();
		assertEquals(expectedListing.length, listing.length);

		for(int i = 0; i < expectedListing.length; i++){
			assertEquals(expectedListing[i], listing[i]);
		}
	}
}
