package interview.bts;

import interview.bts.closevalue.CloseValueStrategy;

public class BST {

    public int value;
    public BST left;
    public BST right;
    private CloseValueStrategy closeValueStrategy;


    public BST(int value) {
        this.value = value;
    }

    public BST(int value, CloseValueStrategy closeValueStrategy) {
        this.value = value;
        this.closeValueStrategy = closeValueStrategy;
    }

    public BST insert(int value) {
        BST currentNode = this;

        while (true) {
            if (value < currentNode.value) {
                if (currentNode.left == null) {
                    currentNode.left = new BST(value);
                    break;
                } else {
                    currentNode = currentNode.left;
                }
            } else {
                if (currentNode.right == null) {
                    currentNode.right = new BST(value);
                    break;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }
        return this;
    }

    public boolean contains(int value) {
        BST currentNode = this;
        while (currentNode != null) {
            if (currentNode.value == value) {
                return true;
            } else if (value < currentNode.value) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return false;
    }

    public BST remove(int value) {
        remove(value, null);
        return this;
    }

    private void remove(int value, BST parentNode) {
        BST currentNode = this;
        while (currentNode != null) {
            if (value < currentNode.value) {
                parentNode = currentNode;
                currentNode = currentNode.left;
            } else if (value > currentNode.value) {
                parentNode = currentNode;
                currentNode = currentNode.right;
            } else {
                if (currentNode.left != null && currentNode.right != null) {
                    currentNode.value = currentNode.right.getMinValue();
                    currentNode.right.remove(currentNode.value, currentNode);
                } else if (parentNode == null) {
                    if (currentNode.left != null) {
                        currentNode.value = currentNode.left.value;
                        currentNode.right = currentNode.left.right;
                        currentNode.left = currentNode.left.left;
                    } else if (currentNode.right != null) {
                        currentNode.value = currentNode.right.value;
                        currentNode.left = currentNode.right.left;
                        currentNode.right = currentNode.right.right;
                    } else {
                        currentNode.value = 0;
                    }
                } else if (parentNode.left == currentNode) {
                    parentNode.left = currentNode.left != null ? currentNode.left : currentNode.right;
                } else if (parentNode.right == currentNode) {
                    parentNode.right = currentNode.left != null ? currentNode.left : currentNode.right;
                }
                break;
            }
        }
    }

    private int getMinValue() {
        return left == null ? value : left.getMinValue();
    }

    public int findClosestValueInBst(int target) {
        return closeValueStrategy.findClosestValueInBst(this, target);
    }
}
