package command.file_system_adapter;

public interface FileSystemAdapter {

    String[] ls(String path);


    boolean createFile(String filePath);

    boolean appendToFile(String filePath, String content);

    boolean writeToFile(String filePath, String content);

    boolean deleteFile(String path);

    boolean createDirectory(String path);

    boolean deleteDirectory(String path);
}

