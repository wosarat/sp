package codeforces.round165d2;
/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/


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
        for(int i=0;i<n;i++){
            if(exists(in.nextInt())){
                out.println("YES");
            }else{
                out.println("NO");
            }
        }
    }

    /**
     * @param a
     * @return
     */
    private boolean exists(int a) {
        if(a<60){
            return false;
        }
        return  360%(180-a)==0;
    }
    
}
