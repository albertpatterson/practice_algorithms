package dataStructures;

import utils.IndexableComparable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apatters on 7/15/2017.
 */

public class MinPriorityQueue<U, V>{

    private HashMap<U, Integer> idxMap = new HashMap<>();
    private ArrayList<IndexableComparable<U, V>> heap = new ArrayList<>();
    private int size = 0;


    public void insert(IndexableComparable<U, V> value){

        size++;
        heap.add(size-1, value);
        idxMap.put(value.idx, size-1);
        heapifyUp(size-1);
    }

    public Boolean isEmpty(){
        return size==0;
    }

//    public IndexableComparable<U, V> peak(){
//        return heap.get(0);
//    }

    public U peak(){
    return heap.get(0).idx;
}


    public U extractMin(){
        IndexableComparable<U, V> min = heap.get(0);
        swap(0, size-1);
        idxMap.remove(min);
        size--;
        heapifyDown(0);
        return min.idx;
    }

    public void update(U key){
        Integer currentIdx = idxMap.get(key);
        heapifyDown(getParentIdx(currentIdx));
        heapifyUp(currentIdx);
    }

//    public int getIdx(U idx){
//        return idxMap.get(idx);
//    }


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
        IndexableComparable<U, V> v1 = heap.get(idx1);
        IndexableComparable<U, V> v2 = heap.get(idx2);
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
        IndexableComparable<U, V> node1 = heap.get(idx1);
        IndexableComparable<U, V> node2 = heap.get(idx2);
        return node1.compareTo((V) node2);
    }

    private Boolean isHeapValid(int parentIdx){
        if(getChildIdxs(parentIdx).left<size){
            IndexableComparable<U, V> minChild = heap.get(getMinChildIdx(parentIdx));
            return heap.get(parentIdx).compareTo((V) minChild)<=0;
        }else{
            return true;
        }
    }
}
