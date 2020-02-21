package interview.bts.closevalue;

import interview.bts.BST;

public class CloseValueIterative implements CloseValueStrategy {
    @Override
    public int findClosestValueInBst(BST tree, int target) {
        BST currentNode = tree;
        double closest = Double.MAX_VALUE;
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
        return (int) closest;
    }
}