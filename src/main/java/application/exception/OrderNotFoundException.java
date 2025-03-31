package application.exception;

public class OrderNotFoundException extends RuntimeException{

	public OrderNotFoundException(Long id) {
		System.out.println("Order could not be found for orderId "+id);
	}
}
