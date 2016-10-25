package profiler;

import file_system_adapter.FakeFileSystemAdapter;
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
        "history",
        "! 4"
    };
    
    public static void main(String[] args){
        new profilerTest().run();
    }
    
    public void run(){
        prompt = new CommandPrompt(new FakeFileSystemAdapter());
        
        for(String command : commands){
            exec(command);
        }
    }
    
    private void exec(String command){
        System.out.println(command + " : " + prompt.command(command));
    }
}
