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
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine()," ");

        int basketCount = Integer.parseInt(st.nextToken());
        Integer[] basketArray = new Integer[basketCount];

        for(int count=0; count<basketCount; count++) {
            basketArray[count] = count+1;
        }

        int reverseCount = Integer.parseInt(st.nextToken());


        for (int count=0; count<reverseCount; count++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            while (i<j) {
                int temp = basketArray[i];
                basketArray[i++] = basketArray[j];
                basketArray[j--] = temp;
            }

        }

        bf.close();

        for (int count=0; count<basketCount; count++) {

            bw.write(basketArray[count]+" ");

        }

        bw.flush();
        bw.close();
    }
    }
