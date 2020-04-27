package codeforces.round166d2;
/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/


import java.io.PrintWriter;
import java.util.Scanner;

public class TaskC {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new TaskC().solve();
    }

    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int bulk=n/k;
        if(bulk<3){
            out.println(-1);
            return;
        }
        for(int i=0;i<k;i++){

            for(int b=0;b<bulk;b++){
                if(i%2==0){
                    if(b==0 && i>0){
                        out.print((i-1)%k+1+" ");
                    }else if(b==2 && i<k-1){
                        out.print((i+1)%k+1+" ");
                    }else{
                        out.print(i+1+" ");
                    }
                }else{
                    if(b==0 && i>0){
                        out.print((i-1)%k+1+" ");
                    }else if(b==1 && i<k-1){
                        out.print((i+1)%k+1+" ");
                    }else{
                        out.print(i+1+" ");
                    }
                }
            }
            out.flush();
        }
        for(int i=0,maxi=n%bulk;i<maxi;i++){
            out.print(k+" ");
        }
        out.flush();
    }

}
