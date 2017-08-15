package graphUtils;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by apatters on 8/15/2017.
 */
public class GraphUtils {

    public LinkedList<Integer> topoSort(LinkedList<LinkedList<Integer>> graph){

        int nNodes = graph.size();
        boolean[] visited = new boolean[nNodes];
        LinkedList<Integer> list = new LinkedList<>();

        for(int idx=0; idx<nNodes; idx++){
            if(!visited[idx]){
                topoSort(graph, idx, visited, list);
            }
        }

        return list;
    }

    private void topoSort(LinkedList<LinkedList<Integer>> graph, int idx, boolean[] visited, LinkedList<Integer> list){

        visited[idx] = true;

        for(Integer neighbor : graph.get(idx)){
            if(!visited[neighbor]){
                topoSort(graph, neighbor, visited, list);
            }
        }
        list.addFirst(idx);
    }
}
