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
        bf.close();

        int[] chessmanCount = new int[6];
        int[] numOfChessPieces = {1,1,2,2,2,8};

        for(int i=0; i<6; i++) {
            chessmanCount[i] = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(numOfChessPieces[i]-chessmanCount[i]));
            bw.write(" ");
        }

        bw.flush();
        bw.close();
    }
    }
