package application.exception;

public class ItemNotFoundException extends RuntimeException{
public ItemNotFoundException(long id){
	System.out.println("Missing item"+id);
}
}
