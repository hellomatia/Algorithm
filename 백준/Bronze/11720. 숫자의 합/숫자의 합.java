import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numCount = Integer.parseInt(bf.readLine());
        String num = bf.readLine();
        int sum = 0;

        for(int i=0; i<numCount; i++){
            sum += Character.getNumericValue(num.charAt(i));
        }

        bf.close();

        bw.write(String.valueOf(sum));

        bw.flush();
        bw.close();
    }
    }
