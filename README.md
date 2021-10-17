# Vending-Machine-System
package com.company;

import java.util.Scanner;

class insertMoney {
    private int itemQuantity;
     private double itemCode, totalBalance = 0, moneyInserted = 0;

   public void insertMoney() {
       Scanner inside = new Scanner(System.in);
       System.out.println("Insert money");                  // accept money
       double insertMoney = inside.nextDouble();

      double  change = moneyInserted - totalBalance;

        System.out.println("Your change is :N$ " + (String.format("%.2f", change)));

        double newChange = change;                             //calculate change
        double twentyDollar = newChange / 20;
        newChange = newChange % 20;
        int twentyDollars = (int) twentyDollar;
        double tenDollar = newChange / 10;
        newChange = newChange % 10;
        int tenD = (int) tenDollar;

        double fiveDollar = newChange / 5;
        newChange = newChange % 5;
        int fiveDollars = (int) fiveDollar;
        double oneDollar = newChange;
        newChange = newChange % 1;
        int oneD = (int) oneDollar;
        double fiftyCent = newChange / 0.5;
        newChange = newChange % 0.5;
        int fiftyC = (int) fiftyCent;
        double tenC = newChange / 0.1;
        newChange = newChange % 0.1;
        int tenCent = (int) tenC;
        double fiveCent = newChange / 0.05;
        newChange = newChange % 0.05;
        int fiveC = (int) Math.round(fiveCent);
        System.out.println("Disbursed as follows: " + twentyDollars + " x N$20; " + tenD + " x N$10; " + fiveDollars +
                " x N$5; " + oneD + " x N$1; " + fiftyC + " x 50c; " + tenCent + " x 10c; " + fiveC + " x 5c ");

    }

}



