package prompt.command;

import file_system_adapter.FSAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import java.util.Objects;

public class Cp extends Command {
	public static final String COULDNT_COPY = "Could not copy. The destination already exist or the source doesn't.";

	public Cp(CommandPrompt prompt) {
		super(prompt, "cp");
	}

	protected String doCommand(PathContainer currentDir, String input) {
		FSAdapter adapter = getAdapter();
		String[] paths = input.split(" ");
		String currDir = currentDir.getPath();
		String source;
		String destination;

		if (paths.length == 2) {
			source = PathContainer.getFullPath(currDir, paths[0]);
			destination = PathContainer.getFullPath(currDir, paths[1]);

			if (adapter.copyFso(source, destination)) {
				return "";
			}
		}

		return COULDNT_COPY;
	}


	public int hashCode() {
		return Objects.hash(getName());
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}

		Cp other = (Cp) o;

		return Objects.equals(getName(), other.getName());
	}

}
