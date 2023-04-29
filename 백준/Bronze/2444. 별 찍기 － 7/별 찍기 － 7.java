import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inputNum = Integer.parseInt(bf.readLine());
        bf.close();

        for(int i=0; i<inputNum; i++) {
            for(int j=0; j<(inputNum-(i+1)); j++) {
                bw.write(" ");
            }
            for(int k=0; k<((i+1)*2-1); k++) {
                bw.write("*");
            }
            bw.write("\n");
        }

        for(int i=0; i<inputNum-1; i++) {
            for(int j=0; j<i+1; j++) {
                bw.write(" ");
            }
            for(int k=0; k<((inputNum-(i+1))*2-1); k++) {
                bw.write("*");
            }
            bw.write("\n");
        }


        bw.flush();
        bw.close();
    }
    }
