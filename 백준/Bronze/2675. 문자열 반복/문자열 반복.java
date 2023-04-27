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

        int testCaseCount = Integer.parseInt(bf.readLine());


        for(int i=0; i<testCaseCount; i++){

            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int numRepetitions = Integer.parseInt(st.nextToken());
            String inputString = st.nextToken();

            for(int j=0; j<inputString.length(); j++){
                for(int k=0; k<numRepetitions; k++){
                    bw.write(Character.toString(inputString.charAt(j)));
                }
            }

            bw.write("\n");
        }

        bf.close();


        bw.flush();
        bw.close();
    }
    }
