package file_system_adapter.fake_FSO;


import file_system_adapter.FakeFSAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FakeDirectory extends FakeFSO {
	private HashMap<String, FakeFSO> content = new HashMap();

	public FakeDirectory(String name) {
		super(name);
	}

	/** used to make a copy of another FakeDirectory*/
	public FakeDirectory(FakeDirectory other){
		super(other.getName());

		for(FakeFSO fso : other.getContent()){

			if(fso instanceof FakeDirectory){
				FakeDirectory fakeDir = (FakeDirectory) fso;
				addFSO(new FakeDirectory(fakeDir));

			} else if (fso instanceof FakeFile){
				FakeFile fakeFile = (FakeFile) fso;
				addFSO(new FakeFile(fakeFile));
			}
		}
	}

	/**
	 * @return File System Objects sorted by name
	 */
	public FakeFSO[] getContent() {
		List<FakeFSO> children = new ArrayList<>(content.values());
		Collections.sort(children);
		FakeFSO[] ret = new FakeFSO[children.size()];
		return children.toArray(ret);
	}

	public FakeFSO[] getDirs(){
		List<FakeFSO> children = new ArrayList<>(content.values());
		Collections.sort(children);
		List<FakeFSO> dirs = new ArrayList<>();
		for(int i = 0; i < children.size(); i++){
			if(children.get(i) instanceof FakeDirectory){
				dirs.add(children.get(i));
			}
		}
		FakeFSO[] dirArray = new FakeFSO[dirs.size()];
		return dirs.toArray(dirArray);
	}

	public FakeFSO[] getFiles(){
		List<FakeFSO> children = new ArrayList<>(content.values());
		Collections.sort(children);
		List<FakeFSO> files = new ArrayList<>();
		for(int i = 0; i < children.size(); i++){
			if(children.get(i) instanceof FakeFile){
				files.add(children.get(i));
			}
		}
		FakeFSO[] dirArray = new FakeFSO[files.size()];
		return files.toArray(dirArray);
	}

	/**
	 * @param fso fake File System Object
	 * @return true if the FSO was added
	 */
	public boolean addFSO(FakeFSO fso) {
		boolean exist = content.containsKey(fso.getName());
		if (!exist)
			content.put(fso.getName(), fso);
		return !exist;
	}

	public FakeFSO pathSearch(String path) {
		if (path.equals(""))
			return this;

		String childName = getChildName(path);
		FakeFSO child = content.get(childName);
		String passOnPath = getPassOnPath(path);
		boolean endOfPath = passOnPath.equals("");

		if (child != null && child instanceof FakeDirectory) {
			FakeDirectory dChild = (FakeDirectory) child;
			return dChild.pathSearch(passOnPath);
		}
		else if (child != null && (child instanceof FakeFile) && endOfPath) {
			return child;
		}
		return null;
	}

	private String getChildName(String path) {
		String separator = FakeFSAdapter.DIR_SEPERATOR;
		int start = path.indexOf(separator) + 1;
		int end = path.indexOf(separator, start);
		if (end == -1) {
			end = path.length();
		}
		return path.substring(start, end);
	}

	private String getPassOnPath(String path) {
		String separator = FakeFSAdapter.DIR_SEPERATOR;
		int sep1 = path.indexOf(separator);
		int sep2 = path.indexOf(separator, sep1 + 1);
		if (sep2 == -1) {
			sep2 = path.length();
		}
		return path.substring(sep2);
	}

	public void removeFSOFromContent(String name){
		content.remove(name);
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
