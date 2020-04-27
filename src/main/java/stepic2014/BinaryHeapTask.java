/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * C1.java 22.09.2014 17:41:22
 *********************************/
package stepic2014;

import java.util.Scanner;

class BinaryHeapTask {
    private final static long START_TIME_MS=System.currentTimeMillis();
    private final static String ZERO="0";
    private static void log(String format, Object ... args){
        System.err.format("%s: ", System.currentTimeMillis()-START_TIME_MS);
        System.err.format(format, args);
        System.err.println();
    }

    static class BinaryHeap{
        private int size=0;
        private final int[] arr;
        public BinaryHeap(int maxSize) {
            arr=new int[maxSize];
        }
        /**
         * @param arg
         */
        public void add(int arg) {
            if(arr.length==size){
                throw new IllegalArgumentException("Cannot add element. Heap is full.");
            }
            arr[size]=arg;
            shiftUp(size);
            size++;
        }
        

        private void shiftUp(int index) {
            if(index==0){
                return;
            }
            int parentIndex = (index-1)/2;
            int parent=arr[parentIndex];
            int child=arr[index];
            if(child>parent){
                arr[index]=parent;
                arr[parentIndex]=child;
                shiftUp(parentIndex);
            }
        }
        
        private void shiftDown(int index){
            int child1Index = index*2+1;
            int child2Index = index*2+2;
            if(child1Index>=size){
                return;
            }
            int current=arr[index];
            int child1 = arr[child1Index];
            if(child2Index>=size){
                if(child1>current){
                    arr[index]=child1;
                    arr[child1Index]=current;
                    return;
                }
                return;
            }
            int child2 = arr[child2Index];
            int childIndex;
            int childValue;
            if(child2>child1){
                childIndex = child2Index;
                childValue=child2;
            }else{
                childIndex = child1Index;
                childValue=child1;
            }
            if(childValue>current){
                arr[index]=childValue;
                arr[childIndex]=current;
                shiftDown(childIndex);
            }
            return;
        }
        
        public int exctract() {
            if(size==0){
                throw new IllegalStateException("Heap is empty");
            }
            int max = arr[0];
            size--;
            arr[0]=arr[size];
            arr[size]=0;
            shiftDown(0);
            return max;
        }
        
    }
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        BinaryHeap bh = new BinaryHeap(n);
        for(int i=0;i<n;i++){
            String command=scanner.next();
            if(command.equals("Insert")){
                int arg=scanner.nextInt();
                bh.add(arg);
            }else if(command.equals("Extract")){
                System.out.println(bh.exctract());
            }else{
                throw new IllegalArgumentException("Unsupported command "+command);
            }
        }
    }

}
