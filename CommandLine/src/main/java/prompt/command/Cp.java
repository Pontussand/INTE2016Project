package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Cp extends Command {
	public static final String COULDNT_COPY = "Could not copy. The destination already exist or the source doesn't.";

	public Cp(CommandPrompt prompt) {
		super(prompt, "cp");
	}

	protected String doCommand(PathContainer currentDir, String input) {
		FileSystemAdapter adapter = getAdapter();
		String[] paths = input.split(" ");
		String currDir = currentDir.getPath();
		String source;
		String destination;

		if (paths.length == 2) {
			source = PathContainer.getFullPath(currDir, paths[0]);
			destination = PathContainer.getFullPath(currDir, paths[1]);

			if (adapter.copyFSO(source, destination)) {
				return "";
			}
		}

		return COULDNT_COPY;
	}

}
