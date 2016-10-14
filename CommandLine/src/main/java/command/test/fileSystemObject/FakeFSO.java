package command.test.fileSystemObject;

public abstract class FakeFSO {
	private String name;

	public FakeFSO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
