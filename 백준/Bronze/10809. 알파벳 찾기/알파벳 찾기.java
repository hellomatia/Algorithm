import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = bf.readLine();
        bf.close();

        int[] firstIndex = new int[26];
        Arrays.fill(firstIndex, -1);

        for(int i=0; i<word.length(); i++){
            int wordIndex = (int) word.charAt(i)-97;
            if (firstIndex[wordIndex] <0) {
                firstIndex[wordIndex] = i;
            }
        }

        for(int i=0; i<26; i++){
            bw.write(String.valueOf(firstIndex[i]));
            bw.write(" ");
        }

        bw.flush();
        bw.close();
    }
    }
