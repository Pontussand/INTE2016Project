package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

import file_system_adapter.FileSystemAdapter;

public class Mkdir extends Command {

	public static final String ERROR_MSG = "Could not create directory. ;_;";

	public Mkdir(CommandPrompt cp){
		super(cp);
	}

	public String getName(){
		return "mkdir";
	}

	public String doCommand(PathContainer currentDir, String input) {
		FileSystemAdapter adapter = super.getAdapter();
		String dirName = PathContainer.getFSOName(input);
		String path = currentDir.getPath();

		if (!validFSOName(input)) {
			return ERROR_MSG;
		}

		if (currentDir.equals("/")) {
			path += dirName;
		} else {
			path += "/" + dirName;
		}

		if (adapter.mkdir(path)) {
			return "";
		} else {
			return ERROR_MSG;
		}
	}
}