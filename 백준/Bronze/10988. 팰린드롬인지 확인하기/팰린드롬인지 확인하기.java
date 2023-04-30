import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputString = bf.readLine();
        char[] reversedArr = new char[inputString.length()];

        int forwardIndex = 0;
        int backwardIndex = inputString.length()-1;

        while(forwardIndex<=backwardIndex) {
            reversedArr[forwardIndex] = inputString.charAt(backwardIndex);
            reversedArr[backwardIndex] = inputString.charAt(forwardIndex);
            backwardIndex--;
            forwardIndex++;
        }

        String reversedString = String.valueOf(reversedArr);

        if(inputString.equals(reversedString)) {
            bw.write("1");
        }
        else {
            bw.write("0");
        }

        bw.flush();
        bw.close();
    }
    }
