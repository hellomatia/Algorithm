import java.io.*;
import java.util.*;

public class Main {

    int[][] superPower;
    int n;
    int teamCount = 0;
    boolean[] hasTeam;
    int minPowerGap = Integer.MAX_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());

        superPower = new int[n][n];
        hasTeam = new boolean[n];


        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0; j<n; j++) {
                superPower[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        splitTeam(0);


        bw.write(String.valueOf(minPowerGap));


        bw.flush();
        bw.close();
    }

    public void splitTeam(int start) {

        if(teamCount==n/2) {
            calculatorTeamPower();
            return;
        }

        for(int i = start; i<n; i++) {
            if(!hasTeam[i]){
                teamCount++;
                hasTeam[i] = true;
                splitTeam(i+1);
                teamCount--;
                hasTeam[i] = false;
            }
        }
    }

    public void calculatorTeamPower() {
        int[] teamPower = new int[2];

        for(int i=0; i<n-1; i++) {
            for(int j=i+1; j<n; j++) {
                if(hasTeam[i]==true&&hasTeam[j]==true) {
                    teamPower[0] += superPower[i][j];
                    teamPower[0] += superPower[j][i];
                }
                if(hasTeam[i]==false&&hasTeam[j]==false) {
                    teamPower[1] += superPower[i][j];
                    teamPower[1] += superPower[j][i];
                }
            }
        }

        int powerGap = Math.abs(teamPower[0]-teamPower[1]);

        minPowerGap = Math.min(minPowerGap, powerGap);
    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}