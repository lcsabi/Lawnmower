package util;
public class Stack<T> {

    Node<T> top;
    int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public Stack(Node<T> top) {
        this.top = top;
        size = 1;
    }

    public static class Node<T> {

        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            next = null;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private boolean isEmpty() {
        return (size == 0);
    }

    public int getSize() {
        return size;
    }

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        if (!isEmpty()) {
            temp.next = top;
        }
        top = temp;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        Node<T> temp = top;
        top = top.next;
        temp.next = null;

        return temp.value;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }

        return top.value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        Node<T> temp = top;
        while (temp != null) {
            sb.append(temp).append(" ");
            temp = temp.next;
        }
        sb.append("]"); // will always have a trailing whitespace
        return sb.toString();
    }
}
