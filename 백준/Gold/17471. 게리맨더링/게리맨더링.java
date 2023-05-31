import java.io.*;
import java.util.*;

public class Main {

    int N; // 구역의 개수
    int[] numOfPeople;
    ArrayList<Integer>[] linkRegion;
    boolean[] isRegionA;
    int minGap = Integer.MAX_VALUE;


    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());
        numOfPeople = new int[N];
        linkRegion = new ArrayList[N];
        isRegionA = new boolean[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for(int i=0; i<N; i++) {
            numOfPeople[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            linkRegion[i] = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            int count = Integer.parseInt(st.nextToken());
            for(int j=0; j<count; j++) {
                linkRegion[i].add(Integer.parseInt(st.nextToken())-1);
            }
        }

        divideIntoRegions(0, 0);

        if(minGap==Integer.MAX_VALUE) minGap = -1;

        bw.write(minGap + "\n");

        bw.flush();
        bw.close();
    }

    public void divideIntoRegions(int idx, int count) {
        if(count>N/2+1) return;

        if(canDivideIntoRegions()) {
            /*
            System.out.println("A : ");
            for(int i=0; i<N; i++) {
                if(isRegionA[i]) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();

            System.out.println("B : ");
            for(int i=0; i<N; i++) {
                if(!isRegionA[i]) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();

             */


            int regionCount = 0;
            for(int i=0; i<N; i++) {
                if(isRegionA[i]) {
                    regionCount += numOfPeople[i];
                } else {
                    regionCount -= numOfPeople[i];
                }
            }
            int gap = Math.abs(regionCount);

            minGap = Math.min(gap, minGap);

        }


        for(int i=idx; i<N; i++){
            isRegionA[i] = true;
            divideIntoRegions(i+1, count+1);
            isRegionA[i] = false;
        }
    }

    public boolean canDivideIntoRegions() {

        boolean[] visit = new boolean[N];

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<N; i++) {
            if(isRegionA[i]) {
                queue.add(i);
                break;
            }
        }

        if(queue.isEmpty()) return false;

        while(!queue.isEmpty()) {
            int region = queue.poll();
            visit[region] = true;

            for(int i=0; i<linkRegion[region].size(); i++) {
                int linkedRegion = linkRegion[region].get(i);
                if(isRegionA[linkedRegion]&&!visit[linkedRegion]) {
                    queue.add(linkedRegion);
                }
            }
        }

        for(int i=0; i<N; i++) {
            if(isRegionA[i] != visit[i]) return false;
        }

        for(int i=0; i<N; i++) {
            if(!isRegionA[i]) {
                queue.add(i);
                break;
            }
        }

        if(queue.isEmpty()) return false;

        while(!queue.isEmpty()) {
            int region = queue.poll();
            visit[region] = true;

            for(int i=0; i<linkRegion[region].size(); i++) {
                int linkedRegion = linkRegion[region].get(i);
                if(!isRegionA[linkedRegion]&&!visit[linkedRegion]) {
                    queue.add(linkedRegion);
                }
            }
        }

        for(int i=0; i<N; i++) {
            if(!visit[i]) return false;
        }


        return true;
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

/*
6
2 2 2 2 2 2
1 3
1 4
1 1
1 2
1 6
1 5

8
17 42 46 81 71 8 37 12
4 2 4 5 7
5 1 3 4 5 8
2 2 4
5 1 2 3 7 8
5 1 2 6 7 8
2 5 8
4 1 4 5 8
5 2 4 5 6 7

5
5 2 3 4 1
1 2
4 1 3 5 4
1 2
1 2
1 2

3
1 2 1
1 2
2 1 3
1 2

4
1 2 3 4
1 2
1 3
1 1
0

8
17 42 46 81 71 8 37 12
4 2 4 5 7
5 1 3 4 5 8
2 2 4
5 1 2 3 7 8
5 1 2 6 7 8
2 5 8
4 1 4 5 8
5 2 4 5 6 7

2
3 4
0
0
 */