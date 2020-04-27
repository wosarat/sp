package codeforces.round181;
/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A {
 
    private final Scanner in=new Scanner(new BufferedInputStream(System.in, 8192),"utf-8");
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
        int n=in.nextInt();
        List<Integer> pos=new ArrayList<Integer>();
        List<Integer> neg=new ArrayList<Integer>();
        List<Integer> zero=new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            int a = in.nextInt();
            if(a>0 && pos.size()==0){
                pos.add(a);
            }else if(a<0){
                neg.add(a);
            }else{
                zero.add(a);
            }
        }
        if(pos.size()==0){
            pos.add(neg.remove(0));
            pos.add(neg.remove(0));
        }
        if(neg.size()%2==0){
            zero.add(neg.remove(0));
        }
        printList(pos);
        printList(neg);
        printList(zero);

        
        out.flush();
    }
    /**
     * @param pos
     */
    private void printList(List<Integer> pos) {
        out.print(pos.size());
        for(int a:pos){
            out.print(" "+a);
        }
        out.println();
    }

}