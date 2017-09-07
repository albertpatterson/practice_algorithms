package sorting;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by apatters on 9/7/2017.
 */
public class SorterTest {
    @Test
    public void quickSort() throws Exception {

        int[] values = {1,6,8,3,5,8,0,3,6,100,-10,12,-1};
        int[] expValues = values.clone();
        Arrays.sort(expValues);
        Sorter sorter = new Sorter(values);
        sorter.quickSort();

        assertArrayEquals(expValues, sorter.values);
    }

    @Test
    public void bubbleSort() throws Exception {

        int[] values = {1,6,8,3,5,8,0,3,6,100,-10,12,-1};
        int[] expValues = values.clone();
        Arrays.sort(expValues);
        Sorter sorter = new Sorter(values);
        sorter.bubbleSort();

        assertArrayEquals(expValues, sorter.values);
    }

    @Test
    public void selectionSort() throws Exception {

        int[] values = {1,6,8,3,5,8,0,3,6,100,-10,12,-1};
        int[] expValues = values.clone();
        Arrays.sort(expValues);
        Sorter sorter = new Sorter(values);
        sorter.selectionSort();

        assertArrayEquals(expValues, sorter.values);
    }

    @Test
    public void insertionSort() throws Exception {

        int[] values = {1,6,8,3,5,8,0,3,6,100,-10,12,-1};
        int[] expValues = values.clone();
        Arrays.sort(expValues);
        Sorter sorter = new Sorter(values);
        sorter.insertionSort();

        assertArrayEquals(expValues, sorter.values);
    }
}