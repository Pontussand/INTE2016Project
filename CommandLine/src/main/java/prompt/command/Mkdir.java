package prompt.command;

import prompt.util.Path;

import file_system_adapter.FileSystemAdapter;

public class Mkdir extends Command {

	public static final String ERROR_MSG = "Could not create directory. ;_;";

	public String doCommand(Path currentDir, String input) {
		FileSystemAdapter adapter = super.getAdapter();

		String path = currentDir.getPath();

		if (currentDir.equals("/")) {
			path += input;
//			System.out.println("inside root, " + path);

		} else {
			path += "/" + input;
//			System.out.println("outside root, " + path);

		}

		if (adapter.mkdir(path)) {
			return "";
		} else {
			return ERROR_MSG;
		}


	}
}