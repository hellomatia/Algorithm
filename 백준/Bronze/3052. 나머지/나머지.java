import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        Set<String> num = new HashSet<>();

        for (int count=0; count<10; count++) {
            num.add(String.valueOf(Integer.parseInt(bf.readLine()) % 42));
        }

        bf.close();

        long numCount=num.stream().count();

        bw.write(String.valueOf(numCount));

        bw.flush();
        bw.close();
    }
    }
