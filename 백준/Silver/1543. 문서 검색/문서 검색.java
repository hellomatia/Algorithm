import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String document = bf.readLine();
        String str = bf.readLine();

        char[] documentArr = document.toCharArray();
        char[] strArr = str.toCharArray();

        int idx = 0;
        int count = 0;

        while(idx<=document.length()-str.length()) {
            for(int i=0; i<str.length(); i++) {
                if(documentArr[idx+i]!=strArr[i]) break;
                if(i==str.length()-1) {
                    count++;
                    idx = idx+i;
                }
            }
            idx++;
        }

        bw.write(count+"\n");


        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}