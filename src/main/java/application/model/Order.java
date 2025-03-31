package application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "orders") 
@Entity
public class Order {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private long transaction_id;
	private long item_id;
	private double price;
	private double subtotal;
	private int quantity;
	public Long getId() {
		return id;
	}
	
	public Order(long transaction_id,long item_id,double price, double subtotal, int quantity) {
		this.transaction_id=transaction_id;
		this.item_id=item_id;
		this.price=price;
		this.subtotal=subtotal;
		this.quantity=quantity;
	}
	
	public Order() {}
	public void setId(Long id) {
		this.id = id;
	}
	public long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
