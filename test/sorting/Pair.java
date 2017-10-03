package sorting;

import java.util.Comparator;

/**
 * Created by apatters on 9/30/2017.
 */
public class Pair{
    int first;
    int second;

    static FirstComparator firstComp = new FirstComparator();
    static SecondComparator secondComp = new SecondComparator();
    static DoubleComparator doubleComp = new DoubleComparator();

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}


class FirstComparator implements Comparator<Pair>{

    @Override
    public int compare(Pair p1, Pair p2) {

        if(p1.first>p2.first){
            return -1;
        }else if(p1.first<p2.first){
            return 1;
        }else{
            return 0;
        }
    }
}

class SecondComparator implements Comparator<Pair>{

    @Override
    public int compare(Pair p1, Pair p2) {

        if(p1.second>p2.second){
            return -1;
        }else if(p1.second<p2.second){
            return 1;
        }else{
            return 0;
        }
    }
}

class DoubleComparator implements Comparator<Pair>{

    @Override
    public int compare(Pair p1, Pair p2) {

        if(p1.first>=p2.first && p1.second>p2.second){
            return -1;
        }else if(p1.first>p2.first && p1.second>=p2.second){
            return -1;
        }else if(p1.first<=p2.first && p1.second<p2.second){
            return 1;
        }else if(p1.first<p2.first && p1.second<=p2.second){
            return 1;
        }else{
            return 0;
        }
    }
}


//class PairComparator implements Comparator<Pair>{
//
//    @Override
//    public int compare(Pair p1, Pair p2) {
//
//        if(p1.first>=p2.first && p1.second>p2.second){
//            return 1;
//        }else if(p1.first>p2.first && p1.second>=p2.second){
//            return 1;
//        }else if(p1.first<=p2.first && p1.second<p2.second){
//            return -1;
//        }else if(p1.first<p2.first && p1.second<=p2.second){
//            return -1;
//        }else{
//            return 0;
//        }
//    }
//}