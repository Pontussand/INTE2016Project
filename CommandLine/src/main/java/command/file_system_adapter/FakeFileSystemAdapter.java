package command.file_system_adapter;


import command.TreeNode;

public class FakeFileSystemAdapter implements FileSystemAdapter {

    private TreeNode root;

    private void initFakeData() {
        this.root = new TreeNode("/");
        this.root.addNode(new TreeNode("settings.ini"));
        this.root.addNode(new TreeNode("home/"));
        this.root.getNodeForKey("home/").addNode(new TreeNode("tenta.docx"));
        this.root.getNodeForKey("home/").addNode(new TreeNode("glad.jpg"));
        this.root.getNodeForKey("home/").addNode(new TreeNode("test.java"));
    }

    public FakeFileSystemAdapter() {
        initFakeData();
    }

    @Override
    public String[] ls(String path) {
        return null;
    }

    @Override
    public boolean createFile(String filePath) {
        return false;
    }

    @Override
    public boolean appendToFile(String filePath, String content) {
        return false;
    }

    @Override
    public boolean writeToFile(String filePath, String content) {
        return false;
    }

    @Override
    public boolean deleteFile(String path) {
        return false;
    }

    @Override
    public boolean createDirectory(String path) { // "home/annika/bilder"
        String[] pathElements = path.split("/");  // splittar

        TreeNode nextNode = root;   //

        for (String pathElement : pathElements) {
            if (nextNode.getNodeForKey(pathElement) == null) { // finns home?
                nextNode.addNode(new TreeNode(pathElement));
            }

            nextNode = nextNode.getNodeForKey(pathElement);
        }
/* returnerar true om hela eller delar av sökvägen skapats
    returnerar false om sökvägen redan existerar(man kan inte skapa nåt som redan finns)
*/

        return false;
    }

    @Override
    public boolean deleteDirectory(String path) {
        return false;
    }

}
