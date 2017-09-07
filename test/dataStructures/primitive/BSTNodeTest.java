package dataStructures.primitive;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by apatters on 9/4/2017.
 */
public class BSTNodeTest {

    @Test
    public void testAdd(){
        BSTNode root = new BSTNode(50);

        root.insert(25);
        root.insert(75);
        root.insert(12);
        root.insert(6);
        root.insert(100);
        root.insert(40);
        root.insert(30);

        List<Integer> sortedVals = Arrays.asList(6, 12, 25, 30, 40, 50, 75, 100);
        ArrayList<Integer> expectedList = new ArrayList<Integer>(sortedVals);

        ArrayList<Integer> actualList = root.flatten();

        assertEquals(expectedList, actualList);
    }

    @Test
    public void testFind(){
        BSTNode root = new BSTNode(50);

        root.insert(25);
        root.insert(75);
        root.insert(12);
        root.insert(6);
        root.insert(100);
        root.insert(40);
        root.insert(30);

        boolean expectedValue = true;
        boolean actualValue = root.find(30);

        assertEquals(expectedValue, actualValue);

        expectedValue = false;
        actualValue = root.find(31);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testRemove(){
        BSTNode root = new BSTNode(50);

        root.insert(25);
        root.insert(75);
        root.insert(12);
        root.insert(6);
        root.insert(100);
        root.insert(40);
        root.insert(30);


        root=root.delete(50);
        List<Integer> expectedList = Arrays.asList(6, 12, 25, 30, 40, 75, 100);

        ArrayList<Integer> sortedVals = new ArrayList<Integer>(expectedList);
        ArrayList<Integer> actualList = root.flatten();
        assertEquals(expectedList, actualList);

        root = root.delete(100);
        root=root.delete(6);
        root=root.delete(12);
        expectedList = Arrays.asList(25, 30, 40, 75);
        sortedVals = new ArrayList<Integer>(expectedList);
        actualList = root.flatten();
        assertEquals(expectedList, actualList);

        root=root.delete(25);
        expectedList = Arrays.asList(30, 40, 75);
        sortedVals = new ArrayList<Integer>(expectedList);
        actualList = root.flatten();
        assertEquals(expectedList, actualList);

        root=root.delete(30);
        root=root.delete(40);
        expectedList = Arrays.asList(75);
        sortedVals = new ArrayList<Integer>(expectedList);
        actualList = root.flatten();
        assertEquals(expectedList, actualList);

        root=root.delete(75);
        assertEquals(null, root);
    }
}