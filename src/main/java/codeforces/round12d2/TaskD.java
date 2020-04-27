package codeforces.round12d2;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class TaskD {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new TaskD().solve();
    }
    class Point{
        int x;
        int y;
        int z;
        double id=-Math.random()*(Double.MAX_VALUE/2);
        boolean removed=false;
        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + ", z=" + z + ", removed=" + removed + "]";
        }
        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
        
    }
    /**
     * 
     */
    private void solve() {
        int n = in.nextInt();
        Point[] points=new Point[n];
        TreeSet<Point> xPoints=new TreeSet<Point>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int result = o1.x-o2.x;
                if(result==0){
                    result= (int) (o1.id-o2.id);
                }
                return result;
            }
        });
        TreeSet<Point> yPoints=new TreeSet<Point>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int result = o1.y-o2.y;
                if(result==0){
                    result= (int) (o1.id-o2.id);
                }
                return result;
            }
        });
        TreeSet<Point> zPoints=new TreeSet<Point>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int result = o1.z-o2.z;
                if(result==0){
                    result= (int) (o1.id-o2.id);
                }
                return result;
            }
        });
        for(int i=0;i<n;i++){
            points[i]=new Point();
            Point point = points[i];
            point.x=in.nextInt();
            xPoints.add(point);
        }
        for(int i=0;i<n;i++){
            points[i].y=in.nextInt();
            Point point = points[i];
            yPoints.add(point);
        }
        
        for(int i=0;i<n;i++){
            Point point = points[i];
            point.z=in.nextInt();
            zPoints.add(point);
        }
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return -(o1.x+o1.y+o1.z)+(o2.x+o2.y+o2.z);
            }
        });
        int removed=0;
        for(Point p:points){
            if(p.removed){
                continue;
            }
            HashSet<Point> xps = new HashSet<Point>(xPoints.headSet(p));
            if(xps.size()==0){
                continue;
            }
            xps.retainAll(yPoints.headSet(p));
            if(xps.size()==0){
                continue;
            }

            xps.retainAll(zPoints.headSet(p));
            if(xps.size()==0){
                continue;
            }

            Iterator<Point> it = xps.iterator();
            while(it.hasNext()){
                Point p2 = it.next();
                if(p2.x==p.x || p2.y==p.y || p2.z==p.z){
                    continue;
                }
                p2.removed=true;
                removed++;
                xPoints.remove(p2);
                yPoints.remove(p2);
                zPoints.remove(p2);
            }
        }
        out.println(removed);
        
    }
    
}
