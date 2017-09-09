package pattern.sequence;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by apatters on 9/9/2017.
 */
public class ParensTest {
    @Test
    public void getValidParenSeqs() throws Exception {

        int n = 4;

        ArrayList<String> validSeqs = Parens.getValidParenSeqs(n);
        int maxSeqCount = 2*n*(2*n-1);

        assertTrue(maxSeqCount>=validSeqs.size());

        for(String seq : validSeqs){
            assertTrue(Parens.isParenSeqValid(seq));
        }

        validSeqs.sort(new StringComparator());

        for(int idx=1; idx<validSeqs.size(); idx++){
            assertFalse(validSeqs.get(idx-1).equals(validSeqs.get(idx)));
        }
    }

    private class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }

        @Override
        public boolean equals(Object s) {
            return this.equals(s);
        }
    }

    @Test
    public void isParenSeqValid() throws Exception {
        String  emptySeq = "",
                validSeq = "(()(()))",
                invalidSeq = "(()))()(",
                invalidSeqAllOpen = "((((",
                invalidSeqAllClose = ")))";


        assertTrue(Parens.isParenSeqValid(emptySeq));
        assertTrue(Parens.isParenSeqValid(validSeq));
        assertFalse(Parens.isParenSeqValid(invalidSeq));
        assertFalse(Parens.isParenSeqValid(invalidSeqAllOpen));
        assertFalse(Parens.isParenSeqValid(invalidSeqAllClose));
    }

}