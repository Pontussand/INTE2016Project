package prompt;

import file_system_adapter.FakeFSAdapter;
import prompt.command.*;
import file_system_adapter.FSAdapter;
import prompt.command.Mkdir;
import prompt.util.PathContainer;

import java.util.*;

public class CommandPrompt {
	private Scanner scan = new Scanner(System.in);
	private FSAdapter adapter;
	private PathContainer currentDir = new PathContainer("");
	private boolean loop = true;

//	private PathContainer curentDir = adapter.getRoot();

	private Map<String, Command> commands = new HashMap<>();

	public CommandPrompt(FSAdapter adapter) {
		this.adapter = adapter;
		Command.setAdapter(this.adapter);
		initialize();
	}

	private void initialize() {
		addCommand(new Append(this));
		addCommand(new Cat(this));
		addCommand(new Cd(this));
		addCommand(new Cp(this));
		addCommand(new Ls(this));
		addCommand(new History(this));
		addCommand(new Mkdir(this));
		addCommand(new Mkdirs(this));
		addCommand(new Pwd(this));
		addCommand(new RepeatFromHistory(this));
		addCommand(new RepeatLast(this));
		addCommand(new Touch(this));

		currentDir.setPath(adapter.rootDirectory());
	}

	private void addCommand(Command c) {
		commands.put(c.getName(), c);
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

	public void setCurrentDir(String path) {
		currentDir.setPath(path);
	}

	protected PathContainer getCurrentDir() {
		return currentDir;
	}

	protected HashMap<String, Command> getCommands() {
		HashMap<String, Command> temp = new HashMap<>();
		temp.putAll(commands);
		return temp;
	}

	public static void main(String[] args) {
		System.out.println("Command Prompt starting...");

//		RealFSAdapter adapter = new RealFSAdapter();
		FakeFSAdapter adapter = new FakeFSAdapter();
		CommandPrompt test = new CommandPrompt(adapter);
		test.run();

	}
}