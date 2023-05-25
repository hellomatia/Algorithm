import java.io.*;
import java.util.StringTokenizer;

public class Main {
    int[] dwarf = new int[9];
    int[] sevenDwarf = new int[7];
    int minScore = Integer.MAX_VALUE;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0; i<9; i++) {
            dwarf[i] = Integer.parseInt(bf.readLine());
        }

        selectSevenDwarf(0,0);

        bw.flush();
        bw.close();
    }

    public void selectSevenDwarf(int start, int count) {
        if(count==7) {
            int sum=0;
            for(int i=0; i<7; i++){
                sum += sevenDwarf[i];
            }
            if(sum==100) {
                for(int i=0; i<7; i++){
                    System.out.println(sevenDwarf[i]);
                }
            }
            return;
        }

        for(int i=start; i<9; i++) {
            sevenDwarf[count] = dwarf[i];
            selectSevenDwarf(i+1, count+1);
        }
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}