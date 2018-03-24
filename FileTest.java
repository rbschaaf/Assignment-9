/**
* We ran out of time for integrating the required components for Assignment 9.
* This is a test of concept for working with files.
*/


import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.UTFDataFormatException;

public class FileTest{
  private static String fileName = "customerInfo.dat";
  private static String customerName;
  private static int customerId;

  public static void setName(){
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter the name of the customer: ");
    customerName = keyboard.nextLine();
  }

  public static void setId(){
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter the id of the customer: ");
    customerId = keyboard.nextInt();
  }

  public static void main(String[] args){
    int anId=0;
    String aName="Joe";
    try{
      ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
      setName();
      outputStream.writeUTF(customerName);
      setId();
      outputStream.writeInt(customerId);
      outputStream.close();
    }catch(FileNotFoundException e){
      System.out.println("Problem opening the file"+ fileName);
    }catch(IOException e){
      System.out.println("Problem with output to file"+ fileName);
    }

    try{
      ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
      System.out.println("Reading the String");
      aName = inputStream.readUTF();
      System.out.println(aName);
      System.out.println("Reading the id");
      anId = inputStream.readInt();
      System.out.println(anId);
      System.out.println("End of reading from file.");
      inputStream.close();
    }catch(FileNotFoundException e){
      System.out.println("Problem opening file"+fileName);
    }catch(EOFException e){
      System.out.println("Problem readin gthe file"+ fileName);
      System.out.println("Reached end of the file.");
    }catch(IOException e){
      System.out.println("Problem reading the file"+fileName);
    }

    Customer testCustomer = new Customer(aName,anId);
    System.out.println(testCustomer.toString());
    System.out.println("Would you like to open a savings account or a chequing account?");
    String accountType;
    Scanner keyboard = new Scanner(System.in);
    accountType = keyboard.nextLine();
    if(accountType.equals("chequing account")){
      BankAccount newAccount = new ChequingAccount(testCustomer,100.0,10);
      System.out.println("Chequing Account chosen");
    }else if(accountType.equals("savings account")){
      BankAccount newAccount = new SavingsAccount(testCustomer,100.0);
      System.out.println("Savings Account chosen");
    }




  }
}
