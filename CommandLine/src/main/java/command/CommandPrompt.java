package command;

import java.util.Scanner;

public class CommandPrompt {
	public Scanner scan = new Scanner(System.in);

	private void run() {
		boolean loop = true;
		while(loop) {
			String cmd = scan.nextLine();
			if (cmd.equalsIgnoreCase("exit")) {
				loop = false;
			} else {
				processCommand(cmd);
			}
		}
	}

	public String processCommand(String cmd) {
		String commandPart = cmd;
		String target = "";
		String result = cmd;

		if(cmd.contains(" ")) {
			commandPart = cmd.split(" ")[0];
			target = cmd.substring(cmd.indexOf(" ") +1);
			result = commandPart + " fil: " + target;
		}

		switch (commandPart) {

		case "ls":
			return result;
		//processCommand for findProjectDir
		case "fpd":
			return result;
		//processCommand for findParentDir
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
		System.out.println("Command Prompt starting...");       // Just so we know it's running
		CommandPrompt test = new CommandPrompt();
		test.run();
		System.out.println("Command Prompt exiting!");
	}
}
