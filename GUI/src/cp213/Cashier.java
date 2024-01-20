package cp213;

import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Iman Chaudhry
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-07-05
 */
public class Cashier {

    // Attributes
    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }
    
    /**
     * Checks if string is Integer value.
     * @return true if string is Int
     */
    private boolean isInt(String str) {
	boolean bool = false;
	try {
	    int num = Integer.parseInt(str);
	    bool = true;
	  } catch (NumberFormatException e) {
	}
	return bool;
    }
    
    /**
     * Print Not a valid number for any bad command or quantity input
     */
    private void notValid() {
	System.out.println("Not a valid number");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!");

	this.printCommands();
	Scanner input = new Scanner(System.in);
	String inputValue = "";
	Order order = new Order();
	
	while (!inputValue.equals("0")) {
	    //cmd
	    System.out.print("Command: ");
	    inputValue = input.nextLine();
	    
	    if (this.isInt(inputValue)) {
		int i = Integer.valueOf(inputValue);
		
		if ( i > 0  && i <= this.menu.size()) {
		    //valid
		    System.out.print("How many do you want? ");
		    String strAmnt = input.nextLine();
		    
		    if (this.isInt(strAmnt)) {
			int amnt = Integer.valueOf(strAmnt);
			
			if (amnt > 0) {
			    order.add(menu.getItem(i-1), amnt);
			} else {
			    notValid();  
			} 
		    } else {
			notValid();
		    }
		} else if (i == 0) {
		    input.close();
		    break;
		} else {
		    printCommands();
		} 
	    } else {
		notValid();
		printCommands();
	    }
	    
	}
	
	System.out.println("----------------------------------------\nReceipt");
	System.out.println(order);

	return order;
    }
}