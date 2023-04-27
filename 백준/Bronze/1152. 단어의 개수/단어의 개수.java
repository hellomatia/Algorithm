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

        String inputString = bf.readLine();
        bf.close();

        StringTokenizer st = new StringTokenizer(inputString," ");

        bw.write(String.valueOf(st.countTokens()));

        bw.flush();
        bw.close();
    }
    }
