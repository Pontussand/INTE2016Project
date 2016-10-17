package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;

public class FakeFileSystemAdapter implements FileSystemAdapter {
	public static final String DIR_SEPERATOR = "/";
	private FakeDirectory root = new FakeDirectory("root");


	@Override
	public String[] ls(String path) {
//        root.getContents(path);

		return null;
	}

	@Override
	public boolean isFile(String path) {
		return false;
	}

	@Override
	public boolean isDir(String path) {
		return false;
	}

	@Override
	public boolean mkdir(String path) {
		String folderName = getFSOName(path);
		boolean validPath = !folderName.equals("");

		if (validPath) {
			String parentDirPath = getParentDirPath(path);
			FakeDirectory parentDir = (FakeDirectory) root.pathSearch(parentDirPath);

			if(parentDir == null) {
				return false;
			}
			return parentDir.addFSO(new FakeDirectory(folderName));
		}
		return false;
	}

	@Override
	public boolean mkdirs(String path) {
		return false;
	}

	@Override
	public boolean createFile(String filePath) {
		return false;
	}

	@Override
	public boolean appendToFile(String filePath, String content) {
		return false;
	}

	@Override
	public boolean writeToFile(String filePath, String content) {
		return false;
	}

	@Override
	public boolean deleteFile(String path) {
		return false;
	}

	@Override
	public boolean createDirectory(String path) {
		return false;
	}

	@Override
	public boolean deleteDirectory(String path) {
		return false;
	}

	protected String getParentDirPath(String path) {
		int stop = path.lastIndexOf(DIR_SEPERATOR);
		if (stop == -1)
			return "";
		return path.substring(0, stop);
	}

	protected String getFSOName(String path) {
		int start = path.lastIndexOf(DIR_SEPERATOR) + 1;
		int stop = path.length();
		return path.substring(start, stop);
	}

	/**
	 * intended for testing only
	 */
	protected void setRoot(FakeDirectory root) {
		this.root = root;
	}

}
