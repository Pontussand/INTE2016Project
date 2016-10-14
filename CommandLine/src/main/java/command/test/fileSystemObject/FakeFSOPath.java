package command.test.fileSystemObject;


public class FakeFSOPath {
	String path;

	public FakeFSOPath(String path) {
		this.path = path;
	}


	public FakeFSOPath[] listFiles() {
		return null;
	}

	public boolean equals(Object other) {
		if(other instanceof FakeFSOPath) {
			FakeFSOPath o = (FakeFSOPath) other;
			o.path.equals(path);
		}
		return false;
	}
}
