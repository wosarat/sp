/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/
package codeforces.krok2013r2;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class C {
    private final Scanner in=new Scanner(System.in,"utf-8");
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new C().solve();
    }
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        String s1 = in.next();
        String s2 = in.next();
        byte[] arr=new byte[2*n];
        for(int i=0;i<2*n;i++){
            arr[i]=(byte) ((s1.charAt(i)-'0')*2+s2.charAt(i)-'0');
        }
        Arrays.sort(arr);
        int first=0;
        int second=0;
        for(int i=0;i<2*n;i++){
            if(i%2==0){
                if(arr[2*n-1-i]==3 || arr[2*n-1-i]==2){
                    first++;
                }
            }else{
                if(arr[2*n-1-i]==3 || arr[2*n-1-i]==1){
                    second++;
                }
            }
        }
        if(first>second){
            out.println("First");    
        }else if(first<second){
            out.println("Second");
        }else{
            out.println("Draw");
        }
        
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