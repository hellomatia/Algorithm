import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int money = 1000 - Integer.parseInt(bf.readLine());
        int[] change = {500, 100, 50, 10, 5, 1};
        int changeCount = 0;

        for(int i=0; i<6; i++){
            changeCount += (money/change[i]);
            money %= change[i];
        }

        bw.write(String.valueOf(changeCount));

        bw.flush();
        bw.close();

    }
}