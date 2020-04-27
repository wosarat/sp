/*********************************
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2013 NVision Group, Inc. All rights reserved.
 *
 * Task1C.java 15.01.2013 21:02:03
 *********************************/
package codeforces.round160;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Task267C {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new Task267C().solve();
    }

    static class Point{
        final int x;
        final int y;
        int weight;
        public int index;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return x+" "+y;
        }
        /**
         * @param nextPoint
         * @return
         */
        public boolean isIntLength(Point nextPoint) {
            int lenExp2 = (nextPoint.x-x)*(nextPoint.x-x)+(nextPoint.y-y)*(nextPoint.y-y);
            long len = Math.round(Math.sqrt(lenExp2));
            return len*len==lenExp2;
        }
    }

    static class PointMatrix{
        private final Point[] points;
        private final Set<Point> pointSet=new HashSet<Task267C.Point>();
        private final int maxResultSize;

        public PointMatrix(int maxX, int maxY) {
            maxResultSize=Math.min(maxX+1, maxY+1);
            
            for(int x=0;x<=maxX;x++){
                for(int y=0;y<=maxY;y++){
                    if(x==0 && y==0){
                        continue;
                    }
                    Point newPoint = new Point(x, y);
                    for(Point prevPoint:pointSet){
                        if(prevPoint.isIntLength(newPoint)){
                            prevPoint.weight++;
                            newPoint.weight++;
                        }
                    }
                    pointSet.add(newPoint);
                }
            }
            points=pointSet.toArray(new Point[0]);
            Arrays.sort(points, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return 1*(o1.weight-o2.weight);
                }

            });
/*
            List<Point> pointList = Arrays.asList(points);
            Collections.shuffle(pointList);
            */
            for(int i=0,n=points.length;i<n;i++){
                points[i].index=i;
            }
            
            debug.println("starting search");
        }

        void returnPoint(Point point){
            points[point.index]=point;
            pointSet.add(point);
        }

        void removePoint(Point point){
            points[point.index]=null;
            pointSet.remove(point);
        }

        Point findNextAfter(Point point){
            if(point==null){
                point=new Point(0,0);
                point.index=0;
            }
            if(point.index+1==points.length){
                return null;
            }
            for(int i=point.index+1, n=points.length;i<n;i++){
                Point nextPoint = points[i];
                if(nextPoint!=null){
                    return nextPoint;
                }
            }
            return null;
        }

        Set<Point> removeBadPoints(Point point){
            Set<Point> removedPoints=new HashSet<Point>();

            for(Point removedPoint:pointSet){
                if(removedPoint.isIntLength(point)){
                    removedPoints.add(removedPoint);
                }
            }
            for(Point removedPoint:removedPoints){
                removePoint(removedPoint);
            }
            return removedPoints;
        }
        /**
         * @return the maxResultSize
         */
        public int getMaxResultSize() {
            return maxResultSize;
        }
    }


    /**
     * 
     */
    private void solve() {
        int maxX = in.nextInt();

        int maxY = in.nextInt();
        int ans = Math.min(maxX, maxY)+1;
        System.out.println(ans);
        for(int i=0;i<ans;i++){
            System.out.println(i+" "+(i+3)%ans);
        }
        
        //solve(maxX, maxY);
    }
    
    private void solve(int maxX, int maxY) {
        PointMatrix pointMatrix = new PointMatrix(maxX, maxY);
        Set<Point> solution=new LinkedHashSet<Task267C.Point>();
        solve(solution, pointMatrix);
        out.println(solution.size());
        for(Point point:solution){
            out.println(point);
        }
    }

    /**
     * @param solution
     * @param pointMatrix
     */
    private boolean solve(Set<Point> solution, PointMatrix pointMatrix) {
        if(solution.size()>=pointMatrix.getMaxResultSize()){
            return true;
        }


            int goodPointCount=goodPointCount(pointMatrix.pointSet);
            if(solution.size()+goodPointCount<pointMatrix.getMaxResultSize()){
                return false;
            }
            //double superK = 1.0*goodPointCount/pointMatrix.pointSet.size();
          /*  
            if((goodPointCount<15) && superK<0.7){
                return false;
            }*/


/*
        if((solution.size()==pointMatrix.getMaxResultSize()-14)){
            debug.println(superK);
            debug.println(solution.size()+"("+pointMatrix.getMaxResultSize()+")"+": "+solution);
            debug.println(pointMatrix.pointSet.size()+"("+goodPointCount+")"+": "+pointMatrix.pointSet);
        }
        */

        Point nextPoint=null;
        while(true){
            nextPoint=pointMatrix.findNextAfter(nextPoint);
            if(nextPoint==null){
                return false; 
            }
            /*if(!isCorrectPoint(solution,nextPoint)){
                continue;
            }*/
            Set<Point> neighbors = pointMatrix.removeBadPoints(nextPoint);
            solution.add(nextPoint);
            if(solve(solution, pointMatrix)){
                return true;
            }
            solution.remove(nextPoint);
            for(Point neighbor:neighbors){
                pointMatrix.returnPoint(neighbor);    
            }
        }
    }



    private static int goodPointCount(Set<Point> tail) {
        Set<Integer> xes=new HashSet<Integer>();
        Set<Integer> yes=new HashSet<Integer>();
        for(Point p:tail){
            xes.add(p.x);
            yes.add(p.y);
        }
        return Math.min(xes.size(), yes.size());
    }



}
