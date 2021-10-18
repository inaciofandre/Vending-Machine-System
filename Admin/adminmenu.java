package com.company;
import ProductArray.Items;
import java.io.IOException;
import java.util.Scanner;

public class adminmenu extends Items { // extends Items ---> It is Calling Items Class
    Scanner input;
    private float balance; // Global variable
    private float transaction;

    //Class constructor --> prompt Admin to enter items, cashIn, pin, add/change price
    public adminmenu() throws IOException, ClassNotFoundException {
        this.input = new Scanner(System.in);
        this.adminPage(); // Calling the Mentioned Function
    }

    public void adminPage() throws IOException, ClassNotFoundException {
        // THIS WHAT DISPLAY ON LANDING PAGE (brief description of the vending machine)
        System.out.println("W E L C O M E  TO  V E N D I N G  M A C H I N E  ! ! ! :)");
        System.out.println("--------------------------------------------------------------------------------------------\n");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Pin: ");
        int pin = 123;

        for(int confirm = input.nextInt(); pin != confirm; confirm = input.nextInt()) {
            System.out.println("Invalid Pin, try again!");
            System.out.print("Enter Pin: ");
        }

        this.twoStageAuthentication(); // Calling the mentioned Function
    }
    // Function OWNER for a login confirmation code(Two Stage Authentication)
    public void twoStageAuthentication() throws IOException, ClassNotFoundException {
        int confirmationCode = 1234;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Your Confirmation code: ");

        for(int confirmCode = input.nextInt(); confirmationCode != confirmCode; confirmCode = input.nextInt()) {
            System.out.println("The code entered is Incorrect,Please try again!");
            System.out.print("Enter confirmation code: ");
        }

        this.showMenu(); // Calling the Mentioned Function
    }
    // Function Cash In certain amount ---> depositing money in the Machine
    public void deposit() throws IOException, ClassNotFoundException {
        System.out.println("Enter an amount to deposit ");
        float depositedAmount = (float)this.input.nextDouble();
        if (depositedAmount < 5.0F) {
            System.out.println("enter amount greater or equal to N$ 5");
            this.repeatDeposit(); // Calling the Mentioned Function
        }

        this.balance += depositedAmount;
        this.transaction = depositedAmount;
        System.out.println("You have Successfully Deposited: N$" + this.transaction);
        System.out.printf("New Balance: N$%.2f", this.balance);
        this.repeatDeposit(); // Calling the Mentioned Function
        this.coinDisbursed(); // Calling the Mentioned Function
    }
    // This Function allow User to repeat Deposit //
    public void repeatDeposit() throws IOException, ClassNotFoundException {
        System.out.println("\nWould you make a deposit ?");
        System.out.print("1 - Yes\n2 - No");
        System.out.println();
        System.out.print("Select option: ");
        int depositOption = this.input.nextInt();
        switch(depositOption) {
            case 1:
                this.deposit(); // Calling the Mentioned Function
            case 2:
                this.showMenu(); // Calling the Mentioned Function
            default:
                System.out.println("Error: Invalid option. Please enter 1 OR 2");
        }
    }
    // Function showing the Admin Menu ---> it calls for other Functions created above
    public void showMenu() throws IOException, ClassNotFoundException {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\nADMINISTRATION PAGE:)");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("What would you like to do ?");
        Scanner input = new Scanner(System.in);
        System.out.print("1 - Restock\n2 - Change Price\n3 - Print all items\n4 - Balance\n5 - Print Restock\n6 - Cash In\n7 - Cash Out\n8- Exit");

