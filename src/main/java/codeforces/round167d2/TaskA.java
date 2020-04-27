package codeforces.round167d2;

import java.io.PrintWriter;
import java.util.Scanner;

public class TaskA {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new TaskA().solve();
    }

    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        int frendsFingers=0;
        for(int i=0;i<n;i++){
            frendsFingers+=in.nextInt();
        }
        //debug.println(frendsFingers);
        int result=0;
        for(int f=1;f<=5;f++){
            if((frendsFingers+f)%(n+1)!=1){
                result++;
            }
        }
        out.println(result);
    }
    
}
