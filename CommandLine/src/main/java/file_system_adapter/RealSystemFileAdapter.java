package file_system_adapter;


import file_system_adapter.fake_FSO.FakeDirectory;
import prompt.util.Path;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class RealSystemFileAdapter implements FileSystemAdapter {

//	private File root = new File("./");

	@Override
	public String[] ls(String path) {
		return null;
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
		return false;
	}
	

//	@Override
//	public boolean mkdir(String newDir) {
//		try {
//			Files.createDirectory(newDir.toPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	@Override
	public boolean mkdir(String path) {
		System.out.println("incoming filePath " + path);


		String folderName = Path.getFSOName(path);
		System.out.println(folderName);

//		if (!folderName.equals("")) {
//			String parentDirPath = Path.getParentPath(path);
//			FakeDirectory parentDir = (FakeDirectory) root.pathSearch(parentDirPath);
//			return parentDir.addFSO(new FakeDirectory(folderName));
//		}
//
//
//		File newFile = new File(filePath+"/"+dirName);
//		       try {
//				   Files.createDirectory(newFile.toPath());
//			   }catch (IOException e){
//				   e.printStackTrace();
//			   }


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
	public boolean deleteFSO(String path) {

		return false;
	}

	@Override
	public String getRootDirectory() {
		return File.listRoots()[0].getAbsolutePath();

	}

    /*OLD:
    @Override
    public ArrayList<String> ls(File path) {

//        

        String[] files = path.list();

        ArrayList<String> strings = new ArrayList<>();

        if (files == null) {
            System.out.println("the directory doesn't exist.");
        } else if (files.length == 0) {
            System.out.println("The directory is empty");
        } else {

            for (String aFile : files) {
                System.out.println(aFile);
                strings.add(aFile);
            }
        }

        return strings;
    }


    @Override
    public String findProjectDir() {
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        return userDir;
    }

    @Override
    public File findParentDir(File currentDir) {

        return new File(currentDir.getParent());
    }

    @Override
    public void mkdir(File newDir) {
        try {
            Files.createDirectory(newDir.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}


