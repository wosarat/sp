/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * C1.java 22.09.2014 17:41:22
 *********************************/
package stepic2014;

import java.util.Arrays;
import java.util.Scanner;

class MergeSort {
    private final static long START_TIME_MS=System.currentTimeMillis();
    private final static String ZERO="0";
    private static void log(String format, Object ... args){
        System.err.format("%s: ", System.currentTimeMillis()-START_TIME_MS);
        System.err.format(format, args);
        System.err.println();
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
        }
        System.out.println(mergeSort(arr, 0, arr.length-1));
        log("mergeSort: %s",Arrays.toString(arr));
    }
    static long mergeSort(int[] arr,int l, int r){
        if(l>r){
            throw new IllegalStateException("l>r");
        }
        if(l==r){
            return 0;
        }
        int m=(l+r)/2;
        long result=mergeSort(arr, l, m);
        result+=mergeSort(arr, m+1, r);
        int[] tmp=new int[r-l+1];
        int index=0;
        int i=l;
        int j=m+1;
        while(index<tmp.length){
                int a1 = arr[i];
                int a2 = arr[j];
                if(a1<=a2){
                    tmp[index++]=a1;
                    i++;
                }else{
                    tmp[index++]=a2;
                    result+=(m-i+1);
                    j++;
                }
                if(i>m){
                    for(;j<=r;j++){
                        tmp[index++]=arr[j];
                    }
                    break;
                }
                if(j>r){
                    for(;i<=m;i++){
                        tmp[index++]=arr[i];
                    }
                    break;
                }
        }
        System.arraycopy(tmp, 0, arr, l, tmp.length);
        return result;
    }

}
