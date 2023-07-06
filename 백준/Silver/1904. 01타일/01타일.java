import java.util.*;
import java.io.*;

public class Main
{
    // tip: arguments are passed via the field below this editor
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] dp = new int[N+1];

        if (N<3) {
            if (N==1) {
                System.out.println("1");
            }

            if (N==2) {
                System.out.println("2");
            }
        } else {
            dp[1] = 1;
            dp[2] = 2;

            for (int i=3; i<=N; i++) {
                dp[i] = (dp[i-1]+dp[i-2]) % 15746;
            }
            System.out.println(dp[N]);
        }
        
    }
}