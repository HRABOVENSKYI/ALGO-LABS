package ua.lviv.iot;

import java.util.*;
import java.util.function.*;

public class CustomPriorityQueue {

    private Object[] priorityQueueArr;
    private int size; // general size of Queue
    private int numOfElements; // current number of elements in Queue

    public CustomPriorityQueue(int size) {
        this.size = size;
        priorityQueueArr = new Object[this.size];
        numOfElements = 0;
    }

    public CustomPriorityQueue() {
        this(10);
    }

    public void add(int value) {
        if (isFull()) {
            size *= 2;
            priorityQueueArr = Arrays.copyOf(priorityQueueArr, size);
        }

        if (numOfElements == 0) {
            priorityQueueArr[numOfElements++] = value; // if there is no elements in queue, add value at starting pos
        } else {
            int i;

            for (i = numOfElements - 1; i >= 0; i--) {
                if (value > (int) priorityQueueArr[i]) {
                    priorityQueueArr[i + 1] = priorityQueueArr[i]; // if value is larger, shift elements to the end till value is larger
                } else {
                    break;
                }
            }

            priorityQueueArr[i + 1] = value; // add element is space created by shift
            numOfElements++;
        }
    }

    public Object poll() {
        if (isEmpty()) {
            return null;
        }
        Object removedElement = priorityQueueArr[--numOfElements];
        priorityQueueArr[numOfElements] =  null;
        return removedElement;
    }

    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1) {
            return false;
        } else {
            removeAt(i);
            return true;
        }
    }

    private void removeAt(int i) {
        int indexOfLastElement = --numOfElements;
        if (indexOfLastElement == i) {
            priorityQueueArr[i] = null;
        } else {
            for (int j = i; j < indexOfLastElement; j++) {
                priorityQueueArr[j] = priorityQueueArr[j + 1];
            }
            priorityQueueArr[indexOfLastElement] = null;
        }
    }

    public Object peek() {
        return priorityQueueArr[numOfElements - 1];
    }

    public void forEach(Consumer<Integer> action) {
        Objects.requireNonNull(action);
        for (int i = 0; i < numOfElements; i++)
            action.accept((Integer) priorityQueueArr[i]);
    }

    public boolean isFull() {
        return (numOfElements >= size);
    }

    public boolean isEmpty() {
        return (numOfElements == 0);
    }

    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < numOfElements; i++) {
                if (o.equals(priorityQueueArr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
