package prompt.command;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AppendTest.class, CatTest.class, CdTest.class, CommandTest.class, CpTest.class,
        HistoryTest.class, LsTest.class, MkdirTest.class, PwdTest.class, RepeatFromHistoryTest.class,
        RepeatLastTest.class, TouchTest.class})
public class TestSuite_commands {
}
