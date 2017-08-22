package dynamicProgramming;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by apatters on 8/10/2017.
 */
public class DynamicProgrammingProblems {

    /**
     * count the number of paths up stiars taking 1 2 or 3 step at a time, using only recursion
     * @param nStairs
     * @return
     */
    public int countPathsRec(int nStairs){
        if(nStairs<=0){
            return 0;
        }else if(nStairs == 1){
            return 1;
        }else if(nStairs == 2){
            return 2;
        }else if(nStairs == 3){
            return 4;
        }else{
            return countPathsRec(nStairs-1)+countPathsRec(nStairs-2)+countPathsRec(nStairs-3);
        }
    }

    public int countPathsMem(int nStairs){
        return new MemoPathCounter(nStairs).countPathsMem();
    }

    public int countPathsDyn(int nStairs){
        int[] prevCounts = {1, 2, 4};
        if(nStairs<=0) {
            return 0;
        }else if(nStairs<4){
            return prevCounts[nStairs-1];
        }else{
            return countPathsDyn(nStairs-3, prevCounts);
        }
    }

    private int countPathsDyn(int remSteps, int[] prevCounts){
        int sum = 0;
        for(int idx=1; idx<=remSteps; idx++){
            sum = prevCounts[0]+prevCounts[1]+prevCounts[2];
            prevCounts[0]=prevCounts[1];
            prevCounts[1]=prevCounts[2];
            prevCounts[2]=sum;
        }
        return sum;
    }

    class MemoPathCounter{

        private int nStairs;
        private int[] pathCounts;

        public MemoPathCounter(int nStairs){
            this.nStairs = nStairs;
            if(nStairs>=3){
                pathCounts = new int[nStairs+1];
                pathCounts[0] = 0;
                pathCounts[1] = 1;
                pathCounts[2] = 2;
                pathCounts[3] = 4;
            }
        }

        public int countPathsMem(){
            if(nStairs<=0){
                return 0;
            }else if(nStairs == 1){
                return 1;
            }else if(nStairs == 2){
                return 2;
            }else if(nStairs == 3){
                return 4;
            }else{
                return countPathsMem(nStairs);
            }
        }

        private int countPathsMem(int nRem){
            if(nRem>0){
                if(pathCounts[nRem] == 0) {
                    pathCounts[nRem] = countPathsMem(nRem-1)+countPathsMem(nRem-2)+countPathsMem(nRem-3);
                }
                return pathCounts[nRem];
            }else{
                return 0;
            }
        }
    }

    public int optimizeKnapsackRec(int[] weights, int[] values, int totalWeight){
        if(weights.length != values.length){
            throw new Error("Weight and value count mismatch");
        }
        return optimizeKnapsackRec(weights, values, weights.length, totalWeight);
    }

    private int optimizeKnapsackRec(int[] weights, int[] values, int nItemsConsidered, int totalWeight){
        if(totalWeight<0 || nItemsConsidered==0){
            return 0;
        }

        int valueWith, valueWithout = optimizeKnapsackRec(weights, values, nItemsConsidered-1, totalWeight);
        if(totalWeight-weights[nItemsConsidered-1]>=0){
            valueWith = optimizeKnapsackRec(weights, values, nItemsConsidered-1, totalWeight-weights[nItemsConsidered-1])+values[nItemsConsidered-1];
        }else{
            valueWith = -1;
        }

        return Math.max(valueWith, valueWithout);
    }

    public int optimizeKnapsackMem(int [] weights, int[] values, int totalWeight){
        if(weights.length != values.length){
            throw new Error("Weight and value count mismatch");
        }
        int n = weights.length;
        int[][] memo = new int[n+1][totalWeight+1];
        return optimizeKnapsackMem(weights, values, weights.length, memo, totalWeight);
    }

    private int optimizeKnapsackMem(int[] weights, int[] values, int nItemsConsidered, int[][] memo, int totalWeight){
        if(nItemsConsidered==0 || totalWeight<=0){
            return 0;
        }

        if(memo[nItemsConsidered][totalWeight]>0){
            return memo[nItemsConsidered][totalWeight];
        }

        int valueWith, valueWithout = optimizeKnapsackMem(weights, values, nItemsConsidered -1, memo, totalWeight);
        if(totalWeight>=weights[nItemsConsidered-1]){
            valueWith = optimizeKnapsackMem(weights, values, nItemsConsidered -1, memo, totalWeight-weights[nItemsConsidered-1])+values[nItemsConsidered-1];
        }else{
            valueWith = -1;
        }

        int max = Math.max(valueWith, valueWithout);
        memo[nItemsConsidered][totalWeight] = max;

        return max;
    }

    public int optimizeKnapsackIter(int [] weights, int[] values, int totalWeight){
        if(weights.length != values.length){
            throw new Error("Weight and value count mismatch");
        }
        int nItemsMax = weights.length;
        int[][] maxValues = new int[nItemsMax+1][totalWeight+1];

        for(int nItems=0; nItems <= nItemsMax; nItems++){
            for(int maxWeight=0; maxWeight <= totalWeight; maxWeight++){
                if(nItems==0 || maxWeight ==0){
                    maxValues[nItems][maxWeight] = 0;
                }else{
                    int valueWith, valueWithout = maxValues[nItems-1][maxWeight];
                    if(maxWeight>=weights[nItems-1]){
                        valueWith = maxValues[nItems-1][maxWeight-weights[nItems-1]]+values[nItems-1];
                    }else{
                        valueWith = -1;
                    }
                    maxValues[nItems][maxWeight] = Math.max(valueWith, valueWithout);
                }
            }
        }
        return maxValues[nItemsMax][totalWeight];
    }


    public int minMatrixOps(int[] dims){

        // dims records the rows in the first matrix and the columns in each matrix, corresponding to the index
        int n = dims.length-1;
        int[][] minVals = new int[n+1][n+1];

        return minMatrixOps(dims, minVals, 1, n);
    }

    private int minMatrixOps(int[] dims, int[][] minVals, int first, int last){
        if(first==last){
            return 0;
        }else if(minVals[first][last]!=0){
            return minVals[first][last];
        }

        int testVal, minVal = Integer.MAX_VALUE;

        for(int k = first; k<last; k++){
            testVal = minMatrixOps(dims, minVals, first, k) + minMatrixOps(dims, minVals,k+1, last) + dims[first-1]*dims[k]*dims[k+1];
            if(testVal<minVal){
                minVal=testVal;
            }
        }
        minVals[first][last] = minVal;
        return minVal;
    }


}

