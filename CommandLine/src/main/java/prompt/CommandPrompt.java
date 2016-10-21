package prompt;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.RealSystemFileAdapter;
import prompt.command.*;
import file_system_adapter.FileSystemAdapter;
import prompt.command.Mkdir;
import prompt.util.PathContainer;

import java.util.*;

public class CommandPrompt {
	private Scanner scan = new Scanner(System.in);
	private FileSystemAdapter adapter;
	private PathContainer currentDir = new PathContainer("");
	private boolean loop = true;

//	private PathContainer curentDir = adapter.getRoot();

	private Map<String, Command> commands = new HashMap<>();

	public CommandPrompt(FileSystemAdapter adapter) {
		this.adapter = adapter;
		Command.setAdapter(this.adapter);
		initialize();
	}

	private void initialize() {
		addCommand(new Append(this));
		addCommand(new Cd(this));
		addCommand(new Ls(this));
		addCommand(new History(this));
		addCommand(new Mkdir(this));
		addCommand(new Mkdirs(this));
		addCommand(new Pwd(this));
		addCommand(new RepeatLast(this));
		addCommand(new Touch(this));


		currentDir.setPath(adapter.rootDirectory());
	}

	private void addCommand(Command c){
		commands.put(c.getName(), c);
	}

	public void setCurrentDir(String path){
		currentDir.setPath(path);
	}

	private void run() {
		while (loop) {
			System.out.println(command(scan.nextLine()));
		}
	}

	public String command(String commandInput) {
		String commandPart = commandInput;
		String target = "";

		if (commandInput.contains(" ")) {
			commandPart = commandInput.split(" ")[0];
			target = commandInput.substring(commandInput.indexOf(" ") +1);
		}

		if (commandInput.equals("exit")) {
			loop = false;
			return "CommandPrompt is shutting down";
		}

		Command command = commands.get(commandPart);

		if (command == null) {
			return commandPart + " is an invalid command";
		}

		return command.execute(currentDir, target, commandInput);
	}

	public static void main(String[] args) {
		System.out.println("Command Prompt starting...");

//		RealSystemFileAdapter adapter = new RealSystemFileAdapter();
		FakeFileSystemAdapter adapter = new FakeFileSystemAdapter();
		CommandPrompt test = new CommandPrompt(adapter);
		test.run();

	}
}