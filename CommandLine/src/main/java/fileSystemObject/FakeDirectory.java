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
		String separator = FakeFileSystemAdapter.DIR_SEPERATOR;
		System.out.println(path);

		int pathChildEnd = path.indexOf(separator);
		String childName = path.substring(0, pathChildEnd);
		String passOnPath = path.substring(pathChildEnd + 1);
		boolean lastRecursion = !passOnPath.contains(separator);

		FakeFSO child = contents.get(childName);
		if (lastRecursion) {
			return child;
		}
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
