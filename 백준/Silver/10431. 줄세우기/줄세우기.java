import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int P;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int count = 0; count < P; count++) {
            standInline();
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        initP();
    }

    private void initP() throws IOException {
        P = Integer.parseInt(bf.readLine());
    }

    private List<Integer> readList() throws IOException {
        return Arrays.stream(bf.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void standInline() throws IOException {
        var students = readList();
        int testCaseCount = students.get(0);

        int result = 0;
        queue = new LinkedList<>();
        queue.offer(students.get(1));
        for (int count = 2; count <= 20; count++) {
            result += getStudentStepBackCount(students.get(count), queue.size());
        }

        printResult(testCaseCount, result);
    }

    private int getStudentStepBackCount(int studentHeight, int numOfStudents) {
        boolean find = false;
        int count = 0;
        while (numOfStudents-- > 0) {
            int nowHeight = queue.poll();
            if (nowHeight > studentHeight && !find) {
                find = true;
                queue.offer(studentHeight);
            }
            if (find) {
                count++;
            }
            queue.offer(nowHeight);
        }
        if (!find) {
            queue.offer(studentHeight);
        }
        return count;
    }

    private void printResult(int testCaseCount, int result) throws IOException {
        bw.write(testCaseCount + " " + result + "\n");
    }
}
