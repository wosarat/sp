/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2014 NVision Group, Inc. All rights reserved.
 *
 * BellmanFord.java 10.11.2014 21:33:50
 *********************************/
package stepic2014;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class BellmanFord {
    private final static long START_TIME=System.currentTimeMillis();
    private final static boolean LOG_ENABLED=true;
    private final static boolean ONLINE_JUDGE = LOG_ENABLED && (System.getProperty("ONLINE_JUDGE") != null);
    private final static String SYSTEM_ENCODING="utf-8";


    private static class Logger{
        private final PrintWriter logWriter=Util.newPrintWriter(System.err,true);
        private final DecimalFormat df=new DecimalFormat("0.000");
        private void message(String type, String message, Object ... params){
            if(ONLINE_JUDGE){
                return;
            }
            logWriter.printf("["+type+"] "+df.format((System.currentTimeMillis()-START_TIME)/1000.0)+": "+message+"\r\n", params);
        }
        public void debug(String message, Object ... params){
            message("DEBUG", message, params);
        }
    }

    private final static class Util{
        public static PrintWriter newPrintWriter(OutputStream out, boolean autoFlush){
            try {
                return new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(out), SYSTEM_ENCODING),autoFlush);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final static class FastScanner{
        private BufferedReader reader;
        private StringTokenizer currentTokenizer;
        public FastScanner(Reader reader) {
            if(reader instanceof BufferedReader){
                this.reader=(BufferedReader) reader;
            }else{
                this.reader=new BufferedReader(reader);
            }
        }

        public String next(){
            if(currentTokenizer==null || !currentTokenizer.hasMoreTokens()){
                try {
                    currentTokenizer=new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }

            return currentTokenizer.nextToken();
        }
        public int nextInt(){
            return Integer.parseInt(next());
        }

        public long nextLong(){
            return Long.parseLong(next());
        }

        public double nextDouble(){
            return Double.parseDouble(next());
        }

    }

    private final static Logger log=new Logger();
    private final BufferedReader reader;
    private final FastScanner in;
    private final PrintWriter out=Util.newPrintWriter(System.out,false);

    public BellmanFord(BufferedReader reader){
        this.reader=reader;
        in=new FastScanner(this.reader);
    }

    public static void main(String[] args) throws IOException {
        log.debug("Started");
        try{
            new BellmanFord(new BufferedReader(new InputStreamReader(System.in, SYSTEM_ENCODING))).run();
        }finally{
            log.debug("Stopped");
        }
    }

    void run(){
        solve();
        out.flush(); 
    }

    private static class Edge{
        private int v2;
        private int weight;
        public Edge(int v2, int weight) {
            super();
            this.v2 = v2;
            this.weight = weight;
        }

    }
    private void solve(){
        int vCount = in.nextInt(); 
        int e = in.nextInt();



        @SuppressWarnings("unchecked")
        List<Edge>[] graph=new List[vCount];
        for(int i=0;i<e;i++){
            int v1 = in.nextInt()-1;
            int v2 = in.nextInt()-1;
            int w=in.nextInt();
            List<Edge> v1Edges = graph[v1];
            if(v1Edges==null){
                v1Edges=new ArrayList<Edge>();
                graph[v1]=v1Edges;
            }
            v1Edges.add(new Edge(v2, w));
        }
//        int u = in.nextInt()-1;
//        int v = in.nextInt()-1;

        int[] dist=new int[vCount];
        int[] prev=new int[vCount];
        Arrays.fill(dist, 0);
        Arrays.fill(prev, -1);
//        dist[u]=0;
        boolean hasNegativeCycles=false;
        for(int i=0;i<vCount;i++){
            hasNegativeCycles=relaxEdges(graph,dist,prev);
            if(!hasNegativeCycles){
                break;
            }
        }
        System.out.println(hasNegativeCycles?1:0);
    }

    /**
     * @param graph
     * @param dist
     * @param prev
     */
    private static boolean relaxEdges(List<Edge>[] graph, int[] dist, int[] prev) {
        boolean hasRelaxes=false;
        for(int v=0;v<graph.length;v++){
            List<Edge> edges=graph[v];
            if(edges==null){
                continue;
            }
            for(Edge e:edges){
                hasRelaxes|=relaxEdge(v,e,dist,prev);
            }
        }
        return hasRelaxes;
    }

    private static boolean relaxEdge(int v, Edge e, int[] dist, int[] prev) {
        int newDist=dist[v]+e.weight;
        if(newDist<dist[e.v2]){
            dist[e.v2]=newDist;
            prev[e.v2]=v;
            return true;
        }else{
            return false;
        }
    }
}
