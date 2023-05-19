import java.io.*;
import java.util.*;

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    int[][] map;
    int n, m;
    int minCityDistance = Integer.MAX_VALUE;
    ArrayList<Point> house = new ArrayList<>();
    ArrayList<Point> chickenShop = new ArrayList<>();
    boolean[] point;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];


        for(int i=0; i<n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1) {
                    house.add(new Point(i, j));
                }
                if(map[i][j]==2) {
                    chickenShop.add(new Point(i, j));
                }
            }
        }

        point = new boolean[chickenShop.size()];


        shutDownShop(0, 0);

        bw.write(String.valueOf(minCityDistance));


        bw.flush();
        bw.close();
    }

    public void shutDownShop(int idx, int count){

        if(count==m){

            cityChickenDistance();
            return;
        }

        for(int i=idx; i<chickenShop.size(); i++){
            point[i] = true;
            shutDownShop(i+1, count+1);
            point[i] = false;
        }


    }

    public void cityChickenDistance(){
        int sumDistance = 0;
        int distance;
        for(int i=0; i<house.size(); i++) {
            int minDistance = Integer.MAX_VALUE;
            for(int j=0; j<chickenShop.size(); j++) {
                if(point[j]){
                    distance = Math.abs(chickenShop.get(j).x - house.get(i).x) + Math.abs(chickenShop.get(j).y - house.get(i).y);
                    minDistance = Math.min(distance, minDistance);
                    //System.out.printf("x : %d, y : %d ", chickenShop.get(j).x, chickenShop.get(j).y);
                }
            }
            //System.out.println();
            sumDistance += minDistance;
        }

        minCityDistance = Math.min(sumDistance, minCityDistance);
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}