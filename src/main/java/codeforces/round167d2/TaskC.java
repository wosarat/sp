package codeforces.round167d2;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class TaskC {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new TaskC().solve();
           // new TaskC().testTime();
    }

    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        TreeMap<Integer, Long> map=new TreeMap<Integer, Long>();
        //long [] ladder=new long[n];
        for(int i=1;i<n+1;i++){
            map.put(i, (long) in.nextInt());
        }
        int m = in.nextInt();
        for(int i=0;i<m;i++){
            int w = in.nextInt();
            int h = in.nextInt();
            Entry<Integer, Long> entry = map.ceilingEntry(w);
            out.println(entry.getValue());
            long newHeight=entry.getValue()+h;
            Iterator<Entry<Integer, Long>> it = map.entrySet().iterator();
            while(it.hasNext()){
                Entry<Integer, Long> next = it.next();
                if(/*next.getKey()<w || */next.getValue()<newHeight){
                    it.remove();
                    continue;
                }
                break;
            }
            if(map.isEmpty()){
                map.put(n, newHeight);
            }else{
                map.put(map.firstKey()-1,newHeight);
            }
        }
        
    }
    
    private void testTime() {
        long s = System.currentTimeMillis();
        int n = 100000;
        TreeMap<Integer, Long> map=new TreeMap<Integer, Long>();
        //long [] ladder=new long[n];
        for(int i=1;i<n+1;i++){
            map.put(i, (long) (i*1000));
        }
        int m = n;
        for(int i=0;i<m;i++){
            int w = (int) (Math.random()*(n-1)+1);
            int h = (int) (Math.random()*1000000000);
            Entry<Integer, Long> entry = map.ceilingEntry(w);
//            out.println(entry.getValue());
            Iterator<Integer> it = map.keySet().iterator();
            while(it.hasNext()){
                if(it.next()<w){
                    it.remove();
                    continue;
                }
                break;
            }
            map.put(w, entry.getValue()+h);
        }
        out.println(System.currentTimeMillis()-s);
        
    }

    
}
