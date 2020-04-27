package codeforces.round202;
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

public class B implements Runnable {
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


    public B(Reader reader, Writer writer){
        this.reader=new BufferedReader(reader);
        this.out=new PrintWriter(writer,false);
        in=new FastScanner(this.reader);
    }

    public static void main(String[] args) throws IOException {
        new B(new InputStreamReader(System.in, SYSTEM_ENCODING),new OutputStreamWriter(new BufferedOutputStream(System.out), SYSTEM_ENCODING)).run();
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
    int [] prices=new int[10];
    int minPrice=Integer.MAX_VALUE;
    static class DP{
        int digit;
        DP nextDP;
        int totalDigits;
        public DP(int digit, int totalDigits, DP nextDP) {
            super();
            this.digit = digit;
            this.totalDigits = totalDigits;
            this.nextDP = nextDP;
        }
    }
    
    private void solve(){
        int v = in.nextInt();
        DP[] dps = new DP[v+1];

        
        for(int i=1;i<=9;i++){
            prices[i]=in.nextInt();
            if(prices[i]<minPrice){
                minPrice=prices[i];
            }
        }
        for(int value=minPrice;value<=v;value++){
            DP optDP=null;
            int optDigit=0;
            int optLength=0;
            for(int i=1;i<=9;i++){
                int nextValue = value-prices[i];
                if(nextValue<0){
                    continue;
                }
                DP nextDP = dps[nextValue];
                int nextOptLength;
                if(nextDP==null){
                    nextOptLength=1;
                }else{
                    nextOptLength=nextDP.totalDigits+1;
                }
                if(nextOptLength>=optLength){
                    optDigit=i;
                    optLength=nextOptLength;
                    optDP=nextDP;
                }
            }
            dps[value]=new DP(optDigit,optLength,optDP);

        }
        DP dp=dps[v];
        if(dp==null){
            out.println(-1);
        }else{
            do{
                out.print(dp.digit);
                dp=dp.nextDP;
            }while(dp!=null);
        }
        out.println();
    }

    /**
     * @param v
     * @return
     */
    
    private String solve(int v) {
        if(v<minPrice){
            return "";
        }
        int digit=0;
        int count=0;
        String reminder="";
        for(int i=1;i<=9;i++){
            int nextCount = v/prices[i];
            if(nextCount==0){
                continue;
            }
            String nextReminder = solve(v-nextCount*prices[i]);
            if(nextCount+nextReminder.length()>=count+reminder.length()){
                digit=i;
                count=nextCount;
                reminder=nextReminder;
            }
        }
        StringBuilder number=new StringBuilder();
        for(int i=0;i<count;i++){
            number.append(digit);
        }
        number.append(reminder);
        return number.toString();
    }

}
//-------------------------------