import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputAlpha = bf.readLine();
        bf.close();
        
        int[] alphaToNum = new int[inputAlpha.length()];
        int totalTime = 0;

        for(int i=0; i<inputAlpha.length(); i++) {
            if(inputAlpha.charAt(i)<83){
                alphaToNum[i] = (inputAlpha.charAt(i)-65)/3 + 2;
            }
            else if (inputAlpha.charAt(i)==83) {
                alphaToNum[i] =  7;
            }
            else if (inputAlpha.charAt(i)<87) {
                alphaToNum[i] =  8;
            }
            else {
                alphaToNum[i] =  9;
            }
        }

        for(int i=0; i<inputAlpha.length(); i++){
            totalTime += (alphaToNum[i] + 1);
        }

        bw.write(String.valueOf(totalTime));


        bw.flush();
        bw.close();
    }
    }
