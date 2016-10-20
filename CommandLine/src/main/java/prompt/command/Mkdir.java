package prompt.command;

import prompt.util.PathContainer;

import file_system_adapter.FileSystemAdapter;

public class Mkdir extends Command {

	public static final String ERROR_MSG = "Could not create directory. ;_;";

	public String doCommand(PathContainer currentDir, String input) {
		FileSystemAdapter adapter = super.getAdapter();

		String dirName = PathContainer.getFSOName(input);
//		System.out.println("dirName to create " + dirName);

		String path = currentDir.getPath();

		if (currentDir.equals("/")) {
			path += dirName;
//			System.out.println("inside root, " + path);

		} else {
			path += "/" + dirName;
//			System.out.println("outside root, " + path);

		}

		if (adapter.mkdir(path)) {
			return "";
		} else {
			return ERROR_MSG;
		}
	}
}