package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import file_system_adapter.fake_FSO.FakeFile;
import prompt.util.Path;

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
	public boolean fsoExist(String path) {
		return root.pathSearch(path) != null;
	}

	@Override
	public boolean isFile(String path) {
        FakeFSO fakefso = root.pathSearch(path);
		return  fakefso != null && fakefso instanceof FakeFile;
	}

	@Override
	public boolean isDir(String path) {
        FakeFSO fakefso = root.pathSearch(path);
        return  fakefso != null && fakefso instanceof FakeDirectory;
	}

	@Override
	public boolean mkdir(String path) {
		String folderName = Path.getFSOName(path);
		boolean validPath = !folderName.equals("");

		if (validPath) {
			String parentDirPath = Path.getParentPath(path);
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
        String fileName = Path.getFSOName(filePath);

        if (!fileName.equals("")) {
            String parentDirPath = Path.getParentPath(filePath);

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
        FakeFile fakeFile = (FakeFile) root.pathSearch(filePath);

        if (fakeFile == null) {
            return false;
        } else {
            System.out.println("before");
            System.out.println(fakeFile.getContent());
            fakeFile.append(content);
            System.out.println("after");
            System.out.println(fakeFile.getContent());
            return true;
        }
//not finished
	}

	@Override
	public boolean writeToFile(String filePath, String content) {
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
        if(true) {
            FakeDirectory parent = (FakeDirectory) root.pathSearch(Path.getParentPath(path));
            parent.removeFSOFromContent(Path.getFSOName(path));
            return true;
        }else {
            return false;
        }
    }
}
