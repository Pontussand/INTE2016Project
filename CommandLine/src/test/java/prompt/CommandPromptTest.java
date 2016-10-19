package prompt;

import file_system_adapter.FakeFileSystemAdapter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandPromptTest {

	private static String ls_filename = "ls abc";
	private static String ls_expected = "ls fil: abc";
	private static String mkdir_filename = "mkdir abc";
	private static String mkdir_expected = "mkdir fil: abc";
	private static String wrongCommand = "Command doesn't exit";
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

	@Test
	public void command_lsFileName(){
		assertEquals(ls_expected, cp.command(ls_filename));
	}

	@Test
	public void command_twoExclemetionMarks(){
		assertEquals(expected20commands, cp.command("!!"));
	}

	@Test
	public void command_mkdirFileName(){
		assertEquals(mkdir_expected, cp.command(mkdir_filename));
	}

	@Test
	public void command_wrongCommand(){
		assertEquals(wrongCommand, cp.command("3 abc"));
	}

	@Test
	public void command_lsNoFileName(){
		assertEquals("ls fil: ", cp.command("ls "));
	}

	@Test
	public void command_pwd(){
		assertEquals("pwd", cp.command("pwd"));
	}

	@Test
	public void command_lsFileNameNoSpace(){
		assertEquals(wrongCommand, cp.command("lsabc"));
	}
}