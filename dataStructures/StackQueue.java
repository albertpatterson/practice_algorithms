package dataStructures;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by apatters on 8/27/2017.
 */
public class StackQueue<T> {
    Deque<T> inStack;
    Deque<T> outStack;

    public void push(T val){
        if(inStack==null){
            inStack = new ArrayDeque<>();
        }
        inStack.push(val);
    }

    public T pop(){
        if(outStack==null){
            outStack = new ArrayDeque<>();
        }
        if(!outStack.isEmpty()){
            return outStack.pop();
        }else if(!inStack.isEmpty()){
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
            return outStack.pop();
        }else{
            throw new Error("Stack Empty");
        }
    }
}
