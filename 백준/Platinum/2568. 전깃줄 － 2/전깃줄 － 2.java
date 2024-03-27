import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Main {

	class Line implements Comparable<Line> {
		int start;
		int end;

		Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Line o) {
			return start - o.start;
		}
	}

	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private int count;
	private List<Line> lines;
	private int[] lineB;
	private Map<Integer, Integer> BAMap;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		init();
		calcResult();
	}

	private void init() throws IOException {
		count = Integer.parseInt(bf.readLine());
		lines = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lines.add(new Line(start, end));
		}

		Collections.sort(lines);
		lineB = new int[count];
		BAMap = new HashMap<>();
		for (int i = 0; i < count; i++) {
			lineB[i] = lines.get(i).end;
			BAMap.put(lines.get(i).end, lines.get(i).start);
		}
	}

	private void calcResult() throws IOException {
		int[] temp = new int[count];
		int[] indexs = new int[count];
		Arrays.fill(temp, Integer.MAX_VALUE);
		int size = 0;

		for (int i = 0; i < count; i++) {
			int index = upperBound(temp, lineB[i]);
			temp[index] = lineB[i];
			indexs[i] = index;
			if (size < index + 1) {
				size = index + 1;
			}
		}

		int[] increasNums = new int[size];
		int tempSize = size - 1;
		for (int i = count - 1; i >= 0; i--) {
			if (tempSize == -1) {
				break;
			}
			if (indexs[i] == tempSize) {
				increasNums[tempSize] = lineB[i];
				tempSize--;
			}
		}

		int resultCount = count - size;
		printResult(resultCount);

		int i = 0;
		int j = 0;

		while (i < size || j < count) {
			if (i == size) {
				printResult(BAMap.get(lineB[j]));
				j++;
				continue;
			} else if (lineB[j] != increasNums[i]) {
				printResult(BAMap.get(lineB[j]));
				j++;
			}
			if (lineB[j] == increasNums[i]) {
				i++;
				j++;
			}
		}
	}

	private int upperBound(int[] temp, int num) {
		int start = 0;
		int end = count - 1;
		int answer = 0;

		while (start <= end) {
			int mid = (start + end) >>> 1;

			if (temp[mid] < num) {
				start = mid + 1;
			} else {
				answer = mid;
				end = mid - 1;
			}
		}
		return answer;
	}

	private void printResult(int result) throws IOException {
		bw.write(result + "\n");
		bw.flush();
	}
}