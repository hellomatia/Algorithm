import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] matrix = new int[9][9];
        int max = -1;
        int n = -1, m = -1;

        for(int i=0; i<9; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for(int j=0; j<9; j++){
                matrix[i][j] += Integer.parseInt(st.nextToken());

                if(max<matrix[i][j]){
                 max = matrix[i][j];
                 n = i+1;
                 m = j+1;

                }
            }
        }

        bw.write(String.valueOf(max));
        bw.write("\n");
        bw.write(String.valueOf(n));
        bw.write(" ");
        bw.write(String.valueOf(m));

        bf.close();

        bw.flush();
        bw.close();
    }
    }