package graphUtils;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by apatters on 8/15/2017.
 */
public class GraphUtilsTest {
    @Test
    public void topoSort() throws Exception {
        GraphUtils myUtils = new GraphUtils();

        LinkedList<LinkedList<Integer>> graph = new LinkedList<>();
        graph.add(0, new LinkedList<Integer>(Arrays.asList(1,2)));
        graph.add(1, new LinkedList<Integer>());
        graph.add(2, new LinkedList<Integer>(Arrays.asList(1)));
        graph.add(3, new LinkedList<Integer>(Arrays.asList(1)));
        graph.add(4, new LinkedList<Integer>(Arrays.asList(2)));

        LinkedList<Integer> actSorting = myUtils.topoSort(graph);
        LinkedList<Integer> expSorting = new LinkedList<>(Arrays.asList(4, 3, 0, 2, 1));
        assertEquals(expSorting, actSorting);
    }

}