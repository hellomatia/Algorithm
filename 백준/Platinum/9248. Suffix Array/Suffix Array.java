import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	class Suffix implements Comparable<Suffix> {
		int index;
		int rank;
		int nextRank;

		public Suffix(int index, int rank) {
			this.index = index;
			this.rank = rank;
		}

		@Override
		public int compareTo(Suffix o) {
			if (this.rank == o.rank) {
				return this.nextRank - o.nextRank;
			}
			return this.rank - o.rank;
		}
	}

	private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private String str;
	private char[] S;
	private int max;
	private int N, d;
	private int[] sa, pos, lcp;
	private StringBuilder sb;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
	}

	private void init() throws IOException {
		str = bf.readLine();
		N = str.length();
		pos = new int[N];
		lcp = new int[N - 1];
		sb = new StringBuilder();
	}

	private void calcResult() {
		calcSA(str);
		calcLCP();
	}

	private void calcSA(String s) {
		Suffix[] sa = new Suffix[N];

		for (int i = 0; i < N; i++) {
			int rank = s.charAt(i) - 'a';
			sa[i] = new Suffix(i, rank);
		}

		for (int i = 0; i < N - 1; i++) {
			sa[i].nextRank = sa[i + 1].rank;
		}
		sa[N - 1].nextRank = -1;

		Arrays.sort(sa);

		int[] temp = new int[N];
		for (int length = 4; length < 2 * N; length <<= 1) {
			int rank = 0;
			int prev = sa[0].rank;
			sa[0].rank = rank;
			temp[sa[0].index] = 0;

			for (int i = 1; i < N; i++) {
				if (sa[i].rank == prev && sa[i].nextRank == sa[i - 1].nextRank) {
					prev = sa[i].rank;
					sa[i].rank = rank;
				} else {
					prev = sa[i].rank;
					sa[i].rank = ++rank;
				}
				temp[sa[i].index] = i;
			}

			for (int i = 0; i < N; i++) {
				int nextIndex = sa[i].index + (length / 2);
				if (nextIndex >= N) {
					sa[i].nextRank = -1;
					continue;
				}
				sa[i].nextRank = sa[temp[nextIndex]].rank;
			}

			Arrays.sort(sa);
		}

		this.sa = new int[N];
		for (int i = 0; i < N; i++) {
			this.sa[i] = sa[i].index;
		}
	}

	void calcLCP() {
		int[] invSuff = new int[N];

		for (int i = 0; i < N; i++) {
			invSuff[sa[i]] = i;
		}

		int k = 0;
		for (int i = 0; i < N; i++) {
			if (invSuff[i] == N - 1) {
				k = 0;
				continue;
			}

			int j = sa[invSuff[i] + 1];

			while (i + k < N && j + k < N) {
				if (str.charAt(i + k) != str.charAt(j + k)) {
					break;
				}
				k++;
			}

			lcp[invSuff[i]] = k;

			if (k > 0) {
				k--;
			}
		}
	}

	private void printResult() throws IOException {
		for (int num : sa) {
			sb.append(num + 1).append(" ");
		}
		sb.append("\nx ");
		for (int num : lcp) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
}