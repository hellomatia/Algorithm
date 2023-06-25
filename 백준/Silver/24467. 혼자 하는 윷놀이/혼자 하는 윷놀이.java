import java.io.*;
import java.util.*;

class Point{
    int idx;
    int course;
    Point(int idx, int course) {
        this.idx = idx;
        this.course = course;
    }
}

public class Main {

    Point now;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<String> yut = new ArrayList<>();

        String str = "";
        
        while((str=bf.readLine())!=null) {
            yut.add(str);
        }

        now = new Point(0, 0);

        if(simulation(yut)) {
            bw.write("WIN");
        } else {
            bw.write("LOSE");
        }

        bw.flush();
        bw.close();
    }

    public boolean simulation(ArrayList yut) {
        /*
            1. 처음에 말은 윷판의 오른쪽 아래에 위치한다.
            2. 열 번의 차례 안에 말 하나가 완주하면 민재가 승리한다.
            3. 차례 한 번에는 윷가락 네 개를 던진 후:
            4. 뒷면이 하나인 경우 말을 한 칸 전진시킨다.
            5. 뒷면이 둘인 경우 말을 두 칸 전진시킨다.
            6. 뒷면이 셋인 경우 말을 세 칸 전진시킨다.
            7. 모두 뒷면인 경우 말을 네 칸 전진시킨 뒤, 윷을 추가로 던진다.
            8. 모두 앞면인 경우 말을 다섯 칸 전진시킨 뒤, 윷을 추가로 던진다.
            9. 윷판을 정해진 경로로 한 바퀴를 돌아 윷판의 오른쪽 아래에 도착한 뒤 한 칸 더 움직여야  완주할 수 있다.
            10. 혼자 하나의 말로 하는 게임이므로 상대 말을 잡거나 자신의 말을 업는 경우는 없다.
            11. 일반적인 윷놀이의 낙과 뒷도 등의 룰은 고려하지 않는다.
         */

        int idx = 0;

        for(int i=0; i<10; i++) {
            int moveCount;
            int move = 0;
            // 윷 이상으로 나올시 계속 다시 돌리기 때문에 while문을 사용한다.
            do {
                moveCount = getMoveCountOfYutPiece(String.valueOf(yut.get(idx++)));
                move += moveCount;
                board(move);
            } while(4<=moveCount);
        }

        if(now.idx==-1) return true;

        return false;
    }

    public int getMoveCountOfYutPiece(String yut) {
        /*
            0은 윷가락 뒷면, 1은 윷가락 앞면
            뒷면이 하나인 경우 말을 한 칸 전진시킨다. => 1
            뒷면이 둘인 경우 말을 두 칸 전진시킨다. => 2
            뒷면이 셋인 경우 말을 세 칸 전진시킨다. => 3
            모두 뒷면인 경우 말을 네 칸 전진시킨 뒤, 윷을 추가로 던진다. => 4
            모두 앞면인 경우 말을 다섯 칸 전진시킨 뒤, 윷을 추가로 던진다. => 5
         */

        int count = 0;
        for(int i=0; i<4; i++) {
            char ch = yut.charAt(i);
            if(ch=='0') {
                count++;
            }
        }

        // 모인 경우
        if(count==0) count=5;

        //System.out.println(count);
        //System.out.println(now.course);

        return count;
    }

    public void board(int move) {
        if(now.idx==-1) return;

        if(now.course==0) {
            now.idx += move;
            if(now.idx==5) {
                now.course = 1;
                now.idx=0;
            } else if(now.idx==10) {
                now.course = 3;
                now.idx = 0;
            } else if(20<now.idx) {
                now.idx = -1;
            }
        }else if(now.course==1) {
            now.idx += move;
            if (now.idx == 3) {
                now.course = 2;
                now.idx = 0;
            } else if (11 < now.idx) {
                now.idx = -1;
            }
        } else if(now.course==2) {
            now.idx += move;
            if(3<now.idx) {
                now.idx = -1;
            }
        }else if(now.course==3) {
            now.idx += move;
            if(6<now.idx) {
                now.idx = -1;
            }
        }
    }



    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}