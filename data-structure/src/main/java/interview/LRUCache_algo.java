package interview;

import java.util.HashMap;
import java.util.Map;

public class LRUCache_algo {
    public static class LRUCache {
        Map<String, Node> cache = new HashMap<>();
        int maxSize;
        int currentSize = 0;
        private DoublyLinkedList listOfMostRecent = new DoublyLinkedList();

        public LRUCache(int maxSize) {
            this.maxSize = Math.max(maxSize, 1);
        }

        public String getMostRecentKey() {
            return listOfMostRecent.head.key;
        }

        public void insertKeyValuePair(String key, int value) {
            if (!cache.containsKey(key)) {
                if (currentSize == maxSize) {
                    evictLeastRecent();
                } else {
                    currentSize++;
                }
                cache.put(key, new Node(key, value));
            } else {
                replaceKey(key, value);
            }
            updateMostRecent(cache.get(key));
        }

        public LRUResult getValueFromKey(String key) {
            if (!cache.containsKey(key)) {
                return new LRUResult(false, -1);
            }
            updateMostRecent(cache.get(key));
            return new LRUResult(true, cache.get(key).value);
        }

        private void evictLeastRecent() {
            String keyToRemove = listOfMostRecent.tail.key;
            listOfMostRecent.removeTail();
            cache.remove(keyToRemove);
        }

        private void updateMostRecent(Node node) {
            listOfMostRecent.setHeadTo(node);
        }

        private void replaceKey(String key, int value) {
            if (!this.cache.containsKey(key)) {
                return;
            }
            cache.get(key).value = value;
        }
    }

    private static class Node {
        private String key;
        private int value;

        private Node next, prev;

        Node(String key, int data) {
            this.key = key;
            this.value = data;
        }

        void removeBinding() {
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            prev = null;
            next = null;
        }
    }

    private static class DoublyLinkedList {
        Node head = null;
        Node tail = null;

        void setHeadTo(Node node) {
            if (head == node) {
                return;
            } else if (head == null) {
                head = node;
                tail = node;
            } else if (head == tail) {
                tail.prev = node;
                head = node;
                head.next = tail;
            } else {
                if (tail == node) {
                    removeTail();
                }
                node.removeBinding();
                head.prev = node;
                node.next = head;
                head = node;
            }
        }

        void removeTail() {
            if (tail == null) {
                return;
            }
            if (tail == head) {
                head = null;
                tail = null;
                return;
            }

            tail = tail.prev;
            tail.next = null;
        }
    }

    public static class LRUResult {
        boolean found;
        int value;

        LRUResult(boolean found, int value) {
            this.found = found;
            this.value = value;
        }

        public boolean isFound() {
            return found;
        }

        public void setFound(boolean found) {
            this.found = found;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}