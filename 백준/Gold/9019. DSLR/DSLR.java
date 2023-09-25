import java.io.*;
import java.util.*;
class Number {
    char[] num;
    String order;
    public Number(char[] num, String order) {
        this.num = num.clone();
        this.order = order;
    }
}
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public void solution() throws IOException {

        int tc = Integer.parseInt(bf.readLine());

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            String startNum = st.nextToken();
            String targetNum = st.nextToken();

            calculation(startNum, targetNum);
        }

        bw.flush();
        bw.flush();
        bw.close();
    }

    public void calculation(String startNum, String targetNum) throws IOException {

        Queue<Number> numQ = new LinkedList<>();
        boolean[] visited = new boolean[10_000];

        int num = Integer.parseInt(startNum);
        visited[num] = true;
        numQ.offer(new Number(intToCharArray(num), ""));

        int target = Integer.parseInt(targetNum);

        while(!numQ.isEmpty()) {
            Number now = numQ.poll();

            String nowStr = String.valueOf(now.num);
            int nowNum = Integer.parseInt(nowStr);

            if (nowNum == target) {
                bw.write(now.order + "\n");
                return;
            }

            //D
            int nextD = (nowNum * 2) % 10_000;
            if (!visited[nextD]) {
                visited[nextD] = true;
                numQ.offer(new Number(intToCharArray(nextD), now.order+"D"));
            }

            //S
            int nextS = nowNum - 1;
            if(nextS == -1) {
                nextS = 9999;
            }
            if (!visited[nextS]) {
                visited[nextS] = true;
                numQ.offer(new Number(intToCharArray(nextS), now.order+"S"));
            }

            //L
            char[] nextLArray = new char[4];
            nextLArray[3] = now.num[0];
            for(int i = 0; i < 3; i++) {
                nextLArray[i] = now.num[i + 1];
            }
            int nextL = Integer.parseInt(String.valueOf(nextLArray));
            if (!visited[nextL]) {
                visited[nextL] = true;
                numQ.offer(new Number(nextLArray, now.order+"L"));
            }

            //R
            char[] nextRArray = new char[4];
            nextRArray[0] = now.num[3];
            for(int i = 0; i < 3; i++) {
                nextRArray[i + 1] = now.num[i];
            }
            int nextR = Integer.parseInt(String.valueOf(nextRArray));
            if (!visited[nextR]) {
                visited[nextR] = true;
                numQ.offer(new Number(nextRArray, now.order+"R"));
            }

        }

    }

    public char[] intToCharArray(int num) {

        String str = String.valueOf(num);

        if (str.length() < 4) {
            int zeroCount = 4 - str.length();
            String zeroStr = "";

            while (zeroCount-- > 0) {
                zeroStr += "0";
            }

            str = zeroStr + str;
        }

        return str.toCharArray();
    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}