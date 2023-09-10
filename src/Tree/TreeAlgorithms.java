package Tree;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public void levelOrder(TreeNode root, List<Integer> path) {
        if(root==null)
            return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode curr = q.remove();
            if(curr.left!=null) q.add(curr.left);
            if(curr.right!=null)  q.add(curr.right);

            path.add(curr.data);
        }
    }

    public TreeNode insertIntoBT(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if(root==null)
            return newNode;
        Queue<TreeNode> q = new LinkedList<>();
                q.add(root);
                while(!q.isEmpty()) {
                    TreeNode curr = q.remove();
                    if(curr.left!=null){
                        q.add(curr.left);
                    } else {
                        curr.left = newNode;
                        break;
                    }
                    if(curr.right!=null) {
                        q.add(curr.right);
                    } else {
                        curr.right = newNode;
                        break;
            }
        }
        return root;
    }

    public TreeNode deletionBT(TreeNode root, int key){
        if(root==null)
            return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        TreeNode keyNode = null;
        TreeNode curr = null;
        while(!q.isEmpty()) {
            curr = q.remove();

            if(curr.data == key) {
                keyNode = curr;
            }

            if(curr.left!=null) q.add(curr.left);
            if(curr.right!=null)  q.add(curr.right);
        }

        if(keyNode == null) {
            System.out.println("Key not exist");
            return root;
        } else {
            //swap with deepest Node
          keyNode.data = curr.data;
        }
        return deleteBottomNode(root, curr);
    }

    private TreeNode deleteBottomNode(TreeNode root, TreeNode delNode) {
        if(root==null) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            TreeNode curr = q.remove();

            if(curr==delNode) {
                curr = null;
                return root;
            }

            if(curr.left!=null) {
                if(curr.left == delNode) {
                    curr.left = null;
                    return root;
                }
                q.add(curr.left);
            }

            if(curr.right!=null) {
                if(curr.right == delNode) {
                    curr.right= null;
                    return root;
                }
                q.add(curr.right);
            }
        }
        return root;
    }

    public TreeNode insertBST(TreeNode curr, int val) {
        if(curr==null) {
            return new TreeNode(val);
        }

        if(val < curr.data) {
            curr.left = insertBST(curr.left, val);
        } else {
            curr.right = insertBST(curr.right, val);
        }

        return curr;
    }
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode curr = root;
        while(curr!=null) {
            if(curr.data == val)
                return curr;

            if(val < curr.data)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return null;
    }

    public TreeNode deleteNodeBST(TreeNode curr, int key) {
        if(curr== null)
            return null;

        if(key < curr.data)
            curr.left = deleteNodeBST(curr.left, key);
        else if(key > curr.data)
            curr.right = deleteNodeBST(curr.right, key);
        else {
            //Case I: no child simple remove
            if(curr.left==null && curr.right==null)
                return null;

            //Case II : 1 child -> swap with child -> remove the child
            else if(curr.left==null)
                return curr.right;
            else if(curr.right==null)
                return curr.left;

            //Case III : 2 child ->  find inorder successor -> swap -> remove successor
            else {
                TreeNode successor = curr.right, successorParent = curr;
                while (successor.left!=null) {
                    successorParent = successor;
                    successor = successor.left;
                }
                if(successorParent==curr) {
                    //when parent is actually the curr node
                    successorParent.right = successor.right;
                } else {
                    successorParent.left = successor.right;
                }
                curr.data = successor.data;
                return curr;
            }
        }
        return curr;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;

        if(p!=null && q!=null)
            return (p.data==q.data) && isSameTree(p.left, q.left) &&
                        isSameTree(p.right, q.right);

        return false;
    }

    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }


}
