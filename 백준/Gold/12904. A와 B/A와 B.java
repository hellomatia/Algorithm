import java.io.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = bf.readLine();
        String T = bf.readLine();

        int changeNum = T.length() - S.length();

        StringBuilder sb = new StringBuilder(T);

        for(int i=0; i<changeNum; i++) {
            if(sb.charAt(sb.length()-1)=='A') {
                sb.deleteCharAt(sb.length()-1);
            } else {
                sb.deleteCharAt(sb.length()-1);
                sb = sb.reverse();
            }
        }

        if(S.equals(sb.toString())) {
            bw.write(String.valueOf(1));
        } else {
            bw.write(String.valueOf(0));
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}