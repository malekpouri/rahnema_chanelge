package ir.utux;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeList {
    public static void main(String[] args) {
        TreeNodeList treeNodeList = new TreeNodeList();
        int[] headValues = {4, 3, 5};
        Integer[] rootValues = {1, 4, 5, 3, 2, 8, 6, null, null, 6, null, null, null, null, 7, null, null, null};
        TreeNodeList.ListNode head = treeNodeList.createList(headValues);
        TreeNodeList.TreeNode root = treeNodeList.createTree(rootValues);
        System.out.printf("Input:%n head = %s,%n root = %s%n", Arrays.toString(headValues), Arrays.toString(rootValues));
        System.out.printf("Output: %b",treeNodeList.isSubPath(head, root));
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        return isSub(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSub(ListNode head, TreeNode node) {
        if (head == null) return true;
        if (node == null) return false;
        if (head.val != node.val) return false;
        return isSub(head.next, node.left) || isSub(head.next, node.right);
    }

    public ListNode createList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    public TreeNode createTree(Integer[] values) {
        if (values.length == 0) return null;
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode node = queue.poll();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            if (++i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
        }
        return root;
    }

}
