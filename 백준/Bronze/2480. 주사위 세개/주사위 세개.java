import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] num = new int[3];
        int bonus, reward;

        for (int i = 0 ; i < 3 ; i++) {
            num[i] = scanner.nextInt();
        }

        Arrays.sort(num);

        if (num[0]==num[1] || num[1]==num[2]) {
            bonus = 1000;
            reward = bonus + (num[1]*100);
            if (num[0]==num[1] && num[1]==num[2]) {
                bonus = 10000;
                reward = bonus + (num[1]*1000);
            }
            System.out.println(reward);
        }
        else {
            reward = num[2]*100;
            System.out.println(reward);
        }

    }
}
