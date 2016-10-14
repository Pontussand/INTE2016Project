package command;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    private List<TreeNode> nodes;
    private String key;

    public TreeNode(String pathKey) {
        this.key = pathKey;
    }

    public void addNode(TreeNode node) {
        if (nodes == null) {
            this.nodes = new LinkedList<>();
        }

        nodes.add(node);
    }

    public String getKey() {
        return this.key;
    }


    public TreeNode getNodeForKey(String key) {

        if (this.nodes == null || this.nodes.size() == 0) {
            return null;
        }

        for (TreeNode elem : this.nodes) {
            if (elem.getKey().equals(key)) {
                return elem;
            }
        }
        return null;
    }
}
