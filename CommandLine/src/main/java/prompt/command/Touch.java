
package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.util.Path;

public class Touch extends Command {

	public static final String UNABLE_TO_CREATE = "Could not create file :'(";

	public String doCommand(Path currentPath, String input) {
		boolean cdFromRootDir = input.startsWith(Path.DIR_SEPERATOR);
		FileSystemAdapter adapter = super.getAdapter();

		String fileWithPath = currentPath.getPath() + "/" + input;

		if (adapter.createFile(fileWithPath)) {
			return "";
		} else {
			return UNABLE_TO_CREATE;
		}
	}
}