public class Prime {
    public static void main(String[] args) {
        int primeCount = 0;
        int currentNumber = 2;

        System.out.println("First 50 prime numbers:");

        while (primeCount < 10) {
            if (isPrime(currentNumber)) {
                System.out.println(currentNumber);
                primeCount++;
            }
            currentNumber++;
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}