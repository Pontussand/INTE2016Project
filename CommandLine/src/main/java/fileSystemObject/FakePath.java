package fileSystemObject;

/**suppose to be equivalent to java.io.File class*/
public class FakePath {
	public static final String DIR_SEPARATOR = "/";
	private static FakeDir root = new FakeDir("root");

	private String path;

	public FakePath(String path){
		this.path = path;
	}

	public boolean createNewFile(){
		boolean successful = true;
		return successful;
	}

	//TODO: finish implementing
	public boolean mkdir() {
		root.addFSO(new FakeDir(path));
		return true;
	}

	public FakePath[] listContents(){
		FakeDir thisDir = (FakeDir)root.getByPath(path);
		String[] subfolders = thisDir.listContents();
		FakePath[] subDirPaths = new FakePath[subfolders.length];
		return null;
	}

	public boolean equals(Object other) {
		if (other instanceof FakePath) {
			FakePath o = (FakePath) other;
			o.path.equals(path);
		}
		return false;
	}
}
