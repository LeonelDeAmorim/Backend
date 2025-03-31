package application.exception;

public class ReviewNotFoundException extends RuntimeException {
	public ReviewNotFoundException(long id) {
		System.out.println("Review not found for id:"+id);
	}
}
