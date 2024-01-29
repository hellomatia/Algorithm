import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private int finish;
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	private void solution() throws IOException {
		init();
		bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		printResult(0);
		bw.flush();
		bw.close();
	}
	
	private void init() throws IOException {
		finish = Integer.parseInt(bf.readLine());
	}
	
	private void printResult(int count) throws IOException {
		if (count == finish) {
			printUnderBar(count);
			bw.write("\"재귀함수가 뭔가요?\"\n");
			
			printUnderBar(count);
			bw.write("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
						
			printUnderBar(count);
			bw.write("라고 답변하였지.\n");
			return;
		}
		
		printUnderBar(count);
		bw.write("\"재귀함수가 뭔가요?\"\n");
		
		printUnderBar(count);
		bw.write("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		
		printUnderBar(count);
		bw.write("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");

		printUnderBar(count);
		bw.write("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");

		printResult(count + 1);
		
		printUnderBar(count);
		bw.write("라고 답변하였지.\n");
	}
	
	private void printUnderBar(int count) throws IOException {
		for (int i = 0; i < count; i++) {
			bw.write("____");
		}
	}
}
