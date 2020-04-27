/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/
package codeforces.round160;


public class Test {
    public static void main(String[] args) {
        long n=0;
        for(long a=2;a<100000;a++){
            for(long b=a+1;b<100000;b++){
                long c = Math.round(Math.sqrt(a*a+b*b));
                if(a*a+b*b==c*c){
                    n++;
                    System.out.println(a+":"+b+":"+c);
                }
            }
        }
        System.out.println(n);
    }
}
