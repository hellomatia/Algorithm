import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static HashSet<String> unheardPeople = new HashSet<>();
    static HashSet<String> didntSeePeople = new HashSet<>();
    static ArrayList<String> haventHeardAndSeenPeople = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        init();
        findHaventHeardAndSeen();
        sortfindHaventHeardAndSeen();
        printResult();
    }

    public void init() throws IOException {
        initialNAndM();
        initialUnheardOfPeople();
        initialDidntSeeOfPeople();
    }

    public void initialNAndM() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        this.N = Integer.parseInt(st.nextToken());
        this.M = Integer.parseInt(st.nextToken());
    }

    public void initialUnheardOfPeople() throws IOException {
        for (int i = 0; i < N; i++) {
            unheardPeople.add(bf.readLine());
        }
    }

    public void initialDidntSeeOfPeople() throws IOException {
        for (int i = 0; i < M; i++) {
            didntSeePeople.add(bf.readLine());
        }
    }

    public void findHaventHeardAndSeen() {
        for (String name : unheardPeople) {
            if (didntSeePeople.contains(name)) {
                haventHeardAndSeenPeople.add(name);
            }
        }
    }

    public void sortfindHaventHeardAndSeen() {
        Collections.sort(haventHeardAndSeenPeople);
    }

    public void printResult() throws IOException {
        bw.write(String.valueOf(haventHeardAndSeenPeople.size()) + "\n");
        for (String name : haventHeardAndSeenPeople) {
            bw.write(name + "\n");
        }
        bw.flush();
    }
}
