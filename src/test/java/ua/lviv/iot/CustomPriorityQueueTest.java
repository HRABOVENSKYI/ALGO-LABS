package ua.lviv.iot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomPriorityQueueTest {

    private static CustomPriorityQueue QUEUE;

    @Before
    public void setUp() {
        QUEUE = new CustomPriorityQueue();
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(QUEUE.isEmpty());
        QUEUE.add(0);
        Assert.assertFalse(QUEUE.isEmpty());
    }

    @Test
    public void testIsFull() {
        QUEUE = new CustomPriorityQueue(1);
        Assert.assertFalse(QUEUE.isFull());
        QUEUE.add(0);
        Assert.assertTrue(QUEUE.isFull());
    }

    @Test
    public void testAdd() {
        QUEUE.add(2);
        QUEUE.add(1);
        QUEUE.add(4);
        QUEUE.add(-1);
        QUEUE.add(0);

        StringBuilder elements = new StringBuilder();
        QUEUE.forEach(el -> elements.append(el).append(" "));
        String actual = elements.toString();

        String expected = "4 2 1 0 -1 ";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPoll() {
        QUEUE.add(2);
        QUEUE.add(1);
        QUEUE.add(4);
        QUEUE.add(-1);
        QUEUE.add(0);

        Assert.assertEquals(-1, QUEUE.poll());
        Assert.assertEquals(0, QUEUE.poll());
        Assert.assertEquals(1, QUEUE.poll());
        Assert.assertEquals(2, QUEUE.poll());
        Assert.assertEquals(4, QUEUE.poll());

        Assert.assertTrue(QUEUE.isEmpty());

        Assert.assertNull(QUEUE.poll());
        Assert.assertNull(QUEUE.poll());
    }

    @Test
    public void testPeek() {
        QUEUE.add(2);
        QUEUE.add(1);

        Assert.assertEquals(1, QUEUE.peek());
        Assert.assertEquals(1, QUEUE.peek());
    }

    @Test
    public void testRemove() {
        QUEUE.add(2);
        QUEUE.add(-1);

        Assert.assertTrue(QUEUE.remove(-1));
        Assert.assertTrue(QUEUE.remove(2));
        Assert.assertFalse(QUEUE.remove(-1));
        Assert.assertFalse(QUEUE.remove(-2));

        Assert.assertTrue(QUEUE.isEmpty());
    }
}
