package Assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class computerStore {
   
    private static Scanner kb;
        
    private static boolean login() {
    	 final  String Password = "password";
         int tries = 3;
         while (tries > 0) {
            tries--;
            System.out.print("Enter your password: ");
            String enteredPassword = kb.nextLine();
            if (enteredPassword.equals(Password)) {
                return true;
            } else {
          
                System.out.println("Incorrect password. " + tries + " attempts remaining.");
            }
        }
        System.out.println("login failed");
        return false;
    }
    
    
     private static int slotAvailble(Computer[] inventory) {
		int slotavailble = 0;
		for(Computer c : inventory) {
			if(c==null) {
				slotavailble++;
			}
		}
		return slotavailble;	
	}
     private static void addNewComputers(Computer[] inventory) {
        int addcomputers = slotAvailble(inventory);
        System.out.print("Enter the number of computers to add (up to " + addcomputers + "): ");
        int numOfComputer = kb.nextInt();
        kb.nextLine();

        if (numOfComputer > addcomputers || numOfComputer <= 0) {
            System.out.println("Invalid input. Please enter a valid number of computers to add.");
            return;
        }

        for (int i = 0; i < numOfComputer; i++) {
            
            System.out.println("Enter the details of computer #" + (i + 1) + ":");
            System.out.print("Brand: ");
            String brand = kb.nextLine();
            System.out.print("Model: ");
            String model = kb.nextLine();
            System.out.print("Price: ");
            double price = kb.nextDouble();    
        }
        System.out.println("Successfully added");
    }
    private static void updateInfo(Computer[] inventory) {
    	System.out.println("show the inventory list");
    	
    	 for (int i = 0; i < inventory.length; i++) {
    	        Computer computer = inventory[i];
    	        if (computer != null) {
    	            System.out.println("Computer #" + (i + 1) + ":");
    	            System.out.println("Brand: " + computer.getBrand());
    	            System.out.println("Model: " + computer.getModel());
    	            System.out.println("Serial Number: " + computer.getSn());
    	            System.out.println("Price: $" + computer.getPrice());
    	            System.out.println();
    	            computer.showInfo(inventory[i]);
    	        }
    	    }
    	 System.out.println("please enter the number of computers you want to Update");
    	 int updatenum = kb.nextInt();
    	 kb.nextLine();

    	    if (updatenum < 1 || updatenum > inventory.length) {
    	        System.out.println("Invalid computer number. Please enter a valid number.");
    	        return;
    	    }
    	 
    	 System.out.println("Enter updated information for Computer #" + updatenum + ":");
    	    System.out.print("Brand: ");
    	    String brand = kb.nextLine();
    	    System.out.print("Model: ");
    	    String model = kb.nextLine();
    	    System.out.print("Price: ");
    	    double price = kb.nextDouble();
    	    
    	    Computer computerToUpdate = inventory[updatenum - 1];
    	    if(computerToUpdate==null) {
    	    	System.out.println("select number of computer is not exist");
    	    	return;
    	    }
    	    computerToUpdate.setBrand(brand);
    	    computerToUpdate.setModel(model);
    	    computerToUpdate.setPrice(price);

    	    System.out.println("Computer information updated successfully!");
    }
    private static void findComputerbyBrand(Computer[] inventory,String brand) {
    	 boolean found = false;
    	    for (Computer computer : inventory) {
    	        if (computer != null && computer.getBrand().equalsIgnoreCase(brand)) {
    	            computer.showInfo(computer);
    	            found = true;
    	        }
    	    }

    	    if (!found) {
    	        System.out.println("No computers with the brand '" + brand + "' found.");
    	    }
    }
    private static void findcomputerbyPrice(Computer[] inventory,double price ) {
    	boolean found = false;
    	for (Computer computer : inventory) {
    		if(computer !=null && computer.getPrice()<= price) {
    			computer.showInfo(computer);
    			found = true; 			
    		}
    	}
    	if (!found) {
	        System.out.println("No computers with the under  '" + price + "'$ found.");
    	}
    }
    
    private static void saveDataToFile(Computer[] inventory, String fileName) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fileName));

            for (Computer computer : inventory) {
                if (computer != null) {
                    pw.println("Brand: " + computer.getBrand());
                    pw.println("Model: " + computer.getModel());
                    pw.println("Serial Number: " + computer.getSn());
                    pw.println("Price: $" + computer.getPrice());
                    pw.println();
                }
            }

            pw.close();
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving data to " + fileName);
            
        }
    }

    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        kb = new Scanner(System.in);
        System.out.println("Welcome to Computer Store");
        
        int maxcomputer = 0;
        
        while(maxcomputer<1) {
        try {
             System.out.println("please enter maximum number of computers you want in your store :");
             maxcomputer = kb.nextInt();
             if(maxcomputer<1) {
            	 System.out.println("invalid input it should be positive number");	 
             }
       
        
        Computer[] inventory = new Computer[maxcomputer];
        
        do {
          	System.out.println("what do you want to do ?\r\n"+
          		    "1.enter new computers(password required)\r\n"+
          		    "2.change Info of a computer(password required)\r\n"+
          		    "3.display all computers by specific brand\r\n"+
          		    "4.dsplay all omputers under certain price\r\n"+
          		    "5.quit");  
          	System.out.println("enter your choice");
          	int choice = kb.nextInt();
          	switch (choice) {
			case 1://user press one
				if(login()) {
					addNewComputers(inventory);				
				}break;	
				
			case 2://user press two
				if(login()) {
			       updateInfo(inventory);		
				}
				
				break;
			case 3://user press three
				System.out.print("Enter the brand to search for: ");
			    kb.nextLine();
			    String findbr = kb.nextLine();
				findComputerbyBrand(inventory,findbr);
				break;
			case 4://user press four
				System.out.println("Enter price ");
				kb.nextLine();
				double findpr = kb.nextDouble();
				findcomputerbyPrice(inventory,findpr);
				break;
				
			case 5://user press five
			
			    saveDataToFile(inventory, "storeinventory.txt");
			    System.out.println("Exiting the program.");
			    System.exit(0);
			    break;
			    
			default:
          		System.out.println("please enter valid number");
          		break;
			}         
	}while(true);
        }catch(InputMismatchException e) {
        	System.out.println("please enter valid input");
        }
        break;
}
}
}
