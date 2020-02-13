import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Random; 

public class matrix{
  
  public matrix(){
  
  }
  
  public float[][] matmult(float[][] A, float[][] B, float[][] C, int m, int n, int p, int threadNum) {
    ThreadPoolExecutor x = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadNum); 
    for(int i = 0; i < m; i++) {
      for(int j = 0; j < p; j++) {
        x.execute(new varExecute(A[i], B[j], C, n,i,j));
      }
    }
    x.shutdown(); 
    return C; 
  }
  
  public float[][] transpose(float[][] X) {
    int r = X.length; 
    int c = X[0].length; 
    float[][] result = new float[c][r]; 
    for(int i = 0; i < r; i++) {
      for(int j = 0; j < c; j++) {
        result[j][i] = X[i][j];
      }
    }
    return result; 
  }
  
  public class varExecute implements Runnable {
    float[] A;
    float[] B;
    float[][] C;
    int n;
    int i; 
    int j;
    public varExecute(float[] A, float [] B, float[][] C, int n, int i, int j) {
      this.A = A; 
      this.B = B;
      this.C = C;
      this.n = n;
      this.i = i;
      this.j = j;   
    }
    
    public void run() {
      for(int k = 0; k < n; k++) {
        C[i][j] += A[k] * B[k];
      }
    }
  }
   
  public void printMatrix(float[][] matrix, int row, int column) {
    for(int i = 0; i < row; i++) {
      for(int j = 0; j < column; j++) {
        System.out.print(matrix[i][j] + " "); 
      }
      System.out.print("\n"); 
    }
  }
  
  public float[][] generateMatrix(int n, int m) {
    float[][] result = new float[n][m]; 
    Random rand = new Random(); 
    for(int i =0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        result[i][j] = rand.nextInt(10); 
      }
    }
    return result; 
  }
   
  public static void main(String[] args) {
    matrix mat = new matrix(); 
    float[][] A = mat.generateMatrix(100,190); 
    float[][] B = mat.generateMatrix(190,180); 
    int m = A.length; 
    int n = A[0].length; 
    int p = B[0].length;
    float[][] C = new float[m][p];
    float[][] BT = mat.transpose(B); 
    System.out.println("Current size is: " + m + "x" + n + "and " + n + "x" + p);    
    int threadNum = 1; 
    while(threadNum <= 8) {
      long startTime = System.currentTimeMillis();
      mat.matmult(A, BT, C, m,n,p, threadNum); 
      long endTime = System.currentTimeMillis();
      System.out.println("Thread " + threadNum + " took:  " + (endTime - startTime));    
      threadNum *= 2; 
    }
    
    A = mat.generateMatrix(500,690); 
    B = mat.generateMatrix(690,780); 
    m = A.length; 
    n = A[0].length; 
    p = B[0].length;
    C = new float[m][p];
    BT = mat.transpose(B); 
    System.out.println("Current size is: " + m + "x" + n + "and " + n + "x" + p);    
    threadNum = 1; 
    while(threadNum <= 8) {
      long startTime = System.currentTimeMillis();
      mat.matmult(A, BT, C, m,n,p, threadNum); 
      long endTime = System.currentTimeMillis();
      System.out.println("Thread " + threadNum + " took:  " + (endTime - startTime));    
      threadNum *= 2; 
    }

  }
}


