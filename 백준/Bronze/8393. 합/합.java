import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int num = 0;

        for (int i = 1 ; i <= count ; i++) {
            num = num + i;
        }

        System.out.println(num);
    }
}
