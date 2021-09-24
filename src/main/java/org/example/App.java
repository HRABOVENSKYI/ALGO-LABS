package org.example;

import java.io.PrintStream;

class Tree {
    Node root;

    public Tree() {
        this.root = null;
    }

    public boolean containsNode(int value) {
        return containsNodeElement(root, value);
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public void traversePreOrder(StringBuilder sb, String padding, String pointer, Node node) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value);
            sb.append("\n");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            paddingBuilder.append("│  ");

            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.right != null) ? "├──" : "└──";

            traversePreOrder(sb, paddingForBoth, pointerForLeft, node.left);
            traversePreOrder(sb, paddingForBoth, pointerForRight, node.right);
        }
    }

    public void print(PrintStream os) {
        StringBuilder sb = new StringBuilder();
        traversePreOrder(sb, "", "", root);
        os.print(sb);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    private boolean containsNodeElement(Node currentNode, int value) {
        if (currentNode == null) {
            return false;
        }
        if (currentNode.value == value) {
            return true;
        }
        return value < currentNode.value
                ? containsNodeElement(currentNode.left, value)
                : containsNodeElement(currentNode.right, value);

    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class App {

    public static void main(String[] args) {
        Tree tree = createTree();
        tree.print(System.out);
    }

    public static Tree createTree() {
        Tree tree = new Tree();
        tree.add(3);
        tree.add(6);
        tree.add(-2);
        tree.add(5);
        tree.add(7);
        tree.add(8);
        tree.add(1);
        tree.add(2);
        return tree;
    }
}
