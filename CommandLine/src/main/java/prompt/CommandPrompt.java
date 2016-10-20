package prompt;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.RealSystemFileAdapter;
import prompt.command.*;
import file_system_adapter.FileSystemAdapter;
import prompt.command.Mkdir;
import prompt.util.PathContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandPrompt {
	private Scanner scan = new Scanner(System.in);
	private String[] last20Commands = new String[20];
	private FileSystemAdapter adapter;
	private PathContainer currentDir = new PathContainer("");

//	private PathContainer curentDir = adapter.getRoot();

	private Map<String, Command> commands = new HashMap<>();


	public CommandPrompt(FileSystemAdapter adapter) {
		this.adapter = adapter;
		Command.setAdapter(this.adapter);
		initialize();
	}


	private void initialize() {
		commands.put("ls", new Ls());
		commands.put("cd", new Cd());
		commands.put("mkdir", new Mkdir());
		commands.put("mkdirs", new Mkdirs());
		commands.put("touch", new Touch());
		commands.put("append", new Append());

		currentDir.setPath(adapter.rootDirectory());
	}


	private void run() {
		boolean loop = true;
		while(true) {
			System.out.println(command(scan.nextLine()));

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

		if(commandInput.contains(" ")){
			commandPart = commandInput.split(" ")[0];
			target = commandInput.substring(commandInput.indexOf(" ") +1);
		}

		addCommandToList(commandPart);
		Command command = commands.get(commandPart);

		return command.doCommand(currentDir, target);
	}

	public static void main(String[] args) {
		System.out.println("Command Prompt starting...");

//		RealSystemFileAdapter adapter = new RealSystemFileAdapter();
		FakeFileSystemAdapter adapter = new FakeFileSystemAdapter();
		CommandPrompt test = new CommandPrompt(adapter);
		test.run();
		System.out.println("Command Prompt exiting!");
	}
}