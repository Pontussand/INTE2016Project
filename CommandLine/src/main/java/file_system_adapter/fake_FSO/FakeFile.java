package file_system_adapter.fake_FSO;


public class FakeFile extends FakeFSO {
	private String contents;

	public FakeFile(String name, String contents) {
		super(name);

		if (contents == null) {
			contents = "";
		}

		this.contents = contents;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void append(String toAdd){
		contents += toAdd;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof FakeFile) {
			FakeFile o = (FakeFile) other;
			return o.getName().equals(getName()) &&
					o.contents.equals(contents);
		}
		return false;
	}
}
