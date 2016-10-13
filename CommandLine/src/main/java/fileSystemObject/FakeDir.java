package fileSystemObject;

import java.util.HashMap;

public class FakeDir extends FakeFSO{
	private HashMap<String, FakeFSO> contents = new HashMap<>();

	public FakeDir(String name){
		super(name);
	}

	public boolean addFSO(FakeFSO content){
		boolean exists = contents.containsKey(content.getName());
		if(!exists)
			contents.put(content.getName(),content);
		return exists;
	}

	public String[] listContents(){
		String[] ret = new String[contents.size()];
		int retInd = 0;
		for(FakeFSO c : contents.values()){
			ret[retInd++] = c.getName();
		}
		return ret;
	}

	public FakeFSO getByPath(String path){
		String localFSO = path.split(FakePath.DIR_SEPARATOR, 2)[0];
		return contents.get(localFSO);
	}
}
