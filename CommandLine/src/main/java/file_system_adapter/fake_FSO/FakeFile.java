package file_system_adapter.fake_FSO;


public class FakeFile extends FakeFSO {
	private String content;

	public FakeFile(String name, String content) {
		super(name);

		if (content == null) {
			content = "";
		}

		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void append(String toAdd){
		content += toAdd;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof FakeFile) {
			FakeFile o = (FakeFile) other;
			return o.getName().equals(getName()) &&
					o.content.equals(content);
		}
		return false;
	}
}
