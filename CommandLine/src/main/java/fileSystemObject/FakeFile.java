package fileSystemObject;

/**
 * Created by felix on 2016-10-13.
 */
public class FakeFile extends FakeFSO {
	private String contents;

	public FakeFile(String name, String contents){
		super(name);
		this.contents = contents;
	}

	public String getContents(){
		return contents;
	}

	public void setContents(String contents){
		this.contents = contents;
	}
}
