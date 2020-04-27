/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * C1.java 22.09.2014 17:41:22
 *********************************/
package stepic2014;

import java.util.Scanner;

class C5 {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] counters=new int[10];
        int[] array=new int[n];
        for(int i=0;i<n;i++){
           array[i]=scanner.nextInt();
           counters[array[i]-1]++;
        }
        boolean first=true;
        for(int i=0;i<counters.length;i++){
            for(int j=0;j<counters[i];j++){
                if(first){
                    first=false;
                }else{
                    System.out.print(" ");    
                }
                System.out.print(i+1);
            }
        }
    }

    @SuppressWarnings("unused")
    private static void log(String format, Object ... args){
        System.err.format(format, args);
        System.err.println();
    }

}
