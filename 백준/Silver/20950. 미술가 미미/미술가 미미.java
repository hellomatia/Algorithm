import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private Rgb[] RGBs;
    private Rgb gomDuRiRgb;
    private int minScore;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        RGBs = new Rgb[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            RGBs[i] = new Rgb(R, G, B);
        }
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int R = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        gomDuRiRgb = new Rgb(R, G, B);
    }

    private String calcAns() {
        minScore = Integer.MAX_VALUE;
        dfs(0, 0, new Rgb(0, 0, 0, 0));
        return minScore + "";
    }

    private void dfs(int start, int count, Rgb now) {
        if (2<= count && count <= 7) {
            int score = calcScore(now);
            minScore = Math.min(score, minScore);
        }

        if (count < 7) {
            for (int i = start; i < N; i++) {
                dfs(i + 1, count + 1, now.sum(RGBs[i]));
            }
        }
    }

    private int calcScore(Rgb rgb) {
        return Math.abs(gomDuRiRgb.getRed() - rgb.getRed()) +
                Math.abs(gomDuRiRgb.getGreen() - rgb.getGreen()) +
                Math.abs(gomDuRiRgb.getBlue() - rgb.getBlue());
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }

    static class Rgb {
        int red;
        int green;
        int blue;
        int count;

        public Rgb(int red, int green, int blue, int count) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.count = count;
        }

        public Rgb(int red, int green, int blue) {
            this(red, green, blue, 1);
        }

        public Rgb sum(Rgb newRgb) {
            return new Rgb(red + newRgb.red, green + newRgb.green, blue + newRgb.blue, count + 1);
        }

        public int getRed() {
            return red / count;
        }

        public int getGreen() {
            return green / count;
        }

        public int getBlue() {
            return blue / count;
        }
    }
}