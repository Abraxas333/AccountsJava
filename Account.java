import java.util.List;
import java.util.Scanner;

public class Account {
    private static int instanceCount = 0;
    private String name;
    private Double balance;
    private int id;

    public Account(Object[] args) {
        this.name = (String) args[0];
        this.balance = (Double) args[1];
        this.id = ++instanceCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void retrieveAccountInfo(Account account) {
        System.out.println("Account name: " + account.getName());
        System.out.println("Account balance: " + account.getBalance());
        System.out.println("Account id: " + account.getId());
    }

    public static void listAccounts(List<Account> accounts) {
        for (Account v : accounts) {
            System.out.println("Name: " + v.getName() + ", Balance: " + v.getBalance() + ", ID: " + v.getId());
        }
    }
    public static Object[] addAccount() {
        Object[] arr = new Object[2];
        Scanner sc = new Scanner(System.in);

        System.out.println("For adding a new account please input account owners name:");
        String name = sc.nextLine();
        while (!name.matches("^[a-zA-Z]+$")) {
            System.out.println("Invalid Input detected, Please input letters only:");
            name = sc.nextLine();
        }
        arr[0] = name;

        System.out.println("Please input balance:");
        while (true) {
            try {
                double balance = Double.parseDouble(sc.nextLine());
                arr[1] = balance;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input detected. Please input a number:");
            }
        }
        return arr;
    }
}
