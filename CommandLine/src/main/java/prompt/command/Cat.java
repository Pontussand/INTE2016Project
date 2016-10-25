package prompt.command;

import file_system_adapter.FSAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Cat extends Command {

    public Cat(CommandPrompt prompt) {
        super(prompt, "cat");
    }

    public String doCommand(PathContainer currentPathContainer, String input) {
        FSAdapter adapter = super.getAdapter();

        String path = currentPathContainer.getPath() + "/" + input;
        return adapter.readFromFile(path);
    }
}
