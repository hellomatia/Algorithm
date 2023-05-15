import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int numOfDna = Integer.parseInt(st.nextToken());
        int dnaLength = Integer.parseInt(st.nextToken());

        String[] dnaArr = new String[numOfDna];

        for(int i=0; i<numOfDna; i++) {
            dnaArr[i] = bf.readLine();
        }

        StringBuilder sb = new StringBuilder();
        int hammingDistance = 0;

        for(int i=0; i<dnaLength; i++) {
            int mostAlphabetCount = -1;
            int mostAlphabetIndex = -1;
            int[] alphabetCount = new int[26];
            for(int j=0; j<numOfDna; j++) {
                int index = dnaArr[j].charAt(i)-65;
                alphabetCount[index]++;
                if(mostAlphabetCount<alphabetCount[index]) {
                    mostAlphabetCount = alphabetCount[index];
                    mostAlphabetIndex = index;
                }
                if(mostAlphabetCount==alphabetCount[index]) {
                    if(index<mostAlphabetIndex) {
                        mostAlphabetIndex = index;
                    }
                }
            }
            sb.append((char)(mostAlphabetIndex+65));
            hammingDistance += numOfDna - mostAlphabetCount;
        }

        bw.write(sb.toString());
        bw.write("\n");
        bw.write(String.valueOf(hammingDistance));

        bw.flush();
        bw.close();


    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}