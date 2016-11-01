package file_system_adapter;

import file_system_adapter.fake_FSO.FakeFSA_deleteFSO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FakeFSA_copyFSO.class, FakeFSA_dir.class, FakeFSA_ls.class, FakeFSA_file.class, FakeFSA_lsDirTest.class, FakeFSA_deleteFSO.class})
public class TestSuite_FakeFSA {}
