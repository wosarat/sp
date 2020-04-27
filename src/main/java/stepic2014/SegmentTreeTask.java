/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * C1.java 22.09.2014 17:41:22
 *********************************/
package stepic2014;

import java.util.Scanner;

class SegmentTreeTask {
    private final static long START_TIME_MS=System.currentTimeMillis();
    private final static String ZERO="0";
    private static void log(String format, Object ... args){
        System.err.format("%s: ", System.currentTimeMillis()-START_TIME_MS);
        System.err.format(format, args);
        System.err.println();
    }

    static class SegmentTree{
        final int[] b;
        public SegmentTree(int[] arr) {
            int n=1;
            while(arr.length>n){
                n=n*2;
            }
            n=n*2;
            b=new int[n-1];
            int halfLen = b.length/2;
            for(int i=halfLen;i<b.length;i++){
                int arrIndex = i-halfLen;
                if(arrIndex<arr.length){
                    b[i]=arr[arrIndex];
                }else{
                    b[i]=Integer.MAX_VALUE;
                }
            }
            for(int i=halfLen-1;i>=0;i--){
                int i1 = i*2+1;
                int i2 = i*2+2;
                b[i]=Math.min(b[i1], b[i2]);
            }
        }

        public int min(int l, int r) {
            return min(0,0, b.length/2+1, l, r);
        }

        private int min(int currentIndex, int vl, int vr, int l, int r) {
            if(vl==l&& vr==r){
                return b[currentIndex];
            }
            int vavg = (vl+vr)/2;
            int child1Index = 2*currentIndex+1;
            int child2Index = 2*currentIndex+2;
            if(r<=vavg){
                return min(child1Index,vl,vavg,l,r);
            }else if(l>=vavg){
                return min(child2Index,vavg,vr,l,r);
            }else{
                int f1 = min(child1Index,vl,vavg,l,vavg);
                int f2 = min(child2Index,vavg,vr,vavg,r);
                return Math.min(f1, f2);
            }
        }

        public void set(int i, int value) {
            int halfLen = b.length/2;
            b[halfLen+i]=value;
            shiftUp(halfLen+i);
        }
        private void shiftUp(int index) {
            int parentIndex = (index-1)/2;
            int child1Index = parentIndex*2+1;
            int child2Index = parentIndex*2+2;
            b[parentIndex]=Math.min(b[child1Index], b[child2Index]);
            if(parentIndex>0){
                shiftUp(parentIndex);
            }
        }

    }
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int [] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=in.nextInt();
        }
        
        SegmentTree st = new SegmentTree(arr);
        for(int i=0;i<m;i++){
            String command=in.next();
            if(command.equals("Min")){
                System.out.println(st.min(in.nextInt()-1,in.nextInt()));
            }else if(command.equals("Set")){
                st.set(in.nextInt()-1, in.nextInt());
            }else{
                throw new IllegalArgumentException("Unsupported command "+command);
            }
        }
        
    }

}
