package application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.DTO.ItemDTO;
import application.exception.TransactionNotFoundException;
import application.model.Item;
import application.model.Transaction;
import application.repository.TransactionRepository;
import application.service.TransactionService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TransactionController {

	private final TransactionRepository repository;
	private TransactionService transactionService;

	TransactionController(TransactionRepository repository, TransactionService transactionService) {
		this.repository = repository;
		this.transactionService = transactionService;
	}

	@GetMapping("/transaction")
	List<Transaction> all() {
		return repository.findAll();
	}

	@PostMapping("/transaction")
	Transaction newTransaction(@RequestBody List<ItemDTO> items, @RequestParam(required = false) Long user_id) {
		long id = -1;
		if (user_id != null)
			id = user_id;
		return transactionService.service(items, id);
	}

	@GetMapping("/transaction/{id}")
	Transaction one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
	}

	@DeleteMapping("/transaction/{id}")
	void deleteTransaction(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PutMapping("/transaction/{id}")
	Transaction replaceTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id) {

		return repository.findById(id).map(transaction -> {
			transaction.setUser_id(newTransaction.getUser_id());
			transaction.setOrder_date(transaction.getOrder_date());
			transaction.setTotal(newTransaction.getTotal());
			return repository.save(transaction);
		}).orElseGet(() -> {
			return repository.save(newTransaction);
		});

	}
}