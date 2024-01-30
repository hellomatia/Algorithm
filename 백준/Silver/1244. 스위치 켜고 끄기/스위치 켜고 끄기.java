import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Student {
	int gender;
	int num;
	
	Student(int gender, int num) {
		this.gender = gender;
		this.num = num;
	}
}

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int bitCount;
	private int[] bits;
	private int studentCount;
	private Student[] students;
	
	private static final int BOY = 1;
	private static final int GIRL = 2;
			
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		calcResult();
		printResult();
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		
		bitCount = Integer.parseInt(bf.readLine());
		bits = new int[bitCount + 1];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= bitCount; i++) {
			bits[i] = Integer.parseInt(st.nextToken());
		}
		
		studentCount = Integer.parseInt(bf.readLine());
		students = new Student[studentCount];
		for (int i = 0; i < studentCount; i++) {
			st = new StringTokenizer(bf.readLine());
			students[i] = new Student(Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()));
		}
	}
	
	private void calcResult() throws IOException {
		for (Student student : students) {
			if (student.gender == BOY) {
				boyChangeBit(student.num);
			} else if (student.gender == GIRL) {
				girlChangeBit(student.num);
			}
		}
	}
	
	private void boyChangeBit(int num) {
		for (int index = num; index <= bitCount; index += num) {
			changeBit(index);
		}
	}
	
	private void girlChangeBit(int num) {
		int start = num;
		int end = num;
		
		while (isIn(start, end) && isPalindrom(start, end))  {
			start--;
			end++;
		}
		
		if (!isIn(start, end) || !isPalindrom(start, end)) {
			start++;
			end--;
		}
		
		for (int index = start; index <= end; index++) {
			changeBit(index);
		}
	}
	
	private void changeBit(int index) {
		bits[index] ^= 1;
	}

	private boolean isPalindrom(int start, int end) {
		return bits[start] == bits[end];
	}
	
	private boolean isIn(int start, int end) {
		return 1 <= start && end <= bitCount;
	}

	private void printResult() throws IOException {
		int start = 1;
		while (start <= bitCount) {
			for (int index = start; index < start + 20 && index <= bitCount; index++) {
				bw.write(bits[index] + " ");
			}
			bw.write("\n");
			start += 20;
		}
	}
}

