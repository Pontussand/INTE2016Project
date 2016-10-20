
package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.util.PathContainer;

public class Touch extends Command {

	public static final String UNABLE_TO_CREATE = "Could not create file :'(";

	public String doCommand(PathContainer currentPathContainer, String input) {
//		boolean cdFromRootDir = input.startsWith(PathContainer.DIR_SEPERATOR);

		if (!validFSOName(input)) {
			return UNABLE_TO_CREATE;
		} else {
			FileSystemAdapter adapter = super.getAdapter();
			String fileWithPath = currentPathContainer.getPath() + "/" + input;

			if (adapter.createFile(fileWithPath)) {
				return "";
			} else {
				return UNABLE_TO_CREATE;
			}
		}
	}
}