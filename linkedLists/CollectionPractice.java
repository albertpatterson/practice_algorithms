package linkedLists;

import javafx.print.Printer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by apatters on 9/27/2017.
 */
public class CollectionPractice {

    public static void print(LinkedList<PrinterNode> lst){

        System.out.println();
        ListIterator<PrinterNode> itr = lst.listIterator();
        while(itr.hasNext()){
            PrinterNode cur = itr.next();
            cur.print();
        }
        System.out.println();
    }

    public static void main(String[] args){
        LinkedList<PrinterNode> lst = new LinkedList<>();
        lst.add(new PrinterNode("1"));
        lst.add(new PrinterNode("2"));
        lst.add(new PrinterNode("3"));
        lst.add(new PrinterNode("4"));
        lst.add(new PrinterNode("5"));
        lst.add(new PrinterNode("6"));
        lst.add(new PrinterNode("7"));
        lst.add(new PrinterNode("8"));

        ListIterator itr = lst.listIterator();

        PrinterNode cur;
        for(int idx=0; idx<4; idx++){
            cur = (PrinterNode)itr.next();
            cur.print();
        }

        System.out.println();
        for(int idx=0; idx<3; idx++){
            cur = (PrinterNode)itr.previous();
            cur.print();
        }

        System.out.println();
        for(int idx=0; idx<2; idx++){
            cur = (PrinterNode)itr.next();
            cur.print();
        }

        System.out.println();
        print(lst);

        itr.remove();
        print(lst);

        itr.next();
        itr.remove();
        print(lst);

        cur = (PrinterNode)itr.next();
        cur.print();

        itr.add(new PrinterNode("11"));
        cur.print();
        cur=(PrinterNode)itr.previous();
        cur.print();
        itr.add(new PrinterNode("12"));

        print(lst);
    }
}

class PrinterNode{
    String val;

    public PrinterNode(String val){
        this.val = val;
    }

    public void print(){
        System.out.print(val + ", ");
    }
}

