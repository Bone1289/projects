package interview.bts.closevalue;

import interview.bts.BST;

public class CloseValueRecursive implements CloseValueStrategy {

    @Override
    public int findClosestValueInBst(BST tree, int target) {
        return findCloseValueInBst(tree, target, Double.MAX_VALUE);
    }

    private static int findCloseValueInBst(BST tree, int target, double closest) {
        if (Math.abs(target - closest) > Math.abs(target - tree.value)) {
            closest = tree.value;
        }
        if (target < tree.value && tree.left != null) {
            return findCloseValueInBst(tree.left, target, closest);
        } else if (target > tree.value && tree.right != null) {
            return findCloseValueInBst(tree.right, target, closest);
        } else {
            return (int) closest;
        }
    }
}
