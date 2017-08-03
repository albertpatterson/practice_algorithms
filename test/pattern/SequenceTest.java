package pattern;

import dynamicProgramming.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 8/2/2017.
 */
public class SequenceTest {
    @Test
    public void findSubsegRK() throws Exception {
        Integer[] ints = {1,1,1,1,1,1,1,1,1,2,3,4,1,2,3,4,5,6,7,8,9};
        Sequence seq = new Sequence(ints);

        Integer[][] fewInts = {{1,1,1,1},{4,5,6,7}};
        int[] expLocs = {0, 15};
        int[] actLocs = seq.findSubsegRK(fewInts);
        assertArrayEquals(expLocs, actLocs);

        fewInts[0][0]=-1;
        expLocs[0] = -1;
        actLocs = seq.findSubsegRK(fewInts);
        assertArrayEquals(expLocs, actLocs);

        fewInts[1][0]=-1;
        expLocs[1] = -1;
        actLocs = seq.findSubsegRK(fewInts);
        assertArrayEquals(expLocs, actLocs);
    }
}