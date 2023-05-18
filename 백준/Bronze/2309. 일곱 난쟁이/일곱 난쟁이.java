import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dwarf = new int[9];
        int dwarfHeightSum = 0;

        for(int i=0; i<9; i++) {
            dwarf[i] = Integer.parseInt(bf.readLine());
            dwarfHeightSum += dwarf[i];
        }

        int count=0;

        while(dwarfHeightSum!=100){
            for(int i=count+1; i<9; i++) {
                if(100 == dwarfHeightSum-(dwarf[i]+dwarf[count])) {
                    dwarf[i] = 0;
                    dwarf[count] = 0;
                    dwarfHeightSum = 100;
                    break;
                }
            }
            count++;
        }



        Arrays.sort(dwarf);

        for(int i=2; i<9; i++) {
            bw.write(String.valueOf(dwarf[i]));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}