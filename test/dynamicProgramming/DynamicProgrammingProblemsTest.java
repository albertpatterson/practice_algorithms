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

    @Test
    public void optimizeKnapsackRec(){
        int[] weights = {1};
        int[] values = {1};
        int totalWeight = 1;


        int maxValue = dp.optimizeKnapsackRec(weights, values, totalWeight);
        int expValue = 1;

        assertEquals(maxValue, expValue);
    }

    @Test
    public void optimizeKnapsackMem(){
        int[] weights = {1};
        int[] values = {1};
        int totalWeight = 1;


        int maxValue = dp.optimizeKnapsackMem(weights, values, totalWeight);
        int expValue = 1;

        assertEquals(maxValue, expValue);
    }

    @Test
    public void optimizeKnapsackItr(){
        int[] weights = {1};
        int[] values = {1};
        int totalWeight = 1;


        int maxValue = dp.optimizeKnapsackIter(weights, values, totalWeight);
        int expValue = 1;

        assertEquals(maxValue, expValue);
    }

    @Test
    public void optimizeKnapsackAll(){
        int[] weights = {1,2,3,4,1,2,3};
        int[] values =  {5,4,3,2,1,5,1};
        int totalWeight = 7;


        int maxValueRec = dp.optimizeKnapsackRec(weights, values, totalWeight);
        int maxValueMem = dp.optimizeKnapsackMem(weights, values, totalWeight);
        int maxValueIter = dp.optimizeKnapsackIter(weights, values, totalWeight);


        assertEquals(maxValueRec, maxValueMem);
        assertEquals(maxValueIter, maxValueMem);
    }
}