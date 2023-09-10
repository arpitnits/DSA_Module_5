package Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeModule {

    public static void main(String[] args) {
        TreeAlgorithms.TreeNode root = build();
        TreeAlgorithms treeAlgorithms = new TreeAlgorithms();

        List<Integer> path = new ArrayList<>();
        treeAlgorithms.levelOrder(root, path);
        System.out.println(path);

        treeAlgorithms.insertIntoBT(root, 22);
        path = new ArrayList<>();
        treeAlgorithms.levelOrder(root, path);
        System.out.println(path);
    }

    public static TreeAlgorithms.TreeNode build() {
        TreeAlgorithms.TreeNode root = new TreeAlgorithms.TreeNode(1);
        root.left = new TreeAlgorithms.TreeNode(2);
        root.left.left = new TreeAlgorithms.TreeNode(4);

        root.right = new TreeAlgorithms.TreeNode(5);
        root.right.left = new TreeAlgorithms.TreeNode(6);
        root.right.left.left = new TreeAlgorithms.TreeNode(9);
        root.right.left.right = new TreeAlgorithms.TreeNode(12);
        root.right.right = new TreeAlgorithms.TreeNode(7);

        return root;
    }

}
