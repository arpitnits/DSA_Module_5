package Heap;

import Tree.TreeAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HeapProblems {

    public class ListNode {
        int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        int K = lists.length;

        //ASC
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.val));


        //create a pq of size K
        for(ListNode list : lists) {
            if(list!=null)
                pq.add(list);
        }

        ListNode root = null;
        ListNode curr = null;
        while (!pq.isEmpty()) {
            ListNode topNode = pq.remove();

            //update root node for first time
            if(root==null)
                root = topNode;
            else
               curr.next = topNode;

            //updating to nextNode
            curr = topNode;

            //add Next Node if exist
            if(topNode.next!=null)
                pq.add(topNode.next);
        }
        return root;
    }

    public static int[] nearlySorted(int[] arr, int n, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i<n;i++) {
            pq.add(arr[i]);
        }

        for(int i=0;i<n;i++)
            arr[i] = pq.remove();

        return arr;
    }

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(); //Min Heap

        for(int num : nums) {
            pq.add(num);
            if(pq.size()>k)
                pq.remove();
        }
        return pq.remove();
    }

    class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            left=null;
            right=null;
        }
    }

    static int inOrderIndex = 0;
    public static void inOrder(Node curr, List<Integer> inOrderPath) {
        if(curr==null)
            return;

        //Left->>DATA->>Right
        inOrder(curr.left, inOrderPath);
        inOrderPath.add(curr.data);
        inOrder(curr.right, inOrderPath);
    }

    public static void postOrder(Node curr, List<Integer> inOrderPath) {
        if(curr==null)
            return;

        //Left->>Right->>DATA
        postOrder(curr.left, inOrderPath);
        postOrder(curr.right, inOrderPath);
        curr.data = inOrderPath.get(inOrderIndex++);
    }

    public static void convertToMaxHeapUtil(Node root) {
       List<Integer> inOrderTraversal = new ArrayList<>();
       inOrder(root, inOrderTraversal);
       inOrderIndex=0;
       postOrder(root, inOrderTraversal);
    }


}
