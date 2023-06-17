import java.io.*;
import java.util.StringTokenizer;

public class Main {
    int N, K, P;// N-> 최대 층 개수, K -> 디스플레이 자리수, P -> 반전시킬 수 있는 디스플레이, X -> 현재층
    boolean[][] number;
    int[][] numberGap;
    int[] display;
    int answer;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        String X = st.nextToken();
        display = new int[K];

        for(int i=0; i<K-X.length(); i++) {
            display[i] = 0;
        }
        int idx = 0;
        for(int i=K-X.length(); i<K; i++) {
            display[i] = X.charAt(idx++) - '0';
        }

        /*
        for(int i=0; i<K; i++) {
            System.out.print(display[i]);
        }

         */

        number = new boolean[10][7];
        numberGap = new int[10][10];

        number[0] = new boolean[]{true, true, true, false, true, true, true};
        number[1] = new boolean[]{false, false, true, false, false, true, false};
        number[2] = new boolean[]{true, false, true, true, true, false, true};
        number[3] = new boolean[]{true, false, true, true, false, true, true};
        number[4] = new boolean[]{false, true, true, true, false, true, false};
        number[5] = new boolean[]{true, true, false, true, false, true, true};
        number[6] = new boolean[]{true, true, false, true, true, true, true};
        number[7] = new boolean[]{true, false, true, false, false, true, false};
        number[8] = new boolean[]{true, true, true, true, true, true, true};
        number[9] = new boolean[]{true, true, true, true, false, true, true};

        fillGap();

        simulation(0,0,0);

        bw.write(answer+"\n");

        bw.flush();
        bw.close();
    }

    public void simulation(int count, int gap, int num) {
        if(gap>P || num>N) return;
        if(count==K) {
            if(gap==0 || num==0) return;
            //System.out.println(num);
            answer++;
            return;
        }
        for(int i=0; i<10; i++) {
            simulation(count+1, gap+numberGap[display[count]][i], num*10+i);
        }

    }

    public void fillGap() {

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(i==j) continue;
                numberGap[i][j] = calculationGap(i, j);
            }
        }
    }

    public int calculationGap(int a, int b) {
        if(numberGap[b][a]!=0) return numberGap[b][a];

        int count = 0;

        for(int i=0; i<7; i++) {
            if(number[a][i]!=number[b][i]) count++;
        }

        return count;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}