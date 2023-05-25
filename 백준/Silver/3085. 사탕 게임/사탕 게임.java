import java.io.*;

public class Main {

    int N; // 세로, 세로 크기
    char[][] candyMap;
    int maxScore = Integer.MIN_VALUE;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());
        candyMap = new char[N][N];

        for(int i=0; i<N; i++) {
            String str = bf.readLine();
            for(int j=0; j<N; j++) {
                candyMap[i][j] = str.charAt(j);
            }
        }

        playBomboni();

        bw.write(maxScore+"\n");


        bw.flush();
        bw.close();
    }

    public void playBomboni() {
        int n = N/2;

        for(int i=0; i<n; i++) {
            int idx = i*2;
            calculationScore(idx, idx+1, idx, idx+1);
        }
        if(N%2 == 1) {
            calculationScore(N-1, -1, N-1, -1);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) {
                if(candyMap[i][j] != candyMap[i][j+1]) {
                    char temp = candyMap[i][j];
                    candyMap[i][j] = candyMap[i][j+1];
                    candyMap[i][j+1] = temp;
                    calculationScore(i, -1, j, j+1);
                    candyMap[i][j+1] = candyMap[i][j];
                    candyMap[i][j] = temp;
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) {
                if(candyMap[j][i] != candyMap[j+1][i]) {
                    char temp = candyMap[j][i];
                    candyMap[j][i] = candyMap[j+1][i];
                    candyMap[j+1][i] = temp;
                    calculationScore(j, j+1, i, -1);
                    candyMap[j+1][i] = candyMap[j][i];
                    candyMap[j][i] = temp;
                }
            }
        }
    }

    public void calculationScore(int x1, int x2, int y1, int y2) {
        int score = 1;

        for(int i=0; i<N-1; i++){
            if(candyMap[x1][i]!=candyMap[x1][i+1]) {
                maxScore = Math.max(score, maxScore);
                score = 1;
            } else if(i==N-2) {
                score++;
                maxScore = Math.max(score, maxScore);
            } else {
                score++;
            }
        }

        score = 1;

        for(int i=0; i<N-1; i++){
            if(x2 == -1) break;
            if(candyMap[x2][i]!=candyMap[x2][i+1]) {
                maxScore = Math.max(score, maxScore);
                score = 1;
            } else if(i==N-2) {
                score++;
                maxScore = Math.max(score, maxScore);
            } else {
                score++;
            }
        }

        score = 1;

        for(int i=0; i<N-1; i++){
            if(candyMap[i][y1]!=candyMap[i+1][y1]) {
                maxScore = Math.max(score, maxScore);
                score = 1;
            } else if(i==N-2) {
                score++;
                maxScore = Math.max(score, maxScore);
            } else {
                score++;
            }
        }

        score = 1;

        for(int i=0; i<N-1; i++){
            if(y2 == -1) break;
            if(candyMap[i][y2]!=candyMap[i+1][y2]) {
                maxScore = Math.max(score, maxScore);
                score = 1;
            } else if(i==N-2) {
                score++;
                maxScore = Math.max(score, maxScore);
            } else {
                score++;
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}