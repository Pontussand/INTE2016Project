package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import file_system_adapter.fake_FSO.FakeFile;

public class FakeFileSystemAdapter implements FileSystemAdapter {
	public static final String DIR_SEPERATOR = "/";
	private FakeDirectory root = new FakeDirectory("root");


	@Override
	public String[] ls(String path) {
		FakeFSO fakeFSO = root.pathSearch(path);

		if (fakeFSO == null || fakeFSO instanceof FakeFile) {

		} else if (fakeFSO instanceof FakeDirectory) {
			FakeFSO[] fsoArray = ((FakeDirectory) fakeFSO).getContent();
			String[] listOfContent = new String[fsoArray.length];

			for (int i = 0; i < fsoArray.length; i++) {
				listOfContent[i] = fsoArray[i].getName();
			}

			return listOfContent;
		}

		return null;
	}

	@Override
	public String getRoot() {
		return "";
	}

	@Override
	public boolean fsoExist(String path) {
		return false;
	}

	@Override
	public boolean isFile(String path) {
		return false;
	}

	@Override
	public boolean isDir(String path) {
		FakeFSO fso = root.pathSearch(path);
		if (fso == null || !(fso instanceof FakeDirectory)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean mkdir(String path) {
		String folderName = getFSOName(path);
		boolean validPath = !folderName.equals("");

		if (validPath) {
			String parentDirPath = getParentDirPath(path);
			FakeDirectory parentDir = (FakeDirectory) root.pathSearch(parentDirPath);
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
        String fileName = getFSOName(filePath);

        if (!fileName.equals("")) {
            String parentDirPath = getParentDirPath(filePath);

            if (parentDirPath.equals("")) {
                return root.addFSO(new FakeFile(fileName, ""));

            } else {
                FakeDirectory parentDir = (FakeDirectory) root.pathSearch(parentDirPath);
                
//                inväntar implementation av Cd för att säkerställa att detta också funkar:

                return parentDir.addFSO(new FakeFile(fileName, ""));
            }
        }
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
	public void setRoot(FakeDirectory root) {
		this.root = root;
	}
}
