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
        double[] aboveAvgRatio = new double[testCaseCount];
        String[] resultAvgRatio = new String[testCaseCount];

        for(int i=0; i<testCaseCount; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int studentCount = Integer.parseInt(st.nextToken());
            double studentAvg = 0;
            double[] studentScore = new double[studentCount];

            for(int j=0; j<studentCount; j++) {
                studentScore[j] = Double.valueOf(st.nextToken());
                studentAvg += studentScore[j];
            }

            studentAvg /= studentCount;

            for(int k=0; k<studentCount; k++) {
                if(studentScore[k]>studentAvg) {
                    aboveAvgRatio[i]++;
                }
            }

            aboveAvgRatio[i] /= studentCount;
            aboveAvgRatio[i] *= 100;
            resultAvgRatio[i] = String.format("%.3f", aboveAvgRatio[i]);

            bw.write(resultAvgRatio[i]);
            bw.write("%\n");
        }

        bf.close();

        bw.flush();
        bw.close();
    }
    }
