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

public class B {
    private final Scanner in=new Scanner(System.in,"utf-8");
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new B().solve();
    }
    /**
     * 
     */
    private void solve() {
        int t = in.nextInt();
        int sx= in.nextInt();
        int sy= in.nextInt();
        int ex= in.nextInt();
        int ey= in.nextInt();
        char[] wind = in.next().toCharArray();
        
        out.println(solve(sx, sy, ex, ey, wind));
        out.flush();
    }
    protected int solve(int sx, int sy, int ex, int ey, char[] wind) {
        char goodX=sx>ex?'W':'E';
        int xSteps=Math.abs(sx-ex);
        char goodY=sy>ey?'S':'N';
        int ySteps=Math.abs(sy-ey);

        int i=0;
        for(char w:wind){
            i++;
            if(w==goodX){
                xSteps--;
            }
            if(w==goodY){
                ySteps--;
            }

            if(xSteps<=0 && ySteps<=0){
                return i;
            }

        }
        return -1;
    }

}