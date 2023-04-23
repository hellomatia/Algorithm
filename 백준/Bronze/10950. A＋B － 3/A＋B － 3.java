import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int[] firstNum = new int[count];
        int[] secondNum = new int[count];

        for (int i = 0 ; i < count ; i++) {
            firstNum[i] = scanner.nextInt();
            secondNum[i] = scanner.nextInt();
        }

        for (int i = 0 ; i < count ; i++) {
            System.out.println(firstNum[i]+secondNum[i]);
        }
    }
}
