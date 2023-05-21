import java.io.*;
import java.util.*;

public class Main {
    int targetChannel;
    int targetChannelDigitCount;
    int[] button;
    int minCount = Integer.MAX_VALUE;

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = bf.readLine();
        targetChannelDigitCount = str.length();
        targetChannel = Integer.parseInt(str);
        int brokenButtonCount = Integer.parseInt(bf.readLine());

        boolean[] brokenButton = new boolean[10];

        if(brokenButtonCount!=0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int i=0; i<brokenButtonCount; i++){
                brokenButton[Integer.parseInt(st.nextToken())] = true;
            }
        }

        if(brokenButtonCount!=10) {
            button = new int[10-brokenButtonCount];
            int index = 0;
            for(int i=0; i<10; i++) {
                if(!brokenButton[i]) {
                    button[index++] = i;
                }
            }
        }

        if(brokenButtonCount == 0) {
            minCount = Math.min(Math.abs(100-targetChannel), targetChannelDigitCount);
        } else if (brokenButtonCount == 10){
            minCount = Math.abs(100-targetChannel);
        } else if (targetChannel == 100) {
            minCount = 0;
        } else {
            countButtonPress(0, 0);
            minCount = Math.min(Math.abs(100-targetChannel), minCount);
        }


        bw.write(minCount + "\n");

        bw.flush();
        bw.close();
    }

    public void countButtonPress(int number, int count) {

        if(count>targetChannelDigitCount) {
            return;
        }

        for(int i=0; i<button.length; i++) {
            int chanel = number*10;
            chanel += button[i];
            minCount = Math.min(Math.abs((targetChannel-chanel))+count+1, minCount);
            countButtonPress(chanel, count+1);
        }

    }


    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}