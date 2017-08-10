package dynamicProgramming;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 8/10/2017.
 */
public class DynamicProgrammingProblemsTest {

    DynamicProgrammingProblems dp;

    @Before
    public void setup(){
        dp = new DynamicProgrammingProblems();
    }

    @Test
    public void countPathsRec_4() throws Exception {
        int expPathsCount = 7;
        int actPathsCount = dp.countPathsRec(4);
        assertEquals(actPathsCount, expPathsCount);
    }

    @Test
    public void countPathsRec_n1() throws Exception {
        int expPathsCount = 0;
        int actPathsCount = dp.countPathsRec(-1);
        assertEquals(actPathsCount, expPathsCount);
    }

    @Test
    public void countPathsMem4() throws Exception {
        int expPathsCount = 7;
        int actPathsCount = dp.countPathsMem(4);
        assertEquals(actPathsCount, expPathsCount);
    }

    @Test
    public void countPathsMem_n1() throws Exception {
        int expPathsCount = 0;
        int actPathsCount = dp.countPathsMem(-1);
        assertEquals(actPathsCount, expPathsCount);
    }

    @Test
    public void countPathsDyn_4() throws Exception {
        int expPathsCount = 7;
        int actPathsCount = dp.countPathsDyn(4);
        assertEquals(actPathsCount, expPathsCount);
    }

    @Test
    public void countPathsDyn_n1() throws Exception {
        int expPathsCount = 0;
        int actPathsCount = dp.countPathsDyn(-1);
        assertEquals(actPathsCount, expPathsCount);
    }

    @Test
    public void countAll() throws Exception {
        int steps = 20;
        int recVal = dp.countPathsDyn(steps);
        int memVal = dp.countPathsMem(steps);
        int dynVal = dp.countPathsDyn(steps);


        assertEquals(recVal, memVal);
        assertEquals(memVal, dynVal);
    }



}