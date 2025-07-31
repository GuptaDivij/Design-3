// Time Complexity : O(1) to get and O(1) to put as add and remove are O(1) too 
// Space Complexity : O(elements) to be added in map
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Approach : I used a doubly linked list to keep a track of elements used in order of last time accessed - first node is the one which was used the last and last node is used most recently. I initialize the head and tail to be dummy with -1,-1. At each usage, I delete the node from list and then add it again, this way head is always last and tail is most recently used.

class ListNode {
    int key;
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    int capacity;
    Map<Integer, ListNode> dic;
    ListNode head;
    ListNode tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!dic.containsKey(key)) return -1;
        ListNode node = dic.get(key);
        remove(node);
        add(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (dic.containsKey(key)) {
            ListNode oldNode = dic.get(key);
            remove(oldNode);
        }
        ListNode node = new ListNode(key, value);
        dic.put(key, node);
        add(node);
        if (dic.size() > capacity) {
            ListNode nodeToDelete = head.next;
            remove(nodeToDelete);
            dic.remove(nodeToDelete.key);
        }
    }
    
    public void add(ListNode node) {
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }
    
    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */