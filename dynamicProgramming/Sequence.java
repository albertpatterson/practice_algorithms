/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicProgramming;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import groovy.util.MapEntry;

import java.lang.reflect.Array;
import java.util.*;

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

    public ArrayList<int[]> getCombsUniqueEls(){
        ArrayList<int[]>[] memo = new ArrayList[sequence.length+1];
        memo[0]=new ArrayList<>();
        memo[0].add(new int[]{});
        return getCombsUniqueEls(sequence.length, memo);
    }

    private ArrayList<int[]> getCombsUniqueEls(int maxLen, ArrayList<int[]>[] memo){
//        if(maxLen<1){
//            return new ArrayList<>();
//        }else
        if(memo[maxLen]!=null){
            return memo[maxLen];
        }else{
            ArrayList<int[]> combs = new ArrayList<>();
            ArrayList<int[]> combPrefixs = getCombsUniqueEls(maxLen-1, memo);
            combs.addAll(combPrefixs);
            for(int[] combPrefix : combPrefixs){
                int[] comb = Arrays.copyOf(combPrefix, combPrefix.length+1);
                comb[comb.length-1]=sequence[maxLen-1];
                combs.add(comb);
            }
            memo[maxLen-1]=combs;
            return combs;
        }
    }

    public ArrayList<int[]> getCombsUniqueElsItr(){
        ArrayList<int[]> combs = new ArrayList<>();
        combs.add(new int[]{});
        for(int idx = 0; idx<sequence.length; idx++){
//            ArrayList<int[]> newCombs = new ArrayList<>();
            int combSize = combs.size();
            for(int combIdx = 0; combIdx<combSize; combIdx++){
                int[] comb = combs.get(combIdx);
                int[] combAppended = Arrays.copyOf(comb, comb.length+1);
                combAppended[combAppended.length-1]=sequence[idx];
                combs.add(combAppended);
            }
//            combs.addAll(newCombs);
        }
        return combs;
    }

    public ArrayList<int[]> getCombsItr(){

        HashMap<Integer, Integer> counts = new HashMap<>();
        for(int el : sequence){
            int putVal = counts.containsKey(el) ? counts.get(el)+1 : 1;
            counts.put(el, putVal);
        }

        ArrayList<int[]> combs = new ArrayList<>(Arrays.asList(new int[]{}));

        for(Map.Entry<Integer, Integer> entry: counts.entrySet()){
            int el = entry.getKey();
            int elMaxcount = entry.getValue();
            int combCount = combs.size();
            for(int combIdx=0; combIdx<combCount; combIdx++){
                int[] comb = combs.get(combIdx);
                int[] newComb = Arrays.copyOf(comb, comb.length+1);
                newComb[newComb.length-1]=el;
                combs.add(newComb);
                for(int elCount = 2; elCount<=elMaxcount; elCount++){
                    newComb = Arrays.copyOf(newComb, newComb.length+1);
                    newComb[newComb.length-1]=el;
                    combs.add(newComb);
                }
            }
        }



        return combs;
    }


    public ArrayList<int[]> getCombsUniqueEls(int len){

        ArrayList<int[]>[][] memo = new ArrayList[len][sequence.length];
        for(int idx=0; idx<sequence.length; idx++){
            memo[0][idx] = new ArrayList<>();
            for(int seqIdx = 0; seqIdx<=idx; seqIdx++){
                memo[0][idx].add(new int[]{sequence[seqIdx]});
            }
        }
        return getCombsUniqueEls(len, sequence.length, memo);
    }


    private ArrayList<int[]> getCombsUniqueEls(int combLen, int maxEls, ArrayList<int[]>[][] memo){
        if(combLen==0 || maxEls==0 || combLen>maxEls){
            return null;
        }
        else if(memo[combLen-1][maxEls-1]!=null){
            return memo[combLen-1][maxEls-1];
        }else{
            ArrayList<int[]> combsOfLen = new ArrayList<>();
            // add combs that include the current element
            ArrayList<int[]> prefixes = getCombsUniqueEls(combLen-1, maxEls-1, memo);
            for(int[] prefix : prefixes){
                int[] combOfLenWithItem = Arrays.copyOf(prefix, prefix.length+1);
                combOfLenWithItem[combOfLenWithItem.length-1] = sequence[maxEls-1];
                combsOfLen.add(combOfLenWithItem);
            }
            // add combs that do not include the current element
            ArrayList<int[]> combsWithoutItem = getCombsUniqueEls(combLen, maxEls-1, memo);
            if(combsWithoutItem!=null){
                combsOfLen.addAll(combsWithoutItem);
            }
            memo[combLen-1][maxEls-1] = combsOfLen;
            return combsOfLen;
        }
    }


    public ArrayList<int[]> getCombsUniqueElsItr(int len){

        ArrayList<int[]>[][] memo = new ArrayList[2][sequence.length];
        for(int idx=0; idx<sequence.length; idx++){
            memo[1][idx] = new ArrayList<>();
            for(int seqIdx = 0; seqIdx<=idx; seqIdx++){
                memo[1][idx].add(new int[]{sequence[seqIdx]});
            }
        }

        for(int curLen=2; curLen<=len; curLen++){
            memo[0] = memo[1];
            memo[1] = new ArrayList[sequence.length];
            for(int n = 0; n<sequence.length; n++){
                if(curLen>(n+1)) {
                    memo[1][n] = new ArrayList<>();
                }else{
                    ArrayList<int[]> combsOfLen = new ArrayList<>();
                    ArrayList<int[]> prefixes = memo[0][n-1];
                    for(int[] prefix : prefixes){
                        int[] combOfLen = Arrays.copyOf(prefix, prefix.length+1);
                        combOfLen[combOfLen.length-1]=sequence[n];
                        combsOfLen.add(combOfLen);
                    }
                    combsOfLen.addAll(memo[1][n-1]);
                    memo[1][n] = combsOfLen;
                }
            }
        }
        return memo[1][sequence.length-1];
    }


//    public ArrayList<int[]> getCombsUniqueEls(int len){
//
//        ArrayList<ArrayList<int[]>> memo = new ArrayList<>(new ArrayList(Arrays.asList(new int[]{})));
//        LinkedList<Integer> include = new LinkedList<>();
//        for(int el : sequence){
//            include.add(Integer.valueOf(el));
//        }
//        return getCombsUniqueEls(len, include, memo);
//    }
//
//    private ArrayList<int[]> getCombsUniqueEls(int len, LinkedList<Integer> include, ArrayList<ArrayList<int[]>> memo){
//        if(len==0){
//            return new ArrayList<>(Arrays.asList(new int[]{}));
////        }else if(memo.size()>len){
////            return memo.get(len);
//        }else{
//            ArrayList<int[]> combsOfLen = new ArrayList<>();
//            LinkedList<Integer> keep = (LinkedList<Integer>)include.clone();
//            int el;
//            while(!keep.isEmpty()) {
//                el = keep.pop();
//                ArrayList<int[]> prefixes = getCombsUniqueEls(len - 1, keep, memo);
//                for (int[] prefix : prefixes) {
//                    int[] combOfLen = Arrays.copyOf(prefix, prefix.length + 1);
//                    combOfLen[combOfLen.length - 1] = el;
//                    combsOfLen.add(combOfLen);
//                }
//            }
//            memo.add(len, combsOfLen);
//            return combsOfLen;
//        }
//    }

//    private ArrayList<int[]> getCombsUniqueEls(int maxLen, ArrayList<int[]>[] memo){
////        if(maxLen<1){
////            return new ArrayList<>();
////        }else
//        if(memo[maxLen]!=null){
//            return memo[maxLen];
//        }else{
//            ArrayList<int[]> combs = new ArrayList<>();
//            ArrayList<int[]> combPrefixs = getCombsUniqueEls(maxLen-1, memo);
//            combs.addAll(combPrefixs);
//            for(int[] combPrefix : combPrefixs){
//                int[] comb = Arrays.copyOf(combPrefix, combPrefix.length+1);
//                comb[comb.length-1]=sequence[maxLen-1];
//                combs.add(comb);
//            }
//            memo[maxLen-1]=combs;
//            return combs;
//        }
//    }

    public static void main(String[] args){

//        int[] ints = {1, 4, 9 ,1, 1, 4};
        int[] ints = {1, 2,3,4,5,6,7};

        Sequence sequence = new Sequence(ints);
        ArrayList<int[]> combs = sequence.getCombsUniqueElsItr(4);
        System.out.println(combs.size());
        for(int[] comb : combs){
            for(int el : comb){
                System.out.print(String.format("%d ", el));
            }
            System.out.println("");
        }

//        int[] ints = {1, 4, 9 ,1, 1, 4};
//        Sequence sequence = new Sequence(ints);
//        ArrayList<int[]> combs = sequence.getCombsItr();
//        System.out.println(combs.size());
//        for(int[] comb : combs){
//            for(int el : comb){
//                System.out.print(String.format("%d ", el));
//            }
//            System.out.println("");
//        }



//        int[] ints = {1,4,67, 8, 9};
//        Sequence sequence = new Sequence(ints);
//        ArrayList<int[]> combs = sequence.getCombsUniqueElsItr();
//        System.out.println(combs.size());
//        for(int[] comb : combs){
//            for(int el : comb){
//                System.out.print(String.format("%d ", el));
//            }
//            System.out.println("");
//        }
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