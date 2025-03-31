package application.exception;

public class ItemExceedingStockException extends RuntimeException{

	public ItemExceedingStockException(String message) {
		super(message);
	}
}
