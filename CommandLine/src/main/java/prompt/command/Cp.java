package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Cp extends Command{
	public static final String COULDNT_COPY = "Could not copy. The destination already exist or the source doesn't.";

	public Cp(CommandPrompt prompt){
		super(prompt);
	}

	public String getName(){
		return "cp";
	}

	protected String doCommand(PathContainer currentDir, String input){
		String[] paths = input.split(" ");
		String currDir = currentDir.getPath();
		String source;
		String destination;

		if (paths.length == 2){
			source = PathContainer.getFullPath(currDir, paths[0]);
			destination = PathContainer.getFullPath(currDir, paths[1]);


		}

		return COULDNT_COPY;
	}

}
