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

	//TODO: may need refactoring
	public FakeFSO pathSearch(String path) {
		String separator = FakeFileSystemAdapter.DIR_SEPERATOR;

		boolean lastRecursion = !path.contains(separator);
		if (lastRecursion) {
			String childName = path;
			FakeFSO child = contents.get(childName);
			return child;
		}

		int pathChildEnd = path.indexOf(separator);
		String childName = path.substring(0, pathChildEnd);
		String passOnPath = path.substring(pathChildEnd + 1);
		FakeFSO child = contents.get(childName);
		if (child != null && child instanceof FakeDirectory) {
			FakeDirectory dChild = (FakeDirectory) child;
			return dChild.pathSearch(passOnPath);
		}
		return null;
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
