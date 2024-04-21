class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    public MyMinHeap() {
        this.heap = new MyArrayList<>();
    }

    public void insert(T element) {
        heap.add(element);
        traverseUp(heap.size() - 1);
    }

    public T extractMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = heap.get(0);
        T lastElement = heap.get(heap.size() - 1);
        heap.remove(heap.size() - 1);
        if (!empty()) {
            heap.set(0, lastElement);
            heapify(0);
        }
        return min;
    }

    public T getMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    public boolean empty() {
        return heap.size() == 0;
    }

    public int size() {
        return heap.size();
    }

    private void heapify(int index) {
        int leftChild = leftChildOf(index);
        int rightChild = rightChildOf(index);
        int smallest = index;
        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChild;
        }
        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChild;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void traverseUp(int index) {
        int parentIndex = parentOf(index);
        while (index > 0 && heap.get(parentIndex).compareTo(heap.get(index)) > 0) {
            swap(parentIndex, index);
            index = parentIndex;
            parentIndex = parentOf(index);
        }
    }

    private int leftChildOf(int index) {
        return index * 2 + 1;
    }

    private int rightChildOf(int index) {
        return index * 2 + 2;
    }

    private int parentOf(int index) {
        if (index == 0) {
            throw new IllegalStateException("Root has no parent");
        }
        return (index - 1) / 2;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
