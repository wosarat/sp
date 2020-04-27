/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Template.java 15.04.2013 19:28:31
 *********************************/
package codeforces.krok2013;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B {
    private final Scanner in=new Scanner(System.in,"utf-8");
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new B().solve();
    }

    private static class Node{
        int id;
        int linkCount=0;
        public Node(int id) {
            super();
            this.id = id;
        }
        @Override
        public String toString() {
            return "Node{"+id+":"+linkCount+"}";
        }
        
    }
    Map<Integer, Node> nodes=new HashMap<Integer,Node>();
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        for(int i=0;i<m;i++){
            int x = in.nextInt();
            int y = in.nextInt();
            Node nodeX=getOrCreateNode(x);
            nodeX.linkCount++;
            Node nodeY=getOrCreateNode(y);
            nodeY.linkCount++;
        }
        Map<Integer,Integer> linkCounts=new HashMap<Integer, Integer>();
        for(Node node:nodes.values()){
            Integer linkCount = linkCounts.get(node.linkCount);
            if(linkCount==null){
                linkCount=0;
            }
            linkCount++;
            linkCounts.put(node.linkCount, linkCount);
        }
        String hash=getTopologyHash(linkCounts.get(1),linkCounts.get(2),linkCounts.get(n-1));
        
        String result="unknown topology";
        if(hash.equals(getTopologyHash(null, n, null))){
            result="ring topology";
        }else if(hash.equals(getTopologyHash(2, n-2, null))){
            result="bus topology";
        }else if(hash.equals(getTopologyHash(n-1, null, 1))){
            result="star topology";
        }
        out.println(result);
        out.flush();
    }
    /**
     * @param integer
     * @param integer2
     * @param integer3
     * @return
     */
    @SuppressWarnings("static-method")
    private String getTopologyHash(Integer integer1, Integer integer2, Integer integerN_2) {
        return integer1+":"+integer2+":"+integerN_2;
    }
    /**
     * @param id
     * @return
     */
    private Node getOrCreateNode(int id) {
        Node result = nodes.get(id);
        if(result==null){
            result=new Node(id);
            nodes.put(id, result);
        }
        return result;
    }

}