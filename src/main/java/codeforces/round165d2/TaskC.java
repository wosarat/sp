package codeforces.round165d2;
/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/


import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskC {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new TaskC().solve();
    }
    private int result=0;
    private int busy=0;
    private static class Box implements Comparable<Box>{
        int k;
        int count;
        int superBox;
        public Box(int k, int count) {
            super();
            this.k = k;
            this.count = count;
            superBox=findSuperBox(k, count);
        }
        private static int findSuperBox(int k, int count){
            if(count==0){
                return k;
            }else if(count<=4){
                return k+1;
            }else{
                return findSuperBox(k+1, count/4+(count%4==0?0:1));
            }
        }
        @Override
        public int compareTo(Box o) {
            return -(k-o.k);
        }
        @Override
        public String toString() {
            return "Box("+k+" "+count+" "+superBox+")";
        }


        
    }
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        Box arr[]=new Box[n];
        int result=0;
        for(int i=0;i<n;i++){
            arr[i]=new Box(in.nextInt(),in.nextInt());
            if(arr[i].superBox>result){
                result=arr[i].superBox;
            }
        }
        out.println(result);
        Arrays.sort(arr);
        LinkedList<Box> list = new LinkedList<Box>(Arrays.asList(arr));
        debug.println(list);

        
    }
}
