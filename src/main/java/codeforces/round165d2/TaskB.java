package codeforces.round165d2;
/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/


import java.io.PrintWriter;
import java.util.Scanner;

public class TaskB {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new TaskB().solve();
    }

    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=in.nextInt();
        }
        for(int i=arr.length-1;i>0;i--){
            if(arr[i-1]>arr[i]){
                out.println(i);
                return;
            }
        }

        out.println(0);

        
    }
}
