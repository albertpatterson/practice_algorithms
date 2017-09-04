package dataStructures.primitive;

import java.util.ArrayList;

/**
 * Created by apatters on 9/3/2017.
 */
public class BinarySearchTreeV3 {

    Node root;
    int count;

    public BinarySearchTreeV3(Node root, int count){
        this.root = root;
        this.count = count;
    }
    public BinarySearchTreeV3(Node root){
        this.root = root;
        count = 1;
    }

    public void insert(int value){
        insert(root, value);
        count++;
    }

    private void insert(Node node, int value) {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                insert(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                insert(node.right, value);
            }
        }
    }

    public int remove(int value){

        Node auxNode = new Node(0);
        auxNode.left = root;
        root = auxNode;

        NodeAndParent nodeAndParent = findNodeAndParent(value, root.left, root, true);

        delete(nodeAndParent.node, nodeAndParent.parent, nodeAndParent.isLeft);

        root = auxNode.left;

        count--;
        return nodeAndParent.node.value;
    }

    private void delete(Node node, Node parent, boolean isLeft) {

        if(node.left!=null && node.right!=null){
            replaceWithLeftMax(node, parent, isLeft);
        }else if(node.left!=null){
            replace(parent, isLeft, node.left);
        }else if(node.right!=null){
            replace(parent, isLeft, node.right);
        }else{
            replace(parent, isLeft, null);
        }
    }

    private void replaceWithLeftMax(Node node, Node parent, boolean isLeft){
        NodeAndParent maxNodeAndParentLeft = findMaxNodeAndParent(node.left, node);
        Node replacement = maxNodeAndParentLeft.node;
        Node replacementParent = maxNodeAndParentLeft.parent;

        replace(parent, isLeft, replacement);
        if(replacement == null){
            return;
        }else if(replacementParent == node){
            replacement.right = node.right;
        }else{
            replacementParent.right = replacement.left;
            replacement.left = node.left;
            replacement.right = node.right;
        }
    }

    private void replace(Node parent, boolean isLeft, Node replacement){
        if(isLeft){
            parent.left=replacement;
        }else{
            parent.right=replacement;
        }
    }

    public int find(int value){
        return findNodeAndParent(value, root, null, true).node.value;
    }

    public int rand(){
        int n = (int)Math.round((count-1)*Math.random());
        CountAndValue countAndValue = new CountAndValue(n);
        walkToNthValue(root, countAndValue);
        return countAndValue.value;
    }

    private void walkToNthValue(Node node, CountAndValue countAndValue){
        if(countAndValue.value!=null){
            return;
        }
        if(node.left!=null){
            walkToNthValue(node.left, countAndValue);
        }
        if(countAndValue.count--==0){
            countAndValue.value = node.value;
        }
        if(node.right!=null){
            walkToNthValue(node.right, countAndValue);
        }
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

    private class CountAndValue{
        int count;
        Integer value;
        public CountAndValue(int count){
            this.count = count;
        }
    }
}
