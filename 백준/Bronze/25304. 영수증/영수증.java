import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int totalAmount = scanner.nextInt();
        int count = scanner.nextInt();
        int testAmount = 0;

        int[] price = new int[count];
        int[] numOfProducts = new int[count];

        for (int i = 0 ; i < count ; i++) {
            price[i] = scanner.nextInt();
            numOfProducts[i] = scanner.nextInt();
            testAmount = testAmount + (price[i]*numOfProducts[i]);
        }

        if (totalAmount == testAmount) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}
