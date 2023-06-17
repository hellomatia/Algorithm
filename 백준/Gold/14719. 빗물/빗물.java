import java.io.*;
import java.util.StringTokenizer;

public class Main {

    int H, W; //길이가 N, 내구도가 0인 칸이 K개 일 경우
    int[][] map;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<W; i++) {
            int height = Integer.parseInt(st.nextToken());
            for(int j=H-1; j>=H-height; j--) {
                //2일경우 블록
                map[j][i]=2;
            }
        }

        /*

        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

         */



        simulation();

        /*
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        
         */

        bw.write(countWater()+"\n");

        bw.flush();
        bw.close();
    }

    public void simulation() {

        for(int i=H-1; i>=0; i--) {
            for(int j=0; j<W; j++) {
                if(map[i][j]==2&&checkFillWater(i, j)) {
                    int idx=j+1;
                    while(map[i][idx]!=2) {
                        map[i][idx]=1;
                        idx++;
                    }
                }
            }
        }

    }

    public boolean checkFillWater(int h, int w) {
        for(int i=w+1; i<W; i++) {
            if(map[h][i]==2) return true;
        }
        return false;
    }

    public int countWater() {
        int count = 0;

        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(map[i][j]==1) count++;
            }
        }

        return count;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}