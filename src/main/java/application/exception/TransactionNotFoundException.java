package application.exception;

public class TransactionNotFoundException extends RuntimeException{

	public TransactionNotFoundException(long id) {
		System.out.println("Transaction id not found for id:"+id);
	}
}
