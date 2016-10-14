package file_system_adapter.fakeFSO;

public abstract class FakeFSO implements Comparable<FakeFSO>{
	private String name;

	public FakeFSO(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public int compareTo(FakeFSO other){
		return name.compareTo(other.getName());
	}
}
