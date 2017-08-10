package dynamicProgramming;

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

}

