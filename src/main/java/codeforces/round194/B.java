package codeforces.round194;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B implements Runnable {
    /**
     * 
     */
    private static final String UGLY = "ugly";
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

    public void run(){
        log.debug("Started");
        try{
            solve();
            out.flush();
        }finally{
            log.debug("Stopped");
        }
    }

    static class Point{
        int x;
        int y;
        
        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Point point = (Point)obj;
            return x==point.x&&y==point.y;
        }
        @Override
        public int hashCode() {
            return x+y;
        }
    }
    private void solve(){
        SortedSet<Integer> xs=new TreeSet<Integer>();
        SortedSet<Integer> ys=new TreeSet<Integer>();
        Set<Point> points=new HashSet<Point>();
        for(int i=0;i<8;i++){
            Point p = new Point(in.nextInt(),in.nextInt());
            points.add(p);
            xs.add(p.x);
            ys.add(p.y);
        }
        if(points.size()!=8|| xs.size()!=3 || ys.size()!=3){
            out.println(UGLY);
            return;
        }
        Iterator<Integer> ix = xs.iterator();
        int x1=ix.next();
        int x2=ix.next();
        int x3=ix.next();
        
        Iterator<Integer> iy = ys.iterator();
        int y1=iy.next();
        int y2=iy.next();
        int y3=iy.next();

        points.remove(new Point(x1,y1));
        points.remove(new Point(x1,y2));
        points.remove(new Point(x1,y3));

        points.remove(new Point(x3,y1));
        points.remove(new Point(x3,y2));
        points.remove(new Point(x3,y3));

        points.remove(new Point(x2,y1));
        points.remove(new Point(x2,y3));

        if(points.size()!=0){
            out.println(UGLY);
            return;
        }
        out.println("respectable");

    }

}
//-------------------------------