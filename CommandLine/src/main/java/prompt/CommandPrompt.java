package prompt;

import file_system_adapter.FakeFileSystemAdapter;
import prompt.command.*;
import file_system_adapter.FileSystemAdapter;
import prompt.command.Mkdir;
import prompt.util.Path;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandPrompt {
	private Scanner scan = new Scanner(System.in);
	private Filemanager fm = new Filemanager();
	private String[] last20Commands = new String[20];
	private FileSystemAdapter adapter;
	private Path currentDir = new Path("");

//	private Path curentDir = adapter.getRoot();

	private Map<String, Command> commands = new HashMap<>();


	public CommandPrompt(FileSystemAdapter adapter) {
		this.adapter = adapter;
		initialize();
	}


	private void initialize() {
		commands.put("ls", new Ls());
		commands.put("cd", new Cd());
		commands.put("mkdir", new Mkdir());

	}





	private void run() {
		boolean loop = true;
		while(true) {
			command(scan.nextLine());
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
//			result = commandPart + " fil: " + target;

		}
		addCommandToList(commandPart);

		Command command = commands.get(commandPart);

		return command.doCommand(currentDir, target);
	}

	public static void main(String[] args) {
		System.out.println("Command Prompt starting...");       // Just so we know it's running

		FakeFileSystemAdapter adapter = new FakeFileSystemAdapter();
		CommandPrompt test = new CommandPrompt(adapter);
		test.run();
		System.out.println("Command Prompt exiting!");
	}
}