import java.io.*;
import java.util.StringTokenizer;


public class Main {

    public static boolean[][] isAvailable;
    public static int row;
    public static int col;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        isAvailable = new boolean[row][col];

        for (int i=0; i<row; i++) {
            String str = bf.readLine();
            for(int j=0; j<col; j++) {
                if(str.charAt(j)=='.') {
                    isAvailable[i][j] = true;
                }
            }
        }

        int maxPipeCount = 0;

        for(int i=0; i<row; i++) {
            if(check(i,0)) {
                maxPipeCount++;
            }
        }

        bw.write(String.valueOf(maxPipeCount));
        bw.flush();
        bw.close();
    }
    
    public static boolean check(int x, int y) {
        isAvailable[x][y] = false;

        if(y == col-1) //마지막 열(원웅이 빵집)에 도착했으면
            return true;

        if(x > 0 && isAvailable[x-1][y+1]) //오른쪽 위
            if(check(x-1, y+1))
                return true;
        if(isAvailable[x][y+1]) //오른쪽
            if(check(x, y+1))
                return true;
        if(x+1 < row && isAvailable[x+1][y+1]) //오른쪽 아래
            if(check(x+1, y+1))
                return true;
        return false;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}
