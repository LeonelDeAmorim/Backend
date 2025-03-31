package application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import application.exception.OrderNotFoundException;
import application.model.Order;
import application.repository.OrderRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class OrderController {

	private final OrderRepository repository;

	OrderController(OrderRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/order")
	List<Order> all() {
		return repository.findAll();
	}

	@PostMapping("/order")
	Order newOrder(@RequestBody Order newOrder) {
		return repository.save(newOrder);
	}

	@GetMapping("/order/{id}")
	Order one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
	}

	@DeleteMapping("/order/{id}")
	void deleteOrder(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PutMapping("/order/{id}")
	Order replaceOrder(@RequestBody Order newOrder, @PathVariable Long id) {

		return repository.findById(id).map(order -> {
			order.setItem_id(newOrder.getItem_id());
			order.setPrice(newOrder.getPrice());
			order.setQuantity(newOrder.getQuantity());
			order.setSubtotal(newOrder.getQuantity());
			order.setTransaction_id(newOrder.getTransaction_id());
			return repository.save(order);
		}).orElseGet(() -> {
			return repository.save(newOrder);
		});

	}
}
