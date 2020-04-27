package codeforces.goodbye2014;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    private static class VGroup{
        Set<Integer> indexes=new HashSet<>();
        @Override
        public boolean equals(Object obj) {
            VGroup other=(VGroup) obj;
            return indexes.equals(other.indexes);
        }
        @Override
        public int hashCode() {
            return indexes.hashCode();
        }

        @Override
        public String toString() {
            return indexes.toString();
        }
    }
    private void solve(){
        int n = in.nextInt();    
        int[]p=new int[n];
        for(int i=0;i<n;i++){
            p[i]=in.nextInt();
        }
        Map<Integer,VGroup> groupsByV=new HashMap<>();
        for(int i=0;i<n;i++){
            VGroup group=groupsByV.get(i);
            String row=in.next();
            for(int j=0;j<n;j++){
                if(row.charAt(j)=='1'){
                    VGroup jgroup = groupsByV.get(j);
                    if(jgroup!=null && group!=null){
                        union(jgroup,group);
                    }else if(jgroup!=null && group==null){
                        group=jgroup;
                    }else if(jgroup==null){
                        if(group==null){
                            group=new VGroup();
                        }
                        group.indexes.add(j);
                        groupsByV.put(j, group);
                    }
                }
            }
            if(group!=null){
                group.indexes.add(i);
                groupsByV.put(i, group);
            }

        }
        Set<VGroup> groups=new HashSet<>();
        for(VGroup group:groupsByV.values()){
            groups.add(group);
        }

        for(VGroup group:groups){
            List<Integer> indexes = new ArrayList<>(group.indexes);
            Collections.sort(indexes);
            List<Integer> values=new ArrayList<>(group.indexes.size());
            for(Integer index:indexes){
                values.add(p[index]);
            }
            Collections.sort(values);
            for(int i=0,size=group.indexes.size();i<size;i++){
                p[indexes.get(i)]=values.get(i);
            }
        }
        for(int i=0;i<n;i++){
            if(i>0){
                out.print(" ");
            }
            out.print(p[i]);
        }
    }

    /**
     * @param jgroup
     * @param group
     */
    private static void union(VGroup jgroup, VGroup group) {
        if(jgroup.indexes!=group.indexes){
            jgroup.indexes.addAll(group.indexes);
            group.indexes=jgroup.indexes;
        }
    }

}
//-------------------------------