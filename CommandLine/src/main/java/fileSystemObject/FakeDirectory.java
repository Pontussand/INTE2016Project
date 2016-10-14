package fileSystemObject;


import file_system_adapter.FakeFileSystemAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FakeDirectory extends FakeFSO {
	private HashMap<String, FakeFSO> contents = new HashMap();

	public FakeDirectory(String name) {
		super(name);
	}

	/**
	 * @return File System Objects sorted by name
	 */
	public FakeFSO[] getContents() {
		List<FakeFSO> children = new ArrayList<>(contents.values());
		Collections.sort(children);
		FakeFSO[] ret = new FakeFSO[children.size()];
		return children.toArray(ret);
	}

	/**
	 * @param fso fake File System Object
	 * @return true if the FSO was added
	 */
	public boolean addFSO(FakeFSO fso) {
		boolean exist = contents.containsKey(fso.getName());
		if (!exist)
			contents.put(fso.getName(), fso);
		return !exist;
	}

	public FakeFSO pathSearch(String path) {
		if(path.equals(""))
			return this;

		String childName = getChildName(path);
		FakeFSO child = contents.get(childName);

		if(child != null && child instanceof FakeDirectory){
			String passOnPath = getPassOnPath(path);
			FakeDirectory dChild = (FakeDirectory) child;
			return dChild.pathSearch(passOnPath);
		}
		return null;
	}

	private String getChildName(String path){
		String separator = FakeFileSystemAdapter.DIR_SEPERATOR;
		int start = path.indexOf(separator) + 1;
		int end = path.indexOf(separator, start);
		if(end == -1){
			end = path.length();
		}
		return path.substring(start, end);
	}

	private String getPassOnPath(String path){
		String separator = FakeFileSystemAdapter.DIR_SEPERATOR;
		int sep1 = path.indexOf(separator);
		int sep2 = path.indexOf(separator, sep1 + 1);
		if(sep2 == -1){
			sep2 = path.length();
		}
		return path.substring(sep2);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof FakeDirectory) {
			FakeDirectory o = (FakeDirectory) other;
			return o.getName().equals(getName());
		}
		return false;
	}
}
