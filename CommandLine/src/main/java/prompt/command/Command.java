package prompt.command;

import file_system_adapter.FSAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Command {

	private static FSAdapter adapter;
	public static List<String> commandHistory = new LinkedList<String>();
	public static final int MAX_HISTORY = 10;
	public static final int MAX_FSO_LENGTH = 256;
	private String name;


	protected CommandPrompt ownerCommandPrompt;

	public Command(CommandPrompt prompt, String name) {
		this.ownerCommandPrompt = prompt;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static void setAdapter(FSAdapter input) {
		adapter = input;
	}

	public static FSAdapter getAdapter() {
		return adapter;
	}


	protected boolean shouldBeAddedToHistory() {
		return true;
	}

	public void addToHistory(String commandUsed) {
		this.commandHistory.add(commandUsed);
		if (this.commandHistory.size() > MAX_HISTORY) {
			commandHistory.remove(0);
		}
	}

	public String execute(PathContainer currentDir, String target, String input) {
		if (shouldBeAddedToHistory()) {
			addToHistory(input);
		}
		return doCommand(currentDir, target);
	}

	public int hashCode() {
		return Objects.hash(getName());
	}


	public boolean equals(Object o) {
		if(o != null && ( this == o || getClass() == o.getClass() ) ){
			Command other = (Command) o;
			return name.equals(other.name);
		}

		return false;
	}

	protected abstract String doCommand(PathContainer currentDir, String input);

	public static boolean validFSOName(String name) {
		return (!name.equals("")
				&& !name.contains(" ")
				&& !name.contains("<")
				&& !name.contains(">")
				&& !name.contains(":")
				&& !name.contains("\"") //quote
				&& !name.contains("/")
				&& !name.contains("|")
				&& !name.contains("?")
				&& !name.contains("*")
				&& !name.contains("\\") //backslash
				&& (name.length() < MAX_FSO_LENGTH));
	}

	public static boolean validFileName(String fileName) {
		return (validFSOName(fileName)
				&& fileName.endsWith(".txt"));
	}


}

