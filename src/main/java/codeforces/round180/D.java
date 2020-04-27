/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/
package codeforces.round180;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class D {
    /**
     * 
     */
    private static final String YES = "YES";
    /**
     * 
     */
    private static final String NO = "NO";
    private final Scanner in=new Scanner(System.in,"utf-8");
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new D().solve();
    }
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        TreeMap<Integer, Long> aliceFishs = getFishs(n);
        TreeMap<Integer, Long> robertFishs = getFishs(m);
        out.println(solve(aliceFishs,robertFishs));
//        out.println("put result here"+aliceFishs);
//        out.println("put result here"+robertFishs);
        out.flush();
    }
    /**
     * @param aliceFishs
     * @param robertFishs
     * @return
     */
    private String solve(TreeMap<Integer, Long> aliceFishs, TreeMap<Integer, Long> robertFishs) {
        while(true){
            if(aliceFishs.size()==0){
                return NO;
            }
            if(robertFishs.size()==0){
                return YES;
            }
            Integer aLastK = aliceFishs.lastKey();
            Integer rLastK = robertFishs.lastKey();

            if(aLastK>rLastK){
                return YES;
            }
            if(aLastK==rLastK){
                Long aCount = aliceFishs.pollLastEntry().getValue();
                Long rCount = robertFishs.pollLastEntry().getValue();
                long dif = aCount-rCount;
                if(dif>0){
                    return YES;
                }
                if(dif==0){
                    continue;
                }
                if(aliceFishs.size()==0){
                    return NO;
                }
                Long rc = robertFishs.get(aliceFishs.lastKey());
                if(rc==null){
                    rc=0l;
                }
                rc+=-dif;
                robertFishs.put(aliceFishs.lastKey(), rc);
                continue;
            }
            Long rc = robertFishs.pollLastEntry().getValue();
            if(robertFishs.size()==0){
                robertFishs.put(aLastK, rc);
            }else{
                Entry<Integer, Long> le = robertFishs.lastEntry();
                robertFishs.put(le.getKey(), le.getValue()+rc);
            }
        }
    }
    
    protected TreeMap<Integer, Long> getFishs(int n) {
        TreeMap<Integer, Long> fishes=new TreeMap<Integer, Long>();
        for(int i=0;i<n;i++){
            int fishType = in.nextInt();
            Long fishTypeCount = fishes.get(fishType);
            if(fishTypeCount==null){
                fishTypeCount=0l;
            }
            fishTypeCount++;
            fishes.put(fishType, fishTypeCount);
        }
        return fishes;
    }

}