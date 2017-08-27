package dataStructures;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 8/27/2017.
 */
public class StackQueueTest {

    @Test
    public void pushPop(){
        StackQueue<Integer> queue = new StackQueue();

        queue.push(1);
        queue.push(2);
        queue.push(3);

        assertEquals(1, (int)queue.pop());
        assertEquals(2, (int)queue.pop());
        queue.push(4);
        assertEquals(3, (int)queue.pop());
        assertEquals(4, (int)queue.pop());
    }

}