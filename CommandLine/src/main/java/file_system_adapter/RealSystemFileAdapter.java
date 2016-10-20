package file_system_adapter;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class RealSystemFileAdapter implements FileSystemAdapter {


	private String root = new File(System.getProperty("user.dir")).getAbsolutePath();

	@Override
	public String[] ls(String path) {
		String[] content;

		Path dir = Paths.get(path);
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			ArrayList<Path> tempContent = new ArrayList<>();
			for (Path file: stream) {
				tempContent.add(file);
			}

			content = new String[tempContent.size()];
			for (int i = 0; i < tempContent.size(); i++) {
				Path file = tempContent.get(i);
				content[i] = file.getFileName().toString();
			}
			return content;

		} catch (IOException | DirectoryIteratorException x) {
			System.err.println(x);
		}

		return null;
	}

	@Override
	public boolean fsoExist(String path) {
//		not tested
		Path file = new File(path).toPath();
		return Files.exists(file);
	}

	@Override
	public boolean isFile(String path) {
//		not tested
		Path file = new File(path).toPath();
		return Files.isRegularFile(file);
	}

	@Override
	public boolean isDir(String path) {
		Path file = new File(path).toPath();
		return Files.isDirectory(file);
	}

	@Override
	public boolean mkdir(String path) {
		return new File(path).mkdir();
	}

	@Override
	public boolean mkdirs(String path) {
		System.out.println(path);
		return new File(path).mkdirs();
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
	public String rootDirectory() {
//		return File.listRoots()[0].getAbsolutePath();
		return new File(System.getProperty("user.dir")).getAbsolutePath();
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


