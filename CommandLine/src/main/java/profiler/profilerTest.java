package profiler;

import file_system_adapter.FakeFSAdapter;
import prompt.CommandPrompt;

public class profilerTest {
    private CommandPrompt prompt;
    
    private String[] commands = {
        "mkdir dest",
        "touch text.txt",
        "ls",
        "append text.txt this is a test text.",
        "cat text.txt",
        "cp text.txt /dest",
        "cd /dest",
        "pwd",
        "history",
        "! 4",
        "!!",
        "mkdirs /dest/newDir"
    };
    
    public static void main(String[] args){
        new profilerTest().profileing();
    }
    
    public void profileing(){
        prompt = new CommandPrompt(new FakeFSAdapter());
        
        for(String command : commands){
            System.out.println(command + " : " + prompt.command(command));
        }
    }
}
