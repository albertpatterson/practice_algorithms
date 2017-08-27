package dataStructures;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 8/27/2017.
 */
public class QueueStackTest {

    @Test
    public void pushPop(){
        QueueStack<Integer> stack = new QueueStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, (int)stack.pop());
        assertEquals(2, (int)stack.pop());
        stack.push(4);
        assertEquals(4, (int)stack.pop());
        assertEquals(1, (int)stack.pop());
    }


}