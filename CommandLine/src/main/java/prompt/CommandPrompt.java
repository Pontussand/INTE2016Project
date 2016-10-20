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
	private List<String> last20Commands = new LinkedList<String>();
	private static int maxHistory = 20;
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
		commands.put("append", new Append(this));
		commands.put("cd", new Cd(this));
		commands.put("ls", new Ls(this));
		commands.put("mkdir", new Mkdir(this));
		commands.put("mkdirs", new Mkdirs(this));
		commands.put("pwd", new Pwd(this));
		commands.put("touch", new Touch(this));

		currentDir.setPath(adapter.rootDirectory());
	}

	private void run() {
		while(loop) {
			System.out.println(command(scan.nextLine()));

		}
	}

	private void addCommandToList(String commandUsed){
		this.last20Commands.add(commandUsed);
	}

	public String command(String commandInput) {
		String commandPart = commandInput;
		String target = "";

		if(commandInput.contains(" ")){
			commandPart = commandInput.split(" ")[0];
			target = commandInput.substring(commandInput.indexOf(" ") +1);
		}

		if(commandPart == "exit"){
			loop = false;
			return "CommandPrompt is shutting down";
		}

		addCommandToList(commandPart);
		Command command = commands.get(commandPart);

		if(command == null){
			return commandPart + " is an invalid command";
		}
		return command.doCommand(currentDir, target);
	}

	public static void main(String[] args) {
		System.out.println("Command Prompt starting...");

//		RealSystemFileAdapter adapter = new RealSystemFileAdapter();
		FakeFileSystemAdapter adapter = new FakeFileSystemAdapter();
		CommandPrompt test = new CommandPrompt(adapter);
		test.run();

	}
}