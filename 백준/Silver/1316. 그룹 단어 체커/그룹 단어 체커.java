import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int wordCount = Integer.parseInt(bf.readLine());
        String[] inputWord = new String[wordCount];
        int[] wordAlphaCheck = new int[wordCount];
        int groupWordCount = 0;

        for(int i=0; i<wordCount; i++) {
            inputWord[i] = bf.readLine();
            for(int j=0; j<inputWord[i].length()-1; j++) {
                if(inputWord[i].charAt(j)!=inputWord[i].charAt(j+1)){
                    wordAlphaCheck[i]++;
                }
            }
            if(removeDuplicates(inputWord[i]).length()-1 == wordAlphaCheck[i]){
                groupWordCount++;
            }
        }

        bw.write(String.valueOf(groupWordCount));


        bf.close();

        bw.flush();
        bw.close();
    }
    static String removeDuplicates(String s) {
        if (s==null || s.isBlank()) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.add(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    }
