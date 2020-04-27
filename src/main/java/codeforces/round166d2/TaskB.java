package codeforces.round166d2;
/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/


import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class TaskB {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new TaskB().solve();
    }
    private final int MAX_NUMBER=100000;
    private final TreeSet<Integer> primeNumbers=new TreeSet<Integer>();
    {
        long s = System.currentTimeMillis();
        int[] primeNumbersTmp = new int[9593];
        int primeIndex = 0;
        primeNumbersTmp[primeIndex++]=2;
        primeNumbers.add(2);
        main: for(int i=3;;i++){
            for(int prime:primeNumbersTmp){
                if(prime==0){
                    break;
                }
                
                if(i%prime==0){
                    continue main;
                }
            }
            primeNumbersTmp[primeIndex++]=i;
            primeNumbers.add(i);
            if(i>=MAX_NUMBER){
                break;
            }
        }
        //debug.println(primeNumbers.size()+":"+(System.currentTimeMillis()-s));
        //debug.println(primeNumbers);
    }

    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] stepMatrix=new int[n][m];
        long minSteps=Long.MAX_VALUE;
        for(int x=0;x<n;x++){
            long steps2=0;
            for(int y=0;y<m;y++){
                int steps=findSteps(in.nextInt());
                //debug.printf("matrix[%s][%s]=%s, steps=%s\r\n",x,y,matrix[x][y],steps);
                stepMatrix[x][y]=steps;
                steps2+=stepMatrix[x][y];
            }
            if(steps2<minSteps){
                minSteps=steps2;
            }

        }


        main2: for(int y=0;y<m;y++){
            long steps=0;
            for(int x=0;x<n;x++){
                steps+=stepMatrix[x][y];
                if(steps>=minSteps){
                    continue main2;
                }

            }
            if(steps<minSteps){
                minSteps=steps;
            }
        }
        out.println(minSteps);
    }

    private int findSteps(int i) {
        if(i==1){
            return 1;
        }
        if(primeNumbers.contains(i)){
            return 0;
        }
        return primeNumbers.ceiling(i)-i;
    }
    
}
