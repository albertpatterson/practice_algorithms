package trie;

/**
 * Created by apatters on 9/30/2017.
 */
public class Trie {
    private TrieNode root;

    public Trie(){
        this.root = new TrieNode('~');
    }

    public void addWord(String word){
        this.root.addWord(word);
    }

    public boolean contains(String word){
        return root.contains(word);
    }
}
