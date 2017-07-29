/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.dataStructures;

import dataStructures.BinarySearchTree;
import dataStructures.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apatt
 */
public class BinarySearchTreeTest {
    
    public BinarySearchTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class BinarySearchTree.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Object key = null;
        Object value = null;
        Node refNode = null;
        BinarySearchTree instance = null;
        instance.add(key, value, refNode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class BinarySearchTree.
     */
    @Test
    public void testGetValue() throws Exception {
        System.out.println("getValue");
        Object key = null;
        BinarySearchTree instance = null;
        Object expResult = null;
        Object result = instance.getValue(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class BinarySearchTree.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Object key = null;
        BinarySearchTree instance = null;
        Boolean expResult = null;
        Boolean result = instance.contains(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
