package fileSystemObject;

/**
 * Created by felix on 2016-10-12.
 */
public class FakeFSO {
	String path;

	public FakeFSO(String path){
		this.path = path;
	}


	public FakeFSO[] listFiles(){
		return null;
	}

	public boolean equals(Object other){
		if(other instanceof FakeFSO){
			FakeFSO o = (FakeFSO) other;
			o.path.equals(path);
		}
		return false;
	}
}
