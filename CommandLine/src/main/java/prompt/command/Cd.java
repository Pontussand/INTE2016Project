package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Cd extends Command {

	public static final String NO_SUCH_DIR_MSG = "Could not find that directory :'(";

	public String doCommand(PathContainer currentPathContainer, String input) {
		boolean cdFromRootDir = input.startsWith(PathContainer.DIR_SEPERATOR);
		FileSystemAdapter adapter = super.getAdapter();

		if (cdFromRootDir) {
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