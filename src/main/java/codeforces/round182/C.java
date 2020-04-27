package codeforces.round182;
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
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class C {
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

    public C(BufferedReader reader){
        this.reader=reader;
        in=new FastScanner(this.reader);
    }

    public static void main(String[] args) throws IOException {
        log.debug("Started");
        try{
            new C(new BufferedReader(new InputStreamReader(System.in, SYSTEM_ENCODING))).run();
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
        int sum=0;
        int minAbs=Integer.MAX_VALUE;
        int neg=0;
        for(int i=0;i<2*n-1;i++){
            int a = in.nextInt();
            if(a<0){
                neg++;
            }
            int absA=Math.abs(a);
            if(absA<minAbs){
                minAbs=absA;
            }
            sum+=absA;
        }
        if(neg%2!=0 && neg!=n && n%2==0){
            sum-=2*minAbs;
        }
        out.print(sum);
    }

}
//-------------------------------