import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.util.Arrays;
    import java.util.StringTokenizer;

    public class Main {
        private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        private int N;
        private int[] fruits;
        private int[] nums;

        public static void main(String[] args) throws IOException {
            new Main().solution();
        }

        private void solution() throws IOException {
            init();
            printAns(calcAns(0, 0, 0, 0, 0));
        }

        private void init() throws IOException {
            N = Integer.parseInt(bf.readLine());

            fruits = new int[N];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                fruits[i] = Integer.parseInt(st.nextToken());
            }

            nums = new int[10];
        }

        private int calcAns(int left, int right, int count, int kind, int max) {
            if (right >= N) {
                return max;
            }

            if (nums[fruits[right]] == 0) {
                kind++;
            }

            count++;
            nums[fruits[right]]++;

            if (kind > 2) {
                if (-- nums[fruits[left]]== 0) {
                    kind--;
                }
                count--;
                left++;
            }

            max = Math.max(max, count);
            return calcAns(left, right + 1, count, kind, max);
        }

        private void printAns(int ans) throws IOException {
            bw.write(ans + "\n");
            bw.flush();
        }
    }