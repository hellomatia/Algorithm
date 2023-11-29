import java.io.*;
import java.util.*;

class Person {
    String name;
    int age;
    int count;

    Person(String name, int age, int count) {
        this.name = name;
        this.age = age;
        this.count = count;
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static PriorityQueue<Person> people;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initN();
        initPeople();
        printResult();
        bw.flush();
        bw.close();
    }

    private void initN() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void initPeople() throws IOException {
        people = new PriorityQueue<>(
                (o1, o2) -> {
                    if (o1.age == o2.age) {
                        return o1.count - o2.count;
                    }
                    return o1.age - o2.age;
                }
        );
;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            people.offer(new Person(name, age, i));
        }
    }
    private void printResult() throws IOException {
        while (!people.isEmpty()) {
            Person person = people.poll();
            bw.write(person.age + " " + person.name);
            bw.write("\n");
        }
    }
}
