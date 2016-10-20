package prompt;

import file_system_adapter.FakeFileSystemAdapter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandPromptTest {

	private static String expected20commands = "lsfdptouchmkdirlsfdptouchmkdirlsfdptouchmkdirlsfdptouchmkdirlsfdptouchmkdir";

	CommandPrompt cp;
	FakeFileSystemAdapter adapter;

	@Before
	public void commandprompt(){
		adapter = new FakeFileSystemAdapter();
		cp = new CommandPrompt(adapter);
	}

	@Before
	public void Perform20Commands(){
		for(int a = 0; a <5; a++) {
			cp.command("mkdir hej");
			cp.command("touch bla");
			cp.command("fdp tja");
			cp.command("ls tja");
		}
	}


}