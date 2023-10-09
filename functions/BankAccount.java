public class BankAccount {
    private int accountNumber;
    private double balance;

    public BankAccount() {
        accountNumber = generateAccountNumber();
        balance = 0;
    }

    public BankAccount(double initialBalance) {
        accountNumber = generateAccountNumber();
        balance = initialBalance;
    }

    public BankAccount(int acc, double initialBalance) {
        accountNumber = acc;
        balance = initialBalance;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    private int generateAccountNumber() {
        return 10011;
    }

    public static void main(String[] args) {
        BankAccount x = new BankAccount();
        System.out.print("Default: ");
        System.out.println(x.getAccountNumber() + " " + x.getBalance());

        System.out.print("1 Parameter: ");
        BankAccount y = new BankAccount(0);
        System.out.println(y.getAccountNumber() + " " + y.getBalance());

        System.out.print("2 Parameters: ");
        BankAccount z = new BankAccount(23456, 500);
        System.out.println(z.getAccountNumber() + " " + z.getBalance());

        System.out.println("Initial Balance: " + x.getBalance());
        x.credit(400);
        System.out.println("After Credit: " + x.getBalance());
        x.debit(20);
        System.out.println("After Debit: " + x.getBalance());
        x.debit(1000);
    }

}