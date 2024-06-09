import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final Tri colors = new Tri();
//    private final Tri nickNames = new Tri();
    private final Set<String> nickNames = new HashSet<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        int q = Integer.parseInt(bf.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < q; i++) {
            String teamName = bf.readLine();
            answer.append(calcAns(teamName))
                    .append("\n");
        }
        printAns(answer.toString());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < c; i++) {
            String color = bf.readLine();
            colors.add(color);
        }

        for (int i = 0; i < n; i++) {
            String nickName = bf.readLine();
            nickNames.add(nickName);
        }
    }

    private String calcAns(String teamName) {
        Tri now = colors;
        for (int i = 0; i < teamName.length(); i++) {
            now = now.getTri(teamName.charAt(i));
            if (now == null) {
                return "No";
            }
            if (now.isWord()) {
                String nickName = teamName.substring(i + 1, teamName.length());
                if (nickNames.contains(nickName)) {
                    return "Yes";
                }
            }
        }
        return "No";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }

    private static class Tri {
        private Tri[] child;
        private boolean isWord;

        public Tri() {
            child = new Tri[26];
        }

        public void add(String word) {
            Tri now = this;
            for (int i = 0; i < word.length(); i++) {
                now = now.addNext(word.charAt(i));
            }
            now.setIsWord();
        }

        public void setIsWord() {
            this.isWord = true;
        }

        public boolean isWord() {
            return isWord;
        }

        private Tri addNext(char value) {
            if (child[value - 'a'] == null)
                child[value - 'a'] = new Tri();
            return child[value - 'a'];
        }

        public Tri getTri(char value) {
            return child[value - 'a'];
        }
    }
}