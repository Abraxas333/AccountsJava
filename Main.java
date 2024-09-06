import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome! Please input 1 for creating a new account, 2 for adding/withdrawing to/from an existing account, 3 to get a list of all accounts, 4 retrieving information of a single account, 5 for deleting an account.");
        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        boolean finished = false;

        while (!finished) {
            int input;
            try {
                input = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input detected, please input a number between 1 and 5.");
                sc.next();
                input = sc.nextInt();
            }
            switch (input) {
                case 1:
                    Object[] accountData = Account.addAccount();
                    Account account = new Account(accountData);
                    accounts.add(account);
                    account.retrieveAccountInfo(account);
                    break;

                case 2:
                    System.out.println("Input the id of the account to update:");
                    int id = sc.nextInt();
                    Account accountToUpdate = null;
                    for (Account v : accounts) {
                        if (v.getId() == id) {
                            accountToUpdate = v;
                            break;
                        }
                    }
                    if (accountToUpdate != null) {
                        System.out.println("Input amount to add/withdraw from account e.g. +100 or -100");
                        String addWithdraw = sc.next();
                        char operation = addWithdraw.charAt(0);
                        double amount;
                        try {
                            amount = Double.parseDouble(addWithdraw.substring(1));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number format. Please try again.");
                            break;
                        }

                        if (operation == '+') {
                            accountToUpdate.setBalance(accountToUpdate.getBalance() + amount);
                        } else if (operation == '-') {
                            if (accountToUpdate.getBalance() >= amount) {
                                accountToUpdate.setBalance(accountToUpdate.getBalance() - amount);
                            } else {
                                System.out.println("Insufficient funds. Current balance: " + accountToUpdate.getBalance());
                            }
                        } else {
                            System.out.println("Invalid operation. Use + or -.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    Account.listAccounts(accounts);
                    break;


                case 4:
                    System.out.println("Please input id of Account to retrieve:");
                    try {
                        input = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please input a valid Account id, must be number.");
                    }
                    for (Account v : accounts) {
                        if (v.getId() == input) {
                            v.retrieveAccountInfo(v);
                        } else {
                            System.out.println("Account id not found.");
                        }
                    }
                break;

                case 5:
                    System.out.println("Please input id of Account to delete:");
                    try {
                        input = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Please input a valid Account id, must be a number.");
                        sc.next(); // Consume the invalid input
                        break;
                    }

                    Account accountToRemove = null;
                    for (Account v : accounts) {
                        if (v.getId() == input) {
                            accountToRemove = v;
                            break; // Account found, exit loop
                        }
                    }

                    if (accountToRemove != null) {
                        accounts.remove(accountToRemove);
                        System.out.println("Account deletion successful.");
                    } else {
                        System.out.println("Account id not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1, 2, 3, 4 or 5.");
            }

            System.out.println("Would you like to perform another operation? (1: Yes, 0: No)");
            try {
                finished = sc.nextInt() == 0;
            } catch (InputMismatchException e) {
                System.out.println("Please input 1 or 0.");
                sc.next();
                finished = sc.nextInt() == 0;
            }
            if (!finished) {
                System.out.println("Please input 1 for creating a new account, 2 for adding/withdrawing to/from an existing account, 3 to get a list of all accounts, 4 retrieving information of a single account, 5 for deleting an account.");
            }
        }
        sc.close();
    }


}
