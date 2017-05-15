

 public class VendingMachineDemo
    {
        public static void main(String[] args)
        {
            VendingMachine machine1 = new VendingMachine(100);
            VendingMachine machine2 = new VendingMachine(200,"Library");
            System.out.println("===== Welcome to CSUMB Vending Machine =====");
            System.out.println(machine1);
            System.out.println("");
            System.out.println(machine2);
            System.out.println("\n=== Compare Two Machines ===");
            System.out.println(machine1.equals(machine2));
            machine1.setLocation("BIT104");
            machine1.setName(50);
            machine1.setName(100);
            System.out.println("\n=== Machine Reset ===");
            machine1.reset(4, 5, 0, 6);  // In this method, we assume that a machine
                                          // operator resets the contents of
                                          // a machine.
            machine1.addItems(1,2,3,4); // An operator can add items to the machine
            System.out.println(machine1);
            System.out.println("");
            machine1.displayMenu();
            System.out.println("\n=== Buy an item ===");
            machine1.buyItem();
            System.out.println("\n=== Buy another item ===");
            if(machine1.buyItem(1,4) == false) {
                System.exit(1);
            }
            System.out.println("\n=== Return two items ===\n");
            machine1.returned(1, 2);//water x 2
            machine1.returned(2, 3);//coffee x 3
            System.out.println("\n=== Buy one more item ===");
            machine1.buyItem(3, 5);//chips x 5
		    System.out.println("\n=== Pay for items ===");
		    if(machine1.payment()) {
		        System.out.println("\n=== Valid payment. ===");
		        machine1.displayReceipt();
		    }
		    else {
		        System.out.println("\n=== Invalid payment. Try one more time. ===");
		        if(machine1.payment()) {
		            System.out.println("\n=== Print Receipt ===");
		            machine1.displayReceipt();
				}
				else {
		            System.exit(1);
		        }
		    }
		    machine1.addItems(5,5,5,5);
		    System.out.println("\n=== Machine Status ===");
		    machine1.status();
		    System.out.println("\n===== Thank you! =====");
        }
}

