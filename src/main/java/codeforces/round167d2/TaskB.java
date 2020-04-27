package codeforces.round167d2;



import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskB {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new TaskB().solve();
        //new TaskB().testTime();
        //new TaskB().testMax();
        //new TaskB().testEqual();
    }
    static final int MAX_N=100000;
    private int f[]=new int[MAX_N+1];
    {
        for(int i=0;i<=MAX_N;i++){
                f[i]=calcFunction(1000000000);
        }
       // debug.println(Arrays.toString(f));
    }
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        Map<Integer, Long> map = new HashMap<Integer, Long>();
        long result=0;
        for(int i=0;i<n;i++){
            int func = calcFunction(in.nextInt());
            Long count = map.get(func);
            if(count==null){
                count=0l;
            }
            if(count>0){
                result+=count;
            }
            count++;
            map.put(func, count);
        }
        
        out.println(result);
        
        
    }
    
    private void testTime() {
        int n = 100000;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result=0;
        for(int i=0;i<n;i++){
            int func = calcFunction((int) (Math.random()*1000000000));
            Integer count = map.get(func);
            if(count==null){
                count=0;
            }
            if(count>0){
                result+=count;
            }
            count++;
            map.put(func, count);
        }
        
        out.println(result);
        
        
    }
    
    private void testMax() {
        int n = 100000;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result=0;
        for(int i=0;i<n;i++){
            int func = calcFunction((int) (Math.random()*n+1000000000-n));
            Integer count = map.get(func);
            if(count==null){
                count=0;
            }
            if(count>0){
                result+=count;
            }
            count++;
            map.put(func, count);
        }
        
        out.println(result);
        
        
    }
    
    private void testEqual() {
        int n = 100000;
        Map<Integer, Long> map = new HashMap<Integer, Long>();
        long result=0;
        for(int i=0;i<n;i++){
            int func = calcFunction(10);
            Long count = map.get(func);
            if(count==null){
                count=0l;
            }
            if(count>0){
                result+=count;
            }
            count++;
            map.put(func, count);
        }
        
        out.println(result);
        
        
    }



    private static int calcFunction(int a){
        if(a==0){
            return 0;
        }
        if(a%2==0){
            return calcFunction(a/2);
        }
        return calcFunction((a-1)/2)+1;
    }
    
}
