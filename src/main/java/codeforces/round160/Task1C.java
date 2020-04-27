/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/
package codeforces.round160;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author starasov
 *
 */
public class Task1C {
    private final Scanner in=new Scanner(System.in);
    private final PrintWriter out=new PrintWriter(System.out);
    public static void main(String[] args) {
        new Task1C().solve();
    }

    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int result=solve(a,b);
            out.println(result);
        }
        out.flush();
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public int solve(int a, int b) {
        int count=0;
        int max=Math.max(a, b);
        int min=Math.min(a, b);
        do{
            count+=max/min;
            int newMax = min;
            min=max%min;
            max=newMax;
        }while(min>0);
        return count;
    }
    
}
