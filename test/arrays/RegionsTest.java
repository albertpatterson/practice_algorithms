package arrays;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 11/3/2017.
 */
public class RegionsTest {
    @Test
    public void getReqionSizes() throws Exception {
        Regions regions = new Regions();

        boolean[][] map = {{true, false, true},{true, true, true},{false, false, false},{true, false, true}};
        int[] sizes = regions.getReqionSizes(map);
        assertArrayEquals(new int[]{5,1,1}, sizes);
    }

}