package codeforces.round305;
//-------------------------------------------------------------------------------------------------------
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class C implements Runnable {
    private final static long START_TIME=System.currentTimeMillis();
    private final static boolean LOG_ENABLED=true;
    private final static boolean ONLINE_JUDGE = LOG_ENABLED && (System.getProperty("ONLINE_JUDGE") != null);
    private final static String SYSTEM_ENCODING="utf-8";
    private final static int DEFAULT_BUFFER_SIZE=100000;


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
        
        public static PrintWriter newPrintWriter(Writer writer, boolean autoFlush){
            return new PrintWriter(writer,autoFlush);
        }

        
        private static void flushStringBuilder(Writer writer, StringBuilder sb) throws IOException {
            if(sb.length()>DEFAULT_BUFFER_SIZE){
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
    private final PrintWriter out;


    public C(Reader reader, Writer writer){
        this.reader=new BufferedReader(reader);
        this.out=new PrintWriter(writer,false);
        in=new FastScanner(this.reader);
    }

    public static void main(String[] args) throws IOException {
        new C(new InputStreamReader(System.in, SYSTEM_ENCODING),new OutputStreamWriter(new BufferedOutputStream(System.out), SYSTEM_ENCODING)).run();
    }

    @Override
    public void run(){
        log.debug("Started");
        try{
            solve();
            out.flush();
        }finally{
            log.debug("Stopped");
        }
    }

    private void solve(){
        int m = in.nextInt();
        int h1= in.nextInt();
        int a1= in.nextInt();
        int x1= in.nextInt();
        int y1= in.nextInt();
        int h2= in.nextInt();
        int a2= in.nextInt();
        int x2= in.nextInt();
        int y2= in.nextInt();
        out.println(solve(m,h1,a1,x1,y1,h2,a2,x2,y2));
    }

    private long solve(int m, int h1, int a1, int x1, int y1, int h2, int a2, int x2, int y2) {
        AtomicInteger aTime1=new AtomicInteger(0);
        AtomicInteger p1=new AtomicInteger(0);
        findParams(h1,x1,y1, m, a1, aTime1, p1);
        log.debug("t1=%s, p1=%s", aTime1.get(), p1.get());
        
        if(aTime1.get()==0){
            return -1;
        }
        
        AtomicInteger aTime2=new AtomicInteger(0);
        AtomicInteger p2=new AtomicInteger(0);
        findParams(h2,x2,y2, m, a2, aTime2, p2);
        log.debug("t2=%s, p2=%s", aTime2.get(), p2.get());
        
        if(aTime2.get()==0){
            return -1;
        }
        return findTime(p1.get(),aTime1.get(), p2.get(), aTime2.get());
    }

    private static void findParams(long h, long x, long y, int m, int a, AtomicInteger aTime, AtomicInteger p){
        boolean[] availableValues = new boolean[m];
        while(true){
            h=(x*h+y)%m;
            p.incrementAndGet();
            if(availableValues[(int) h]==true){
                return;
            }
            availableValues[(int) h]=true;
            if(h==a){
                aTime.set(p.get());
            }
        }
    }
    
    private long findTime(int p1, int t1, int p2, int t2) {
        int a=p1;
        int b=-p2;
        int c=t1-t2;
        AtomicLong x0=new AtomicLong();
        AtomicLong y0=new AtomicLong();
        AtomicLong g=new AtomicLong();
        findAnySolution(a, b, c, x0, y0, g);
        log.debug("x0=%s, y0=%s, g=%s", x0,y0,g);
        return 1l*t1+1l*p1*x0.get();
    }

    int gcd (int a, int b, AtomicLong x, AtomicLong y) {
        if (a == 0) {
            x.set(0); y.set(1);
            return b;
        }
        AtomicLong x1=new AtomicLong();
        AtomicLong y1=new AtomicLong();
        int d = gcd (b%a, a, x1, y1);
        x.set(y1.get() - (b / a) * x1.get());
        y.set(x1.get());
        return d;
    }
    boolean findAnySolution(int a, int b, int c, AtomicLong x0, AtomicLong y0, AtomicLong g) {
        g.set(gcd (Math.abs(a), Math.abs(b), x0, y0));
        if (c % g.get() != 0){
            return false;
        }
        x0.set(x0.get()*c / g.get());
        y0.set(y0.get()*c / g.get());
        if (a < 0)   x0.set(x0.get()*-1);
        if (b < 0)   y0.set(y0.get()*-1);
        return true;
    }        


}
//-------------------------------