package deques;

public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    Node<T> front;
    Node<T> back;

    public LinkedDeque() {
        size = 0;
        back = null;
        front = null;
    }

    static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public void addFirst(T item) {
        Node addFir = new Node(item);
        if (size == 0) {
            back = addFir;
        } else {
            front.prev = addFir;
        }
        addFir.next = front;
        front = addFir;
        size += 1;
    }

    public void addLast(T item) {
        Node addLas = new Node(item);
        if (size == 0) {
            front = addLas;
        } else {
            back.next = addLas;
        }
        addLas.prev = back;
        back = addLas;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T firVal = front.value;
        if (front.next == null) {
            front = null;
            back = null;
        } else {
            front.next.prev = null;
            front = front.next;
        }
        size -= 1;
        return firVal;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T lasVal = back.value;
        if (back.prev == null) {
            back = null;
            front = null;
        } else {
            back.prev.next = null;
            back = back.prev;
        }
        size -= 1;
        return lasVal;
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        Node<T> curr = front;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    public int size() {
        return size;
    }
}
