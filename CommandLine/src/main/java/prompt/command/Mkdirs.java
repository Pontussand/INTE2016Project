package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

import file_system_adapter.FSAdapter;

import java.util.Objects;

public class Mkdirs extends Command {

	public Mkdirs(CommandPrompt prompt){
		super(prompt, "mkdirs");
	}

	public String doCommand(PathContainer currentDir, String input) {
		FSAdapter adapter = super.getAdapter();
		String path = currentDir.getPath();

		if (input.startsWith("/")) {
			path = input;
		} else {
			path += "/" + input;
		}

		if (adapter.mkdirs(path)) {
			return "";
		} else {
			return Mkdir.ERROR_MSG;
		}
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

		Mkdirs other = (Mkdirs) o;

		return Objects.equals(getName(), other.getName());
	}
}