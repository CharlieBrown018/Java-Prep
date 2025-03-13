package com.interview.problems.junior;

class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

public class Q32_FlipBinaryTree {
    public static void flipTree(TreeNode root) {
        if (root == null) return;

        // Swap left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively flip left and right subtrees
        flipTree(root.left);
        flipTree(root.right);
    }

    public static void printTree(TreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.value + " ");
            printTree(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Original tree:");
        printTree(root);

        flipTree(root);

        System.out.println("\nFlipped tree:");
        printTree(root);
    }
}
