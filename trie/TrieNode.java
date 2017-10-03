package trie;

import java.util.HashMap;

/**
 * Created by apatters on 9/30/2017.
 */
public class TrieNode {
    public HashMap<Character, TrieNode> children;
    public boolean terminates;
    public char value;

    public TrieNode(char c){
        children = new HashMap<>();
        this.value = c;
    }

    public void addWord(String word){

        if(word.isEmpty()){
            this.terminates = true;
        }else{
            char nextChar = word.charAt(0);
            TrieNode child = children.get(nextChar);
            if(child==null){
                child = new TrieNode(nextChar);
                this.children.put(nextChar, child);
            }
            child.addWord(word.substring(1));
        }
    }

    public boolean contains(String word){
        if(word.isEmpty() && this.terminates){
            return true;
        }

        char nextChar = word.charAt(0);
        TrieNode child = children.get(nextChar);

        if(child==null){
            return false;
        }else{
            return child.contains(word.substring(1));
        }
    }
}
