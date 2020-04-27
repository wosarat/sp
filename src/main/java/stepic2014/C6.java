/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * C1.java 22.09.2014 17:41:22
 *********************************/
package stepic2014;

import java.util.Scanner;

class C6 {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array=new int[n];
        for(int i=0;i<n;i++){
            array[i]=scanner.nextInt();
        }

        int k = scanner.nextInt();
        boolean first=true;
        for(int i=0;i<k;i++){
            int b = scanner.nextInt();
            if(first){
                first=false;
            }else{
                System.out.print(" ");    
            }
            System.out.print(find(array,b));
        }
    }
    private static int find(int[] array, int b) {
        int l=0, r=array.length-1;
        while(l<=r){
            int m = (l+r)/2;
            if(array[m]==b){
                return m+1;
            }else if(array[m]<b){
                l=m+1;
            }else{
                r=m-1;
            }
        }
        return -1;
    }

    @SuppressWarnings("unused")
    private static void log(String format, Object ... args){
        System.err.format(format, args);
        System.err.println();
    }

}
