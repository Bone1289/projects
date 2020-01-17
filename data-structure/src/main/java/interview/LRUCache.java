package interview;

/*
 * Implement a class for a Least Recently Used (LRU) Cache. The cache should support inserting key / value pairs
 * (the insertKeyValuePair() method), retrieving a key's value (the getValueFromKey() method), and retrieving the most
 * recently "active" key (the getMostRecentKey() method); each of these methods should run in constant time.
 * When a key / value pair is inserted or a key's value is retrieved, the key in question should become the most recent key.
 * Also, the LRUCache class should store a maxSize property set to the size of the cache, which is passed in as an argument
 * during instantiation. This size represents the maximum number of key / value pairs that the cache can hold at once.
 * If a key / value pair is added to the cache when it has reached maximum capacity, the least recently used ("active")
 * key / value pair should be evicted from the cache and no longer retrievable; the newly added key / value pair should
 * effectively replace it. Inserting a key / value pair with an already existing key should simply replace the key's
 * value in the cache with the new value and should not evict a key / value pair if the cache is full. Attempting to retrieve
 * a value from a key that is not in the cache should return the None (null) value.
 */


import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    Map<K, Node<K, V>> cache = new HashMap<>();

    private int maxSize;
    private DoublyLinkedList<K, V> listOfMostRecent = new DoublyLinkedList<>();

    public LRUCache(int maxSize) {
        this.maxSize = Math.max(maxSize, 1);
    }

    public void insertKeyValuePair(K key, V value) {
        if (!cache.containsKey(key)) {
            if (cache.size() == maxSize) {
                evictLeastRecent();
            }
            cache.put(key, new Node<>(key, value));
        } else {
            replaceKey(key, value);
        }
        updateMostRecent(cache.get(key));
    }

    public LRUResult getValueFromKey(K key) {
        if (!cache.containsKey(key)) {
            return new LRUResult(false, -1);
        }
        updateMostRecent(cache.get(key));
        return new LRUResult(true, cache.get(key).value);
    }

    private void evictLeastRecent() {
        K keyToRemove = listOfMostRecent.tail.key;
        listOfMostRecent.removeTail();
        cache.remove(keyToRemove);
    }

    private void updateMostRecent(Node<K, V> node) {
        listOfMostRecent.setHeadTo(node);
    }

    private void replaceKey(K key, V value) {
        if (!this.cache.containsKey(key)) {
            return;
        }
        cache.get(key).value = value;
    }

    public static class Node<K, V> {
        private K key;
        private V value;

        private Node<K, V> next, prev;

        public Node(K key, V data) {
            this.key = key;
            this.value = data;
        }

        public void removeBinding() {
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            prev = null;
            next = null;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private class DoublyLinkedList<T, V> {
        Node<T, V> head = null;
        Node<T, V> tail = null;

        public void setHeadTo(Node<T, V> node) {
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
                head.next = head;
                head = node;
            }
        }

        public void removeTail() {
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

    class LRUResult<V> {
        boolean found;
        V value;

        public LRUResult(boolean found, V value) {
            this.found = found;
            this.value = value;
        }
    }
}
