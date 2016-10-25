
package prompt.command;

import file_system_adapter.FSAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Touch extends Command {

	public static final String UNABLE_TO_CREATE = "Could not create file :'(";
	public static final String FILE_EXISTS = "There is already a file with that name, try again";
	public static final String INVALID_FILE_NAME = "That is not a valid file name, try again";
	public static final String INVALID_PATH = "That is not a valid path, try again";


	public Touch(CommandPrompt prompt){
		super(prompt, "touch");
	}

	public String doCommand(PathContainer currentPathContainer, String input) {

		if (!validFileName(input)) {
			return UNABLE_TO_CREATE;
		} else {
			FSAdapter adapter = super.getAdapter();
			String fileWithPath = currentPathContainer.getPath() + "/" + input;

			if (adapter.createFile(fileWithPath)) {
				return "";
			} else {
				return UNABLE_TO_CREATE;
			}
		}
	}
}