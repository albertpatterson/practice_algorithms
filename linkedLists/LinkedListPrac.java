package linkedLists;

/**
 * Created by apatters on 8/22/2017.
 */
public class LinkedListPrac<T>{

    public Node<T> head;

    public LinkedListPrac(T[] vals){
        Node<T> curNode, head = new Node<>(vals[0]);
        curNode = head;
        for(int idx=1; idx<vals.length; idx++){
            curNode.next = new Node<>(vals[idx]);
            curNode = curNode.next;
        }
        this.head = head;
    }

//    public Node<T> partition(T partVal){
//        Node<T> leftHead, leftTail, rightHead, rightTail, curNode = head;
//
//        do{
//            if(partVal > curNode.val){
//                if(leftHead==null){
//                    leftHead=curNode;
//                }else{
//                    leftTail.next = curNode;
//                }
//
//            }
//        }while(curNode!=null);
//
//    }

    public void disp(){
        Node<T> curNode = head;
        do{
            System.out.println(curNode.val);
            curNode = curNode.next;
        }while(curNode!=null);
    }

    public static void main(String[] args){
        Integer[] myInts = {1,2,3, 10, 1, 1000, -100};
        LinkedListPrac<Integer> myList = new LinkedListPrac<>(myInts);
        myList.disp();
    }
}

class ComaprableLinkedList<T extends Comparable> extends LinkedListPrac<T>{

    public ComaprableLinkedList(T vals[]){
        super(vals);
    }

    public void dispComp(T val){
        Node<T> curNode = head;
        do{
            System.out.println(curNode.val+" "+curNode.val.compareTo(val));
            curNode = curNode.next;
        }while(curNode!=null);
    }

    public Node<T> part(T partVal){
        Node<T> leftHead=null, rightHead=null, leftTail=null, rightTail=null, curNode = head;
        while(curNode!=null){
            if(curNode.val.compareTo(partVal)<0){
                if(leftHead==null){
                    leftHead = curNode;
                }else if(leftTail==null) {
                    leftTail = curNode;
                    leftHead.next = leftTail;
                }else{
                    leftTail.next = curNode;
                    leftTail = curNode;
                }
            }else{
                if(rightHead==null){
                    rightHead = curNode;
                }else if(rightTail==null) {
                    rightTail = curNode;
                    rightHead.next = rightTail;
                }else{
                    rightTail.next = curNode;
                    rightTail = curNode;
                }
            }
            curNode=curNode.next;
        }

        if(rightTail!=null){
            rightTail.next = null;
        }

        if(leftHead == null){
            return rightHead;
        }else if(leftTail==null){
            leftHead.next = rightHead;
            return rightHead;
        }else{
            leftTail.next = rightHead;
            return leftHead;
        }
    }

    public static void main(String[] args){
        Integer[] myInts = {1,2,3, 10, 1, 11, 12, 9, 15, 1000, -100};
        ComaprableLinkedList<Integer> myList = new ComaprableLinkedList<>(myInts);
//        myList.dispComp(10);

        int val = 5;
        Node<Integer> parted = myList.part(val);
        Node<Integer> curNode = parted;
        while(curNode!=null){
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
    }
}

class Node<T>{
    public T val;
    public Node<T> next;

    public Node(T val){
        this.val = val;
    }

    public Node(T val, Node<T> next){
        this.val = val;
        this.next = next;
    }
}
