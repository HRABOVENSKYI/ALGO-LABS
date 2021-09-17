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
    public void testAdd() {
        // first element
        QUEUE.add(2);
        Assert.assertEquals(2, QUEUE.peek());
        Assert.assertEquals(1, QUEUE.size());

        // second (with bigger priority)
        QUEUE.add(-3);
        Assert.assertEquals(-3, QUEUE.peek());
        Assert.assertEquals(2, QUEUE.size());

        // third (with smaller priority)
        QUEUE.add(4);
        Assert.assertEquals(-3, QUEUE.peek());
        Assert.assertEquals(3, QUEUE.size());
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

        // when empty
        Assert.assertEquals(0, QUEUE.poll());
        Assert.assertEquals(0, QUEUE.poll());
    }

    @Test
    public void testPeek() {
        QUEUE.add(2);
        QUEUE.add(1);

        Assert.assertEquals(1, QUEUE.peek());
        Assert.assertEquals(1, QUEUE.peek());
    }
}
