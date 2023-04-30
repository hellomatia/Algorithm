import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int basketCount = Integer.parseInt(st.nextToken());
        int changeCount = Integer.parseInt(st.nextToken());

        int[] basketOrder = new int[basketCount];
        int[] tempBasketOrder = new int[basketCount];

        for(int i=0; i<basketCount; i++) {
            basketOrder[i] = i+1;
        }

        for(int i=0; i<changeCount; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int beginNum = Integer.parseInt(st.nextToken());
            int endNum = Integer.parseInt(st.nextToken());
            int midNum = Integer.parseInt(st.nextToken());

            for(int j=0; j<(endNum-midNum)+1; j++) {
                tempBasketOrder[(beginNum-1)+j] = basketOrder[(midNum-1)+j];
            }
            for(int k=0; k<(midNum-beginNum); k++ ) {
                tempBasketOrder[(beginNum+endNum-midNum)+k] = basketOrder[(beginNum-1)+k];
            }
            for(int l=(beginNum-1); l<endNum; l++) {
                basketOrder[l] = tempBasketOrder[l];
            }
        }
        
        bf.close();

        for(int i=0; i<basketCount; i++){
            bw.write(String.valueOf(basketOrder[i]));
            bw.write(" ");
        }


        bw.flush();
        bw.close();
    }
    }
