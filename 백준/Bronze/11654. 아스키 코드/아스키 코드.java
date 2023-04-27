import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = bf.readLine();
        char wordChar = word.charAt(0);
        bf.close();

        bw.write(String.valueOf((int)wordChar));

        bw.flush();
        bw.close();
    }
    }
