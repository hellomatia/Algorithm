import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            List<Integer> listA = new ArrayList<>();
            List<Integer> listB = new ArrayList<>();
            init(br, listA, listB);

            Set<Integer> setA = new HashSet<>(listA);
            Set<Integer> setB = new HashSet<>(listB);
            Set<Integer> setAB = setA.stream()
                    .filter(setB::contains)
                    .collect(Collectors.toSet());

            List<Integer> result = new ArrayList<>();
            while (!setAB.isEmpty()) {
                int max = setAB.stream()
                        .max(Integer::compareTo)
                        .orElseThrow();

                result.add(max);
                int idxA = listA.indexOf(max);
                int idxB = listB.indexOf(max);
                listA = listA.subList(idxA + 1, listA.size());
                listB = listB.subList(idxB + 1, listB.size());

                setA = new HashSet<>(listA);
                setB = new HashSet<>(listB);
                setAB = setA.stream()
                        .filter(setB::contains)
                        .collect(Collectors.toSet());
            }

            bw.write(result.size() + "\n");
            for (int num : result) {
                bw.write(num + " ");
            }
        }
    }

    private void init(BufferedReader br, List<Integer> listA,  List<Integer> listB) throws IOException {
        int arrASize = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arrASize; i++) {
            listA.add(Integer.parseInt(st.nextToken()));
        }

        int arrBSize = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arrBSize; i++) {
            listB.add(Integer.parseInt(st.nextToken()));
        }
    }
}