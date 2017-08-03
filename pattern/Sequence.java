package pattern;

import java.util.Arrays;

/**
 * Created by apatters on 8/1/2017.
 */
public class Sequence<T> {
    T[] sequence;

    public Sequence(T[] sequence){
        this.sequence = sequence;
    }


    public int findSubsegRK(T[] subseg){
        int len = subseg.length;
        int nHashes = sequence.length-subseg.length+1;

        int subsegHash = rabinFingerPrint(subseg);

        int hash = 0;
        for(int i=0; i<nHashes; i++){

            if(i==0){
                hash = rabinFingerPrint(sequence, 0, subseg.length);
            }else{
                T removeValue = sequence[i-1];
                T addValue = sequence[i-1+len];
                hash = rabinFingerPrint(hash, len, removeValue, addValue);
            }

            if(hash==subsegHash && compareSeqs(sequence, subseg, i)){
                return i;
            }
        }
        return -1;
    }

    private Boolean compareSeqs(T[] longSeq, T[] smallSeq, int offset){
        for(int i=0; i<smallSeq.length; i++){
            if(longSeq[i+offset]!=smallSeq[i]){
                return false;
            }
        }
        return true;
    }

    private int rabinFingerPrint(T[] subseq){
        return rabinFingerPrint(subseq, 0, subseq.length);
    }

    private int rabinFingerPrint(T[] subseq, int start, int len){
        int total = 0;
        int coef, exp;
        for(int i=start; i<start+len; i++){
            coef = (int)(Integer)subseq[i];
            exp = (len+start-1-i);
            total+=coef*Math.pow(101,exp);
        }
        return total;
    }

    private int rabinFingerPrint(int fingerPrint, int len, T removeValue, T addValue){

        return (int) (101*(fingerPrint - (int)(Integer)removeValue*Math.pow(101,len-1)) + (int)(Integer)addValue);
    }

}
