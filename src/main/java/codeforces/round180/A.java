/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/
package codeforces.round180;

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
        char[] blocks = in.next().toCharArray();
        int s=0;
        int t=0;
        int firstR=0;
        int lastR=0;
        int firstL=0;
        int lastL=0;
        for(int i=1;i<n+1;i++){
            char c = blocks[i-1];
            if(c=='R'){
                lastR=i;
                if(firstR==0){
                    firstR=i;
                }
            }
            if(c=='L'){
                lastL=i;
                if(firstL==0){
                    firstL=i;
                }
            }

        }
        if(firstL==0){
            s=firstR;
            t=lastR+1;
        }else if(firstR==0){
            s=lastL;
            t=firstL-1;
        }else {
            s=firstR;
            t=firstL-1;
        }
        out.println(s+" "+t);
        out.flush();
    }

}