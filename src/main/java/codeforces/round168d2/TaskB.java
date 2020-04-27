package codeforces.round168d2;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskB {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
        new TaskB().solve();
    }
    static class Pair{
        int i;
        int j;
        public Pair(int i, int j) {
            super();
            this.i = i;
            this.j = j;
        }

    }
    private int n;
    private int m;

    /**
     * 
     */
    private void solve() {
        n=in.nextInt();
        m=in.nextInt();
        boolean[][] matrix=new boolean[n][m];
        boolean[][] matrix2=new boolean[n][m];
        int firstI=-1;
        int firstJ=-1;

        int lastI=-1;
        int lastJ=-1;


        boolean isFirst=true;
        for(int i=0;i<n;i++){
            char[] row = in.next().toCharArray();
            for(int j=0;j<m;j++){
                if(row[j]=='B'){
                    if(isFirst){
                        isFirst=false;
                        firstI=i;
                        firstJ=j;
                    }
                    matrix[i][j]=true;
                    matrix2[i][j]=true;
                    lastI=i;
                    lastJ=j;


                }
            }
        }
        {
            List<Pair> verticalPairs=removeVertiacalPairs(firstI,firstJ,matrix);
            for(Pair pair:verticalPairs){
                removeHorizontalPairs(pair.i, pair.j,matrix);
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(matrix[i][j]){
                        out.println("NO");
                        return;
                    }
                }
            }}
        
        {
            List<Pair> verticalPairs=removeVertiacalPairs(lastI,lastJ,matrix2);
            for(Pair pair:verticalPairs){
                removeHorizontalPairs(pair.i, pair.j,matrix2);
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(matrix[i][j]){
                        out.println("NO");
                        return;
                    }
                }
            }}

        out.println("YES");
    }
    /**
     * @param currI
     * @param firstJ
     * @return
     */
    private List<Pair> removeVertiacalPairs(int currI, final int j,boolean[][] matrix) {
        List<Pair> verticalPairs=new ArrayList<TaskB.Pair>();
        verticalPairs.add(new Pair(currI, j));
        matrix[currI][j]=false;
        for(int i=currI+1;i<n;i++){
            if(i>=n){
                break;
            }
            if(!matrix[i][j]){
                break;
            }
            matrix[i][j]=false;
            verticalPairs.add(new Pair(i, j));
        }
        for(int i=currI-1;i>=0;i--){
            if(i<0){
                break;
            }
            if(!matrix[i][j]){
                break;
            }
            matrix[i][j]=false;
            verticalPairs.add(new Pair(i, j));
        }
        return verticalPairs;
    }
    private List<Pair> removeHorizontalPairs(final int i, int currJ, boolean[][] matrix) {
        List<Pair> pairs=new ArrayList<TaskB.Pair>();
        for(int j=currJ+1;j<m;j++){
            if(j>=m){
                break;
            }

            if(!matrix[i][j]){
                break;
            }
            matrix[i][j]=false;
            pairs.add(new Pair(i, j));
        }
        for(int j=currJ-1;j>=0;j--){
            if(j<0){
                break;
            }

            if(!matrix[i][j]){
                break;
            }
            matrix[i][j]=false;
            pairs.add(new Pair(i, j));
        }
        return pairs;
    }


}
