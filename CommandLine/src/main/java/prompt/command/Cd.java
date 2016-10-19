package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.util.Path;

public class Cd extends Command {

	public static final String NO_SUCH_DIR_MSG = "Could not find that directory :'(";

	public String doCommand(Path currentPath, String input) {
		boolean cdFromRootDir = input.startsWith(Path.DIR_SEPERATOR);
		FileSystemAdapter adapter = super.getAdapter();

		if(cdFromRootDir){
			if(adapter.isDir(input)) {
				currentPath.setPath(input);
			}else{
				return NO_SUCH_DIR_MSG;
			}
		}else{
			String appended = currentPath.getPath() + input;
			if(adapter.isDir(appended)){
				currentPath.setPath(appended);
			}else{
				return NO_SUCH_DIR_MSG;
			}
		}
		return "";
	}


}