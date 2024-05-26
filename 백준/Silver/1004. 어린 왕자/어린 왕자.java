import java.io.*;
import java.util.StringTokenizer;

class Planet{
    int x;
    int y;
    int radius;

    public Planet(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int answer = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            int n = Integer.parseInt(br.readLine());

            Planet arr[] = new Planet[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int radius = Integer.parseInt(st.nextToken());

                boolean check1 = false;
                boolean check2 = false;

                if(Math.pow(startX - x, 2) + Math.pow(startY - y, 2) > Math.pow(radius, 2)) check1 = true;
                if(Math.pow(endX - x, 2) + Math.pow(endY - y, 2) > Math.pow(radius, 2)) check2 = true;

                if(check1 == true && check2 == false) answer++;
                else if(check1 == false && check2 == true) answer++;
            }

            bw.write(answer + "\n");
        }
        bw.close();
        br.close();
    }
}