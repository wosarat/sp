package codeforces.round183;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

public class E {
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

    public final static class Util{
        public final static String LINE_SEPARATOR=System.getProperty("line.separator"); 
        public static PrintWriter newPrintWriter(OutputStream out, boolean autoFlush){
            try {
                return new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(out), SYSTEM_ENCODING),autoFlush);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }
        private static void flushStringBuilder(Writer writer, StringBuilder sb) throws IOException {
            if(sb.length()>100000){
                writer.append(sb);
                sb.setLength(0);
            }
        }

        public static void printArray(Writer writer, Object ... elements) throws IOException{
            StringBuilder sb = new StringBuilder();
            boolean first=true;
            for(Object o:elements){
                if(first){
                    first=false;
                }else{
                    sb.append(" ");
                }
                sb.append(o);
                flushStringBuilder(writer, sb);
            }
            sb.append(LINE_SEPARATOR);
            writer.append(sb);
        }
        public static void printArray(Writer writer, int ... elements) throws IOException{
            StringBuilder sb = new StringBuilder();
            boolean first=true;
            for(Object o:elements){
                if(first){
                    first=false;
                }else{
                    sb.append(" ");
                }
                sb.append(o);
                flushStringBuilder(writer, sb);
            }
            sb.append(LINE_SEPARATOR);
            writer.append(sb);
        }

        public static int[] getIntArray(FastScanner in, int n) {
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i]=in.nextInt();
            }
            return arr;
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
                    currentTokenizer=new  StringTokenizer(reader.readLine());
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
    private int cacheIndex;

    public E(BufferedReader reader){
        this.reader=reader;
        in=new FastScanner(this.reader);
    }

    public static void main(String[] args) throws IOException {
        log.debug("Started");
        try{
            if(ONLINE_JUDGE){
                new E(new BufferedReader(new InputStreamReader(System.in, SYSTEM_ENCODING))).run();
            }else{
                PipedReader pipeReader = new PipedReader();
                final Writer testWriter = new PipedWriter(pipeReader);
                new Thread(){
                    @Override
                    public void run() {
                        Random random = new Random(3);
                        try {
                            int n=5000;
                            int k=3;
                            testWriter.append(n+" "+k+"\r\n");
                            
                            Set<Integer> numbers=new HashSet<Integer>();
                            int i=0;
                            while(numbers.size()<=n){
                                numbers.add((int) (random.nextDouble()*1000001));
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                numbers.add(i++);
                                
                            }
                            Util.printArray(testWriter, numbers.toArray());
                            testWriter.flush();
                            testWriter.close();
                            log.debug("Test input has been generated. n="+numbers.size());
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                }.start();
                new E(new BufferedReader(pipeReader)).run();
            }
        }finally{
            log.debug("Stopped");
        }
    }

    void run(){
        solve();
        out.flush(); 
    }

    private void solve(){
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr=Util.getIntArray(in,n);
        Arrays.sort(arr);
        int m=n-k;
        if(m<=0){
            m=1;
        }
        BitSet hasValue = new BitSet(1000001);

        int[] badReminderCounts=new int[1000001];
        for(int i=1;i<n;i++){
            int a1 = arr[i];
            hasValue.clear();
            for(int j=0;j<i;j++){
                int a2=arr[j];
                int badReminder=a1-a2;
                for(int x=1;x<=7;x++){
                    if(badReminder %x==0){
                        int reminder = badReminder/x;
                        if(reminder<m || hasValue.get(reminder)){
                            continue;
                        }
                        hasValue.set(reminder);
                        badReminderCounts[reminder]++;

                    }
                }

            }
        }
        log.debug("badReminderCounts.size=%s", badReminderCounts.length);
        int maxA = arr[n-1];
        log.debug("maxA=%s", maxA);
        
        cacheIndex=-1;
        Map<Integer, Integer> reminderCountsCache=new HashMap<Integer,Integer>();



        M: for(;m<=maxA+1;m++){
//            int reminderCount = 0;
//            for(int i=1;i<20;i++){
//                if(m*i>maxA){
//                    break;
//                }
//                reminderCount+=badReminderCounts[m*i];
//                if(reminderCount>k){
//                    continue M;
//                }
//            }
                if(badReminderCounts[m]>k){
                    continue M;
                }
            
            updateReminderCountsCache(n, arr, m, reminderCountsCache);
            Map<Integer, Integer> reminderCounts=new HashMap<Integer,Integer>(reminderCountsCache);
            int sameReminders=0;
            for(int i=cacheIndex+1;i<n;i++){
                int o = arr[i]%m;
                Integer count = reminderCounts.get(o);
                if(count==null){
                    count=0;
                }
                count++;
                if(count>1){
                    sameReminders++;
                    if(sameReminders>k){
                        continue M;
                    }
                }
                reminderCounts.put(o, count);
            }
            break;
        }
        if(m>maxA){
            m=maxA+1;
        }
        out.print(m);
    }

    protected void updateReminderCountsCache(int n, int[] arr, int m, Map<Integer, Integer> reminderCountsCache) {
        for(int i=cacheIndex+1;i<n;i++){
            int a=arr[i];
            if(a>=m){
                break;
            }
            cacheIndex=i;
            reminderCountsCache.put(a, 1);
        }
    }

}