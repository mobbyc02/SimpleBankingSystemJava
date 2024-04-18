import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void viewAccountInfo() {
        JOptionPane.showMessageDialog(null,
                "Account Number: " + accountNumber + "\n" +
                        "Account Holder: " + accountHolderName + "\n" +
                        "Balance: $" + balance);
    }

    public void deposit(double amount) {
        assert amount > 0 : "Deposit amount must be positive.";

        balance += amount;

        assert balance >= 0 : "Balance cannot be negative after deposit.";

        JOptionPane.showMessageDialog(null, "$" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        assert amount > 0 : "Withdrawal amount must be positive.";
        assert balance >= amount : "Insufficient funds for withdrawal.";

        balance -= amount;

        assert balance >= 0 : "Balance cannot be negative after withdrawal.";

        JOptionPane.showMessageDialog(null, "$" + amount + " withdrawn successfully.");
    }

    public void transfer(BankAccount recipient, double amount) {
        assert amount > 0 : "Transfer amount must be positive.";
        assert balance >= amount : "Insufficient funds for transfer.";

        balance -= amount;
        recipient.deposit(amount);

        assert balance >= 0 : "Balance cannot be negative after transfer.";

        JOptionPane.showMessageDialog(null, "$" + amount + " transferred to " + recipient.accountHolderName + " successfully.");
    }

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(123456, "Mobby Cheng", 1000);
        BankAccount account2 = new BankAccount(789012, "Paul John Cabance", 2000);

        JFrame frame = new JFrame("Banking System");
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton viewInfoBtn = new JButton("View Account Info");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");

        viewInfoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                account1.viewAccountInfo();
            }
        });

        depositBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter deposit amount:");
                double amount = Double.parseDouble(input);
                account1.deposit(amount);
            }
        });

        withdrawBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter withdrawal amount:");
                double amount = Double.parseDouble(input);
                account1.withdraw(amount);
            }
        });

        transferBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter transfer amount:");
                double amount = Double.parseDouble(input);
                account1.transfer(account2, amount);
            }
        });

        frame.add(viewInfoBtn);
        frame.add(depositBtn);
        frame.add(withdrawBtn);
        frame.add(transferBtn);

        frame.setVisible(true);
    }
}
