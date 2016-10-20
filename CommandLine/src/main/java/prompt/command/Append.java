
package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.util.PathContainer;

public class Append extends Command {

	public static final String UNABLE_TO_APPEND = "Could not write to file :'(";

	public String doCommand(PathContainer currentPathContainer, String input) {
		boolean cdFromRootDir = input.startsWith(PathContainer.DIR_SEPERATOR);
		FileSystemAdapter adapter = super.getAdapter();

		String fileName = input.split(" ")[0];
		String content = input.substring(input.indexOf(" ") +1);

		String fileWithPath = currentPathContainer.getPath() + "/" + fileName;

		if (adapter.appendToFile(fileWithPath, content)) {
			return "";
		} else {
			return UNABLE_TO_APPEND;
		}
	}
}