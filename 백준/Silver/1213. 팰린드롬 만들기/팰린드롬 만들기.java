import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputString = bf.readLine();
        int[] alphaCount = new int[26];
        char[] tempChar = new char[inputString.length()];

        for(int i=0; i<inputString.length(); i++) {
            alphaCount[inputString.charAt(i)-65]++;
        }

        int oddCount = 0;
        int oddIndex = 0;
        int startIndex = 0;
        int reverseIndex = inputString.length()-1;
        for(int i=0; i<26; i++) {
            if(alphaCount[i]%2!=0){
                oddIndex = i;
                oddCount++;
            }
            for(int j=0; j<alphaCount[i]/2; j++) {
                tempChar[startIndex++]=(char) (i+65);
                tempChar[reverseIndex--]=(char) (i+65);
            }
        }

        String outputString;

        if(oddCount==1){
            tempChar[inputString.length()/2] = (char) (oddIndex+65);
            outputString = String.valueOf(tempChar);
        }
        else if(oddCount==0){
            outputString = String.valueOf(tempChar);
        }
        else {
            outputString = "I'm Sorry Hansoo";
        }


        bw.write(outputString);



        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

