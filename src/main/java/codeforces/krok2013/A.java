/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/
package codeforces.krok2013;

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
    private void solve() {
        int n = in.nextInt();
        int prevT=-1;
        long currentQ=0;
        long maxQ=0;
        for(int i=0;i<n;i++){
            int t=in.nextInt();
            int c=in.nextInt();
            if(i>0){
                currentQ-=t-prevT;
                if(currentQ<0){
                    currentQ=0;
                }
            }
            currentQ+=c;
            if(currentQ>maxQ){
                maxQ=currentQ;
            }
            prevT=t;
        }
        out.println((prevT+currentQ)+" "+maxQ);
        out.flush();
    }

}