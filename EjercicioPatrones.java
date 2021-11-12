
import java.util.HashMap;
import java.util.Map;

class BinaryTreeNode implements Cloneable {
    int data;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(int item) {
        data = item;
        left = right = null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

}

class BinaryTree {
    private static BinaryTree instance = null;
    BinaryTreeNode root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(int item) {
        root = new BinaryTreeNode(item);
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public static BinaryTree getInstance() {
        if (instance == null) {
            synchronized (BinaryTree.class) {
                if (instance == null) {
                    instance = new BinaryTree();
                }
            }
        }
        return instance;
    }

}

class Registry {

    private Map<String, BinaryTreeNode> items = new HashMap<String, BinaryTreeNode>();

    public Registry() {
        loadItems();
    }

    public BinaryTreeNode createItem(String type) {
        BinaryTreeNode item = null;

        try {
            item = (BinaryTreeNode) (items.get(type)).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return item;
    }

    private void loadItems() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        items.put("node", root);

    }
}

class EjercicioPatrones {
    public static void main(String[] args) {
        BinaryTree bt = BinaryTree.getInstance();
        Registry registry = new Registry();
        BinaryTreeNode node = (BinaryTreeNode) registry.createItem("node");
        bt.setRoot(node);
        bt.getRoot().setLeft((BinaryTreeNode) registry.createItem("node"));
        bt.getRoot().setRight((BinaryTreeNode) registry.createItem("node"));
    }
}
