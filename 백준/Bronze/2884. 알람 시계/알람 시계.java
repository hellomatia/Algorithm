import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int hour = scanner.nextInt();
        int min = scanner.nextInt();

        int adjustedHour = hour;
        int adjustedMin = min - 45;

        if (adjustedMin<0) {
            adjustedHour = adjustedHour - 1;
            adjustedMin = adjustedMin + 60;
            if (adjustedHour<0) {
                adjustedHour = adjustedHour + 24;
            }
            System.out.println(adjustedHour+" "+adjustedMin);
        }
        else {
            System.out.println(adjustedHour+" "+adjustedMin);
        }

    }
}
