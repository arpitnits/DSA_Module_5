package Tree;

import javax.swing.tree.TreeNode;
import java.util.List;

public class TreeAlgorithms {

    public static class TreeNode {
        int data;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode() {

        }

        TreeNode(int val) {
            this.data = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.data =val;
            this.left = left;
            this.right = right;
        }
    }

    public void preOrder(TreeNode curr, List<Integer> path) {
        if(curr==null)
            return;

        //Data->>Left->>Right
        path.add(curr.data);
        preOrder(curr.left, path);
        preOrder(curr.right, path);
    }

    public void inOrder(TreeNode curr, List<Integer> path) {
        if(curr==null)
            return;

        //Left->>DATA->>Right
        inOrder(curr.left, path);
        path.add(curr.data);
        inOrder(curr.right, path);
    }

    public void postOrder(TreeNode curr, List<Integer> path) {
        if(curr==null)
            return;

        //Left->>Right->>DATA
        postOrder(curr.left, path);
        postOrder(curr.right, path);
        path.add(curr.data);
    }

}
