package file_system_adapter;

import java.io.File;
import java.util.ArrayList;

public interface FileSystemAdapter {

    String[] ls(String path);

	String[] lsDir(String path);

	String[] lsFile(String path);

	boolean fsoExist(String path);

	boolean isFile(String path);

	boolean isDir(String path);

    boolean mkdir(String path);

	boolean mkdirs(String path);

    boolean createFile(String filePath);

	String appendToFile(String filePath, String content);

	boolean writeToFile(String filePath, String content);

    boolean deleteFSO(String path);

	String rootDirectory();

	String readFromFile(String filePath);



}

