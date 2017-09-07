package dataStructures.primitive;

import java.util.ArrayList;

/**
 * Created by apatters on 9/4/2017.
 */
public class BSTNode {

    int val;
    BSTNode left, right;

    BSTNode(int val){
        this.val = val;
    }

    public void insert(int val){
        if(val < this.val){
            if(this.left == null){
                this.left = new BSTNode(val);
            }else{
                this.left.insert(val);
            }
        }else{
            if(this.right == null){
                this.right = new BSTNode(val);
            }else{
                this.right.insert(val);
            }
        }
    }

    public boolean find(int val){
        if(val < this.val){
            if(this.left == null){
                return false;
            }else{
                return this.left.find(val);
            }
        }else if(val > this.val){
            if(this.right == null){
                return false;
            }else{
                return this.right.find(val);
            }
        }else{
            return true;
        }
    }

    public BSTNode delete(int val){
        BSTNode aux = new BSTNode(Integer.MIN_VALUE);
        aux.right = this;
        NodeAndParent nodeAndParent = findNodeAndParent(val, aux);
        BSTNode nodeToDelete = nodeAndParent.node;
        BSTNode parent = nodeAndParent.parent;
        nodeToDelete.delete(parent);
        return aux.right;
    }

    public void delete(BSTNode parent){
        if( (this.left!=null) && (this.right!=null) ){
            NodeAndParent maxNodeAndParent = findMaxChildNodeAndParent(this.left);
            BSTNode maxNode = maxNodeAndParent.node;
            BSTNode maxParent = maxNodeAndParent.parent;
            replaceInTree(parent, maxNode);
            if(this == maxParent){
                maxNode.right = this.right;
            }else{
                maxParent.right = maxNode.left;
                maxNode.right = this.right;
                maxNode.left = this.left;
            }
        }else if(this.left != null){
            this.replaceInTree(parent, this.left);
        }else if(this.right != null){
            this.replaceInTree(parent, this.right);
        }else{
            this.replaceInTree(parent, null);
        }
    }

    public void replaceInTree(BSTNode parent, BSTNode replacement){
        if(this.val < parent.val){
            parent.left = replacement;
        }else{
            parent.right = replacement;
        }
    }

    public ArrayList<Integer> flatten(){
        ArrayList<Integer> flatList = new ArrayList<>();
        this.populateFlatList(flatList);
        return flatList;
    }

    public void populateFlatList(ArrayList<Integer> flatList){
        if(this.left!=null){
            this.left.populateFlatList(flatList);
        }
        flatList.add(this.val);
        if(this.right!=null){
            this.right.populateFlatList(flatList);
        }
    }

    public NodeAndParent findMaxChildNodeAndParent(BSTNode child){
        if(child.right==null){
            return new NodeAndParent(child, this);
        }else{
            return child.findMaxChildNodeAndParent(child.right);
        }
    }


    public NodeAndParent findNodeAndParent(int val, BSTNode parent){
        if(val < this.val){
            if(this.left == null){
                return null;
            }else{
                return this.left.findNodeAndParent(val, this);
            }
        }else if(val > this.val){
            if(this.right == null){
                return null;
            }else{
                return this.right.findNodeAndParent(val, this);
            }
        }else{
            return new NodeAndParent(this, parent);
        }
    }

    private class NodeAndParent{
        BSTNode node, parent;
        public NodeAndParent(BSTNode node, BSTNode parent){
            this.node = node;
            this.parent = parent;
        }
    }

}
