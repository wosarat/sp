package codeforces.round166d2;
/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/


import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TaskA {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new TaskA().solve();
    }

    /**
     * 
     */
    private void solve() {
        int y = in.nextInt();
        Set<Character> chars=new HashSet<Character>();
        for(int x=y+1;true;x++){
            char[] digits = String.valueOf(x).toCharArray();
            chars.clear();
            for(char c:digits){
                chars.add(c);
            }
            if(chars.size()==digits.length){
                out.println(x);
                break;
            }
        }
    }
    
}