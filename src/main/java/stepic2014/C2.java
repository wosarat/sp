/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * C1.java 22.09.2014 17:41:22
 *********************************/
package stepic2014;

import java.util.Scanner;

public class C2 {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(solve(n));
    }

    private static long solve(int n) {
        if(n<2){
            return n;
        }
        long fa=0;
        long fb=1;
        long fResult;
        int i=1;
        do{
            i++;
            fResult=fa+fb;

            System.err.println("F"+i+" = "+fResult);

            fa=fb;
            fb=fResult;
        }while(i<n);
        return fResult;
    }
}
