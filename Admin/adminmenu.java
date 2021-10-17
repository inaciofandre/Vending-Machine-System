package com.company;
import ProductArray.Items;

import java.util.Scanner; // Allow user input

public class adminmenu extends Items { // extends Items ---> It is Calling Items Class

    //Class Variables
    Scanner input= new Scanner(System.in); // Global variable

    // private String firstName;
    // private String lastName;
    // private String cashOut;
    // private String deposit;
    private float balance,transaction;

    // private String itemName;
    // private float itemPrice;


    //Class constructor --> prompt Admin to enter items, cashIn, pin, add/change price
    public adminmenu() {

        adminPage(); // Calling the Mentioned Function

    }

    public void adminPage() {
        System.out.println("\n=====================================================================================");
        System.out.println("W E L C O M E  TO  V E N D I N G  M A C H I N E  ! ! ! :)");  // THIS WHAT DISPLAY ON LANDING PAGE (brief description of the vending machine)
        System.out.println("=====================================================================================\n");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Pin: ");
        int pin = 123;
        int confirm;
        confirm = input.nextInt();
        while (pin != confirm) {
            System.out.println("Invalid Pin, try again!");
            System.out.print("Enter Pin: ");
            confirm = input.nextInt();
        }
        twoStageAuthentication(); // Calling the mentioned Function
    }

    // Function OWNER for a login confirmation code(Two Stage Authentication)
    public void twoStageAuthentication() {
        int confirmationCode = 1234;
        Scanner input = new Scanner(System.in);
        int confirmCode;
        System.out.print("Enter Your Confirmation code: ");
        confirmCode = input.nextInt();
        while (confirmationCode != confirmCode) {
            System.out.println("The code entered is Incorrect,Please try again!");
            System.out.print("Enter confirmation code: ");
            confirmCode = input.nextInt();
        }
        showMenu(); // Calling the Mentioned Function
    }

    // Function Cash In certain amount ---> depositing money in the Machine
    public void deposit() {
        System.out.println("Enter an amount to deposit ");
        float depositedAmount = (float) input.nextDouble();

        if (depositedAmount < 5) {
            System.out.println("enter amount greater or equal to N$ 5");
            repeatDeposit(); // Calling the Mentioned Function
        }
        balance = (balance + depositedAmount);
        transaction = depositedAmount;
        System.out.println("You have Successfully Deposited: N$" + transaction);
        System.out.printf("New Balance: N$%.2f", +balance);
        repeatDeposit(); // Calling the Mentioned Function
        coinDisbursed(); // Calling the Mentioned Function
    }
    // This Function allow User to repeat Deposit //
    public void repeatDeposit(){
        System.out.println("\nWould you make a deposit ?");
        System.out.print("1 - Yes\n2 - No");
        int depositOption;
        System.out.println();
        System.out.print("Select option: ");
        depositOption = input.nextInt();
        switch (depositOption){
            case 1: {
                deposit(); // Calling the Mentioned Function
            }
            case 2:{
                showMenu(); // Calling the Mentioned Function
            } default : System.out.println("Error: Invalid option. Please enter 1 OR 2");
        }
    }
    // Function for Print summary of items --->
    // Function for Print item needed for restock --->
    // Function showing the Admin Menu ---> it calls for other Functions created above
    public void showMenu() {
        System.out.println("\n=====================================================================================");
        System.out.println("W E L C O M E   B A C K  ! ! ! :)");
        System.out.println("=====================================================================================\n");
        System.out.println("What would you like to do ?\n");
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
                    System.out.println("=============================================================================");
                    System.out.println("Restock: ");
                    System.out.println("=============================================================================");
                }
                // CASE 2 ---> Allow admin to Change price of items
                case 2 -> {
                    System.out.println("=============================================================================");
                    //System.out.println("Change Price: ");
                    this.itemList();

                    System.out.println("=============================================================================");
                }
                // CASE 3 ---> Allow Admin to Print All the Items in the machine
                case 3 -> {
                    System.out.println("=============================================================================");
                    System.out.println("Print all Items: ");
                    System.out.println("=============================================================================");
                }
                // CASE 4 ---> Allow Admin to Print amount of cash in categories (100NAD, 50NAD, 20NAD, 10NAD up to 5c)
                case 4 -> {
                    System.out.println("=============================================================================");
                    System.out.printf("Total Amount Available: N$ %.2f", +balance);
                    System.out.println("=============================================================================");
                }
                // CASE 5 ---> Allow Admin to print out ITEM that need RESTOCKING
                case 5 -> {
                    System.out.println("=============================================================================");
                    System.out.println("Item to Restock: ");
                    System.out.println("=============================================================================");
                }
                // Allow Admin to CASH IN certain amount in the Machine
                case 6 ->
                    deposit(); // Calling the Mentioned Function

