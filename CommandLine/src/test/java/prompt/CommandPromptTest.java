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

	@Test
	public void command_invalidCommand() {
		assertEquals("love is an invalid command", cp.command("love"));
	}

	@Test
	public void command_exit() {
		assertEquals("CommandPrompt is shutting down", cp.command("exit"));
	}

	@Test
	public void command_history_responding() {
		assertEquals("0 history", cp.command("history"));
	}

	@Test
	public void command_append_responding() {

/**		lite svårt att få respons av Append på ett "enkelt" sätt
*		för att Append ska funka så måste jag ju sätta upp en existerande fil med Touch först
*		och försöka skriva till den.
 *		*/

		assertEquals("Could not write to file :'(", cp.command("append nonExistingFile content"));
	}

	@Test
	public void command_mkdir_responding() {
		assertEquals("", cp.command("mkdir FolderName"));
	}


}