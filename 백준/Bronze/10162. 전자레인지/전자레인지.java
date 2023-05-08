import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int time = Integer.parseInt(bf.readLine());
        int[] buttonCount = new int[3];

        buttonCount[0] = time/300;
        time %= 300;
        buttonCount[1] = time/60;
        time %= 60;
        buttonCount[2] = time/10;
        time %= 10;

        if(time == 0){
            for(int i=0; i<3; i++){
                bw.write(String.valueOf(buttonCount[i]));
                bw.write(" ");
            }
        }
        else {
            bw.write(String.valueOf(-1));
        }

        bw.flush();
        bw.close();

    }
}

