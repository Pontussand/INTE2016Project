package fileSystemObject;

import java.util.ArrayList;
import java.util.HashSet;

public class FakeDir extends FakeFSO{
	private HashSet<FakeFSO> contents = new HashSet<>();

	public FakeDir(String name){
		super(name);
	}

	public boolean addFSO(FakeFSO content){
		return contents.add(content);
	}

	public String[] listContents(){
		String[] ret = new String[contents.size()];
		int retInd = 0;
		for(FakeFSO c : contents){
			ret[retInd++] = c.getName();
		}
		return ret;
	}
}
