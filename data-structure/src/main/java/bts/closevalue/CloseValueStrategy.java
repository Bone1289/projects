package bts.closevalue;

import bts.BST;

public interface CloseValueStrategy {
    int findClosestValueInBst(BST tree, int target);
}
