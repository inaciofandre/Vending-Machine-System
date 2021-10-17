package ProductArray;
import java.util.ArrayList;
import java.util.Scanner;
/*
Reference : Dr. Parag Shukla
*/
public class Items  {
     String itemName;
     float itemPrice;
     int itemID;

    // Class constructor --> prompt Admin to enter items,add/change price
    public Items(int itemID, String itemName, float itemPrice){
        // Declare Instance Variable
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;

    }

    public Items() {

    }

    // String Method to Display
    public String toString(){
        return itemID+" "+itemName+" "+itemPrice;
    }
    public void itemList(){
        ArrayList<Items> productDetails = new ArrayList<Items>();
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        int choice = -1;
        do{
            System.out.print("1 - Add Item\n2 - Display Item\n3 - Delete Item\n4 - Change Price\n0 - Exit");
            System.out.print("\nEnter Your Choice: ");
            choice = input1.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("How many Items Do you want to Add: ");
                    int numberToAdd = input1.nextInt();
                    // This line will repeat the number of times the Owner wants to add to the System
                    for (int i = 0; i < numberToAdd; i++) {
                        System.out.println("Enter Item ID: ");
                        this.itemID = input1.nextInt();

                        System.out.println("Enter Item Name: ");
                        this.itemName = input2.nextLine();

                        System.out.println("Enter Item price: ");
                        this.itemPrice = input1.nextFloat();
                        productDetails.add(new Items(this.itemID, this.itemName, this.itemPrice));
                    }

                    break;
                }
                case 2 -> {
                    System.out.println(productDetails);

                    break;
                }
            }

        }while (choice != 0);
    }
}