import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sugarWeight = Integer.parseInt(bf.readLine());
        int bagCount = -1;

        for(int i=sugarWeight/3; i>-1; i--){
            int remainingSugar = sugarWeight - (i*5);
            if(remainingSugar%3==0 && remainingSugar>-1){
                bagCount = i;
                bagCount += remainingSugar/3;
                break;
            }
        }

        bw.write(String.valueOf(bagCount));


        bw.flush();
        bw.close();


    }

}