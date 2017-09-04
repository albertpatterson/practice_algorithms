package dataStructures.primitive;

import java.util.ArrayList;

/**
 * Created by apatters on 9/3/2017.
 */
public class BinarySearchTreeV2 {

    Node root;

    public BinarySearchTreeV2(Node root){
        this.root = root;
    }

    public void insert(int value){
        insert(root, value);
    }

    private void insert(Node node, int value) {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                insert(node.left, value);
            }
        } else if (value > node.value) {
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                insert(node.right, value);
            }
            // otherwise, do nothing as the value already exists
        }
    }

    public int remove(int value){
        return remove(root, null, true, value);
    }

    private int remove(Node node, Node parent,  boolean isLeft, int value) {

        NodeAndParent nodeAndParent = findNodeAndParent(value, root, null);
        delete(nodeAndParent.node, nodeAndParent.parent, nodeAndParent.isLeft);
        return node.value;
    }

    private void delete(Node node, Node parent, boolean isLeft) {

        if(node.left!=null && node.right!=null){
            replaceWithLeft(node, parent, isLeft);
        }else if(node.left!=null){
            if(node == root) {
                root = node.left;
            }else if(isLeft){
                    parent.left = node.left;
            }else{
                parent.right = node.left;
            }
        }else if(node.right!=null){
            if(node == root) {
                root = node.right;
            }else if(isLeft){
                parent.left = node.right;
            }else{
                parent.right = node.right;
            }
        }else{
            if(node == root) {
                root = null;
            }else if(isLeft){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }


//        if (node.left != null) {
//            replaceWithLeft(node, parent, isLeft);
//        } else if (node.right != null) {
//            replaceWithRight(node, parent, isLeft);
//        } else {
//            if (node == root) {
//                root = null;
//            }else if (isLeft) {
//                parent.left = null;
//            } else {
//                parent.right = null;
//            }
//        }
    }

    private void replaceWithLeft(Node node, Node parent, boolean isLeft){
        NodeAndParent maxNodeAndParentLeft = findMaxNodeAndParent(node.left, node);
        Node replacement = maxNodeAndParentLeft.node;
        Node replacementParent = maxNodeAndParentLeft.parent;

        if(node == root){
            root = replacement;
            replacement.right = root.right;
        }else if(replacement==null){
            if(isLeft){
                parent.left=null;
            }else{
                parent.right=null;
            }
        }else if(replacementParent == node){
            if(isLeft){
                parent.left=replacement;
            }else{
                parent.right=replacement;
            }
            replacement.right = node.right;
        }else{
            if(isLeft){
                parent.left=replacement;
            }else{
                parent.right=replacement;
            }
            replacementParent.right = replacement.left;
            replacement.left = node.left;
            replacement.right = node.right;
        }
    }

//    private void replaceWithRight(Node node, Node parent,  boolean isLeft){
//        NodeAndParent minNodeAndParentRight = findMinNodeAndParent(node.right, node);
//        Node replacement = minNodeAndParentRight.node;
//        Node replacementParent = minNodeAndParentRight.parent;
//
//        if(node == root){
//            root = replacement;
//            replacement.left = root.left;
//        }else if(replacement==null){
//            if(isLeft){
//                parent.left=null;
//            }else{
//                parent.right=null;
//            }
//        }else if(replacementParent == node){
//            if(isLeft){
//                parent.left=replacement;
//            }else{
//                parent.right=replacement;
//            }
//            replacement.left = node.left;
//        }else{
//            if(isLeft){
//                parent.left=replacement;
//            }else{
//                parent.right=replacement;
//            }
//            replacementParent.left = replacement.right;
//            replacement.left = node.left;
//            replacement.right = node.right;
//        }
//    }

    public int find(int value){
        return findNodeAndParent(value, root, null).node.value;
    }

    public ArrayList<Integer> flatten(){
        ArrayList<Integer> values = new ArrayList<>();
        pushIntoFlatList(root, values);
        return values;

    }

    private void pushIntoFlatList(Node node, ArrayList<Integer> values){
        if(node==null){
            return;
        }

        pushIntoFlatList(node.left, values);
        values.add(node.value);
        pushIntoFlatList(node.right, values);
    }




    private NodeAndParent findNodeAndParent(int value, Node node, Node parent){
        return findNodeAndParent(value, node, parent, true);
    }

    private NodeAndParent findNodeAndParent(int value, Node node, Node parent, boolean isLeft){
        if (value < node.value) {
            if (node.left == null) {
                throw new Error("Item not found");
            } else {
                return findNodeAndParent(value, node.left, node, true);
            }
        } else if (value > node.value) {
            if (node.right == null) {
                throw new Error("Item not found");
            } else {
                return findNodeAndParent(value, node.right, node, false);
            }
        } else {
            return new NodeAndParent(node, parent, isLeft);
        }
    }

    private NodeAndParent findMaxNodeAndParent(Node node, Node parent){
        if(node==null){
            return new NodeAndParent(null, parent, false);
        }
        if(node.right==null){
            return new NodeAndParent(node, parent, false);
        }else{
            return findMaxNodeAndParent(node.right, node);
        }
    }

    private NodeAndParent findMinNodeAndParent(Node node, Node parent){
        if(node==null){
            return new NodeAndParent(null, parent, true);
        }
        if(node.left==null){
            return new NodeAndParent(node, parent, true);
        }else{
            return findMinNodeAndParent(node.left, node);
        }
    }

    class NodeAndParent{
        public Node node;
        public Node parent;
        boolean isLeft;
        public NodeAndParent(Node node, Node parent, boolean isLeft){
            this.node=node;
            this.parent = parent;
            this.isLeft = isLeft;
        }
    }
}
