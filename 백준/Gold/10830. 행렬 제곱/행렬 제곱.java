import java.io.*;
import java.util.*;

public class Main {
    static final int NUM_ALLOW_LEN = 1_000;

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static long B;
    static int[][] inputMatrix;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        initNAndB();
        initInputMatrix();
        int[][] result = matrixPower(inputMatrix, B);
        printMatrix(result);
        bw.flush();
        bw.close();
    }

    private void initNAndB() throws IOException {
        String[] strings = bf.readLine().split(" ");
        N = Integer.parseInt(strings[0]);
        B = Long.parseLong(strings[1]);
    }

    private void initInputMatrix() throws IOException {
        inputMatrix = new int[N][N];
        for (int row = 0; row < N; row++) {
            String[] rowValues = bf.readLine().split(" ");
            for (int col = 0; col < N; col++) {
                inputMatrix[row][col] = Integer.parseInt(rowValues[col]) % NUM_ALLOW_LEN;
            }
        }
    }

    private int[][] matrixPower(int[][] matrix, long power) {
        if (power == 1) {
            return matrix;
        }
        if (power % 2 == 0) {
            int[][] temp = matrixPower(matrix, power / 2);
            return matrixMultiply(temp, temp);
        } else {
            int[][] temp = matrixPower(matrix, (power - 1) / 2);
            int[][] result = matrixMultiply(temp, temp);
            return matrixMultiply(result, matrix);
        }
    }

    private int[][] matrixMultiply(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += (matrix1[i][k] * matrix2[k][j]) % NUM_ALLOW_LEN;
                    result[i][j] %= NUM_ALLOW_LEN;
                }
            }
        }
        return result;
    }

    private void printMatrix(int[][] matrix) throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(matrix[i][j] + " ");
            }
            bw.write("\n");
        }
    }
}
