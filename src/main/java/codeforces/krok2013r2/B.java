/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/
package codeforces.krok2013r2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;

public class B {
    private final Scanner in=new Scanner(System.in,"utf-8");
    private final Reader inr=new BufferedReader(new InputStreamReader(System.in,"utf-8"));
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) throws Exception {
        System.setProperty("file.encoding", "utf-8");
        new B().solve();
    }
    /**
     * 
     */
    public B() throws Exception{
    }
    /**
     * @throws IOException 
     * 
     */
    private void solve() throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        
        out.println(solve(n, k));
        out.flush();
    }
    protected String solve(int n, int k) throws IOException {
        String s=in.next();
        int stoneCount=0;
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            switch (ch) {
            case '.':
                stoneCount=0;
                break;
            case '#':
                stoneCount++;
                if(stoneCount>k-1){
                    return "NO";
                }
                break;
            default:
                throw new IllegalStateException();
            }
        }
        return "YES";
    }

}