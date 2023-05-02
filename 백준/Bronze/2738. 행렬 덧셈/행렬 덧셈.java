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

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[N][M];

        for(int i=0; i<2; i++) {
            for(int j=0; j<N; j++){
                st = new StringTokenizer(bf.readLine(), " ");
                for(int k=0; k<M; k++){
                    matrix[j][k] += Integer.parseInt(st.nextToken());
                }
            }
        }
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                bw.write(String.valueOf(matrix[i][j]));
                bw.write(" ");
            }
            bw.write("\n");
        }
        


        bf.close();

        bw.flush();
        bw.close();
    }
    }