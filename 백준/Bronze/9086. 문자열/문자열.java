import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int wordCount = Integer.parseInt(bf.readLine());

        for (int i=0; i<wordCount; i++) {
            String word = bf.readLine();
            bw.write(word.charAt(0));
            bw.write(word.charAt(word.length()-1)+"\n");
        }
        bf.close();

        bw.flush();
        bw.close();
    }
    }
