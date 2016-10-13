package command;

import java.util.Scanner;

public class CommandPrompt {
	public Scanner scan = new Scanner(System.in);

	private void run() {
		while(true) {
			String command = scan.nextLine();
			command(command);
		}
	}

	public String command(String command) {
		String commandPart = command;
		String target = "";
		String result = command;

		if(command.contains(" ")){
			commandPart = command.split(" ")[0];
			target = command.substring(command.indexOf(" ") +1);
			result = commandPart + " fil: " + target;
		}

		switch (commandPart) {

		case "ls":
			return result;
		//command for findProjectDir
		case "fpd":
			return result;
		//command for findParentDir
		case "fpad":
			return result;
		case "mkdir":
			return result;
		case "pwd":
			return result;
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
