package fileSystemObject;


import java.util.HashSet;

public class FakeDirectory extends FakeFSO {
	private HashSet<FakeFSO> contents = new HashSet<>();

	public FakeDirectory(String name) {
		super(name);
	}

	public FakeFSO[] getContents() {
		FakeFSO[] emptyArr = new FakeFSO[contents.size()];
		return contents.toArray(emptyArr);
	}

	/**
	 * @param fso fake File System Object
	 * @return true if the FSO was added
	 */
	public boolean addFSO(FakeFSO fso){
		boolean exist = contents.add(fso);
		return exist;
	}

	public FakeFSO pathSearch(String path){
		return null;
	}

	@Override
	public boolean equals(Object other){
		if(other instanceof FakeDirectory){
			FakeDirectory o = (FakeDirectory) other;
			return o.getName().equals(getName());
		}
		return false;
	}
}
