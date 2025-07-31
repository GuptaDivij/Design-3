// Time Complexity : O(1) for next, O(n) for initialization, O(1) amortized for hasNex
// Space Complexity : O(n) for storing in stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Appraoch : I used a stack to add all elements in reverse order, hasNext is always called before next so I update nextEl to be the next integer -> if stack.pop is an integer, then I can use it, if it is a list, while the top is not an integer, I keep adding the list's elements to the stack in reverse order

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;
    private NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        nextEl = null;
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger curr = stack.pop();
            if (curr.isInteger()) {
                nextEl = curr;
                return true;
            } else {
                List<NestedInteger> list = curr.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }
        }
        return false;
    }
}
