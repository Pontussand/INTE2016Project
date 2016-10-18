package file_system_adapter;

import prompt.util.Path;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import file_system_adapter.fake_FSO.FakeFile;

public class FakeFileSystemAdapter implements FileSystemAdapter {
	private FakeDirectory root = new FakeDirectory("root");


    @Override
    public String[] ls(String path) {
        FakeFSO fakeFSO = root.pathSearch(path);

        if (fakeFSO == null || fakeFSO instanceof FakeFile) {

        }

        else if (fakeFSO instanceof FakeDirectory) {
            FakeFSO[] fsoArray = ((FakeDirectory) fakeFSO).getContent();

            String[] listOfContent = new String[fsoArray.length];

            for (int i = 0; i < fsoArray.length; i ++) {
                listOfContent[i] = fsoArray[i].getName();

            }

            return listOfContent;
        }

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
		String folderName = Path.getFSOName(path);
		boolean validPath = !folderName.equals("");

		if (validPath) {
			String parentDirPath = Path.getParentPath(path);
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

	/**
	 * intended for testing only
	 */
	public void setRoot(FakeDirectory root) {
		this.root = root;
	}

}
