package ua.lviv.iot;

import java.util.*;

class CustomPriorityQueue {
    private int[] heap;
    private int heapSize, capacity;

    public CustomPriorityQueue(int capacity) {
        this.capacity = capacity + 1;
        heap = new int[this.capacity];
        heapSize = 0;
    }

    public CustomPriorityQueue() {
        this(10);
    }

    public void clear() {
        heap = new int[capacity];
        capacity = 1;
        heapSize = 0;
    }

    public int size() {
        return heapSize;
    }

    public void add(int value) {
        if (capacity <= heapSize + 1) {
            capacity = (heapSize + 1) * 2;
            heap = Arrays.copyOf(heap, capacity);
        }

        heap[heapSize++] = value;
        int pos = heapSize - 1;
        while (pos != 0 && value < heap[(pos - 1) / 2]) {
            heap[pos] = heap[(pos - 1) / 2];
            pos -= 1;
            pos /= 2;
        }
        heap[pos] = value;
    }

    public int poll() {
        if (isEmpty()) {
            return 0;
        }
        int parent, child;
        int item, temp;

        item = heap[0];
        temp = heap[--heapSize];

        parent = 0;
        child = 1;
        while (child <= heapSize) {
            if (child < heapSize && heap[child] > heap[child + 1])
                child++;
            if (temp <= heap[child])
                break;

            heap[parent] = heap[child];
            parent = child;
            child *= 2;
            child += 1;
        }
        heap[parent] = temp;
        if (heapSize == 0) {
            clear();
        }

        return item;
    }

    public int peek() {
        return heap[0];
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }
}