package prompt.command;

import file_system_adapter.fake_FSO.FakeDirectory;
import prompt.util.Path;

import file_system_adapter.FileSystemAdapter;

public class Mkdir extends Command {

	public String doCommand(Path currentDir, String input) {
		FileSystemAdapter adapter = super.getAdapter();

		String path = currentDir.getPath();

		path += "/" + input;
		System.out.println(path);

		if (adapter.mkdir(path)) {
			return "";
		} else {
			return "Error message";
		}


	}
}