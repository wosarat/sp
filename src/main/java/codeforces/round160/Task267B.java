/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/
package codeforces.round160;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Task267B {
    private final int DOMINO_VARIANTS=7;
    private final Scanner in=new Scanner(System.in);
    private final PrintWriter out=new PrintWriter(System.out);
    public static void main(String[] args) {
        new Task267B().solve();
    }
    public static class Domino{
        int id;
        int part1;
        int part2;
        boolean rotated=false;


        public Domino(int id,int part1, int part2) {
            super();
            this.part1 = part1;
            this.part2 = part2;
            this.id=id;
        }
        @Override
        public String toString() {
            return getRotatedPart1()+":"+getRotatedPart2();
        }
        int getRotatedPart2(){
            return rotated?part1:part2;
        }
        int getRotatedPart1(){
            return rotated?part2:part1;
        }
        boolean isDouble(){
            return part1==part2;
        }


    }

    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        Set<Domino> heap=new HashSet<Domino>();
        for(int i=0;i<n;i++){
            heap.add(new Domino(i+1, in.nextInt(), in.nextInt()));
        }
        LinkedList<Domino> chain=new LinkedList<Domino>();
        Map<Integer, List<Domino>> doubles = removeDoubles(heap);
        Set<Integer> doubleValues=new HashSet<Integer>(doubles.keySet());
        for(Domino d:heap){
            doubleValues.remove(d.part1);
            doubleValues.remove(d.part2);
        }
        if(heap.isEmpty() && doubleValues.size()==1){
            printResult(doubles.get(doubleValues.iterator().next()));
        }else if(!doubleValues.isEmpty()){
            out.println("No solution");
        }else{
            boolean result = solve(chain,heap);
            if(result){
                printResult(calculateFinalChain(chain, doubles));
            }else{
                out.println("No solution");
            }
        }
        out.flush();
    }


    /**
     * @param chain
     * @param doubles
     */
    private List<Domino> calculateFinalChain(LinkedList<Domino> chain, Map<Integer, List<Domino>> doubles) {
        List<Domino> result=new ArrayList<Task267B.Domino>();
        
        Iterator<Domino> i = chain.iterator();
        while(i.hasNext()){
            Domino domino = i.next();
            List<Domino> dbls = doubles.remove(domino.getRotatedPart1());
            if(dbls!=null){
                result.addAll(dbls);
            }
            result.add(domino);
            dbls = doubles.remove(domino.getRotatedPart2());
            if(dbls!=null){
                result.addAll(dbls);
            }
        }
        return result;
        
    }


    /**
     * @param heap
     * @return
     */
    private static Map<Integer,List<Domino>> removeDoubles(Set<Domino> heap) {
        Iterator<Domino> i = heap.iterator();
        Map<Integer, List<Domino>> result=new HashMap<Integer, List<Domino>>();
        while(i.hasNext()){
            Domino d = i.next();
            if(d.isDouble()){
                List<Domino> doubles = result.get(d.part1);
                if(doubles==null){
                    doubles=new ArrayList<Task267B.Domino>();
                    result.put(d.part1, doubles);
                }
                doubles.add(d);
                i.remove();
            }

        }
        
        return result;
    }

    /**
     * @param dominos
     */
    private void printResult(List<Domino> dominos) {
        for(Domino d:dominos){
            out.printf("%s %s\r\n", d.id,d.rotated?"-":"+");
        }

    }

    /**
     * @param heap
     * @return
     */
    private boolean solve(LinkedList<Domino> chain, Set<Domino> heap) {
        if(heap.isEmpty()){
            return true;
        }
        List<Domino> nextDominos=getNextStepDominos(chain, heap);
        for(Domino nextDomino:nextDominos){
            heap.remove(nextDomino);
            chain.add(nextDomino);
            if(chain.size()==1){
                nextDomino.rotated=false;
            }
            boolean result = solve(chain,heap);
            if(result==false && chain.size()==1){
                nextDomino.rotated=true;
                result=solve(chain,heap);
                if(result==false){
                    nextDomino.rotated=false;
                }
            }
            if(result==true){
                return true;
            }
            chain.remove(nextDomino);
            heap.add(nextDomino);
        }
        return false;
    }

    /**
     * @param heap
     * @param lastPart2
     * @return
     */
    private List<Domino> getNextStepDominos(LinkedList<Domino> chain, Set<Domino> heap) {
        int[] dominoCounts=new int[DOMINO_VARIANTS];
        for(Domino domino:heap){
            dominoCounts[domino.part1]++;
            dominoCounts[domino.part2]++;
        }
        int oddCount=0;
        Set<Integer> possibleIndexes=new HashSet<Integer>();
        for(int i=0,n=dominoCounts.length;i<n;i++){
            int count = dominoCounts[i];
            if(count%2==1){
                oddCount++;
                possibleIndexes.add(i);
            }
        }
        boolean possible = (oddCount==0 || oddCount==2);
        if(!possible){
            return Collections.emptyList();
        }

        if(chain.isEmpty()){
            if(oddCount==0){
                return new ArrayList<Domino>(heap);
            }else{
                return selectDominos(heap, possibleIndexes);
            }
        }else{
            if(oddCount==0|| possibleIndexes.contains(chain.getLast().getRotatedPart2())){
                possibleIndexes.clear();
                possibleIndexes.add(chain.getLast().getRotatedPart2());
                return selectDominos(heap, possibleIndexes);
            }
            return Collections.emptyList();
        }
    }

    /**
     * @param heap
     * @param possibleIndexes
     * @return
     */
    private List<Domino> selectDominos(Set<Domino> heap, Set<Integer> possibleIndexes) {
        List<Domino> result=new ArrayList<Domino>();
        for(Domino d:heap){
            if(possibleIndexes.contains(d.part1)){
                d.rotated=false;
                result.add(d);
            }
            if(possibleIndexes.contains(d.part2)){
                d.rotated=true;
                result.add(d);
            }

        }

        return result;
    }

    private boolean isSolutionPossible(LinkedList<Domino> dominos){
        int[] dominoCounts=new int[DOMINO_VARIANTS];
        for(Domino domino:dominos){
            dominoCounts[domino.part1]++;
            dominoCounts[domino.part2]++;
        }
        int oddCount=0;
        for(int count:dominoCounts){
            if(count%2==1){
                oddCount++;
            }
        }
        boolean possible = (oddCount==0 || oddCount==2);
        return possible;
    }


}
