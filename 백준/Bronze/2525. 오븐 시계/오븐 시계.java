import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int hour = scanner.nextInt();
        int min = scanner.nextInt();
        int ovenTime = scanner.nextInt();

        int adjustedHour = hour;
        int adjustedMin = min + ovenTime;

        if (adjustedMin>=60) {
            adjustedHour = adjustedHour + (adjustedMin/60);
            adjustedMin = adjustedMin % 60;
            if (adjustedHour>=24) {
                adjustedHour = adjustedHour - 24;
            }
            System.out.println(adjustedHour+" "+adjustedMin);
        }
        else {
            System.out.println(adjustedHour+" "+adjustedMin);
        }

    }
}
