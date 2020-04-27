package codeforces.memsql2014;

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
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B {
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

  public B(BufferedReader reader){
      this.reader=reader;
      in=new FastScanner(this.reader);
  }

  public static void main(String[] args) throws IOException {
      log.debug("Started");
      try{
          new B(new BufferedReader(new InputStreamReader(System.in, SYSTEM_ENCODING))).run();
      }finally{
          log.debug("Stopped");
      }
  }

  void run(){
      solve();
      out.flush(); 
  }

  private void solve(){
      int n0 = in.nextInt(); 
      int m0 = in.nextInt(); 
      boolean reverse=m0>n0;
      int n=Math.max(n0, m0);
      int m=Math.min(n0, m0);
      int[] x=new int[4];
      int[] y=new int[4];
      if(m==0){
          x[0]=1;
          x[1]=n;
          x[2]=0;
          x[3]=n-1;
      }else{
          x[0]=1;y[0]=0;
          x[1]=n;y[1]=m;
          x[2]=0;y[2]=0;
          x[3]=n-1;y[3]=m;
          int length = getLength(x, y);
          int[] xx=new int[4];
          int[] yy=new int[4];

          xx[0]=0;yy[0]=m;
          xx[1]=n;yy[1]=0;
          xx[2]=0;yy[2]=0;
          xx[3]=n;yy[3]=m;
          int length2 = getLength(xx, yy);
          if(length2>length){
              length=length2;
              x=xx;
              y=yy;
          }
          
          xx=new int[4];
          yy=new int[4];

          xx[0]=n-1;yy[0]=m-1;
          xx[1]=0;yy[1]=0;
          xx[2]=n;yy[2]=m;
          xx[3]=1;yy[3]=1;
          if(isOk(xx,yy)){
              length2 = getLength(xx, yy);
              if(length2>length){
                  length=length2;
                  x=xx;
                  y=yy;
              }
          }
          
          xx=new int[4];
          yy=new int[4];
          
          xx[0]=n;yy[0]=m-1;
          xx[1]=0;yy[1]=0;
          xx[2]=n;yy[2]=m;
          xx[3]=0;yy[3]=1;
          if(isOk(xx,yy)){
              length2 = getLength(xx, yy);
              if(length2>length){
                  length=length2;
                  x=xx;
                  y=yy;
              }
          }


      }
      for(int i=0;i<4;i++){
          out.println(reverse?y[i]+" "+x[i]:x[i]+" "+y[i]);
      }
  }
  private static boolean isOk(int[] xx, int[] yy) {
      Set<String> set=new HashSet<String>();
      for(int i=0;i<4;i++){
          if(!set.add(xx[i]+";"+yy[i])){
              return false;
          }
      }
      return true;
  }

  int getLength(int [] xx, int[] yy){
      int result=0;
      for(int i=1;i<4;i++){
          result+=1.0*(xx[i-1]-xx[i])*(xx[i-1]-xx[i])+1.0*(yy[i-1]-yy[i])*(yy[i-1]-yy[i]);
      }
      return result;

  }

}
//-----