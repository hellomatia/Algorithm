import java.io.*;
import java.util.*;

class LectureTime {
    int startTime;
    int endTime;
    LectureTime(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int lectureCount = Integer.parseInt(bf.readLine());
        LectureTime[] lectureTime = new LectureTime[lectureCount];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < lectureCount; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            lectureTime[i] = new LectureTime(startTime, endTime);
        }


        Arrays.sort(lectureTime, new Comparator<LectureTime>() {
            @Override
            public int compare(LectureTime o1, LectureTime o2) {
                if (o1.startTime == o2.startTime) {
                    return o1.endTime - o2.endTime;
                }
                return o1.startTime - o2.startTime;
            }
        });

        pq.offer(lectureTime[0].endTime);

        for (int i = 1; i < lectureCount; i++) {
            if (pq.peek() <= lectureTime[i].startTime) {
                pq.poll();
            }
            pq.offer(lectureTime[i].endTime);
        }


        bw.write(String.valueOf(pq.size()));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

