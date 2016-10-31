import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import file_system_adapter.TestSuite_FakeFSA;
import file_system_adapter.fake_FSO.FakeDirectoryTest;
import file_system_adapter.fake_FSO.FakeFileTest;
import prompt.CommandPromptTest;
import prompt.command.TestSuite_commands;
import prompt.util.PathContainerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestSuite_FakeFSA.class, FakeDirectoryTest.class, FakeFileTest.class, TestSuite_commands.class,
        PathContainerTest.class, CommandPromptTest.class})
public class TestSuite_allTests {

}
