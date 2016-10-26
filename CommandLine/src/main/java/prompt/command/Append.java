
package prompt.command;

import file_system_adapter.FSAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import java.util.Objects;

public class Append extends Command {

	public static final String UNABLE_TO_APPEND = "Could not write to file :'(";

	public Append(CommandPrompt prompt){
		super(prompt, "append");
	}

	public String doCommand(PathContainer currentPathContainer, String input) {
		FSAdapter adapter = super.getAdapter();

		String fileName = input.split(" ")[0];
		String content = input.substring(input.indexOf(" ") +1);

		String fileWithPath = currentPathContainer.getPath() + "/" + fileName;
		boolean appended = adapter.appendToFile(fileWithPath, content);

		if (appended) {
			return "";
		} else {
			return UNABLE_TO_APPEND;
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

		Append other = (Append) o;
		return Objects.equals(getName(), other.getName());
	}
}