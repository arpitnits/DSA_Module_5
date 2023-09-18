package Tree;

import javax.swing.tree.TreeNode;
import java.util.*;

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Stack<TreeNode> s1 = new Stack<>(); //left->right
        Stack<TreeNode> s2 = new Stack<>(); //right->left

        List<List<Integer>> zigZagList = new ArrayList<>();
        List<Integer> tempList;

        if(root==null)
            return zigZagList;

        s1.push(root);

        while(!s1.isEmpty() || !s2.isEmpty()) {

            tempList = new ArrayList<>();
            while (!s1.isEmpty()) {
                TreeNode curr = s1.pop();
                if(curr.left!=null)
                    s2.push(curr.left);
                if(curr.right!=null)
                    s2.push(curr.right);

                tempList.add(curr.data);
            }

            zigZagList.add(tempList);

            while (!s2.isEmpty()) {
                TreeNode curr = s2.pop();
                if(curr.right!=null)
                    s1.push(curr.right);
                if(curr.left!=null)
                    s1.push(curr.left);

                tempList.add(curr.data);
            }

            zigZagList.add(tempList);
        }
        return zigZagList;
    }

    public boolean isBalanced(TreeNode root) {
        int height = getHeightForBalanced(root);
        if(height==-1)
            return false;
        return true;
    }
    private int getHeightForBalanced(TreeNode root) {
        if(root==null)
            return 0;

        int leftHeight = getHeightForBalanced(root.left);
        int rightHeight = getHeightForBalanced(root.right);

        if(leftHeight == -1  || rightHeight == -1 ||
                Math.abs(leftHeight-rightHeight) > 1)
            return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private int diameter =0;
    public int getHeightAndUpdateDiameter(TreeNode root) {

        if(root==null)
            return 0;

        int leftHeight = getHeightAndUpdateDiameter(root.left);
        int rightHeight = getHeightAndUpdateDiameter(root.right);

        diameter = Math.max(diameter, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight) + 1;

    }
    public int diameterOfBinaryTree(TreeNode root) {
        getHeightAndUpdateDiameter(root);
        return diameter;
    }
    public int getLevel(TreeNode root, int data) {
        if(root==null)
            return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 1;
        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int i=0;i<qSize;i++) {
                TreeNode curr = q.remove();

                if(curr.data == data)
                    return level;

                if(curr.left!=null) q.add(curr.left);
                if(curr.right!=null)  q.add(curr.right);
            }
            level++;
        }
        return 0;
    }

    private void swapNode(TreeNode curr) {
        TreeNode temp = curr.right;
        curr.right = curr.left;
        curr.left = temp;
    }
    public TreeNode invertTree(TreeNode root) {

        if(root==null)
            return null;

        swapNode(root);
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    int maxcol =0, minCol =0;
    public void buildTraversalMap(TreeNode root, int col, int row,
                                  Map<Integer, TreeMap<Integer, List<Integer>>> verticalMap) {

        if(root==null)
            return;

        if(!verticalMap.containsKey(col)) {
            verticalMap.put(col, new TreeMap<>());
        }

        if(!verticalMap.get(col).containsKey(row)) {
            verticalMap.get(col).put(row, new ArrayList<>());
        }
        verticalMap.get(col).get(row).add(root.data);

        minCol = Math.min(minCol, col);
        maxcol = Math.max(maxcol, col);

        buildTraversalMap(root.left, col-1, row+1, verticalMap);
        buildTraversalMap(root.right, col+1, row+1, verticalMap);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, TreeMap<Integer, List<Integer>>> verticalMap = new TreeMap<>();

        //O(N)
        buildTraversalMap(root, 0, 0, verticalMap);

        List<List<Integer>> finalPath = new ArrayList<>();

        //O(N*logN)
        for(int i=minCol; i<=maxcol; i++) {
            List<Integer> verticalPath = new ArrayList<>();
            for(List<Integer> values :  verticalMap.get(i).values()) {
                Collections.sort(values);
                verticalPath.addAll(values);
            }
            finalPath.add(verticalPath);
        }
        return finalPath;
    }

    private int ans = Integer.MIN_VALUE;
    private int maxPathSumUtil(TreeNode root) {
        if(root==null)
            return 0;

        int leftMax = maxPathSumUtil(root.left);
        int rightMax = maxPathSumUtil(root.right);

        int currMax = Math.max(root.data, Math.max(leftMax, rightMax) + root.data);

        ans = Math.max(ans, Math.max(currMax, leftMax + rightMax + root.data));

        return currMax;
    }

    public int maxPathSum(TreeNode root) {
        maxPathSumUtil(root);
        return ans;
    }
    private boolean hasPath(TreeNode curr, int key, ArrayList<Integer> arrayList) {

        if(curr==null)
            return false;

        if(curr.data == key) {
            arrayList.add(curr.data);
            return true;
        }

        arrayList.add(curr.data);

        if(hasPath(curr.left, key, arrayList) || hasPath(curr.right, key, arrayList))
            return true;

        arrayList.remove(arrayList.size()-1);
        return false;
    }
    public ArrayList<Integer> pathToNodeBT(TreeNode root, int key) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        hasPath(root, key, arrayList);
        return arrayList;
    }

    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;

        if(p.data < root.data && q.data < root.data)
            return lowestCommonAncestorBST(root.left, p, q);
        else if(p.data > root.data && q.data>root.data)
            return lowestCommonAncestorBST(root.right, p, q);

        return root;
    }

    public TreeNode lowestCommonAncestorBT(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;

        if(root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestorBT(root.left, p, q);
        TreeNode right = lowestCommonAncestorBT(root.right, p, q);

        if(left!=null && right!=null)
            return root;

        if(left!=null)
            return left;
        else
            return right;
    }
    public void flatten(TreeNode root) {
        while(root!=null) {
            if(root.left!=null) {
                TreeNode curr = root;
                TreeNode tempRight = curr.right;
                curr.right = curr.left;
                curr.left = null;
                while(curr.right!=null)
                    curr = curr.right;

                curr.right = tempRight;
            }
            root = root.right;
        }
    }

    int preIndex = 0;
    public TreeNode buildTreeFromPreAndInOrderUtil(int[] preorder, int[] inorder,
                                                   int inStart, int inEnd) {

        if(inStart>inEnd)
            return null;

       TreeNode newNode = new TreeNode(preorder[preIndex++]);

       if(inStart == inEnd)
           return newNode;

       int inOrderIndex = search(inorder, inStart, inEnd, newNode.data);

       newNode.left = buildTreeFromPreAndInOrderUtil(preorder, inorder, inStart, inOrderIndex -1);
       newNode.right = buildTreeFromPreAndInOrderUtil(preorder, inorder, inOrderIndex+1, inEnd);

       return newNode;
    }

    private int search(int[] arr, int start, int end, int key) {
        for(int i=start;i<=end;i++) {
            if(key == arr[i])
                return i;
        }
        return -1;
    }
    public TreeNode buildTreeFromPreAndInOrder(int[] preorder, int[] inorder) {
        return  buildTreeFromPreAndInOrderUtil(preorder, inorder, 0, inorder.length-1);
    }

    private boolean isSiblings(TreeNode root, int x, int y) {
        if(root==null)
            return false;

        if(root.left!=null && root.right!=null && (
                (root.left.data==x && root.right.data==y) ||
                        (root.right.data==x && root.left.data==y))) {
            return true;
        }

        return ((isSiblings(root.left, x, y)) || (isSiblings(root.right, x, y)));
    }
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null)
            return false;

        int xLevel = getLevel(root, x);
        int yLevel = getLevel(root, y);

        if(xLevel==0 || yLevel==0)
            return false;

        return (xLevel==yLevel) && (!isSiblings(root, x, y));
    }


}
