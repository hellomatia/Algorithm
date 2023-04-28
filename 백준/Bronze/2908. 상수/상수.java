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

        int[] inputNum = new int[2];
        int[] reversedNum = new int[2];

        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        inputNum[0] = Integer.parseInt(st.nextToken());
        inputNum[1] = Integer.parseInt(st.nextToken());

        for(int i=0; i<2; i++) {
            int numDigits = 100;
            for(int j=0; j<3; j++) {
                int unitsDigit = inputNum[i] % 10;
                inputNum[i] = inputNum[i] / 10;
                reversedNum[i] += (unitsDigit * numDigits);
                numDigits /= 10 ;
            }
        }

        if(reversedNum[0]>reversedNum[1]) {
            bw.write(String.valueOf(reversedNum[0]));
        }
        else {
            bw.write(String.valueOf(reversedNum[1]));
        }




        bw.flush();
        bw.close();
    }
    }
