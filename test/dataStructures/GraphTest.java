package dataStructures;

import dataStructures.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 7/16/2017.
 */
public class GraphTest {

    private Graph graph;

    @Before
    public void createGraph(){
        graph = new Graph();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 10);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(3, 2, 2);
        graph.addEdge(4, 3, 3);
    }


    @Test
    public void findShortestPathDijkstra() throws Exception {
        Graph.OptPaths optPaths = graph.findShortestPathDijkstra(0);
        assertEquals(0, optPaths.distances[0]);
        assertEquals(1, optPaths.distances[1]);
        assertEquals(2, optPaths.distances[2]);
        assertEquals(3, optPaths.distances[3]);
        assertEquals(Integer.MAX_VALUE, optPaths.distances[4]);

        assertEquals(0, optPaths.previousVertices[0]);
        assertEquals(0, optPaths.previousVertices[1]);
        assertEquals(1, optPaths.previousVertices[2]);
        assertEquals(1, optPaths.previousVertices[3]);
        assertEquals(-1, optPaths.previousVertices[4]);
    }

}