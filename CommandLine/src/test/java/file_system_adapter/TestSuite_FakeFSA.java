package file_system_adapter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FakeFSA_copyFSO.class, FakeFSA_dir.class, FakeFSA_ls.class, FakeFSA_file.class, FakeFSA_lsDirTest.class})
public class TestSuite_FakeFSA {}
