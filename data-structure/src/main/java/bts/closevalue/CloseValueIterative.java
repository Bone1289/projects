package bts.closevalue;

import bts.BST;

public class CloseValueIterative implements CloseValueStrategy {
    @Override
    public int findClosestValueInBst(BST tree, int target) {
        BST currentNode = tree;
        int closest = currentNode.value;
        while (currentNode != null) {
            if (Math.abs(target - closest) > Math.abs(target - currentNode.value)) {
                closest = currentNode.value;
            }

            if (target < currentNode.value) {
                currentNode = currentNode.left;
            } else if (target > currentNode.value) {
                currentNode = currentNode.right;
            } else {
                break;
            }
        }
        return closest;
    }
}