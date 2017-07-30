/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicProgramming;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author apatt
 */
public class Sequence {

    int[] sequence;

    public Sequence(int[] sequence){
        this.sequence = sequence;
    }

//    private int[] getLongestIncreasingSubsequence(int[] sequence){
//
//        ArrayList<Integer[]> subSequences = new ArrayList<>();
//        Integer[] ss1 = {(Integer)sequence[0]};
//        subSequences.add(ss1);
//        for(int idx=1; idx < sequence.length; idx++){
//
//        }
//
//        int[] out = {};
//        return out;
//    }
//
//    private int indexOfLastItemLessThanOrEqualTo(ArrayList<Integer[]> increasingSeqs, int value){
//        return indexOfLastItemLessThanOrEqualTo(increasingSeqs, value, 0, increasingSeqs.size());
//    }

//    private int indexOfLastItemLessThanOrEqualTo(ArrayList<Integer[]> increasingSeqs, int value, int start, int stop){
//
//        if(start == stop){
//            Integer[] startSeq = increasingSeqs.get(start);
//            Integer startSeqEnd = startSeq[startSeq.length];
//            if(startSeqEnd>value){
//                return start-1;
//            }else{
//                return start;
//            }
//        }else{
//            int mid = start+(stop-start)/2;
//            Integer[] midSeq = increasingSeqs.get(mid);
//            Integer midSeqEnd = midSeq[midSeq.length];
//            if(midSeqEnd>value){
//                return indexOfLastItemLessThanOrEqualTo(increasingSeqs, value, start, mid-1);
//            }else if(midSeqEnd<value){
//                return indexOfLastItemLessThanOrEqualTo(increasingSeqs, value, mid+1, stop);
//            }else{
//                return mid;
//            }
//        }
//
//    }

    private MatchAndNeighbors findEndMatchAndNeighbors(int value, ArrayList<int[]> subseqs){
        return findEndMatchAndNeighbors(value, subseqs, 0, subseqs.size()-1);
    }

    private MatchAndNeighbors findEndMatchAndNeighbors(int value, ArrayList<int[]> subseqs, int start, int stop){
        if(start == stop){
            int[] compSubSeq = subseqs.get(start);
            int endIdx = compSubSeq.length-1;
            int higher = subseqs.size() >= (start+1+1) ? start+1 : -1;

            // the end value of the seq at start is a match
            if(compSubSeq[endIdx] == value){
                return new MatchAndNeighbors(start-1, start, higher);
                // the end value of the seq at start is lower
            }else if(compSubSeq[endIdx] < value) {
                return new MatchAndNeighbors(start, -1, higher);
            // the end value of the seq at start is higher
            }else{
                return new MatchAndNeighbors(start-1, -1, start);
            }
        }else{
            int mid = start+(stop-start)/2;

            int[] compSubSeq = subseqs.get(mid);
            int endIdx = compSubSeq.length-1;

            if(compSubSeq[endIdx] > value){
                int newStop = start==mid ? start : mid - 1;
                return findEndMatchAndNeighbors(value, subseqs, start, newStop);
            } else if (compSubSeq[endIdx] < value){
                int newStart = stop==mid ? stop : mid + 1;
                return findEndMatchAndNeighbors(value, subseqs, newStart, stop);
            }else{
                return findEndMatchAndNeighbors(value, subseqs, mid, mid);
            }
        }
    }

    
            
    public int[] getLongestIncreasingSubsequence(){
        
        ArrayList<int[]> subseqs = new ArrayList<>();
        MatchAndNeighbors matchAndNeighbors;
        int matchIdx, lowerIdx, higherIdx, value, len;
        int[] newSubseq;

        int[] intialSubset ={sequence[0]};
        subseqs.add(0, intialSubset);
        for(int idx=1; idx < sequence.length; idx++){
            value = sequence[idx];
            matchAndNeighbors = findEndMatchAndNeighbors(value, subseqs);
            matchIdx = matchAndNeighbors.match;
            lowerIdx = matchAndNeighbors.lower;
            higherIdx = matchAndNeighbors.higher;


            int oppSubseq[];
            if(matchIdx==-1){
                if(lowerIdx==-1){
                    oppSubseq = subseqs.get(higherIdx);
                    len = oppSubseq.length;
                    if(len<2 || oppSubseq[len-2]<value){
                        oppSubseq[len-1] = value;
                    }
                // there is at least 1 subseq with the end lower than value
                }else{

                    oppSubseq = subseqs.get(lowerIdx);
                    len = oppSubseq.length;
                    newSubseq = Arrays.copyOf(oppSubseq, oppSubseq.length+1);
                    newSubseq[len]=value;

                    // there is no subseq with the end equal to or greater than value but there is at least one subseq
                    // whose end is less than value, a new subseq should be created
                    if(higherIdx == -1){
                        subseqs.add(newSubseq);

                    // there is a subseq with the end greater than value and there is a subseq
                    // whose end is less than value, the subseq ending with the higher value should be updated
                    }else{
                        subseqs.set(higherIdx, newSubseq);
                    }
                }

            // there is a subsequence ending in value
            }else{
                // it does not matter if there is a subseq ending in a lower value
                // if there is no subseq ending in a higher value, do nothing

                // update the end the subseg ending in the higher value if possible
                if(higherIdx!=-1){
                    oppSubseq = subseqs.get(higherIdx);
                    len = oppSubseq.length;
                    if(len<2 || oppSubseq[len-2]<value){
                        oppSubseq[len-1] = value;
                    }
                }
            }

        }
        
        return subseqs.get(subseqs.size()-1);
    }
}

class MatchAndNeighbors{
    int lower;
    int match;
    int higher;
    MatchAndNeighbors(int lower, int match, int higher){
        this.lower = lower;
        this.match = match;
        this.higher = higher;
    }
}