package prompt.util;

public class PathContainer {
	private String path;
	public static final String DIR_SEPERATOR = "/";

	public PathContainer(String path) {
		this.path = path;
	}

	public void append(String input) {
		this.path += input;
	}

	public String getParentPath() {
		return getParentPath(path);
	}

	public String getFSOName() {
		return getFSOName(path);
	}

	public static String getParentPath(String path) {
		int stop = path.lastIndexOf(DIR_SEPERATOR);
		if (stop == -1)
			return "";
		return path.substring(0, stop);
	}

	public static String getFSOName(String path) {
		int start = path.lastIndexOf(DIR_SEPERATOR) + 1;
		int stop = path.length();
		return path.substring(start, stop);
	}

	public static String getFullPath(String currentPath, String newPath) {
		String result = currentPath;
		boolean fullPath = newPath.startsWith(DIR_SEPERATOR);

		if (fullPath) {
			result = newPath;
		} else {
			result += DIR_SEPERATOR + newPath;
		}
		return result;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
