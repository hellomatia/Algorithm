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
        inputString = inputString.toUpperCase();
        int[] alphaArr = new int[26];

        for(int i=0; i<inputString.length(); i++) {
            alphaArr[inputString.charAt(i)-65]++;
        }

        int max = -1;
        char mostFrequentAlphabet = '?';

        for(int i=0; i<26; i++){

            if (alphaArr[i]>max){
                max = alphaArr[i];
                mostFrequentAlphabet = (char) (i+65);
            }
            else if (alphaArr[i]==max){
                mostFrequentAlphabet = '?';
            }

        }

        bw.write(String.valueOf(mostFrequentAlphabet));

        bw.flush();
        bw.close();
    }
    }
