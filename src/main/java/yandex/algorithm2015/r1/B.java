/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2015 NVision Group, Inc. All rights reserved.
 *
 * B.java 25.05.2015 19:38:25
 *********************************/
package yandex.algorithm2015.r1;

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
import java.util.Random;
import java.util.StringTokenizer;

public class B {
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
                currentTokenizer=new StringTokenizer(nextLine());
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
        
        public String nextLine(){
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }


    }

    private final static Logger log=new Logger();
    private final BufferedReader reader;
    private final FastScanner in;
    private final PrintWriter out=Util.newPrintWriter(System.out,false);

    public B(BufferedReader reader){
        this.reader=reader;
        in=new FastScanner(this.reader);
    }

    public static void main(String[] args) throws IOException {
        log.debug("Started");
        try{
            new B(new BufferedReader(new InputStreamReader(System.in, SYSTEM_ENCODING))).run();
        }finally{
            log.debug("Stopped");
        }
    }

    void run(){
        solve();
        out.flush(); 
    }

    class City{
        int x;
        int y;
        int a;
    }
    private int solve(int n, City[]  cities){
        long curr = System.currentTimeMillis();
        int result=Integer.MAX_VALUE;
        if(n==1){
            result=cities[0].a;
        }else if(n==2){
            result=Math.abs(cities[0].a-cities[1].a);
        }else{
            for(int p1=0;p1<n-1;p1++){
                City city1 = cities[p1];
                int x1=city1.x;
                int y1=city1.y;
                int a1=city1.a;
//                if(System.currentTimeMillis()-curr>11870){
//                    return result;
//                }
                for(int p2=p1+1;p2<n;p2++){
                    City city2 = cities[p2];
                    int x2=city2.x;
                    int y2=city2.y;
                    int a2=city2.a;
                    int k1=0;
                    int k2=0;
                    int x21=x2 - x1;
                    int y21=y2 - y1;
                    for(int i=0;i<n;i++){
//                        if(i==p1||i==p2){
//                            continue;
//                        }
                        City city3 = cities[i];
                        int x3=city3.x;
                        int y3=city3.y;
                        int a3=(i==p1||i==p2)?0:city3.a;
                        boolean d = (x3 - x1) * (y21) - (y3 - y1) * (x21)<0;
                        k1=d?k1+a3:k1;
                        k2=d?k2:k2+a3;
//                        if(d){
//                            k1+=a3;
//                        }else{
//                            k2+=a3;
//                        }

//                        if(i>n*0.8){
//                            int aa=k1-k2;
//                            aa=(aa < 0) ? -aa : aa;
//                            int bestPossibleResult=aa-(s-k1-k2);
//                            if(bestPossibleResult>=result){
//                                continue a;
//                            }
//                        }
                        
                    }
                    int newResult = 0;//getMin(a1,a2,k1-k2);
                    result=(newResult<result)?newResult:result;
                }
            }
        }
        return result;
    }
    private void solve(){
//        int n = in.nextInt(); 
//        int[] x= new int[n];
//        int[] y= new int[n];
//        int[] a= new int[n];
//        for(int i=0;i<n;i++){
//            x[i]=in.nextInt();
//            y[i]=in.nextInt();
//            a[i]=in.nextInt();
//        }

        int n=1000;
      City[] cities= new City[n];

        Random r = new Random();
        for(int i=0;i<n;i++){
            cities[i]=new City();
            cities[i].x=r.nextInt(40000)-20000;
            cities[i].y=r.nextInt(40000)-20000;
            cities[i].a=r.nextInt(1000000)+1;
        }
        out.println(solve(n, cities));
    }

    /**
     * @param a1
     * @param a2
     * @param k1
     * @param k2
     * @return
     */
    private static int getMin(int a1, int a2, int k12) {
        int r1=Math.abs(k12+a1+a2);
        int r2=Math.abs(k12+a1-a2);
        int r3=Math.abs(k12-a1+a2);
        int r4=Math.abs(k12-a1-a2);
        return Math.min(Math.min(r1, r2), Math.min(r3, r4)) ;
    }

}
