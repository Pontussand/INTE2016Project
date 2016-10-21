package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

import file_system_adapter.FileSystemAdapter;

public class Mkdirs extends Command {

	public static final String ERROR_MSG = "Could not create directory. ;_;";

	public String doCommand(PathContainer currentDir, String input) {
		FileSystemAdapter adapter = super.getAdapter();
		String path = currentDir.getPath();

		if (currentDir.equals("/")) {
			path += input;
		} else {
			path += "/" + input;
		}

		if (adapter.mkdirs(path)) {
			return "";
		} else {
			return ERROR_MSG;
		}
	}
}