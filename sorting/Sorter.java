package sorting;

/**
 * Created by apatters on 9/7/2017.
 */
public class Sorter {

    public int[] values;

    public Sorter(int[] values){
        this.values = values;
    }

    public void quickSort(){
        quickSort(0, values.length-1);
    }

    private void quickSort(int start, int stop) {

        int left = start;
        int right = stop;
        int pivot = getPivot(left, right);

        while(right > left){
            if((left == pivot || right == pivot) && (right-left>1)){
                int newPivot = getMean(left, right);
                swap(pivot, newPivot);
                pivot = newPivot;
            }else if(right-left==1){
                if(values[left]>values[right]){
                    pivot = pivot==left?right:left;
                    swap(left, right);
                }
                left++;
                right--;
            }else {
                boolean leftOK = values[left] < values[pivot],
                        rightOK = values[right] >= values[pivot];

                if ((!leftOK) && (!rightOK)) {
                    swap(left, right);
                    left++;
                    right--;
                } else {
                    if(leftOK){
                        left++;
                    }
                    if(rightOK){
                        right--;
                    }
                }
            }
        }

        if(stop>start){
            quickSort(start, pivot-1);
            quickSort(pivot+1, stop);
        }
    }

    private int getPivot(int left, int right){
        return getMean(left, right);//(int)Math.round(left + Math.random()*(right-left));
    }

    private int getMean(int left, int right){
        return (left+right)/2;
    }

    private void swap(int a, int b){
        int temp = values[a];
        values[a] = values[b];
        values[b] = temp;

    }



    public void bubbleSort(){
        int unsortedLen = values.length;

        while(unsortedLen>0){
            for(int idx = 0; idx<(unsortedLen-1); idx++){
                if(values[idx]>values[idx+1]){
                    swap(idx, idx+1);
                }
            }
            unsortedLen--;
        }
    }

    public void selectionSort(){
        int unsortedLen = values.length;

        while(unsortedLen>0){
            int maxIdx = 0;
            for(int idx=1; idx<unsortedLen; idx++){
                if(values[idx]>values[maxIdx]){
                    maxIdx = idx;
                }
            }
            swap(maxIdx, unsortedLen-1);
            unsortedLen--;
        }
    }

    public void insertionSort(){
        int unsortedLen = values.length-1;

        while(unsortedLen>0){
            int insertVal = values[0];
            int insertIdx = unsortedLen-1;
            for(int sortedIdx = unsortedLen; sortedIdx<values.length; sortedIdx++){
                if(insertVal>values[sortedIdx]){
                    insertIdx=sortedIdx;
                    swap(sortedIdx-1, sortedIdx);
                }else{
                    break;
                }
            }
            
            if(unsortedLen>1) {
                swap(0, insertIdx);
            }
            unsortedLen--;
        }
    }
}
