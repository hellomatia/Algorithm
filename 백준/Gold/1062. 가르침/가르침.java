import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    int numOfWords;
    int alphabetNum;
    boolean[] alphabet;
    String[] words;
    int maxNumCount = Integer.MIN_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        numOfWords = Integer.parseInt(st.nextToken());
        alphabetNum = Integer.parseInt(st.nextToken());

        words = new String[numOfWords];
        alphabet = new boolean[26];

        alphabet['a'-97] = true;
        alphabet['c'-97] = true;
        alphabet['n'-97] = true;
        alphabet['t'-97] = true;
        alphabet['i'-97] = true;

        for(int i=0; i<numOfWords; i++) {
            words[i] = bf.readLine();
            words[i].replace("anta", "");
            words[i].replace("tica", "");
        }

        if(alphabetNum<5) maxNumCount = 0;
        else selectAlphabet(0,5);

        bw.write(maxNumCount+"\n");

        bw.flush();
        bw.close();
    }

    public void selectAlphabet(int idx, int count) {
        if(count==alphabetNum) {
            maxNumCount = Math.max(calculationScore(), maxNumCount);
            return;
        }

        for(int i=idx; i<26; i++) {
            if(!alphabet[i]) {
                alphabet[i] = true;
                selectAlphabet(i+1, count+1);
                alphabet[i] = false;
            }
        }
    }

    public int calculationScore() {

        int wordCount = 0;
        for(int i=0; i<numOfWords; i++) {
            char[] word = words[i].toCharArray();
            for(int j=0; j<word.length; j++) {
                if(!alphabet[word[j]-97]) break;
                if(j==word.length-1) wordCount++;
            }
        }
        return wordCount;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}