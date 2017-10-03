package trie;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by apatters on 9/30/2017.
 */
public class TrieTest {
    @Test
    public void contains() throws Exception {
        Trie t = new Trie();

        t.addWord("apple");
        t.addWord("ants");
        t.addWord("butter");
        t.addWord("bubble");
        t.addWord("app");
        t.addWord("bunt");
        t.addWord("matt");

        assertTrue(t.contains("bubble"));
        assertTrue(t.contains("app"));
        assertTrue(t.contains("matt"));

        assertFalse(t.contains("bubbe"));
        assertFalse(t.contains("apps"));
        assertFalse(t.contains("matthew"));


    }

}