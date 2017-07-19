/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicProgramming;

import java.util.HashMap;

/**
 *
 * @author apatt
 */
public class CoinBank {
 
    public int[] values;
    
    private HashMap<Integer, Integer> countsToTotal = new HashMap<>();
    
    
    public CoinBank(int[] values){
        this.values = values;
        for(int i=0; i<values.length; i++){
            countsToTotal.put(values[i], 1);
        }
    }
        
    public int minCoinsForTotal(int total){
        if(total<0){
            // if the sum is less than 0, it is not possible to get the value
            return -1;
        }else if(countsToTotal.containsKey(total)){
            // if the min number of coins is stored, return the stored value
            return countsToTotal.get(total);
        }else{
            // if the min number is not yet known, calculate it            
            int absMinForSmallerTotal = -1;
            int minForSmallerTotal;
            for(int i=0; i<values.length; i++){
                minForSmallerTotal = minCoinsForTotal(total-values[i]);
                if((minForSmallerTotal>-1)&&( (minForSmallerTotal<absMinForSmallerTotal)||(absMinForSmallerTotal==-1))){
                    absMinForSmallerTotal = minForSmallerTotal;
                }
            }
            int min = absMinForSmallerTotal>0?(absMinForSmallerTotal+1):-1;
            countsToTotal.put(total, min);
            return min;
        }
    }
    
    public static void main(String[] args){
        
        int[] values = {2, 5};
        CoinBank cb = new CoinBank(values);
        int minCoins = cb.minCoinsForTotal(301);
        System.out.println(minCoins);
    }
}
