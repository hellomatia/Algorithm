import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cityCount = Integer.parseInt(bf.readLine());
        int[] cityDistances = new int[cityCount-1];
        int[] cityLiterPrice = new int[cityCount];

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0; i<cityCount-1; i++){
            cityDistances[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<cityCount; i++){
            cityLiterPrice[i] = Integer.parseInt(st.nextToken());
        }

        bf.close();

        long minCost = 0;
        int minCostPerLiter = Integer.MAX_VALUE;

        for(int i=0; i<cityCount-1; i++){
            if(minCostPerLiter>cityLiterPrice[i]){
                minCostPerLiter = cityLiterPrice[i];
            }
            minCost += minCostPerLiter*cityDistances[i];
        }

        bw.write(String.valueOf(minCost));


        bw.flush();
        bw.close();

    }
}

