package interview;

import java.util.ArrayList;
import java.util.List;

public class BranchSums {

    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
        }

        BinaryTree insert(List<Integer> values) {
            return insert(values, 0);
        }

        BinaryTree insert(List<Integer> values, int i) {
            if (i >= values.size()) return null;
            List<BinaryTree> queue = new ArrayList<>();
            queue.add(this);

            while (queue.size() > 0) {
                BinaryTree current = queue.get(0);
                queue.remove(0);

                if (current.left == null) {
                    current.left = new BinaryTree(values.get(i));
                    break;
                }
                queue.add(current.left);
                if (current.right == null) {
                    current.right = new BinaryTree(values.get(i));
                    break;
                }
                queue.add(current.right);
            }
            insert(values, i + 1);

            return this;
        }
    }

    public static List<Integer> branchSums(BinaryTree root) {
        List<Integer> sums = new ArrayList<>();
        calculateBranchSums(root, 0, sums);
        return sums;
    }

    private static void calculateBranchSums(BinaryTree node, int runningSum, List<Integer> sums) {
        if (node == null) return;

        int newRunningSum = runningSum + node.value;

        //check if leaf node
        if (node.left == null && node.right == null) {
            sums.add(newRunningSum);
            return;
        }

        calculateBranchSums(node.left, newRunningSum, sums);
        calculateBranchSums(node.right, newRunningSum, sums);
    }
}

