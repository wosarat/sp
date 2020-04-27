/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * SieveofEratosthenes.java 12.07.2013 21:13:10
 *********************************/
package freak.primes;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * @author starasov
 *
 */
public class SieveofEratosthenes {

    /**
     * 
     */
    public SieveofEratosthenes() {
    }
    public static void main(String[] args) {
        //int n=Integer.MAX_VALUE-1;
        int n=100000000;
        
        BitSet nonPrime = new BitSet(n+1);
        nonPrime.set(0);
        nonPrime.set(1);
        for(int i=2;;i=nonPrime.nextClearBit(i+1)){
            long from = ((long)i)*i;
            if(from>n){
                break;
            }
            for(long j=from;j<=n;j+=i){
                nonPrime.set((int) j);
            }
        }
        nonPrime.flip(0, n+1);
        System.out.println(nonPrime.previousSetBit(n));
        System.out.println(nonPrime.previousSetBit(nonPrime.previousSetBit(n)-1));
        //nonPrime.flip(0, n+1);
        //System.out.println(nonPrime);
        
        
        List<Integer> firstPrimes=new ArrayList<Integer>((int) (1.1*n/Math.log(n)));
        for(int i=2;i<=n+1;i++){
            boolean isPrime=true;
            for(int prime:firstPrimes){
                if(prime*prime>i){
                    break;
                }
                if(i%prime==0){
                    isPrime=false;
                    break;
                }
            }
            if(isPrime){
                firstPrimes.add(i);
            }

        }
        System.out.println(firstPrimes.size());
    }
    

}
