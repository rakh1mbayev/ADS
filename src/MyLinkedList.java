import java.util.Iterator;

class MyLinkedList<T> implements MyList<T> {
    private static class MyNode<T> {
        T data;
        MyNode<T> next;
        MyNode<T> previous;

        MyNode(T data) {
            this.data = data;
        }
    }

    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        MyNode<T> node = getNode(index);
        node.data = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            addLast(item);
            return;
        }
        if (index == 0) {
            addFirst(item);
            return;
        }
        MyNode<T> newNode = new MyNode<T>(item);
        MyNode<T> current = getNode(index);
        MyNode<T> previous = current.previous;
        previous.next = newNode;
        newNode.previous = previous;
        newNode.next = current;
        current.previous = newNode;
        size++;
    }

    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        newNode.next = head;
        head = newNode;
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == 0) {
            addFirst(item);
            return;
        }
        MyNode<T> newNode = new MyNode<T>(item);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public T get(int index) {
        MyNode<T> node = getNode(index);
        return node.data;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new IndexOutOfBoundsException();
        }
        return tail.data;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            MyNode<T> previous = getNode(index - 1);
            previous.next = previous.next.next;
            size--;
        }
    }

    @Override
    public void removeFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
    }

    @Override
    public void removeLast() {
        if (size <= 1) {
            removeFirst();
            return;
        }
        MyNode<T> previous = getNode(size - 2);
        previous.next = null;
        tail = previous;
        size--;
    }

    @Override
    public void sort() {

    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        MyNode<T> current = head;
        while(current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        MyNode<T> current = tail;
        while (current != null) {
            if (current.data.equals(object)) {
                return index + 1;
            }
            current = getNode(index--);
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        MyNode<T> current = head;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };

    }
    private MyNode<T> getNode(int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException();
        }
        MyNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}