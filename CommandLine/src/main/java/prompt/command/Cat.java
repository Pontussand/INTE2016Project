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
        return "";
    }
}
