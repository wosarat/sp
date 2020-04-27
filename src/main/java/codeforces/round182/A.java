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

public class A {
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
        
    }

    private final static Logger log=new Logger();
    private final BufferedReader reader=new BufferedReader(new InputStreamReader(System.in, SYSTEM_ENCODING));
    private final FastScanner in=new FastScanner(reader);
    private final PrintWriter out=Util.newPrintWriter(System.out,false);

    public A() throws IOException{
    }
    
    public static void main(String[] args) throws IOException {
        log.debug("Started");
        try{
            new A().run();
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
        int m = in.nextInt();
        int posCount=0;
        int negCount=0;
        for(int i=0;i<n;i++){
            if(in.next().equals("1")){
                posCount++;
            }else{
                negCount++;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            int l=in.nextInt();
            int r=in.nextInt();
            String result="0";
            int count = r-l+1;
            int halfCount=count/2;
            if((count%2)==0 && posCount>=halfCount && negCount>=halfCount){
                result="1";
            }
            sb.append(result).append(System.lineSeparator());
        }
        out.print(sb.toString());

    }

}
//-------------------------------