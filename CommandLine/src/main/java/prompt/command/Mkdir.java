package prompt.command;

import prompt.util.Path;

import file_system_adapter.FileSystemAdapter;

public class Mkdir extends Command {

	public static final String ERROR_MSG = "Could not create directory. ;_;";

	public String doCommand(Path currentDir, String input) {
		FileSystemAdapter adapter = super.getAdapter();

		System.out.println("Hejsan");

		String path = currentDir.getPath();

		path += "/" + input;

		if (adapter.mkdir(path)) {
			return "";
		} else {
			return ERROR_MSG;
		}


	}
}