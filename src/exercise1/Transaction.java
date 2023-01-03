package exercise1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction extends Thread implements Runnable{
	
	private Person person;
	
	public Transaction(Person p) {
		
		this.person = p;
	}
	
	public static void main(String[] args) {
		
		Transaction ts1 = new Transaction(new Person("Moe"));
		ts1.start();
		Transaction ts2 = new Transaction(new Person("Zack"));
		ts2.start();
		Transaction ts3 = new Transaction(new Person("Kate"));
		ts3.start();
    }
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			
			try {
				
				Account myAcc = Account.getAccount(person);
				
				myAcc.withdraw(100);
				
				try {
					
					Thread.sleep(200);
					
				} catch (InterruptedException ex) {
					
					Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				if (myAcc.getBal() < 0) {
					
					System.out.println("Acoount is over the withdraw limit!");
				}
				
				myAcc.deposit(200);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		System.out.println("Final Account balance of " + person.getName() + " is $" + Account.getBal());
	}
} 
