import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        boolean[] studentCheck = new boolean[30];

        for (int count=0; count<28; count++) {
            int studentNum = Integer.parseInt(bf.readLine());
            studentCheck[studentNum-1] = true;
        }

        bf.close();

        for (int count=0; count<30; count++) {
            if (!studentCheck[count]) {
                bw.write((count+1)+"\n");
            }
        }

        bw.flush();
        bw.close();
    }
    }
