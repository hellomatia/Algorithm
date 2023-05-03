import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputString = new String[5];
        int max = -1;

        for(int i=0; i<5; i++) {
            inputString[i] = bf.readLine();
            if(max<inputString[i].length()){
                max = inputString[i].length();
            }
        }

        for(int i=0; i<max; i++){
            for(int j=0; j<5; j++){
                if(i>inputString[j].length()-1) {
                    continue;
                }
                bw.write(String.valueOf(inputString[j].charAt(i)));
            }
        }
        bf.close();

        bw.flush();
        bw.close();
    }
    }
