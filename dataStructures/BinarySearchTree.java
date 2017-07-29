/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructures;

class Node<K, V>{
    public K key;
    public V value;
    public Node<K, V> left;
    public Node<K, V> right;
    
    public Node(K key, V value){
        this.key = key;
        this.value = value;
    }
    
    public void setLeft(K key, V leftValue){
        this.left = new Node<>(key, leftValue);
    }

    public void setRight(K key, V rightValue){
        this.right=new Node<>(key, rightValue);
    }    
}

/**
 *
 * @author apatt
 */
public class BinarySearchTree<K extends Comparable, V>{
    
    private Node<K, V> root;
    
    public BinarySearchTree(K rootKey, V rootValue){
        root = new Node<>(rootKey, rootValue);
    }
    
    public void add(K key, V value){
        add(key, value, root);
    }
    
    private void add(K key, V value, Node refNode){
        if(key.compareTo(refNode.key)<0){
            if(refNode.left == null){
                refNode.left = new Node<>(key, value);
            }else{
                add(key, value, refNode.left);
            }
        }else if(key.compareTo(refNode.key)>0){
            if(refNode.right == null){
                refNode.right = new Node<>(key, value);
            }else{
                add(key, value, refNode.right);
            }
        }else{
            refNode.value = value;
        }
    }
    
    public V getValue(K key) throws Exception{
        return getValue(key, root);
    }
       
    private V getValue(K key, Node refNode) throws Exception{
        V value;        
        if(key.compareTo(refNode.key)<0){
            if(refNode.left == null){
                throw new Exception(String.format("key %s not found", key));
            }else{
                value = getValue(key, refNode.left);
            }
        }else if(key.compareTo(refNode.key)>0){
            if(refNode.right == null){
                throw new Exception(String.format("key %s not found", key));
            }else{
                value = getValue(key, refNode.right);
            }
        }else{
            value=(V) refNode.value;
        }
        
        return value;
    }
    
    public Boolean contains(K key){
        return contains(key, root);
    }
    
    private Boolean contains(K key, Node refNode){
        if(key.compareTo(refNode.key)<0){
            if(refNode.left == null){
                return false;
            }else{
                return contains(key, refNode.left);
            }
        }else if(key.compareTo(refNode.key)>0){
            if(refNode.right == null){
                return false;
            }else{
                return contains(key, refNode.right);
            }
        }else{
            return true;
        }
    }
}
