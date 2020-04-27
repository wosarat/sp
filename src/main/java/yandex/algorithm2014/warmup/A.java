package yandex.algorithm2014.warmup;
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

public class A implements Runnable {
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


    public A(Reader reader, Writer writer){
        this.reader=new BufferedReader(reader);
        this.out=new PrintWriter(writer,false);
        in=new FastScanner(this.reader);
    }

    public static void main(String[] args) throws IOException {
        new A(new InputStreamReader(System.in, SYSTEM_ENCODING),new OutputStreamWriter(new BufferedOutputStream(System.out), SYSTEM_ENCODING)).run();
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
        char[] wk = in.next().toCharArray();    
        char[] wr = in.next().toCharArray();
        char[] bk = in.next().toCharArray();
        normalizecoordinates(wk);
        normalizecoordinates(wr);
        normalizecoordinates(bk);
        if(isStrange(wk,bk)){
            out.println("Strange");
            return;
        }
        boolean[][] chessboard=new boolean[8][8];
        setWK(wk,chessboard);
        setWR(wr,chessboard);

//        for(int i=0;i<8;i++){
//            log.debug(Arrays.toString(chessboard[i]));
//        }
        boolean canMove=canMove(bk,chessboard);
        boolean underAttack=chessboard[bk[0]][bk[1]];
        String result;
        if(underAttack){//король под ударом
            if(canMove){
                result="Check";
            }else{
                result="Checkmate";
            }
        }else{//король не под ударом
            if(canMove){
                result="Normal";
            }else{
                result="Stalemate";
            }
        }
        out.println(result);
    }

    private static boolean canMove(char[] bk, boolean[][] chessboard) {
        for(int i=bk[0]-1;i<=bk[0]+1;i++){
            if(!isCorrectCoordinate(i)){
                continue;
            }
            for(int j=bk[1]-1;j<=bk[1]+1;j++){
                if(!isCorrectCoordinate(j)){
                    continue;
                }
                if(i==bk[0]&&j==bk[1]){
                    continue;
                }
                if(!chessboard[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

    private static void setWK(char[] wk, boolean[][] chessboard) {
        for(int i=wk[0]-1;i<=wk[0]+1;i++){
            if(!isCorrectCoordinate(i)){
                continue;
            }
            for(int j=wk[1]-1;j<=wk[1]+1;j++){
                if(!isCorrectCoordinate(j)){
                    continue;
                }
                chessboard[i][j]=true;
            }
        }

        chessboard[wk[0]][wk[1]]=false;
    }

    private static boolean isCorrectCoordinate(int i) {
        return i>=0 && i<=7;
    }

    private static void setWR(char[] wr, boolean[][] chessboard) {
        for(int i=0;i<8;i++){
            chessboard[i][wr[1]]=true;
            chessboard[wr[0]][i]=true;
        }
        chessboard[wr[0]][wr[1]]=false;
    }

    private static boolean isStrange(char[] wk, char[] bk) {
        int distance2 = (wk[0]-bk[0])*(wk[0]-bk[0])+(wk[1]-bk[1])*(wk[1]-bk[1]);
        return distance2<4;
    }

    /**
     * @param wk
     */
    private static void normalizecoordinates(char[] wk) {
        wk[0]-='a';
        wk[1]-='1';
    }

}
//-------------------------------