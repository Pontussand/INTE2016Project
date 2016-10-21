
package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Append extends Command {

	public static final String UNABLE_TO_APPEND = "Could not write to file :'(";

	public Append(CommandPrompt cp){
		super(cp);
	}

	public String getName(){
		return "append";
	}

	public String doCommand(PathContainer currentPathContainer, String input) {
		boolean cdFromRootDir = input.startsWith(PathContainer.DIR_SEPERATOR);
		FileSystemAdapter adapter = super.getAdapter();

		String fileName = input.split(" ")[0];
		String content = input.substring(input.indexOf(" ") +1);

		String fileWithPath = currentPathContainer.getPath() + "/" + fileName;
		String result = adapter.appendToFile(fileWithPath, content);

		if (result != null) {
			return result;
		} else {
			return UNABLE_TO_APPEND;
		}
	}
}