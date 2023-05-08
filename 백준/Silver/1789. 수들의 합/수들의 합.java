import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        long num = Long.parseLong(bf.readLine());
        long count = 1L;

        while(num>=count){
            num -= count++;
        }



        bw.write(String.valueOf(count-1));

        bw.flush();
        bw.close();

    }
}

