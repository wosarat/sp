package freak;
//-------------------------------------------------------------------------------------------------------
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class EloRating {
    private final static long START_TIME=System.currentTimeMillis();
    private final static boolean LOG_ENABLED=true;
    private final static boolean ONLINE_JUDGE = LOG_ENABLED && (System.getProperty("ONLINE_JUDGE") != null);
    private final static String SYSTEM_ENCODING="utf-8";

    private static class Logger{
        private final PrintWriter logWriter=Util.newPrintWriter(System.err);
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
        public static PrintWriter newPrintWriter(OutputStream out){
            try {
                return new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(out), SYSTEM_ENCODING),true);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private final static Logger log=new Logger();
    private final Scanner in=new Scanner(new BufferedInputStream(System.in, 8192),SYSTEM_ENCODING);
    private final PrintWriter out=Util.newPrintWriter(System.out);

    public static void main(String[] args) {
        log.debug("Started");
        try{
            new EloRating().solve();
        }finally{
            log.debug("Stopped");
        }
    }
    void run(){
        solve();
        out.flush(); 
    }

    private void solve(){
        double ra = in.nextDouble();
        for(double rb=1400;rb<=3088;rb+=10){
            out.printf("%10f %10f %10f\r\n", ra,rb, 1.0/(1+Math.pow(10, (rb-ra)/400)));
        }
    }
    /**
     * @param n
     * @return
     */
    private long calcFi(int n, long fnm1,long fnm2) {
        if(n==0){
            return 0;
        }else if(n==1){
            return 1;
        }else{
            if(fnm2==-1){
                fnm2=calcFi(n-2, -1, -1);
            }
            if(fnm1==-1){
                fnm1=calcFi(n-1, fnm2, -1);
            }
            return fnm1+fnm2;
        }
    }

}
//-------------------------------