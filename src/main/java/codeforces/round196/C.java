package codeforces.round196;
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
        int n = in.nextInt();    
        int m = in.nextInt();
        int k = in.nextInt();
        int endCount=(n-m)/(k-1);
        if((n-m)%(k-1)!=0){
            endCount++;
        }
        int beginCount=m-endCount;

        if(beginCount<0){
            beginCount=0;
        }
        int div=1000000009;
        long score=getScore2(k,div,beginCount);
        score+=endCount*(k-1); 

        long x = Math.max(0, m - (n - n % k) / k * (k-1) - n % k);
        
        long result = ((exponentiationBySquaring(2, x+1, div)-2)*k + m-x*k)%div;
        if(result<0){
            result+=div;
        }
        out.println(result);
    }

    /**
     * @return
     */
    private long getScore(int k, int div, int beginCount) {
        long score=0;


        for(int i=0;i<beginCount/k;i++){
            score=(score+k)*2;
            if(score>div){
                score=score%div;
            }
        }
        return (score+beginCount%k)%div;
    }

    private long getScore2(int k, int div, int beginCount) {
        long result = (exponentiationBySquaring(2,(beginCount/k)+1,div)-2)*k+beginCount%k;
        return result;
    }
    long pow(long value, long pow, long div){
        long result=1;
        for(int i=0;i<pow;i++){
            result=(result*value)%div;
        }
        return result;
    }
    private static long exponentiationBySquaring(long number, long pow, long mod) {
        final int MAX_BIT=62;
        long mask=1l<<MAX_BIT;
        long result=1l;
        boolean modEnabled=mod>0;
        while(mask>0){
            result*=result;
            if(modEnabled){
                result%=mod;
            }
            if((mask&pow)!=0){
                result*=number;
            }
            if(modEnabled){
                result%=mod;
            }
            mask>>=1;
        }
        return result;
    }


}
//-------------------------------