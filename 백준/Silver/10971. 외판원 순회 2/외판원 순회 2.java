import java.io.*;
import java.util.*;

public class Main {
    int N;
    int[][] cost;
    int[] travelRoute;
    boolean[] isVisit;
    int minCost = Integer.MAX_VALUE;


    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine()); // 도시의 수

        cost = new int[N][N];

        travelRoute = new int[N];
        isVisit = new boolean[N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        generateTravelRoute(0);


        bw.write(minCost + "\n");
        bw.flush();
        bw.close();

    }

    public void generateTravelRoute(int count) {
        if(count==N){
            calculationTravelCost();
        }

        for(int i=0; i<N; i++){
            if(!isVisit[i]) {
                travelRoute[count] = i;
                isVisit[i] = true;
                generateTravelRoute(count+1);
                isVisit[i] = false;
            }
        }
    }

    public void calculationTravelCost() {

        int totalCost = cost[travelRoute[N-1]][travelRoute[0]];

        if(totalCost==0) {
            return;
        }

        for(int i=0; i<N-1; i++) {
            int tempCost = cost[travelRoute[i]][travelRoute[i+1]];
            if(tempCost==0) {
                return;
            }
            totalCost += tempCost;
        }

        minCost = Math.min(totalCost, minCost);

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}