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

	public boolean mkdir() {
		root.addFSO(new FakeDir(path));
		return true;
	}

	public FakePath[] listFiles(){
		return null;
	}

	public boolean equals(Object other){
		if(other instanceof FakePath){
			FakePath o = (FakePath) other;
			o.path.equals(path);
		}
		return false;
	}
}
