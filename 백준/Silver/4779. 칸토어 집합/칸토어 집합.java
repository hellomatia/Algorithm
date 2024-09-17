import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private String[] kantores; 
    private List<Integer> list;

    public static void main(String[] args) throws IOException {
        new Main().soulution();
    }

    private void soulution() throws IOException {
        init();
        printAns(calcAns());
        close();
    }

    private void init() throws IOException {
        list = new ArrayList<>();
        String num;
        while ((num = bf.readLine()) != null) {
            list.add(Integer.parseInt(num));
        }
    }

    private String calcAns() {
        StringBuilder ans = new StringBuilder();
        kantores = new String[13];
        kantores[0] = "-";
        for (int num : list) {
            ans.append(kantore(num))
              .append("\n");
        }
        return ans.toString();
    }

    private String kantore(int count) {
        if (kantores[count] != null) {
            return kantores[count];
        }

        StringBuilder blank = new StringBuilder();
        int blankCount = (int) Math.pow(3, count - 1);
        for (int i = 0; i < blankCount; i++) {
            blank.append(" ");
        }

        return kantores[count] = kantore(count - 1) + blank.toString() + kantore(count - 1);
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
    }

    private void close() throws IOException {
        bw.close();
        bf.close();
    }
}