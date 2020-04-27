package stepic2014;


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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
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
                  currentTokenizer=new StringTokenizer(reader.readLine());
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

  public Main(BufferedReader reader){
      this.reader=reader;
      in=new FastScanner(this.reader);
  }

  public static void main(String[] args) throws IOException {
      log.debug("Started");
      try{
          new Main(new BufferedReader(new InputStreamReader(System.in, SYSTEM_ENCODING))).run();
      }finally{
          log.debug("Stopped");
      }
  }

  void run(){
      solve();
      out.flush(); 
  }

  private void solve(){
      int vCount = in.nextInt(); 
      int e = in.nextInt();
      
      
      
      @SuppressWarnings("unchecked")
      List<Integer>[] graph=new List[vCount];
      for(int i=0;i<e;i++){
          int v1 = in.nextInt()-1;
          int v2 = in.nextInt()-1;
          List<Integer> v1Edges = graph[v1];
          if(v1Edges==null){
              v1Edges=new ArrayList<Integer>();
              graph[v1]=v1Edges;
          }
          v1Edges.add(v2);
          List<Integer> v2Edges = graph[v2];
          if(v2Edges==null){
              v2Edges=new ArrayList<Integer>();
              graph[v2]=v2Edges;
          }
          v2Edges.add(v1);

      }
      int u = in.nextInt()-1;
      int v = in.nextInt()-1;

      int[] dist=new int[vCount];
      Arrays.fill(dist, -1);
      dist[u]=0;
      LinkedList<Integer> queue = new LinkedList<Integer>();
      queue.add(u);
      while(!queue.isEmpty()){
          Integer x = queue.removeFirst();
          
        List<Integer> children = graph[x];
        if(children==null){
            continue;
        }
        for(Integer child:children){
              if(dist[child]!=-1){
                  continue;
              }
              dist[child]=dist[x]+1;
              queue.add(child);
          }
      }
      
      out.println(dist[v]);
  }


}
//-------------------------------