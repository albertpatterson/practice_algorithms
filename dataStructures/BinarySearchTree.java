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
    
    public Node<K, V> root;
    
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
        return !(lookup(key)==null);
    }

    public Node lookup(K key){
        return lookup(key, root);
    }

    private Node lookup(K key, Node refNode){
        if(key.compareTo(refNode.key)<0){
            if(refNode.left == null){
                return null;
            }else{
                return lookup(key, refNode.left);
            }
        }else if(key.compareTo(refNode.key)>0){
            if(refNode.right == null){
                return null;
            }else{
                return lookup(key, refNode.right);
            }
        }else{
            return refNode;
        }
    }

    private NodeWithParent lookupWithParent(K key, Node refNode, Node parent){
        if(key.compareTo(refNode.key)<0){
            if(refNode.left == null){
                return null;
            }else{
                return lookupWithParent(key, refNode.left, refNode);
            }
        }else if(key.compareTo(refNode.key)>0){
            if(refNode.right == null){
                return null;
            }else{
                return lookupWithParent(key, refNode.right, refNode);
            }
        }else{
            return new NodeWithParent(refNode, parent);
        }
    }

    public void delete(K key){
        NodeWithParent nodeToDeleteWithParent = lookupWithParent(key, root, null);
        if(!(nodeToDeleteWithParent==null)){

            Node<K, V> parent = nodeToDeleteWithParent.parent;
            Node<K, V> nodeToDelete = nodeToDeleteWithParent.node;

            Boolean hasLeft = nodeToDelete.left!=null;
            Boolean hasRight = nodeToDelete.right!=null;

            if( hasLeft && hasRight ) {
                NodeWithParent<K, V> minNodeWithParent = findMinWithParent(nodeToDelete, parent);
                Node<K, V> minNode  = minNodeWithParent.node;
                Node<K, V> minNodeParent  = minNodeWithParent.parent;

                updateChildNode(parent, nodeToDelete, minNode);
                updateChildNode(minNodeParent, minNode, null);

            }else if(hasLeft){
                updateChildNode(parent, nodeToDelete, nodeToDelete.left);
            }else if(hasRight){
                updateChildNode(parent, nodeToDelete, nodeToDelete.right);
            }else{
                updateChildNode(parent, nodeToDelete, null);
            }
        }
    }

    private NodeWithParent findMinWithParent(){
        return findMinWithParent(root, null);
    }

    private NodeWithParent findMinWithParent(Node refNode, Node parent){
        if(refNode.left==null){
            return new NodeWithParent(refNode, parent);
        }else{
            return findMinWithParent(refNode.left, refNode);
        }
    }

    private void updateChildNode(Node<K, V> parent, Node<K, V> oldValue, Node<K, V> newValue){
        if(oldValue.key.compareTo(parent.key) < 0){
            parent.left = newValue;
        }else{
            parent.right = newValue;
        }
    }
}

class NodeWithParent<K extends Comparable, V>{
    Node<K, V> node;
    Node<K, V> parent;
    NodeWithParent(Node<K, V> node, Node<K, V> parent){
        this.node = node;
        this.parent = parent;
    }
}