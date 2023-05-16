import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(bf.readLine());
        bw.write(String.valueOf(countHansu(num)));



        bw.flush();
        bw.close();
    }

    public int countHansu(int number) {
        int count=0;
        while(number>0) {
            StringBuilder sb = new StringBuilder(String.valueOf(number));

            boolean isHansu = true;
            if (sb.length()==1) {
                count++;
                number--;
                continue;
            }

            int gap = sb.charAt(1) - sb.charAt(0);
            for(int i=0; i<sb.length()-1; i++){
                if(gap!=sb.charAt(i+1)-sb.charAt(i)) {
                    isHansu = false;
                    break;
                }
            }
            if(isHansu) count++;
            number--;
        }
        return count;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}