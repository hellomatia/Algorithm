import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];
        
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
        
        // A와 B의 합 배열 생성
        int[] AB = new int[n * n];
        int index = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                AB[index++] = A[i] + B[j];
            }
        }
        
        // C와 D의 합 배열 생성
        int[] CD = new int[n * n];
        index = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                CD[index++] = C[i] + D[j];
            }
        }
        
        // 정렬
        Arrays.sort(AB);
        Arrays.sort(CD);
        
        // 투 포인터로 합이 0이 되는 쌍 찾기
        long answer = 0;
        int left = 0;
        int right = n * n - 1;
        
        while(left < n * n && right >= 0) {
            int sum = AB[left] + CD[right];
            
            if(sum == 0) {
                // 중복 원소 처리
                long countAB = 1;
                long countCD = 1;
                
                while(left + 1 < n * n && AB[left] == AB[left + 1]) {
                    countAB++;
                    left++;
                }
                
                while(right - 1 >= 0 && CD[right] == CD[right - 1]) {
                    countCD++;
                    right--;
                }
                
                answer += countAB * countCD;
                left++;
                right--;
            }
            else if(sum > 0) {
                right--;
            }
            else {
                left++;
            }
        }
        
        System.out.println(answer);
    }
}