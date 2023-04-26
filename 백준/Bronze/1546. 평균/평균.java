import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int subjectCount = Integer.parseInt(bf.readLine());
        double[] subjectScore = new double[subjectCount];

        StringTokenizer st = new StringTokenizer(bf.readLine()," ");

        for(int count=0; count<subjectCount; count++) {
            subjectScore[count] = Double.parseDouble(st.nextToken());
        }
        bf.close();

        Arrays.sort(subjectScore);

        double newSubjectTotalScore = 0;

        for (int count=0; count<subjectCount; count++) {
            newSubjectTotalScore += ((subjectScore[count]/subjectScore[subjectCount-1])*100);
        }

        bw.write(String.valueOf(newSubjectTotalScore/subjectCount));


        bw.flush();
        bw.close();
    }
    }
