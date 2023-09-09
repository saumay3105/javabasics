import java.util.Scanner;

public class SpyNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();

        int product = 1;
        int sum = 0;

        while (num > 0) {
            int digit = num % 10;
            sum += digit;
            product *= digit;
            num /= 10;
        }

        if (sum == product) {
            System.out.println("Spy Number");
        } else {
            System.out.println("Not a Spy Number");
        }

        scanner.close();
    }
}
