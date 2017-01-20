package vasilenko.serivces.dto;

/**
 * Created by Alkit on 1/15/2017.
 */
public class TreeNode {
    private final String id;
    private final String parent;
    private final String text;

    public TreeNode(String id, String parent, String text) {
        this.id = id;
        this.parent = parent;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", parent='" + parent + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
