package prompt.command;

import file_system_adapter.FSAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import java.util.Objects;

public class Cd extends Command {

	public static final String NO_SUCH_DIR_MSG = "Could not find that directory :'(";

	public Cd(CommandPrompt prompt) {
		super(prompt, "cd");
	}

	public String doCommand(PathContainer currentPathContainer, String input) {
		boolean absolutePath = input.startsWith(PathContainer.DIR_SEPERATOR);
		FSAdapter adapter = super.getAdapter();

		if (absolutePath) {
			if (adapter.isDir(input)) {
				currentPathContainer.setPath(input);
			} else {
				return NO_SUCH_DIR_MSG;
			}
		} else {
			String appended = currentPathContainer.getPath() + PathContainer.DIR_SEPERATOR + input;
			if (adapter.isDir(appended)) {
				currentPathContainer.setPath(appended);
			} else if (input.equals("..")) {
				currentPathContainer.setPath(currentPathContainer.getParentPath());
			} else if (input.equals("")){
				currentPathContainer.setPath("");
			} else {
				return NO_SUCH_DIR_MSG;
			}
		}
		return "";
	}

}