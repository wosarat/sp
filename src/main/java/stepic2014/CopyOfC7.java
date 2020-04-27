/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * C1.java 22.09.2014 17:41:22
 *********************************/
package stepic2014;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class CopyOfC7 {
    private final static long START_TIME_MS=System.currentTimeMillis();
    private final static String ZERO="0";
    private static void log(String format, Object ... args){
        System.err.format("%s: ", System.currentTimeMillis()-START_TIME_MS);
        System.err.format(format, args);
        System.err.println();
    }

    public static void main(String[] args) {
        String[] strs={"fex_1","filey_10","filez_2","fileadsf_22","fileas_3","fileddd_33","fileasdf_0","fileas_00","fileasd_123"};
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = o1.length()-o2.length();
                if(result!=0){
                    return result;
                }
                return o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(strs));
    }

    private static String solveJavaBigInteger(String a, String b) {
        return new BigInteger(a).multiply(new BigInteger(b)).toString();
    }


    private static String multiply(String a, String b){
        int n=Math.max(a.length(), b.length());
        if(n<=1){
            return String.valueOf(Long.parseLong(a)*Long.parseLong(b));
        }
        int halfN=n/2;
        String [] alAndAr=split(a,halfN);
        String al=alAndAr[0];
        String ar=alAndAr[1];
        String [] blAndbr=split(b,halfN);
        String bl=blAndbr[0];
        String br=blAndbr[1];
        String result = multiply(al, bl);
        result=shift(result,halfN);
        result=add(result,multiply(ar, bl));
        result=add(result,multiply(al, br));
        result=shift(result,halfN);
        result=add(result,multiply(ar, br));
        return result;

    }
    
    private static String karatsuba(String a, String b){
        if(ZERO.equals(a)||ZERO.equals(b)){
            return ZERO;
        }
        
        int n=Math.max(a.length(), b.length());
        int min=Math.min(a.length(), b.length());
        if(n+min<=18){
            return String.valueOf(Long.parseLong(a)*Long.parseLong(b));
        }
        int halfN=n/2;
        String [] alAndAr=split(a,halfN);
        String al=alAndAr[0];
        String ar=alAndAr[1];
        String [] blAndbr=split(b,halfN);
        String bl=blAndbr[0];
        String br=blAndbr[1];
        String p1 = karatsuba(al, bl);
        String p2 = karatsuba(ar, br);
        String p3 = karatsuba(add(al, ar), add(bl, br));
        String result=subtract(p3, add(p1,p2));
        result=shift(result,halfN);
        result=add(result, shift(p1, halfN*2));
        result=add(result,p2);
        return result;

    }


    /**
     * @param p3
     * @param add
     * @return
     */
    private static String subtract(String a, String b) {
        int n = Math.max(a.length(), b.length());
        if(n<=18){
            return String.valueOf(Long.parseLong(a)-Long.parseLong(b));
        }
        StringBuilder result=new StringBuilder(n);
        int transfer=0;
        for(int i=0;i<n;i++){
            int digitA=getDigit(a,i);
            int digitB=getDigit(b,i);
            int resultDigit = digitA-digitB-transfer;
            transfer=0;
            while(resultDigit<0){
                resultDigit+=10;
                transfer++;
            }
            result.append(resultDigit);
        }
        if(transfer>0){
            throw new IllegalStateException("Negative results not suppored");
        }
        return normalize(result.reverse().toString());
    }

    private static String normalize(String string) {
        int i=0;
        while(i<string.length()&& string.charAt(i)=='0'){
            i++;
        }
        if(string.length()==i){
            return ZERO;
        }
        return string.substring(i);
    }

    private static String add(String a, String b) {
        int n = Math.max(a.length(), b.length());
        if(n<=18){
            return String.valueOf(Long.parseLong(a)+Long.parseLong(b));
        }

        StringBuilder result=new StringBuilder(n);
        int transfer=0;
        for(int i=0;i<n;i++){
            int digitA=getDigit(a,i);
            int digitB=getDigit(b,i);
            int resultDigit = digitA+digitB+transfer;
            transfer=resultDigit/10;
            resultDigit=resultDigit%10;
            result.append(resultDigit);
        }
        if(transfer>0){
            result.append(transfer);
        }
        return result.reverse().toString();
    }

    private static int getDigit(String integer, int n) {
        if(integer.length()<=n){
            return 0;
        }
        return integer.charAt(integer.length()-n-1)-'0';
    }

    private static String shift(String result, int n) {
        StringBuilder sb=new StringBuilder(result.length()+n);
        sb.append(result);
        for(int i=0;i<n;i++){
            sb.append('0');
        }
        return sb.toString();
    }

    private static String[] split(String string, int halfN) {
        if(halfN>=string.length()){
            return new String[]{ZERO, string};
        }
        return new String[]{string.substring(0, string.length()-halfN), string.substring(string.length()-halfN, string.length())};
    }

}
