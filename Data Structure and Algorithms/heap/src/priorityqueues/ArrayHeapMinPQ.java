package priorityqueues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T /*extends Comparable<T>*/> implements ExtrinsicMinPQ<T> {
    static final int START_INDEX = 0;
    List<PriorityNode<T>> items;
    private Map<T, Integer> map;

    public ArrayHeapMinPQ() {
        map = new HashMap<>();
        items = new ArrayList<>();
    }

    // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
    // You'll probably want to add more helper methods like this one to make your code easier to read.
    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {
        PriorityNode<T> aTemp = items.get(a);
        PriorityNode<T> bTemp = items.get(b);
        map.put(aTemp.getItem(), b);
        map.put(bTemp.getItem(), a);
        items.set(a, bTemp);
        items.set(b, aTemp);
    }

    /**
     * Adds an item with the given priority value.
     * Runs in O(log N) time (except when resizing).
     * @throws IllegalArgumentException if item is null or is already present in the PQ
     */
    @Override
    public void add(T item, double priority) {
        if (item == null || map.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        PriorityNode<T> temp = new PriorityNode<>(item, priority);
        items.add(temp);
        map.put(item, items.size() - 1);
        moveTop(items.size() -1);
    }

    private void moveTop(int index) {
        if (items.get((index - 1)/2).getPriority() > items.get(index).getPriority() && index > 0) {
            swap(index, (index - 1)/2);
            moveTop((index - 1)/2);
        }
    }

    /**
     * Returns true if the PQ contains the given item; false otherwise.
     * Runs in O(log N) time.
     */
    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    /**
     * Returns the item with the least-valued priority.
     * Runs in O(log N) time.
     * @throws NoSuchElementException if the PQ is empty
     */
    @Override
    public T peekMin() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(0).getItem();
    }

    /**
     * Removes and returns the item with the least-valued priority.
     * Runs in O(log N) time (except when resizing).
     * @throws NoSuchElementException if the PQ is empty
     */
    @Override
    public T removeMin() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        T value = items.get(0).getItem();
        swap(0, items.size() - 1);
        items.remove(items.size() - 1);
        map.remove(value);
        moveBottom(0);
        return value;
    }

    private void moveBottom(int index) {
        int right = index * 2 + 2;
        int left = index * 2 + 1;
        if (right <= items.size() - 1 && left <= items.size() - 1) {
            if (items.get(index).getPriority() > items.get(left).getPriority() ||
                items.get(index).getPriority() > items.get(right).getPriority()) {
                if (items.get(left).getPriority() <= items.get(right).getPriority()) {
                    swap(index, left);
                    moveBottom(left);
                } else {
                    swap(index, right);
                    moveBottom(right);
                }
            }
        } else if ((items.size() - 1) >= left) {
            if (items.get(index).getPriority() >= items.get((items.size() - 1)).getPriority()) {
                swap(index, left);
                moveBottom(left);
            }
        }
    }

    /**
     * Changes the priority of the given item.
     * Runs in O(log N) time.
     * @throws NoSuchElementException if the item is not present in the PQ
     */
    @Override
    public void changePriority(T item, double priority) {
        if (!map.containsKey(item)) {
            throw new NoSuchElementException();
        }
        int aIndex = map.get(item);
        PriorityNode<T> changedNode = new PriorityNode<>(item, priority);
        items.set(aIndex, changedNode);
        if (items.get((aIndex - 1)/2).getPriority() <= priority) {
            moveBottom(aIndex);
        } else {
            moveTop(aIndex);
        }
    }

    /**
     * Returns the number of items in the PQ.
     * Runs in O(log N) time.
     */
    @Override
    public int size() {
        return items.size();
    }
}
