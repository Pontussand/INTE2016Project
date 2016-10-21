
package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Touch extends Command {

	public static final String UNABLE_TO_CREATE = "Could not create file :'(";

	public Touch(CommandPrompt cp){
		super(cp);
	}

	public String getName(){
		return "touch";
	}

	public String doCommand(PathContainer currentPathContainer, String input) {
//		boolean cdFromRootDir = input.startsWith(PathContainer.DIR_SEPERATOR);

		if (!validFileName(input)) {
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