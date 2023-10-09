public class mathops {
    static int add(int x, int y) {
        return x + y;
    }

    static double add(double x, double y) {
        return x + y;
    }

    static double add(int x, double y) {
        return x + y;
    }

    public static void main(String[] args) {
        int intResult1 = add(10, 13);
        double doubleResult1 = add(7.6, 9.4);
        double doubleResult2 = add(9, 8.4);

        System.out.println("Sum of two integers: " + intResult1);
        System.out.println("Sum of two doubles: " + doubleResult1);
        System.out.println("Sum of an integer and a double: " + doubleResult2);
    }

}
