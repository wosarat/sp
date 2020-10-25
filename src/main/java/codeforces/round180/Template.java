/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/
package codeforces.round180;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Template {
    private final Scanner in=new Scanner(System.in,"utf-8");
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new Template().solve();
    }
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        out.println("put result here");
        out.flush();
    }

}