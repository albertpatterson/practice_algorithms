package pattern.sequence;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by apatters on 9/9/2017.
 */
public class Parens {

    public static ArrayList<String> getValidParenSeqs(int m){

        int n = m+1;

        ArrayList<String>[][] rows = new ArrayList[2][n];

        rows[1][0] = new ArrayList<>(Arrays.asList(""));

        for(int col=1; col<n; col++){
            rows[1][col]=new ArrayList<>(Arrays.asList(rows[1][col-1].get(0)+"("));
        }

        for(int idx = n; idx < n*n; idx++){
            int row = idx/n;
            int col = idx%n;

            if(row>0 && col==0){
                rows[0]=rows[1];
                rows[1] = new ArrayList[n];
            }

            rows[1][col] = new ArrayList<>();

            ArrayList<String> prefs = new ArrayList<>();

            if(col>=row){
                ArrayList<String> prefsMinusOpen = rows[1][col-1];

                for(String pref : prefsMinusOpen){
                    prefs.add(pref+"(");
                }

                ArrayList<String> prefsMinusClose = rows[0][col];
                for(String pref : prefsMinusClose){
                    prefs.add(pref+")");
                }
            }
            rows[1][col]=prefs;
        }

        return rows[1][n-1];
    }

    public static boolean isParenSeqValid(String seq){
        int sum = 0;
        for(int idx=0; idx<seq.length(); idx++){
            if(seq.charAt(idx)=='('){
                sum++;
            }else if(seq.charAt(idx)==')'){
                if(--sum<0){
                    return false;
                }
            }else{
                return false;
            }
        }
        return sum==0;
    }

    public static void main(String[] args){

        ArrayList<String> seqs = getValidParenSeqs(5);
        for(String seq : seqs){
            System.out.println(seq);
        }

    }

}
