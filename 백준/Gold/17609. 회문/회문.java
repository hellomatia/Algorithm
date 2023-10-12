import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        int T = Integer.parseInt(bf.readLine());

        for (int i = 0; i < T; i++) {
            bw.write(isPalindrome(bf.readLine()) + "\n");
        }

        bw.flush();
        bw.close();
    }

    public int isPalindrome(String str) {

        int forward = 0;
        int backward = str.length() - 1;

        int notSameCount1 = 0;

        int count = str.length() / 2;

        while (count-- > 0) {
            
            if (str.charAt(forward) != str.charAt(backward)) {
                notSameCount1 += 2;
                if (str.charAt(forward + 1) == str.charAt(backward)) {
                    forward++;
                    notSameCount1--;
                }
            }

            forward++;
            backward--;

            if (notSameCount1 >= 2) {
                notSameCount1 = 2;
                break;
            }
        }

        forward = 0;
        backward = str.length() - 1;

        int notSameCount2 = 0;
        count = str.length() / 2;

        while (count-- > 0) {
            
            if (str.charAt(forward) != str.charAt(backward)) {
                notSameCount2 += 2;

                if (str.charAt(forward) == str.charAt(backward - 1)) {
                    backward--;
                    notSameCount2--;
                }
            }

            forward++;
            backward--;

            if (notSameCount2 >= 2) {
                notSameCount2 = 2;
                break;
            }
        }

        return Math.min(notSameCount1, notSameCount2);
    }

    public static void main(String[] args) throws IOException {

        new Main().solution();

    }
}