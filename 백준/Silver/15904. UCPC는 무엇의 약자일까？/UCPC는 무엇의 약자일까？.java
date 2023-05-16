import java.io.*;
import java.util.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputString = bf.readLine();

        boolean condition1 = true, condition2 = false, condition3 = false, condition4 = false;

        boolean hasU=false, hasFirstC=false, hasP=false, hasSecondC=false;

        //'U'=85, 'C'=67, 'P'=80
        for(int i=0; i<inputString.length(); i++){
            if(condition1){
                if(inputString.charAt(i)=='U'){
                    condition1 = false;
                    condition2 = true;
                    hasU=true;
                }
            }
            if(condition2){
                if(inputString.charAt(i)=='C'){
                    condition2 = false;
                    condition3 = true;
                    hasFirstC=true;
                }
            }
            if(condition3){
                if(inputString.charAt(i)=='P'){
                    condition3 = false;
                    condition4 = true;
                    hasP=true;
                }
            }
            if(condition4){
                if(inputString.charAt(i)=='C'){
                    condition4 = false;
                    hasSecondC=true;
                }
            }
        }

        if(hasU&&hasFirstC&&hasP&&hasSecondC) bw.write("I love UCPC");
        else bw.write("I hate UCPC");



        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}