import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfMeetings = Integer.parseInt(bf.readLine());
        int[][] meetingTimes = new int[numOfMeetings][2];

        for(int i=0; i<numOfMeetings; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for(int j=0; j<2; j++){
                meetingTimes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(meetingTimes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if(o1[1]==o2[1]) {
                    return  o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int maxMeetingCount=0;
        int finishTime=0;

        for(int i=0; i<numOfMeetings; i++){
            if(finishTime <= meetingTimes[i][0]){
                finishTime = meetingTimes[i][1];
                maxMeetingCount++;
            };

        }


        bw.write(String.valueOf(maxMeetingCount));

        bw.flush();
        bw.close();

    }
}

