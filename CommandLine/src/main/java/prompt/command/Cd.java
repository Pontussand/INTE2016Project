package prompt.command;

import prompt.util.Path;

public class Cd extends Command {

	public String doCommand(Path currentPath, String input) {
		if(!input.startsWith(Path.DIR_SEPERATOR)){
			String
			currentPath.setPath(input);
		}
		return "";

	}
}
