package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;

public class FakeFileSystemAdapter implements FileSystemAdapter {
	public static final String DIR_SEPERATOR = "/";
	private FakeDirectory root;

	@Override
	public String[] ls(String path){
		return null;
	}

	@Override
	public boolean mkdir(String path){
		String folderName = getFSOName(path);
		boolean validPath = !folderName.equals("");

		if(validPath) {
			String parentDirPath = getParentDirPath(path);
			FakeDirectory parentDir = (FakeDirectory) root.pathSearch(parentDirPath);
			return parentDir.addFSO(new FakeDirectory(path));
		}
		return false;
	}

	@Override
	public boolean mkdirs(String path){
		return false;
	}

	@Override
	public boolean createFile(String filePath){
		return false;
	}

	@Override
	public boolean appendToFile(String filePath, String content){
		return false;
	}

	@Override
	public boolean writeToFile(String filePath, String content){
		return false;
	}

	@Override
	public boolean deleteFile(String path){
		return false;
	}

	@Override
	public boolean createDirectory(String path){
		return false;
	}

	@Override
	public boolean deleteDirectory(String path){
		return false;
	}

	private String getParentDirPath(String path){
		int stop = path.lastIndexOf(DIR_SEPERATOR);
		if(stop == -1)
			return "";
		return path.substring(0, stop);
	}

	private String getFSOName(String path){
		int start = path.lastIndexOf(DIR_SEPERATOR) + 1;
		int stop = path.length();
		return path.substring(start, stop);
	}

	/**intended for testing only*/
	protected void setRoot(FakeDirectory root){
		this.root = root;
	}

}
