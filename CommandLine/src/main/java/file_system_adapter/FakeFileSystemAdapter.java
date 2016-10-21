package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import file_system_adapter.fake_FSO.FakeFile;
import prompt.util.PathContainer;

import java.nio.file.Path;
import java.util.ArrayList;

public class FakeFileSystemAdapter implements FileSystemAdapter {
	public static final String DIR_SEPERATOR = "/";
	public static final String NULL_ERROR_MESSAGE = "Directory does not exist";
	public static final String FILE_ERROR_MESSAGE = "Address leads to a textfile";



	private FakeDirectory root = new FakeDirectory("root");


	@Override
	public String[] ls(String path) {
		FakeFSO fakeFSO = root.pathSearch(path);
		String[] errorMessage = new String[1];

		if (fakeFSO == null) {
			errorMessage[0] = NULL_ERROR_MESSAGE;

		} else if (fakeFSO instanceof FakeFile) {
			errorMessage[0] = FILE_ERROR_MESSAGE;

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
	public boolean fsoExist(String path) {
		return root.pathSearch(path) != null;
	}

	@Override
	public boolean isFile(String path) {
		FakeFSO fakefso = root.pathSearch(path);
		return fakefso != null && fakefso instanceof FakeFile;
	}

	@Override
	public boolean isDir(String path) {
		FakeFSO fakefso = root.pathSearch(path);
		return fakefso != null && fakefso instanceof FakeDirectory;
	}

	@Override
	public boolean mkdir(String path) {
		String folderName = PathContainer.getFSOName(path);
		boolean validPath = !folderName.equals("");

		if (validPath) {
			String parentDirPath = PathContainer.getParentPath(path);
			FakeDirectory parentDir = (FakeDirectory) root.pathSearch(parentDirPath);
			return parentDir.addFSO(new FakeDirectory(folderName));
		}
		return false;
	}

	@Override
	public boolean mkdirs(String path) {
		if (!fsoExist(path)) {
			boolean succeeded = false;
			ArrayList<String> dirs = getMkdirsDirs(path);

			for (int i = dirs.size() - 1; i >= 0; i--) {
				succeeded = mkdir(dirs.get(i));
			}
			return succeeded;
		}
		return false;
	}

	protected ArrayList<String> getMkdirsDirs(String path) {
		ArrayList<String> newDirs = new ArrayList<>();

		String currentPath = path;
		while (!currentPath.equals("")) {
			newDirs.add(currentPath);
			currentPath = PathContainer.getParentPath(currentPath);
		}
		return newDirs;
	}


	@Override
	public boolean createFile(String filePath) {
		String fileName = PathContainer.getFSOName(filePath);

		if (!fileName.equals("")) {
			String parentDirPath = PathContainer.getParentPath(filePath);

			if (parentDirPath.equals("")) {
				return root.addFSO(new FakeFile(fileName, ""));


			} else {
				FakeDirectory parentDir = (FakeDirectory) root.pathSearch(parentDirPath);
				return parentDir.addFSO(new FakeFile(fileName, ""));
			}
		}
		return false;
	}

	@Override
	public boolean appendToFile(String filePath, String content) {
		FakeFile fakeFile = (FakeFile) root.pathSearch(filePath);

		if (fakeFile == null) {
			return false;
		} else {
			if (!fakeFile.getContent().equals("")) {
				content = "\n" + content;
			}
			fakeFile.append(content);
			return true;
		}
//not finished
	}

	@Override
	public boolean writeToFile(String filePath, String content) {
		FakeFSO fso = root.pathSearch(filePath);
		if (fso != null && fso instanceof FakeFile){
			FakeFile file = (FakeFile) fso;
			file.setContent(content);
			return true;
		}
		return false;
	}

	/**
	 * intended for testing only
	 */
	public void setRoot(FakeDirectory root) {
		this.root = root;
	}

	@Override
	public boolean deleteFSO(String path) {
		if (isDir(path) || isFile(path)) {
			FakeDirectory parent = (FakeDirectory) root.pathSearch(PathContainer.getParentPath(path));
			parent.removeFSOFromContent(PathContainer.getFSOName(path));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String rootDirectory() {
		return "";
	}
}
