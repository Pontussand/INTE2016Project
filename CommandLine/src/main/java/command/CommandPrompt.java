package command;

import java.util.Scanner;

public class CommandPrompt {
	public Scanner scan = new Scanner(System.in);
	public Filemanager fm = new Filemanager();
	public String[] last20Commands = new String[20];

	private void run() {
		while(true) {
			String command = scan.nextLine();
			command(command);
		}
	}
	private void addCommandToList(String commandUsed){
		if(!commandUsed.equals("!!")) {
			for (int a = 0; a < 19; a++) {
				last20Commands[19 - a] = last20Commands[18 - a];
			}
			last20Commands[0] = commandUsed;
		}
	}

	public String command(String commandInput) {
		String commandPart = commandInput;
		String target = "";
		String result = commandInput;

		if(commandInput.contains(" ")){
			commandPart = commandInput.split(" ")[0];
			target = commandInput.substring(commandInput.indexOf(" ") +1);
			result = commandPart + " fil: " + target;
		}
		addCommandToList(commandPart);

		switch (commandPart) {

		case "ls":
			return result;
		//command for findProjectDir
		case "fpd":
			return result;
		//command for findParentDir
		case "touch":
			return result;
		case "mkdir":
			return result;
		case "pwd":
			return result;
		case "!!":
			String commandsused = "";
			for(int a = 0; a < last20Commands.length; a++){
				commandsused += last20Commands[a];
			}
			return commandsused;
		default:
			result = "Command doesn't exit";
			return result;
		}
	}

	public static void main(String[] args) {
		CommandPrompt test = new CommandPrompt();
		test.run();
	}
}
