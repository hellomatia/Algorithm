import java.io.*;
import java.util.*;

class Person {
    int weight;
    int height;
    public Person (int weight, int height) {
        this.weight = weight;
        this.height = height;
    }
}

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfPerson = Integer.parseInt(bf.readLine());
        Person[] person = new Person[numOfPerson];

        for(int i=0; i<numOfPerson; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            person[i] = new Person(weight, height);
        }

        for(int i=0; i<numOfPerson; i++) {
            int rank = 1;
            for(int j=0; j<numOfPerson; j++) {
                if(i==j) continue;

                if(person[i].weight<person[j].weight && person[i].height<person[j].height) {
                    rank++;
                }
            }
            bw.write(String.valueOf(rank));
            bw.write(" ");
        }


        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}