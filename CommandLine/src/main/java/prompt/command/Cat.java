package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Cat extends Command{

    public Cat(CommandPrompt cp){
        super(cp);
    }

    public String getName(){
        return "cat";
    }

    public String doCommand(PathContainer currentPathContainer, String input){
        FileSystemAdapter adapter = super.getAdapter();
        if(input.contains(" ")){
            input = input.split(" ")[0];
        }
        String path = currentPathContainer.getPath() + "/" + input;
        return adapter.readFromFile(path);
    }
}
