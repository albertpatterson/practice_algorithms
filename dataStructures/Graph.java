package dataStructures;

import utils.IndexableComparable;

import java.util.ArrayList;

/**
 * Created by apatters on 7/16/2017.
 */
public class Graph {

    public ArrayList<Vertex<Integer>> vertices = new ArrayList<>();

    public void addVertex(int idx){
        Vertex<Integer> v = new Vertex<>(idx);
        vertices.add(idx, v);
    }

    public void addEdge(int headIdx, int tailIdx, int weight){
        Vertex head = vertices.get(headIdx);
        Vertex tail = vertices.get(tailIdx);
        head.addEdge(tail.key, weight);
    }

    public OptPaths findShortestPathDijkstra(int fromNode){

        int nVertices = vertices.size();
        int[] distances = new int[nVertices];
        int[] previousVertices = new int[nVertices];

        class ComparableByDistance extends IndexableComparable<Integer, ComparableByDistance> {

            ComparableByDistance(int idx){
                this.idx=idx;
            }

            @Override
            public int compareTo(ComparableByDistance o) {
                if(distances[idx]<distances[(int)o.idx]){
                    return -1;
                }else if(distances[idx]>distances[(int)o.idx]){
                    return 1;
                }else{
                    return 0;
                }
            }
        }

        MinPriorityQueue<Integer, ComparableByDistance> vertexQ = new MinPriorityQueue<>();

        int distance;
        int previous;
        ComparableByDistance comparableByDistance;


        for(int idx = 0; idx<nVertices; idx++){

            if(idx==fromNode){
                distance = 0;
                previous = fromNode;
            }else{
                distance=Integer.MAX_VALUE;
                previous=-1;
            }
            comparableByDistance = new ComparableByDistance(idx);
            distances[idx] = distance;
            previousVertices[idx]=previous;
            vertexQ.insert(comparableByDistance);
        }

        while(!vertexQ.isEmpty()){

            int closestIdx = vertexQ.extractMin();
            Vertex vertex = vertices.get(closestIdx);

            int successorIdx;
            int pathWeight;
            int updatedDist;
            ArrayList<Path> edgeList = vertex.edgeList;
            for(int idx=0; idx<edgeList.size(); idx++){
                successorIdx = (int)edgeList.get(idx).tail;
                pathWeight = edgeList.get(idx).weight;
                updatedDist = distances[closestIdx] + pathWeight;
                if((updatedDist >0) && (updatedDist < distances[successorIdx])){
                    distances[successorIdx] = updatedDist;
                    previousVertices[successorIdx] = closestIdx;
                    vertexQ.update(successorIdx);
                }
            }
        }
        return new Graph.OptPaths(distances, previousVertices);
    }

    public static class OptPaths{
        public int[] distances;
        public int[] previousVertices;

        OptPaths(int[] distances, int[] previousVertices){
            this.distances = distances;
            this.previousVertices = previousVertices;
        }
    }
}

class Path<V>{
    public V tail;
    public int weight;
    Path(V tail, int weight){
        this.tail = tail;
        this.weight = weight;
    }
}

class Vertex<V>{
    public V key;
    public ArrayList<Path<V>> edgeList = new ArrayList<Path<V>>();

    Vertex(V key){
        this.key = key;
    }

    void addEdge(V tail, int weight){
        edgeList.add(new Path(tail, weight));
    }
}
