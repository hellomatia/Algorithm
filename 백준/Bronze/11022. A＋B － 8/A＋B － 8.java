import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static void main(String args[]) throws IOException {
 
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
 
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= T; i++) {
			String str = br.readLine();
			int A = str.charAt(0)-'0';
			int B = str.charAt(2)-'0';
			sb.append("Case #").append(i).append(": ").append(A)
			.append(" + ").append(B).append(" = ").append(A+B).append('\n');
		}
		System.out.println(sb);
	}
}
 