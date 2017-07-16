package graph;

import graph.IndexableComparable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apatters on 7/15/2017.
 */

public class MinPriorityQueue<U, V, T extends IndexableComparable<U, V>>{

    private HashMap<U, Integer> idxMap = new HashMap<>();
    private ArrayList<T> heap = new ArrayList<>();
    private int size = 0;


    public void insert(T value){

        size++;
        heap.add(size-1, value);
        idxMap.put(value.idx, size-1);
        heapifyUp(size-1);
    }

    public Boolean isEmpty(){
        return size==0;
    }

    public T peak(){
        return heap.get(0);
    }

    public T extractMin(){
        T min = heap.get(0);
        swap(0, size-1);
        idxMap.remove(min);
        size--;
        heapifyDown(0);
        return min;
    }

    public void update(int nodeIdx){
        heapifyDown(getParentIdx(nodeIdx));
        heapifyUp(nodeIdx);
    }

    public int getIdx(U idx){
        return idxMap.get(idx);
    }


    private static class ChildIdxs{
        int right;
        int left;

        ChildIdxs(int parentIdx){
            left = parentIdx*2+1;
            right = left+1;
        }
    }

    private ChildIdxs getChildIdxs(int parentIdx){
        return new ChildIdxs(parentIdx);
    }

    private int getParentIdx(int childIdx){
        return (childIdx-1)/2;
    }

    private int getMinChildIdx(int parentIdx){
        ChildIdxs childIdxs = getChildIdxs(parentIdx);

        if(!(childIdxs.left<size)){
            throw new Error(String.format("Node at index %d has no children", parentIdx));
        }

        int minChildIdx;
        if((childIdxs.right<size)&&(compareNodes(childIdxs.right, childIdxs.left)<0)){
            minChildIdx = childIdxs.right;
        }else{
            minChildIdx = childIdxs.left;
        }

        return minChildIdx;
    }

    private void swap(int idx1, int idx2){
        T v1 = heap.get(idx1);
        T v2 = heap.get(idx2);
        heap.set(idx1,v2);
        idxMap.replace(v2.idx, idx1);

        heap.set(idx2, v1);
        idxMap.replace(v1.idx, idx2);
    }

    private void heapifyDown(int parentIdx){
        int minChildIdx;
        while(!isHeapValid(parentIdx)){
            minChildIdx = getMinChildIdx(parentIdx);
            swap(parentIdx, minChildIdx);
            parentIdx = minChildIdx;
        }
    }

    private void heapifyUp(int childIdx){
        int parentIdx;
        while((childIdx > 0) && (compareToParent(childIdx)<0)){
            parentIdx = getParentIdx(childIdx);
            swap(childIdx, parentIdx);
            childIdx = parentIdx;
        }
    }

    private int compareToParent(int childIdx){
        int parentIdx = getParentIdx(childIdx);

        return compareNodes(childIdx, parentIdx);
    }

    private int compareNodes(int idx1, int idx2){
        T node1 = heap.get(idx1);
        T node2 = heap.get(idx2);
        return node1.compareTo((V) node2);
    }

    private Boolean isHeapValid(int parentIdx){
        if(getChildIdxs(parentIdx).left<size){
            T minChild = heap.get(getMinChildIdx(parentIdx));
            return heap.get(parentIdx).compareTo((V) minChild)<=0;
        }else{
            return true;
        }
    }

}

//public class graph.MinPriorityQueue<T, U> {
//
//    public static class KeyValue<K, V>{
//        K key;
//        V value;
//
//        KeyValue(K key, V value){
//            this.key = key;
//            this.value = value;
//        }
//    }
//
//    private ArrayList<KeyValue> heap;
//
//    public void insert(T key, U value){
//
//    }
//
//    public KeyValue peak(){
//        return this.heap
//    }
//
//}
