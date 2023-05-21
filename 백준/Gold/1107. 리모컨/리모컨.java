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

        ArrayList<Integer> brokenButton = new ArrayList<>();

        if(brokenButtonCount>0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int i=0; i<brokenButtonCount; i++){
                brokenButton.add(Integer.parseInt(st.nextToken()));
            }
        }

        if(brokenButtonCount<10) {
            button = new int[10-brokenButtonCount];
            int index = 0;
            for(int i=0; i<10; i++) {
                if(!brokenButton.contains(i)) {
                    button[index++] = i;
                }
            }
        }

        if(brokenButtonCount == 0) {
            minCount = Math.min(Math.abs(100-targetChannel), minCount);
            minCount = Math.min(targetChannelDigitCount, minCount);
        } else if (brokenButtonCount == 10){
            minCount = Math.min(Math.abs(100-targetChannel), minCount);
        } else {
            StringBuilder sb = new StringBuilder("");
            countButtonPress(sb, 0);
            minCount = Math.min(Math.abs(100-targetChannel), minCount);
        }



        bw.write(minCount + "\n");




        bw.flush();
        bw.close();
    }

    public void countButtonPress(StringBuilder number, int index) {

        for(int i=0; i<button.length; i++) {
            StringBuilder sb = new StringBuilder(number.toString());
            sb.append(button[i]);
            minCount = Math.min(Math.abs((targetChannel-Integer.parseInt(sb.toString())))+sb.length(), minCount);
            if(index < 6) {
                countButtonPress(sb, index+1);
            }
        }

    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}