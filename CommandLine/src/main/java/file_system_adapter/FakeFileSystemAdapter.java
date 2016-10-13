package file_system_adapter;

import fileSystemObject.FakeDirectory;
import fsobject.Directory;

import java.io.File;
import java.util.ArrayList;

public class FakeFileSystemAdapter implements FileSystemAdapter {
	private FakeDirectory root;

	@Override
	public String[] ls(String path){
		return null;
	}

	@Override
	public boolean mkdir(String path){
		root.addFSO(new FakeDirectory(path));
		return true;
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

	/**intended for testing only*/
	protected void setRoot(FakeDirectory root){
		this.root = root;
	}

}
