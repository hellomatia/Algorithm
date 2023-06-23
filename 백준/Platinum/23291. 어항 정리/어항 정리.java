import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K; // 어항의 수, 가장 많이 들었는 어항과 가장 적게 들어있는 어항의 차 기준
    static int ans;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] fishTanks = new int[N];

        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; i++) {
            fishTanks[i] = Integer.parseInt(st.nextToken());
        }



        organizeFishTank(fishTanks, 0);
        bw.write(ans+"\n");


        bw.flush();
        bw.close();
    }

    static void organizeFishTank(int[] fishTanks, int count) {
        /*
         * @어항 정리 방법
         * 0. 어항중 제일 작은 어항에 한마리씩 추가
         * 1. 어항 공중 부양. (가장 오른쪽에 있는 어항의 아래에 바닥에 있는 어항이 있을때까지
         * 2. 어항에 있는 물고기의 수를 조절 (인접한 두 어항의 차를 5로 나눈 몫을 구하고 몫이 0보다 크면 그만큼 이동, 동시에 이동) (type1)
         * 3. 다시 어항을 1열로 만들어 가장 왼쪽에 있는 어항부터, 그리고 아래있는 어항부터 가장 위에 있는 어항까지 순서대로
         * 4. 다시 공중 부양 작업 (가운데를 중심으로 왼쪽 N/2개를 공중 부양시켜 전체를 180도 회전하고 그 위로 쌓기, 이 작업을 두번) (type2)
         * 5. 어항에 있는 물고기의 수를 조절
         * 6. 다시 어항을 1열로 만들어 가장 왼쪽에 있는 어항부터, 그리고 아래있는 어항부터 가장 위에 있는 어항까지 순서대로
         * 7. 이 작업을 가장 많은 어항과 적은 어항의 차가 K 이하 될때까지 정리
         */

        //정리방법 7-2 어항의 차가 K이하 일 경우 return
        if(numOfFishMinMax(fishTanks)<=K) {
            ans = count;
            return;
        }

        // 정리 방법 0
        minFishTanksPlus(fishTanks);

        /*
        for(int i=0; i<fishTanks.length; i++) {
            System.out.print(fishTanks[i] + " ");
        }
        System.out.println("\n");
         */

        // 정리 방법 1
        int[][] fishTanksMap = fishTankLevitation(fishTanks, 1);

        /*
        for(int i=0; i<fishTanksMap.length; i++) {
            for(int j=0; j<fishTanksMap[0].length; j++) {
                System.out.print(fishTanksMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
         */

        // 정리 방법 2
        controlNumOfFish(fishTanksMap);
        /*
        for(int i=0; i<fishTanksMap.length; i++) {
            for(int j=0; j<fishTanksMap[0].length; j++) {
                System.out.print(fishTanksMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
         */

        //정리방법 3
        fishTanks = lineUpFishTank(fishTanksMap);
        /*
        for(int i=0; i<fishTanks.length; i++) {
            System.out.print(fishTanks[i] + " ");
        }
        System.out.println("\n");
         */

        //정리방법 4
        fishTanksMap = fishTankLevitation(fishTanks, 2);
        /*
        for(int i=0; i<fishTanksMap.length; i++) {
            for(int j=0; j<fishTanksMap[0].length; j++) {
                System.out.print(fishTanksMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
         */

        //정릴방법 5
        controlNumOfFish(fishTanksMap);
        /*
        for(int i=0; i<fishTanksMap.length; i++) {
            for(int j=0; j<fishTanksMap[0].length; j++) {
                System.out.print(fishTanksMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
         */

        //정리방법 6
        fishTanks = lineUpFishTank(fishTanksMap);
        /*
        for(int i=0; i<fishTanks.length; i++) {
            System.out.print(fishTanks[i] + " ");
        }
        System.out.println("\n");
        */

        //정리방법 7-1
        organizeFishTank(fishTanks, count+1);



    }

    static void minFishTanksPlus (int[] fishTanks) {
        int min = Integer.MAX_VALUE;
        // 전체 어항중에서 가장 작은 물고기수 찾기
        for(int i=0; i<N; i++) {
            min = Math.min(min, fishTanks[i]);
        }
        // 제일 작은 물고리를 가지고 있는 어항 한마리씩 추가
        for(int i=0; i<N; i++) {
            if(fishTanks[i]==min) {
                fishTanks[i]++;
            }
        }
    }

    static int[][] fishTankLevitation(int[] fishTanks, int type) {

        // fishTanks 초기화 진행
        // fishTanksMap이라는 어떠한 크기도 가능한 2차원 배열을 선언
        int[][] fishTanksMap = new int[N][N];

        //0일 경우는 어항이 없는 부분, 숫자가 있으면 어항이 있음
        //1차원 어항을 제일 밑에 clone하여 공중부양 실행
        fishTanksMap[N-1] = fishTanks.clone();

        // 1번 공중부양일 시
        if(type==1) {
            // 쌓아야하는 너비
            int w = 1;
            // 쌓아햐하는 높이
            int h = 1;
            // 쌓는걸 시작하는 지점
            int idx = 0;

            // 쌓는걸 진행하는 높이가 남아있는 너비보다 길면 중단
            while(h<=N-(idx+w)) {
                int hIdx = N-2;
                for(int i=idx+w-1; i>=idx; i--) {
                    int wIdx = idx+w;
                    for(int j=N-1; j>=N-h; j--) {
                        fishTanksMap[hIdx][wIdx++] = fishTanksMap[j][i];
                        fishTanksMap[j][i] = 0;
                    }
                    hIdx--;
                }
                int temp = h;
                h = w+1;
                idx += w;
                w = temp;
                /*
                for(int i=0; i<fishTanksMap.length; i++) {
                    for(int j=0; j<fishTanksMap[0].length; j++) {
                        System.out.print(fishTanksMap[i][j] + " ");
                    }
                    System.out.println();
                }
                 */
            }


        } else { // 2번 공중부양일 시
            // 첫번째 과정
            int w=N/2;
            int h=1;
            int idx = w;
            for(int i=w-1; i>=0; i--) {
                fishTanksMap[N-2][idx++] = fishTanksMap[N-1][i];
                fishTanksMap[N-1][i] = 0;
            }

            // 두번째 과정
            w = N/4;
            int hIdx = N-3;
            for(int i=N-2; i<=N-1; i++) {
                int wIdx = N-w;
                for(int j=N-1-w; j>=N/2; j--) {
                    fishTanksMap[hIdx][wIdx++] = fishTanksMap[i][j];
                    fishTanksMap[i][j]=0;
                }
                hIdx--;
            }
        }

        return fishTanksMap;
    }

    static int[] lineUpFishTank(int[][] fishTanksMap) {

        // 넘겨줄 fishTanks 배열 선언
        int[] fishTanks = new int[N];

        // fishTanks의 기준 idx 선언
        int idx = 0;

        // 하나의 col씩 아래에서 위로 탐색하면서 어항을 찾아 1차원 배열에 넣어줌
        for(int i=0; i<N; i++) {
            for(int j=N-1; j>=0; j--) {
                //0을 만나면 더이상 어항이 없기에 다음 col으로 이동
                if(fishTanksMap[j][i]==0) break;
                fishTanks[idx++] = fishTanksMap[j][i];
            }
        }
        return fishTanks;
    }

    static void controlNumOfFish(int[][] fishTanksMap) {

        // 상하좌우 인접한 어항에 접근하기 위한 dir배열 선언
        int[] dirX = {-1, 1, 0, 0};
        int[] dirY = {0, 0, -1, 1};

        // 물고기 수 조절을 위한 이동 개체수를 저장하기 위한 2차원 배열 선언
        int[][] controlMap = new int[N][N];

        // map전체를 하나씩 탐색하면서 인접한 어항끼리의 차를 계산
        // 어항끼리의 차를 5로 나눈 몫의 만큼 이동
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                //어항이 있으면 접근
                if(fishTanksMap[i][j]!=0) {
                    for(int k=0; k<4; k++) {
                        int x = i+dirX[k];
                        int y = j+dirY[k];

                        //map의 범위를 넘어섰거나 어항이 없으면 비교를 진행하지 않고 넘어감
                        if(x<0 || y<0 || N<=x || N<=y || fishTanksMap[x][y]==0) continue;

                        // 어항끼리의 차를 5로 나눈 몫
                        int d = Math.abs(fishTanksMap[i][j]-fishTanksMap[x][y])/5;

                        // 어항끼리 이동이 필요한 경우
                        if(d>0) {
                            // 어느쪽에서 어느쪽으로 이동한지 확인
                            if(fishTanksMap[i][j]>fishTanksMap[x][y]) {
                                controlMap[i][j] -= d;
                                controlMap[x][y] += d;
                            } else {
                                controlMap[i][j] += d;
                                controlMap[x][y] -= d;
                            }
                        }

                    }
                }
            }
        }

        // 이동하는 양을 저장한 controlMap에 있는 값들을 fishTanksMap에 적용
        // 2번 중복하여 탐색되기 때문에 2를 나눈값을 더해줌
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                fishTanksMap[i][j] += (controlMap[i][j]/2);
            }
        }

        /*
        for(int i=0; i<fishTanksMap.length; i++) {
            for(int j=0; j<fishTanksMap[0].length; j++) {
                System.out.print(controlMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
         */

    }

    static int numOfFishMinMax(int[] fishTanks){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<N; i++) {
            min = Math.min(min, fishTanks[i]);
            max = Math.max(max, fishTanks[i]);
        }
        return max-min;
    }
    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}