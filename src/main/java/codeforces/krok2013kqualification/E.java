/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * E.java 26.04.2013 20:30:54
 *********************************/
package codeforces.krok2013kqualification;

/**
 * @author starasov
 *
 */
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class E {
    private final Scanner in=new Scanner(System.in,"utf-8");
    private final PrintWriter out=new PrintWriter(new BufferedOutputStream(System.out),true);
    @SuppressWarnings("unused")
    private final PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new E().solve();
    }
    Map<Integer, Node> nodes=new HashMap<Integer, Node>();
    private String string;
    private char[] stringChars;
    private long targetHash;
    
    private static class Node{
        String s;
        int id;
        List<Node> children=new ArrayList<Node>();
        public Node(int id, String s) {
            this.id = id;
            this.s = s;
        }
        @Override
        public String toString() {
            return this.getClass().getSimpleName()+"{id="+id+", s="+s+", children="+children+"}";
        }
        
        
        
    }
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();

        Node root = new Node(1,"");
        nodes.put(1, root);
        for(int i=2;i<=n;i++){
            int parentId=in.nextInt();
            String s=in.next();
            Node child = getOrCreateNode(i);
            child.s=s;
            Node parent = getOrCreateNode(parentId);
            parent.children.add(child);
        }
        string=in.next();
        stringChars=string.toCharArray();
        targetHash = getHash(stringChars, 0, stringChars.length);
        out.println(solve(root,new StringBuilder(3*100000+100/*на всякий пожарный :)*/),0));
        out.flush();
    }
    /**
     * @param node
     * @param path
     * @param i
     * @return
     */
    private int solve(Node node, StringBuilder fullPath, int from) {
        int count=0;
        fullPath.append(node.s);
        int indexOf;
        char[] fullPathChars = fullPath.toString().toCharArray();
        while((indexOf=indexOf(fullPathChars,0,fullPathChars.length,stringChars,0,stringChars.length,from))!=-1){
            count++;
            from=indexOf+1;
        }
        for(Node child:node.children){
            count+=solve(child, fullPath, from);
        }
        fullPath.setLength(fullPath.length()-node.s.length());
        return count;
    }
    Node getOrCreateNode(int id){
        Node node = nodes.get(id);
        if(node==null){
            node=new Node(id,null);
            nodes.put(id, node);
        }
        return node;
    }
    
    int indexOf(char[] source, int sourceOffset, int sourceCount,
            char[] target, int targetOffset, int targetCount,
            int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }
        
        
        char first = target[targetOffset];
        long sourceHash = getHash(source, fromIndex, fromIndex+targetCount);
        
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            while(targetHash!=sourceHash){
                i++;
                if(i>=max){
                    break;
                }
                
                char nextSourceChar = source[i];
                char outSourceChar = source[i-targetCount-1];
                sourceHash=sourceHash+nextSourceChar-outSourceChar;
            }
            
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }
    static long getHash(char[] chars, int from, int count){
        long hash=0;
        for(int i=from, n=from+count;i<n;i++){
            hash+=chars[i];
        }
        return hash;
    }


    
    

}