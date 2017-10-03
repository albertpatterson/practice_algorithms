package sorting;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by apatters on 9/30/2017.
 */
public class PairSorterTest {
    @Test
    public void optimizeCircuisTower() throws Exception {

        PairSorter ps = new PairSorter();
        ArrayList<Pair> performers = new ArrayList<>();
//        performers.add(new Pair(65,100));
//        performers.add(new Pair(70,150));
//        performers.add(new Pair(56,90));
//        performers.add(new Pair(75,190));
//        performers.add(new Pair(60,95));
//        performers.add(new Pair(68,110));

        performers.add(new Pair(10,10));
        performers.add(new Pair(5,9));
        performers.add(new Pair(10,7));
        performers.add(new Pair(10,8));


        performers.add(new Pair(10,7));
        performers.add(new Pair(4,8));

        ArrayList<Pair> towerTeam = ps.optimizeCircuisTower(performers);



    }

};