package codeforces.round193;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
    static class Order{
        int n;
        public Order(int n, int a, int b) {
            super();
            this.n = n;
            this.a = a;
            this.b = b;
        }
        int a;
        int b;
        /**
         * @return the a
         */
        public int getA() {
            return a;
        }
        /**
         * @return the b
         */
        public int getB() {
            return b;
        }
        @Override
        public String toString() {
            return "Order [n=" + n + ", a=" + a + ", b=" + b + "]";
        }
        
        
    }
    private void solve(){
        int n = in.nextInt();    
        int p = in.nextInt();    
        int k = in.nextInt();
        Order[] orders=new Order[n];
        TreeSet<Order> ordersSortedByBA=new TreeSet<Order>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                int result = - (o1.b-o2.b);
                if(result==0){
                    result=  (o1.a-o2.a);
                }
                if(result==0){
                    result=o1.n-o2.n;
                }
                return result;
            }
        }); 
        for(int i=0;i<n;i++){
            orders[i]=new Order(i+1, in.nextInt(), in.nextInt());
            ordersSortedByBA.add(orders[i]);
        }
        Arrays.sort(orders, new Comparator<Order>() {

            @Override
            public int compare(Order o1, Order o2) {
                int result = - (o1.a-o2.a);
                if(result==0){
                    result= - (o1.b-o2.b);
                }
                if(result==0){
                    result=o1.n-o2.n;
                }
                return result;
            }
        });
        List<Order> result=new ArrayList<Order>(p);
        A: while(true){
            for(int i=0;i<n;i++){
                Order order = orders[i];
                NavigableSet<Order> tail = ordersSortedByBA.tailSet(order, false);
                if(tail.size()+result.size()+1>=p){
                    result.add(order);
                    ordersSortedByBA.remove(order);
                    if(result.size()==k){
                        for(int j=0;j<p-k;j++){
                            result.add(tail.pollFirst());
                        }
                        break A;
                    }
                    break;
                }
            }
        }
        for(int i=0;i<p;i++){
            out.println(result.get(i).n);
        }
    }

}
//-------------------------------