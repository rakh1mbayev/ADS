class MyStack<T extends Comparable<T>> {
    private MyArrayList<T> list;

    public MyStack() {
        this.list = new MyArrayList<T>();
    }

    public void push(T element) {
        list.add(element);
    }

    public T pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int lastIndex = size() - 1;
        T item = list.get(lastIndex);
        list.remove(lastIndex);
        return item;
    }

    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.get(list.size() - 1);
    }

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}
