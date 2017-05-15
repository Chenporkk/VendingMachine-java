/**
 * Title: VendingMachine.java
 * Abstract: A program to simulate a vending machine. Users are able to pick
 *           from a small menu of items, add items, pay, check on contents and status of machine.
 * Author: Daniel Wilson
 * ID: 3898
 * Date: 9/3/16
 *
 */
import java.util.Scanner;

public class VendingMachine {
	private int serialNum, index, quantity, tempIndex, tempQuantity;
	private double earnings, cost= 0.0, tax = 0.10, taxCost, total, money = 0.0;
	private String location = "";
	private String[] productNames = {"Water","Coffee","Sun Chip","Chocolate Bar"};
	private double[] productCosts = {1.50,2.00,1.00,2.50};
	private int[] productQuantity = {0,0,0,0};//Indexes: Water=0, Coffee=1, Sun Chips=2, ChocolateBar=3
	private int[] currentSale = {0,0,0,0};
	private int[] productSales = {0,0,0,0};
	
	public VendingMachine(int aSNum){
		setName(aSNum);
		setLocation("UNKNOWN");
	}
	
	public VendingMachine(int aSNum, String aLocation){
		setName(aSNum);
		setLocation(aLocation);
	}
	
	public void setName(int aSNum){
		serialNum = aSNum;
	}
	
	public void setLocation(String aLocation){
		location = aLocation;
	}
	
	public void addItems(int water, int coffee, int chips, int chocolate){
		productQuantity[0] += water;
		productQuantity[1] += coffee;
		productQuantity[2] += chips;
		productQuantity[3] += chocolate;
	}
	
	public void returned(int itemIndex, int itemQuantity){
		tempIndex = itemIndex - 1;
		tempQuantity = itemQuantity;
		if(tempQuantity > currentSale[tempIndex]){
			System.out.println("Return Failed. We have not sold enough " + productNames[tempIndex] + ".");
		}
		else{
			productQuantity[tempIndex] = (productQuantity[tempIndex] + tempQuantity);
			currentSale[tempIndex] = (currentSale[tempIndex] - tempQuantity);
			
		}
	}
	
	public boolean buyItem(int itemIndex, int itemQuantity){
		tempIndex = (itemIndex - 1);
		tempQuantity = itemQuantity;
		System.out.println("You selected " + productNames[tempIndex] + ". Quantity: " + tempQuantity);
		if(tempQuantity > productQuantity[tempIndex]){
			System.out.println("Selection Failed. We don't have enough " + productNames[tempIndex] + ".");
			return false;
		}
		else{
			index = tempIndex;
			quantity = tempQuantity;
			productQuantity[tempIndex] = (productQuantity[index] - quantity);
			currentSale[index] = (currentSale[index] + quantity);
			cost = productCosts[index] * quantity;
			taxCost = cost * tax;
			total = cost + taxCost;
			return true;
		}
	}
	
	public void buyItem(){
		boolean valid_index = false, valid_quantity = false;
		Scanner choices = new Scanner(System.in);
		
		try{
			while(valid_index == false){
				System.out.print("Select an item number: ");
				tempIndex = choices.nextInt() - 1;
				if (tempIndex < 0){
					System.out.println("Must be greater than 0.");
				}
				else{
					valid_index = true;
					choices.nextLine();
				}
			}
			while(valid_quantity == false){
				System.out.print("How many do you want to buy? ");
				tempQuantity = choices.nextInt();
				if (tempQuantity < 0){
					System.out.println("Must be greater than 0.");
				}
				else{
					valid_quantity = true;
					choices.nextLine();
				}
			}
		}catch(Exception e){
			System.out.println("Invalid Input!\nMust be an integer value.");
		}
		System.out.println("You selected " + productNames[tempIndex] + ". Quantity: " + tempQuantity);
		if(tempQuantity > productQuantity[tempIndex]){
			System.out.println("Selection Failed. We don't have enough " + productNames[tempIndex] + ".");
		}
		else{
			index = tempIndex;
			quantity = tempQuantity;
			productQuantity[index] -= quantity;
			currentSale[index] += quantity;
			cost = (productCosts[index] * quantity);
			taxCost = cost * tax;
			total = cost + taxCost;
		} 
	}
	
	public boolean payment(){
		System.out.print("Enter money amount: $");
		Scanner deposit = new Scanner(System.in);
		try{
			money = deposit.nextDouble();
		}catch(Exception e){
			System.out.println("Invalid Input\nMust be in the form of a double.");
		}
		if(money < getTotal()){
			System.out.printf("%nInsufficient money. $%,.2f returned.%n", money);
			return false;
		}
		else{
			money -= getTotal();
			earnings += getTotal();
			System.out.printf("\nSufficient money. $%,.2f returned.%n", money);
		}
		return true;
	}
	public double getCost(){
		cost = 0;
		for(int i = 0; i < 4; i++){
			cost += (currentSale[i] * productCosts[i]);
		}
		return cost;
	}
	public double getTotal(){
		taxCost = getCost() * tax;
		total = cost + taxCost;
		return total;
	}
	//Tester functions
	public double printCost(){
		return cost;
	}
	public void getItemCost(){
		System.out.printf("productCosts[index] = %,.2f  index = %d cost = %,.2f%n",productCosts[index], index, cost);
	}
	
	public void displayReceipt(){
		quantity = currentSale[index];
		cost = (productCosts[index] * quantity);
		
		System.out.printf("%s: $%,.2f X %d = $%,.2f%n", productNames[index], productCosts[index], quantity, cost);
		System.out.printf("Tax %s: $%,.2f%n", "(10.0%)", taxCost);
		System.out.printf("Total: $%,.2f%n", getTotal());
		for(int i = 0; i < 4; i++){
		productSales[i] += currentSale[i];
		currentSale[i] = 0;
		}
		cost = 0;
		quantity = 0;
		taxCost = 0;
	}
	
	public void reset(int water, int coffee, int chips, int chocolate){
		productQuantity[0] = water;
		productQuantity[1] = coffee;
		productQuantity[2] = chips;
		productQuantity[3] = chocolate;
	}
	
	public void displayMenu(){
		System.out.println("===== Vending Machine Menu =====");
		System.out.println("  1.  Water............$1.50");
		System.out.println("  2.  Regular Coffee...$2.00");
		System.out.println("  3.  Sun Chips........$1.00");
		System.out.println("  4.  Chocolate Bar....$2.50");
	}
	
	public void contents(){
		System.out.println("Contents:");
		for(int i = 0; i < 4; i++){
			System.out.println("  " + productNames[i] + ": " + productQuantity[i]);
		}
	}
	
	public void status(){
		System.out.printf("Serial Number: %d%nLocation: %s%n", serialNum, location);
		System.out.println("Sold Items:");
		for(int i = 0; i < 4; i++){
			System.out.println("  " + productNames[i] + ": " + productSales[i]);
		}
		System.out.println("Current Contents:");
		for(int i = 0; i < 4; i++){
			System.out.println("  " + productNames[i] + ": " + productQuantity[i]);
		}
		System.out.printf("Total Earnings: $%,.2f%n",  earnings);
	}
	
	public boolean equals(VendingMachine otherMachine){
		if(otherMachine == null){
    		return false;
    	}
    	else
    	{
    		return((location.equals(otherMachine.location)) && 
    				(serialNum == otherMachine.serialNum));
    	}
	}
	
	public String toString(){
		System.out.printf("Serial Number: %d%nLocation: %s%n", serialNum, location);
		contents();
		return("\n ");
	}

}
