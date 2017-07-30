package dataStructures;

import dataStructures.MinPriorityQueue;
import utils.IndexableComparable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by apatters on 7/15/2017.
 */
public class MinPriorityQueueTest {

    static class Letter extends IndexableComparable<Integer, Letter> {
        public char c;

        public Letter(int idx, char c){
            this.idx=idx;
            this.c=c;
        }

        @Override
        public int compareTo(Letter letter) {
            if(this.c>letter.c){
                return 1;
            }else if(this.c<letter.c){
                return -1;
            }else{
                return 0;
            }
        }
    }

    private MinPriorityQueue<Integer, Letter> letterQ;
    private ArrayList<Letter> letters;

    @Before
    public void setupQ(){
        letterQ = new MinPriorityQueue<>();
        letters = new ArrayList<>();
        letters.add(new Letter(0, 'a'));
        letters.add(new Letter(1, 'z'));
        letters.add(new Letter(2, 'b'));
        letters.add(new Letter(3, 'y'));
        letters.add(new Letter(4, 'b'));
        letters.add(new Letter(5, 'f'));

        letters.forEach((letter)->letterQ.insert(letter));
    }


    @Test
    public void peak() throws Exception {
        int minIdx = letterQ.peak();
        assertEquals(minIdx, 0);

        minIdx = letterQ.peak();
        assertEquals(minIdx, 0);
    }

    @Test
    public void extractMin() throws Exception {
        Letter min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'a');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'b');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'b');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'f');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'y');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'z');
    }



//    @Test
//    public void getIdx() throws Exception {
//        int currentIdx = letterQ.getIdx(letters.get(0).idx);
//        assertEquals(0, currentIdx);
//
//        currentIdx = letterQ.getIdx(letters.get(1).idx);
//        assertEquals(3, currentIdx);
//
//        currentIdx = letterQ.getIdx(letters.get(2).idx);
//        assertEquals(2, currentIdx);
//
//        currentIdx = letterQ.getIdx(letters.get(3).idx);
//        assertEquals(4, currentIdx);
//
//        currentIdx = letterQ.getIdx(letters.get(4).idx);
//        assertEquals(1, currentIdx);
//
//        currentIdx = letterQ.getIdx(letters.get(5).idx);
//        assertEquals(5, currentIdx);
//    }

    @Test
    public void update() throws Exception {
        Letter a = letters.get(0);
        a.c='m';
        letterQ.update(0);

        Letter min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'b');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'b');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'f');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'm');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'y');

        min = letters.get(letterQ.extractMin());
        assertEquals(min.c, 'z');
    }

}