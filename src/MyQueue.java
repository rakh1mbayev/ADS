class MyQueue<T extends Comparable<T>> {
    private MyLinkedList<T> list;

    public MyQueue() {
        this.list = new MyLinkedList<>();
    }

    public void enqueue(T element) {
        list.add(element);
    }

    public T dequeue() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }

    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return (T) list.get(0);
    }

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}