        int option;
        do {
            System.out.println();
            System.out.print("Select option: ");
            option = input.nextInt();
            switch (option) {
                // CASE 1 ---> Allow admin to restock items
                case 1 -> {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("Restock: ");
                    System.out.println("--------------------------------------------------------------------------------");
                }
                // CASE 2 ---> Allow admin to Change price of items
                case 2 -> {
                    System.out.println("--------------------------------------------------------------------------------");
                    this.itemList();
                    System.out.println("--------------------------------------------------------------------------------");
                }
                // CASE 3 ---> Allow Admin to Print All the Items in the machine
                case 3 -> {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("Print all Items: ");
                    System.out.println("--------------------------------------------------------------------------------");
                }
                // CASE 4 ---> Allow Admin to Print amount of cash in categories (100NAD, 50NAD, 20NAD, 10NAD up to 5c)
                case 4 -> {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.printf("Total Amount Available: N$ %.2f", this.balance);
                    System.out.println("--------------------------------------------------------------------------------");
                }
                // CASE 5 ---> Allow Admin to print out ITEM that need RESTOCKING
                case 5 -> {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("Item to Restock: ");
                    System.out.println("--------------------------------------------------------------------------------");
                }
                // Allow Admin to CASH IN certain amount in the Machine
                case 6 -> this.deposit();
                // CASE 7 ---> Allow Admin to CASH OUT certain amount
                case 7 -> {
                    System.out.println("Enter an amount to withdraw  ");
                    float withdrawnAmount = (float) input.nextDouble();
                    this.transaction = withdrawnAmount;
                    while (withdrawnAmount > this.balance || this.balance == 0.0F) {
                        System.out.println("\nYou have insufficient Balance make this transaction,Please try again!");
                        System.out.println("Balance: " + this.balance);
                        System.out.println("---------------------------------------------------------------------");
                        this.repeatDeposit(); // Calling the Mentioned Function
                    }
                    this.balance -= withdrawnAmount;
                    System.out.println("You have Successfully withdraw: N$" + withdrawnAmount);
                    System.out.printf("New Balance: N$%.2f", this.balance);
                    this.coinDisbursed(); // Calling the Mentioned Function
                }
                // CASE 8 ---> let Admin know that they entered INVALID option
                case 8 -> this.adminPage();
                default -> System.out.println("Error: Invalid option. Please enter 1,2,3,4,5,6,7 and 8");
            }
        } while(option != 9);

    }
    // Function for Disbursed of : ? x N$ 100; ? x N$50; ? x N$20; ? x N$10; ? x N$5; ? x N$1; ? x 50c; ? x 10c; ? x 5c
    public void coinDisbursed() {
        float coinsHandOut = this.balance;  // coinsHandOut ---> balance to get the number of coins while keeping the amount entered
        // 100 Namibian Dollar
        int hundredDollar = (int)(coinsHandOut / 100.0F);
        float remainder = coinsHandOut % 100.0F;
        // 50 Namibian Dollar
        int fiftyDollar = (int)(remainder / 50.0F);
        remainder %= 50.0F;
        // 20 Namibian Dollar //
        int twentyDollar = (int)(remainder / 20.0F);
        remainder %= 20.0F;
        // 10 Namibian Dollar //
        int tenDollar = (int)(remainder / 10.0F);
        remainder %= 10.0F;
        // 5 Namibian Dollar //
        int fiveDollar = (int)(remainder / 5.0F);
        remainder %= 5.0F;
        // 1 Namibian Dollar //
        int oneDollar = (int)remainder;
        remainder %= 1.0F;
        // 50 cent --> 50 /100 //
        int fiftyCents = (int)((double)remainder / 0.5D);
        remainder = (float)((double)remainder % 0.5D);
        // 10 cent --> 10 /100
        int tenCents = (int)((double)remainder / 0.1D);
        remainder = (float)((double)remainder % 0.1D);
        // 5 cent --> 5 /100 //
        int fiveCents = (int)((double)remainder / 0.05D);
        System.out.println("\nDisbursed :\n" + hundredDollar + " x N$100\n" + fiftyDollar + " x N$50\n" + twentyDollar + " x N$20\n" + tenDollar + " x N$10\n" + fiveDollar + " x N$5\n" + oneDollar + " x N$1\n" + fiftyCents + " x 50c\n" + tenCents + " x 10c\n" + fiveCents + " x 5c");
    }
}
