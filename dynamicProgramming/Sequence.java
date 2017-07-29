/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicProgramming;

import java.util.ArrayList;

/**
 *
 * @author apatt
 */
public class Sequence {
    
    private int[] getLongestIncreasingSubsequence(int[] sequence){
        
        ArrayList<Integer[]> subSequences = new ArrayList<>();
        Integer[] ss1 = {(Integer)sequence[0]};
        subSequences.add(ss1);
        for(int idx=1; idx < sequence.length; idx++){
            
        }
        
        int[] out = {};
        return out;
    }
    
    private int indexOfLastItemLessThanOrEqualTo(ArrayList<Integer[]> increasingSeqs, int value){
        return indexOfLastItemLessThanOrEqualTo(increasingSeqs, value, 0, increasingSeqs.size());
    }

    private int indexOfLastItemLessThanOrEqualTo(ArrayList<Integer[]> increasingSeqs, int value, int start, int stop){
        
        if(start == stop){
            Integer[] startSeq = increasingSeqs.get(start);
            Integer startSeqEnd = startSeq[startSeq.length];
            if(startSeqEnd>value){
                return start-1;
            }else{
                return start;
            }            
        }else{
            int mid = start+(stop-start)/2;            
            Integer[] midSeq = increasingSeqs.get(mid);
            Integer midSeqEnd = midSeq[midSeq.length];
            if(midSeqEnd>value){
                return indexOfLastItemLessThanOrEqualTo(increasingSeqs, value, start, mid-1);
            }else if(midSeqEnd<value){
                return indexOfLastItemLessThanOrEqualTo(increasingSeqs, value, mid+1, stop);
            }else{
                return mid;
            }
        }
        
    }
    
            
    private int[] getLongestIncreasingSubsequence(int[] sequence, int stop){
        
        ArrayList<Integer[]> sequences = new ArrayList<>();
        
        for(int idx=0; idx < stop; idx++){
            
        }
        
        int[] out = {};
        return out;
    }
    
    public static void main(String[] args){
        
    }
}
