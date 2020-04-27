/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/
package codeforces.krok2013r2;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class A {
    private final Scanner in=new Scanner(System.in,"utf-8");
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new A().solve();
    }
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        int[] a=new int[n];
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int ai = in.nextInt();
            if(ai<min){
                min=ai;
            }
            a[i]=ai;
        }
        out.println(solve(a,min));
        out.flush();
    }
    /**
     * @param a
     * @param min
     * @return
     */
    private int solve(int[] a, int min) {
        for(int ai:a){
            if(ai%min!=0){
                return -1;
            }
        }
        return min;
    }

}