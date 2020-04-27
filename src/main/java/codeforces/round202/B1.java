/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * B1.java 27.09.2013 22:01:42
 *********************************/
package codeforces.round202;

import java.io.*;
import java.util.*;

public class B1 {
  public static void main(String[] args){
    FastScanner sc = new FastScanner();
    int v = sc.nextInt();
    int[] a = new int[9];
    int min = Integer.MAX_VALUE;
    int minIndex = -1;
    for(int i = 0; i < 9; i++) {
      a[i] = sc.nextInt();
      if(a[i] <= min) {
        min = a[i];
        minIndex = i;
      }
    }

    int digits = v / min;
    v -= min * digits;
    StringBuffer sb = new StringBuffer();
    for(int i = 0; i < digits; i++) {
      int nextDigit = minIndex;
      for(int j = 8; j > minIndex; j--) {
        if((a[j] - min) <= v) {
          nextDigit = j;
          v -= (a[j] - min);
          break;
        }
      }
      sb.append(nextDigit + 1);
    }
    if(sb.length() > 0) {
      System.out.println(sb);
    } else {
      System.out.println(-1);
    }

  }

  public static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(String s) {
      try {
        br = new BufferedReader(new FileReader(s));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String nextToken() {
      while (st == null || !st.hasMoreElements()) try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(nextToken());
    }

    long nextLong() {
      return Long.parseLong(nextToken());
    }

    double nextDouble() {
      return Double.parseDouble(nextToken());
    }
  }
}