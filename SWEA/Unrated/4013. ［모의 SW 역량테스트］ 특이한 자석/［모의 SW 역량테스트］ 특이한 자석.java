import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

	class Gear {
		List<Integer> gearPoles;

		int startIndex;

		Gear(List<Integer> gearPoles) {
			this.gearPoles = gearPoles;
			startIndex = 0;
		}

		int getTopGearPole() {
			int index = startIndex;
			return gearPoles.get(index);
		}

		int getRightGearPole() {
			int index = (startIndex + 2) % 8;
			return gearPoles.get(index);
		}

		int getDownGearPole() {
			int index = (startIndex + 4) % 8;
			return gearPoles.get(index);
		}

		int getLeftGearPole() {
			int index = (startIndex + 6) % 8;
			return gearPoles.get(index);
		}

		public void rotateCounterClockwise() {
			startIndex++;
			startIndex %= 8;
		}

		public void rotateClockwise() {
			startIndex--;
			startIndex += 8;
			startIndex %= 8;
		}
	}

	private static final int GEAR_COUNT = 4;
	private static final int POLE_N = 0;
	private static final int POLE_S = 1;
	private static final int CLOCK_WISE = 1;
	private static final int COUNTER_CLOCK_WISE = -1;

	private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	private Gear[] gears;
	private boolean[] visited;
	private int rotateCount;

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private void solution() throws IOException {
		int T = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= T; i++) {
			init();
			rotateGears();
			printResult(i);
		}
	}

	private void init() throws IOException {
		initRotateCount();
		initGears();
	}

	private void initGears() {
		gears = new Gear[GEAR_COUNT];
		IntStream.range(0, GEAR_COUNT).forEach(count -> {
			try {
				gears[count] = readGear();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

	private Gear readGear() throws IOException {
		return new Gear(readNumbers());
	}

	private List<Integer> readNumbers() throws IOException {
		return Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
	}

	private void initRotateCount() throws IOException {
		rotateCount = Integer.parseInt(bf.readLine());
	}

	private void rotateGears() throws IOException {
		while (rotateCount-- > 0) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int rotateGearNum = Integer.parseInt(st.nextToken()) - 1;
			int directionOfRotation = Integer.parseInt(st.nextToken());
			visited = new boolean[GEAR_COUNT];
			rotateGear(rotateGearNum, directionOfRotation);
		}
	}

	private void rotateGear(int rotateGearNum, int directionOfRotation) {
		int rightGearNum = rotateGearNum + 1;
		int leftGearNum = rotateGearNum - 1;

		visited[rotateGearNum] = true;

		if (rightGearNum < GEAR_COUNT && !visited[rightGearNum]
				&& canRotate(gears[rotateGearNum], gears[rightGearNum])) {
			rotateGear(rightGearNum, getOppositeDirection(directionOfRotation));
		}
		if (0 <= leftGearNum && !visited[leftGearNum] && canRotate(gears[leftGearNum], gears[rotateGearNum])) {
			rotateGear(leftGearNum, getOppositeDirection(directionOfRotation));
		}

		if (directionOfRotation == CLOCK_WISE) {
			gears[rotateGearNum].rotateClockwise();
		}
		if (directionOfRotation == COUNTER_CLOCK_WISE) {
			gears[rotateGearNum].rotateCounterClockwise();
		}
	}

	private boolean canRotate(Gear leftGear, Gear rightGear) {
		return leftGear.getRightGearPole() != rightGear.getLeftGearPole();
	}

	private int getOppositeDirection(int direction) {
		if (direction == CLOCK_WISE) {
			return COUNTER_CLOCK_WISE;
		}
		return CLOCK_WISE;
	}

	private int getResult(int gearNum) {
		if (gearNum == 0) {
			if (gears[gearNum].getTopGearPole() == POLE_N) {
				return 0;
			}
			return 1;
		}
		if (gearNum == 1) {
			if (gears[gearNum].getTopGearPole() == POLE_N) {
				return 0;
			}
			return 2;
		}
		if (gearNum == 2) {
			if (gears[gearNum].getTopGearPole() == POLE_N) {
				return 0;
			}
			return 4;
		}
		if (gearNum == 3) {
			if (gears[gearNum].getTopGearPole() == POLE_N) {
				return 0;
			}
			return 8;
		}
		return -1;
	}

	private void printResult(int testCase) throws IOException {
		int result = IntStream.range(0, GEAR_COUNT).map(this::getResult).sum();
		bw.write("#" + testCase + " ");
		bw.write(result + "\n");
		bw.flush();
	}
}