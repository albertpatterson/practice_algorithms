package linkedLists;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by apatters on 9/27/2017.
 */
public class ExpressionEvaluator {

    private static char[] opChars = {'+','-','*','/'};

    public static int evaluate(String expr){
        LinkedList<Character> ops = new LinkedList<>();
        LinkedList<Integer> nums = new LinkedList<>();

        int numStart = 0;
        int num;
        for(int idx=0; idx<expr.length(); idx++){
            char cur = expr.charAt(idx);
            if(isOp(cur)){
                ops.add(cur);

                num = Integer.valueOf(expr.substring(numStart, idx));
                nums.add(num);
                numStart = idx+1;
            }
        }
        num = Integer.valueOf(expr.substring(numStart, expr.length()));
        nums.add(num);

        ListIterator<Character> opItr = ops.listIterator();
        ListIterator<Integer> numItr = nums.listIterator();

        // scan for mult and division
        int firstNum, nextNum, newNum;
        char op;
        while(opItr.hasNext()){
            op = opItr.next();
            firstNum = numItr.next();

            if(op=='*'||op=='/'){
                nextNum = numItr.next();
                if(op=='*'){
                    newNum = firstNum*nextNum;
                }else{
                    newNum = firstNum/nextNum;
                }

                opItr.remove();
                numItr.previous();
                numItr.previous();
                numItr.set(newNum);
                numItr.next();
                numItr.next();
                numItr.remove();
                numItr.previous();
            }
        }
        if(numItr.hasNext()){
            numItr.next();
        }




        while(opItr.hasPrevious()){
            op = opItr.previous();
            firstNum = numItr.previous();
            nextNum = numItr.previous();
            if(op=='+'){
                newNum = firstNum+nextNum;
            }else{
                newNum = nextNum-firstNum;
            }

            opItr.remove();
            numItr.next();
            numItr.next();
            numItr.set(newNum);
            numItr.previous();
            numItr.previous();
            numItr.remove();
            numItr.next();
        }



        return numItr.previous();
    }

    private static boolean isOp(char c){
        boolean isOp = false;
        for(int opIdx = 0; opIdx < opChars.length; opIdx++){
            if(c==opChars[opIdx]){
                isOp = true;
                break;
            }
        }
        return isOp;
    }

    public static void main(String[] args){
        System.out.println(evaluate("100/3+15/3/5+10*2-5*2"));
    }
}