                // CASE 7 ---> Allow Admin to CASH OUT certain amount
                case 7 -> {
                    System.out.println("Enter an amount to withdraw  ");
                    float withdrawnAmount = (float) input.nextDouble();
                    transaction = withdrawnAmount;
                    while (withdrawnAmount > balance || balance == 0) {
                        System.out.println("\nYou have insufficient Balance make this transaction,Please try again!");
                        System.out.println("Balance: "+ balance);
                        System.out.println("---------------------------------------------------------------------");
                        repeatDeposit(); // Calling the Mentioned Function
                    }
                    balance = (balance - withdrawnAmount);
                    System.out.println("You have Successfully withdraw: N$" + withdrawnAmount);
                    System.out.printf("New Balance: N$%.2f", +balance);
                    coinDisbursed(); // Calling the Mentioned Function
                }
                // CASE 8 ---> let Admin know that they entered INVALID option
                case 8 -> adminPage();
                default -> System.out.println("Error: Invalid option. Please enter 1,2,3,4,5,6,7 and 8");
            }
        } while (option != 9);
    }
    // Function for Disbursed of : ? x N$ 100; ? x N$50; ? x N$20; ? x N$10; ? x N$5; ? x N$1; ? x 50c; ? x 10c; ? x 5c //
    public void coinDisbursed() {
        int hundredDollar, fiftyDollar, twentyDollar, tenDollar, fiveDollar, oneDollar, fiftyCents, tenCents, fiveCents;
        float remainder;
        float coinsHandOut = balance; // coinsHandOut ---> balance to get the number of coins while keeping the amount entered

        // 100 Namibian Dollar
        hundredDollar = (int) (coinsHandOut / 100);
        remainder = coinsHandOut % 100;
        coinsHandOut = remainder;

        // 50 Namibian Dollar
        fiftyDollar = (int) (coinsHandOut / 50);
        remainder = remainder % 50;
        coinsHandOut = remainder;

        // 20 Namibian Dollar //
        twentyDollar = (int) (coinsHandOut / 20);
        remainder = remainder % 20;
        coinsHandOut = remainder;

        // 10 Namibian Dollar //
        tenDollar = (int) (coinsHandOut / 10);
        remainder = remainder % 10;
        coinsHandOut = remainder;

        // 5 Namibian Dollar //
        fiveDollar = (int) (coinsHandOut / 5);
        remainder = remainder % 5;
        coinsHandOut = remainder;

        // 1 Namibian Dollar //
        oneDollar = (int) (coinsHandOut);
        remainder = remainder % 1;
        coinsHandOut = remainder;

        // 50 cent --> 50 /100 //
        fiftyCents = (int) (coinsHandOut / 0.5);
        remainder = (float) (remainder % 0.5);
        coinsHandOut = remainder;

        // 10 cent --> 10 /100
        tenCents = (int) (coinsHandOut / 0.1);
        remainder = (float) (remainder % 0.1);
        coinsHandOut = remainder;

        // 5 cent --> 5 /100 //
        fiveCents = (int) (coinsHandOut / 0.05);
        //remainder = (float) (remainder % 0.05);
        System.out.println("\nDisbursed :\n" + hundredDollar + " x N$100\n" + fiftyDollar + " x N$50\n" + twentyDollar
                + " x N$20\n" + tenDollar + " x N$10\n" + fiveDollar + " x N$5\n" + oneDollar + " x N$1\n"
                + fiftyCents + " x 50c\n" + tenCents + " x 10c\n" + fiveCents + " x 5c");
    }
}

