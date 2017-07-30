package dynamicProgramming;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 7/30/2017.
 */
public class SequenceTest {

    private Sequence sequence;

    @Before
    public void createSeq(){
        int[] ints = {1,4,67, 67, 68, 70, 8,3,6,2,90,3,58, 110, 1,2,3,4,5};
        sequence = new Sequence(ints);
    }


    @Test
    public void getLongestIncreasingSubsequence() throws Exception {
        int[] longestSubseq = sequence.getLongestIncreasingSubsequence();
        int[] expSeq = {1,4,67,68,70,90,110};
        int expLen = expSeq.length;
        int actLen = longestSubseq.length;
        assertEquals(expLen, actLen);
        if(expLen == actLen){
            for(int idx = 0; idx<expLen; idx++){
                assertEquals(expSeq[idx], longestSubseq[idx]);
            }
        }
    }
}