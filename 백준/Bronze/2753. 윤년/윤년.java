import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int year = scanner.nextInt();

        int multiplesOf4 = year%4;
        int multiplesOf100 = year%100;
        int multiplesOf400 = year%400;

        if ((multiplesOf4==0 && multiplesOf100 != 0) || multiplesOf400 == 0) {
            System.out.println("1");
        }
        else {
            System.out.println("0");
        }

    }
}
