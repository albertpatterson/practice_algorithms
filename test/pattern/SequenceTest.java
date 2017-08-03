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

        Integer[] fewInts = {4,5,6,7};
        assertEquals(15, seq.findSubsegRK(fewInts));

        fewInts[3]=17;
        assertEquals(-1, seq.findSubsegRK(fewInts));
    }
}