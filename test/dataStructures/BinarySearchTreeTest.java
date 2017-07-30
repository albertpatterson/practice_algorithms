package dataStructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 7/30/2017.
 */
public class BinarySearchTreeTest {

    BinarySearchTree<Integer, String> bst;

    private void populateBST(){
        bst.add(5,"five");
        bst.add(2,"two");
        bst.add(7,"seven");
        bst.add(3,"three");

        bst.add(15,"fifteen");
        bst.add(12,"twelve");
        bst.add(17,"seventeen");
        bst.add(14,"fourteen");
    }

    @Before
    public void createBST(){
        bst = new BinarySearchTree<>(10, "ten");
    }

    @Test
    public void add(){
        assertEquals("ten", bst.root.value);
        assertEquals(null, bst.root.left);
        bst.add(5,"five");

        Node left = bst.root.left;
        assertNotEquals(null, left);
        assertEquals(5, left.key);
        assertEquals("five", left.value);
    }

    @Test
    public void getValue() throws Exception {
        populateBST();

        assertEquals("ten", bst.getValue(10));
        assertEquals("three", bst.getValue(3));
        assertEquals("fourteen", bst.getValue(14));
    }

    @Test
    public void contains(){
        populateBST();

        assertTrue(bst.contains(14));
        assertFalse(bst.contains(11));
    }

    @Test
    public void lookup(){
        populateBST();

        Node node3 = bst.lookup(3);
        assertEquals(3, node3.key);
        assertEquals("three", node3.value);

        Node node9 = bst.lookup(9);
        assertEquals(null, node9);
    }

    @Test
    public void delete() throws Exception {
        populateBST();

        assertTrue(bst.contains(15));
        bst.delete(15);
        assertFalse(bst.contains(15));

        assertEquals("fourteen", bst.getValue(14));

    }
}