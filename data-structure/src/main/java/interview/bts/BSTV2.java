package interview.bts;

public class BSTV2 {

    public int value;
    public BSTV2 left;
    public BSTV2 right;

    public BSTV2(int value) {
        this.value = value;
    }

    public BSTV2 insert(int value) {
        if (value < this.value) {
            if (left == null) {
                left = new BSTV2(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new BSTV2(value);
            } else {
                right.insert(value);
            }
        }
        return this;
    }

    public boolean contains(int value) {
        if (value == this.value) {
            return true;
        } else if (value < this.value) {
            if (left == null) {
                return false;
            } else {
                return left.contains(value);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }

    public BSTV2 remove(int value) {
        remove(value, null);
        return this;
    }

    private void remove(int value, BSTV2 parentNode) {
        if (value < this.value) {
            if (left != null) {
                left.remove(value, this);
            }
        } else if (value > this.value) {
            if (right != null) {
                right.remove(value, this);
            }
        } else {
            if (left != null && right != null) {
                this.value = right.getMinValue();
                right.remove(this.value, this);
            } else if (parentNode == null) {
                if (left != null) {
                    this.value = left.value;
                    right = left.right;
                    left = left.left;
                } else if (right != null) {
                    this.value = right.value;
                    left = right.left;
                    right = right.right;
                }
            } else if (parentNode.left == this) {
                parentNode.left = left != null ? left : right;
            } else if (parentNode.right == this) {
                parentNode.right = left != null ? left : right;
            }
        }
    }

    private int getMinValue() {
        return left == null ? value : left.getMinValue();
    }

}
