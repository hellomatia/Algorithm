import java.io.*;
import java.util.Arrays;

public class Main {
    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int wordCount = Integer.parseInt(bf.readLine());
        String[] word = new String[wordCount];
        int[] alphabetArr = new int[26];

        for(int i=0; i<wordCount; i++){
            word[i] = bf.readLine();
            for(int j=0; j<word[i].length(); j++){
                alphabetArr[(int)word[i].charAt(j)-65] += (int)Math.pow(10, word[i].length()-j-1);
            }
        }

        Arrays.sort(alphabetArr);
        int maxNum = 0;

        int num = 9;

        for(int i=25; i>15; i--){
            maxNum += (alphabetArr[i]*num);
            num--;
        }

        bw.write(String.valueOf(maxNum));


        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

