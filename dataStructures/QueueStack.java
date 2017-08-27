package dataStructures;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by apatters on 8/27/2017.
 */
public class QueueStack<T> {
    public Deque<T> Aqueue;
    public Deque<T> Bqueue;

    public QueueStack(){
        Aqueue = new ArrayDeque<>();
        Bqueue = new ArrayDeque<>();
    }

    public void push(T val){
        Bqueue.push(val);
        while(!Aqueue.isEmpty()){
            Bqueue.push(Aqueue.pop());
        }
        while(!Bqueue.isEmpty()){
            Aqueue.push(Bqueue.pop());
        }
    }

    public T pop(){
        if(Aqueue.isEmpty()){
            throw new Error("Stack empty");
        }

        int count = 0;
        while(!Aqueue.isEmpty()){
            Bqueue.push(Aqueue.pop());
            count++;
        };
        for(int idx=0; idx<(count-1); idx++){
            Aqueue.push(Bqueue.pop());
        }
        return Bqueue.pop();
    }
}
