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

        double totalCredit = 0;
        double totalGrade = 0;

        for(int i=0; i<20; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            String subjectName = st.nextToken();
            double credit = Double.valueOf(st.nextToken());
            String grade = st.nextToken();

            if (grade.equals("A+")) {
                totalCredit += credit;
                totalGrade += (credit*4.5);
            }
            else if (grade.equals("A0")) {
                totalCredit += credit;
                totalGrade += (credit*4);
            }
            else if (grade.equals("B+")) {
                totalCredit += credit;
                totalGrade += (credit*3.5);
            }
            else if (grade.equals("B0")) {
                totalCredit += credit;
                totalGrade += (credit*3);
            }
            else if (grade.equals("C+")) {
                totalCredit += credit;
                totalGrade += (credit*2.5);
            }
            else if (grade.equals("C0")) {
                totalCredit += credit;
                totalGrade += (credit*2);
            }
            else if (grade.equals("D+")) {
                totalCredit += credit;
                totalGrade += (credit*1.5);
            }
            else if (grade.equals("D0")) {
                totalCredit += credit;
                totalGrade += (credit*1);
            }
            else if (grade.equals("F")) {
                totalCredit += credit;
                totalGrade += (credit*0);
            }

        }

        bw.write(String.format("%.6f",totalGrade/totalCredit));


        bf.close();

        bw.flush();
        bw.close();
    }
    }
