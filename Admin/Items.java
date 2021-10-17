package ProductArray;
import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
/*
Reference : Dr. Parag Shukla
*/
public class Items implements Serializable {
     String itemName;
     float itemPrice;
     int itemID;
    //Scanner input = new Scanner(System.in); // Global variable
     // Display Item in file in sequential order
    // It will Display items below each other

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
    public void itemList() throws IOException, ClassNotFoundException {
        File file = new File("ItemList.txt");
        ArrayList<Items> productDetails = new ArrayList<>();
        ObjectOutputStream Upload_To_File;
        ObjectInputStream readFile;
       ListIterator sequenceList;

        if(file.isFile()){
            readFile = new ObjectInputStream(new FileInputStream(file));
            productDetails = (ArrayList<Items>) readFile.readObject();
            readFile.close();
        }
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        int choice;
        do{
            System.out.print("1 - Add Item\n2 - Display Item\n3 - Delete Item\n4 - Change Price\n0 - Exit");
            System.out.print("\nEnter Your Choice: ");
            choice = input1.nextInt();
            switch (choice) {
                case 1 -> {
                    if(file.isFile()){
                        readFile = new ObjectInputStream(new FileInputStream(file));
                        productDetails = (ArrayList<Items>) readFile.readObject();
                        readFile.close();
                    }
                    else {
                        System.out.println("File Does Not Exist!");
                    }

                    System.out.print("How many Items Do you want to Add: ");
                    int numberToAdd = input1.nextInt();
                    // This line will repeat the number of times the Owner wants to add to the System
                    for (int i = 0; i < numberToAdd; i++) {
                        System.out.print("Enter Item ID: ");
                        this.itemID = input1.nextInt();

                        System.out.print("Enter Item Name: ");
                        this.itemName = input2.nextLine();

                        System.out.print("Enter Item price: ");
                        this.itemPrice = input1.nextFloat();
                        productDetails.add(new Items(this.itemID, this.itemName, this.itemPrice));
                    }
                    // This line store all the Items entered in the file
                    Upload_To_File = new ObjectOutputStream(new FileOutputStream(file));
                    Upload_To_File.writeObject(productDetails);
                    Upload_To_File.close();
                    System.out.println("--------------------------------------------------------------------------------");
                }
                case 2 -> {
                    if(file.isFile()){
                        readFile = new ObjectInputStream(new FileInputStream(file));
                        productDetails = (ArrayList<Items>) readFile.readObject();
                        readFile.close();
                        System.out.println("----------------------------------------------------------------------------");
                        sequenceList = productDetails.listIterator();
                        while (sequenceList.hasNext()) { // Method of Java Scanner class which returns TRUE if this scanner has another token in its input.
                            System.out.println(sequenceList.next()); // It will Display items below each other
                        }
                        System.out.println("----------------------------------------------------------------------------");
                    }
                    else {
                        System.out.println("File Not Found!");
                    }

                }
                case 3 -> {
                    if(file.isFile()){
                        readFile = new ObjectInputStream(new FileInputStream(file));
                        productDetails = (ArrayList<Items>) readFile.readObject();
                        readFile.close();
                        boolean itemFound = false;
                        System.out.print("Enter Item to Delete: ");
                        this.itemID = input1.nextInt();
                        System.out.println("----------------------------------------------------------------------------");
                        sequenceList = productDetails.listIterator();
                        while (sequenceList.hasNext()) { // Method of Java Scanner class which returns TRUE if this scanner has another token in its input.
                            Items itemSearcher = (Items)sequenceList.next();
                            if(itemSearcher.itemID == itemID){
                                sequenceList.remove();
                                itemFound = true;
                            }
                        }
                        if (itemFound){
                            Upload_To_File = new ObjectOutputStream(new FileOutputStream(file));
                            Upload_To_File.writeObject(productDetails);
                            Upload_To_File.close();
                            System.out.println("Item Deleted Successfully");

                        }
                        else {
                            System.out.println("Item Not Found!");
                        }
                        System.out.println("----------------------------------------------------------------------------");
                    } else {
                        System.out.println("File Not Found!");
                    }
                }
            }
        }while (choice != 0);
    }
}
