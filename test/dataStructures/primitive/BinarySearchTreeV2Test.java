package dataStructures.primitive;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by apatters on 9/3/2017.
 */
public class BinarySearchTreeV2Test {


    @Test
    public void testAdd(){
        Node root = new Node(50);
        BinarySearchTreeV2 BST = new BinarySearchTreeV2(root);

        BST.insert(25);
        BST.insert(75);
        BST.insert(12);
        BST.insert(6);
        BST.insert(100);
        BST.insert(40);
        BST.insert(30);

        List<Integer> sortedVals = Arrays.asList(6, 12, 25, 30, 40, 50, 75, 100);
        ArrayList<Integer> expectedList = new ArrayList<Integer>(sortedVals);

        ArrayList<Integer> actualList = BST.flatten();

        assertEquals(expectedList, actualList);
    }

    @Test
    public void testFind(){
        Node root = new Node(50);
        BinarySearchTreeV2 BST = new BinarySearchTreeV2(root);

        BST.insert(25);
        BST.insert(75);
        BST.insert(12);
        BST.insert(6);
        BST.insert(100);
        BST.insert(40);
        BST.insert(30);

        int expectedValue = 30;
        int actualValue = BST.find(30);

        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void testRemove(){
        Node root = new Node(50);
        BinarySearchTreeV2 BST = new BinarySearchTreeV2(root);

        BST.insert(25);
        BST.insert(75);
        BST.insert(12);
        BST.insert(6);
        BST.insert(100);
        BST.insert(40);
        BST.insert(30);


        BST.remove(100);
        List<Integer> expectedList = Arrays.asList(6, 12, 25, 30, 40, 50, 75);

        ArrayList<Integer> sortedVals = new ArrayList<Integer>(expectedList);
        ArrayList<Integer> actualList = BST.flatten();
        assertEquals(expectedList, actualList);

        BST.remove(6);
        BST.remove(12);
        expectedList = Arrays.asList(25, 30, 40, 50, 75);
        sortedVals = new ArrayList<Integer>(expectedList);
        actualList = BST.flatten();
        assertEquals(expectedList, actualList);

        BST.remove(25);
        expectedList = Arrays.asList(30, 40, 50, 75);
        sortedVals = new ArrayList<Integer>(expectedList);
        actualList = BST.flatten();
        assertEquals(expectedList, actualList);

        BST.remove(30);
        BST.remove(40);
        BST.remove(50);
        expectedList = Arrays.asList(75);
        sortedVals = new ArrayList<Integer>(expectedList);
        actualList = BST.flatten();
        assertEquals(expectedList, actualList);

        BST.remove(75);
        expectedList = Arrays.asList();
        sortedVals = new ArrayList<Integer>(expectedList);
        actualList = BST.flatten();
        assertEquals(expectedList, actualList);
    }
    
}