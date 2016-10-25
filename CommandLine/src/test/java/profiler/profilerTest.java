package profiler;

import file_system_adapter.FakeFileSystemAdapter;
import prompt.CommandPrompt;

public class profilerTest {
    
    public static void main(String[] args){
        new profilerTest().run();
    }
    
    public void run(){
        CommandPrompt prompt = new CommandPrompt(new FakeFileSystemAdapter());
    }
}
