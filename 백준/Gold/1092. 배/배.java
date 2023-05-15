import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int craneCount = Integer.parseInt(bf.readLine());
        ArrayList<Integer> craneWeight = new ArrayList<>();
        ArrayList<Integer> boxWeight = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<craneCount; i++) {
            craneWeight.add(Integer.parseInt(st.nextToken()));
        }

        int boxCount = Integer.parseInt(bf.readLine());

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<boxCount; i++) {
            boxWeight.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(craneWeight, Collections.reverseOrder());
        Collections.sort(boxWeight, Collections.reverseOrder());

        int maxCount = 0;

        if(craneWeight.get(0)<boxWeight.get(0)) {
            bw.write("-1");
            bw.flush();
            bw.close();
            return;
        }

        while(boxWeight.size()>0) {
            int boxSize = 0;
            for(int i=0; i<boxWeight.size(); i++) {
                if(boxSize == craneCount) break;

                if(craneWeight.get(boxSize) >= boxWeight.get(i)) {
                    boxSize++;
                    boxWeight.remove(i);
                    if(boxWeight.size() == 0) break;
                    i = i-1;
                }
            }
            maxCount++;
        }

        bw.write(String.valueOf(maxCount));

        bw.flush();
        bw.close();


    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}