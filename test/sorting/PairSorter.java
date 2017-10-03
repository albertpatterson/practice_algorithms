package sorting;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by apatters on 9/30/2017.
 */
public class PairSorter {

    public ArrayList<Pair> optimizeCircuisTower(ArrayList<Pair> performers){

        ArrayList<Pair> sortedPerformers = (ArrayList<Pair>)performers.clone();
        Collections.sort(sortedPerformers, Pair.firstComp);
        Collections.sort(sortedPerformers, Pair.doubleComp);
        System.out.println("height weight comp");
        for(int i =0; i<sortedPerformers.size(); i++){
            Pair performer = sortedPerformers.get(i);
            int h = performer.first;
            int w = performer.second;
            String comp = i>0 ? Integer.toString(Pair.doubleComp.compare(sortedPerformers.get(i-1), performer)) : "x";
            System.out.println(String.format("%d\t\t%d\t\t%s", h, w, comp));

//            System.out.print(Pair.comp.compare(sortedPerformers.get(i), sortedPerformers.get(i+1)));
        }



        ArrayList<Pair> team = new ArrayList<Pair>();
//        team.add(sortedPerformers.get(0));
//        for(int i=1; i<sortedPerformers.size(); i++){
//            Pair performer = sortedPerformers.get(i);
//            if(performer.compareTo(team.get(team.size()-1))==1){
//                team.add(performer);
//            }
//        }
        return team;
    }
}
