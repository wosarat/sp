package codeforces.round168d2;
import java.io.PrintWriter;
import java.util.Scanner;

public class TaskA {
    private final static Scanner in=new Scanner(System.in);
    private final static PrintWriter out=new PrintWriter(System.out,true);
    private final static PrintWriter debug=new PrintWriter(System.err,true);
    public static void main(String[] args) {
            new TaskA().solve();
    }

    /**
     * 
     */
    private void solve() {
        boolean[][] matrix=new boolean[3][3];
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                matrix[i][j]=true;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int count=in.nextInt();
                if(count%2==1){
                    inverseWithNeib(matrix,i,j);
                }
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                out.print(matrix[i][j]?1:0);
            }
            out.println();
        }


    }

    /**
     * @param matrix
     * @param i
     * @param j
     */
    private void inverseWithNeib(boolean[][] matrix, int i, int j) {
     //   inverse(matrix,i-1,j-1);
        inverse(matrix,i  ,j-1);
     //   inverse(matrix,i+1,j-1);
        inverse(matrix,i-1,j  );
        inverse(matrix,i  ,j  );
        inverse(matrix,i+1,j  );
     //   inverse(matrix,i-1,j+1);
        inverse(matrix,i  ,j+1);
     //   inverse(matrix,i+1,j+1);
    }

    /**
     * @param matrix
     * @param i
     * @param j
     */
    private void inverse(boolean[][] matrix, int i, int j) {
        if(i<0 || i>2 || j<0 || j>2){
            return;
        }
        matrix[i][j]=!matrix[i][j];
    }
    
}
