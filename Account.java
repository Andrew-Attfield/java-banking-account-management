package Lab8;
import java.util.Date;
import java.util.Objects;

public abstract class Account implements Comparable {
	protected int accountNo;

	protected double balance;
	protected String fullName;
	protected Date dateOpened;
	protected double maxTransferable;
	
	public abstract void deposit(double amount); 
		
	public abstract boolean withdraw(double amount) throws Exception;
	
	public boolean transferFrom(Account to, double amount) throws Exception {
		
		this.withdraw(amount);
		
		to.deposit(amount);
		
		return true;
		
	}

	public int getAccountNo() {
		
		return this.accountNo;
		
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public Date getDateOpened() {
		return this.dateOpened;
		
	}
	
	public double getMaxTransferable() {
		return this.maxTransferable;
	}
	
	@Override
	public abstract int compareTo(Object o);
	
}
	
	
class Current extends Account {
	
	public Current(int accountNo, double balance, String fullName, Date dateOpened, double maxTransferable) {
		this.accountNo = accountNo;
		this.balance = balance;
		this.fullName = fullName;
		this.dateOpened = dateOpened;
		this.maxTransferable = maxTransferable;
		
	}
	
	public void deposit(double amount) {
		
		this.balance += amount;
		
		
	}
	
	public boolean withdraw(double amount) throws Exception {
		
		if (amount > this.balance) {
			
			throw new NotEnoughMoneyException("Amount withdrawn is greater than avaliable balance.");
			
		}
		
		else if (amount > this.maxTransferable) {
			
			throw new TransferNotAllowedException("Amount withdrawn is greater than transferable limit.");
			
		}
		
		this.balance = this.balance - amount;
		
		return true;
		
	}
	
	public int compareTo(Object o) {
		
		Account other = (Account) o;
		
		if (this.equals(o)) {
			
			return 0;
		}
		
		else if (this.accountNo < other.accountNo) {
			
			return -1;
		}
		
		else {
			
			return 1;
			
		}
		
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			
			return true;
			
		}
		
		else if (obj == null || getClass() != obj.getClass()) {
			
			return false;
			
		}
		
		else {
			
			Current other = (Current) obj;
		
			return this.accountNo == other.accountNo && 
					Double.compare(this.balance,  other.balance) == 0 &&
					Objects.equals(this.fullName, other.fullName) &&
					Objects.equals(this.dateOpened, other.dateOpened) &&
					Double.compare(this.maxTransferable, other.maxTransferable) == 0;
			
		}
		
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(accountNo, balance, fullName, dateOpened, maxTransferable);
		
		
	}
	
	
}

class TransferNotAllowedException extends Exception {
	public TransferNotAllowedException() {}
	public TransferNotAllowedException(String message) {
		
		super(message);
		
	}
	
	
}

class NotEnoughMoneyException extends TransferNotAllowedException {
	public NotEnoughMoneyException() {}
	public NotEnoughMoneyException(String message) {
		super(message);
		
	}
}


