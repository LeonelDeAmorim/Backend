package application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.DTO.ItemDTO;
import application.exception.ItemExceedingStockException;
import application.exception.ItemNotFoundException;
import application.model.Item;
import application.model.Order;
import application.model.Transaction;
import application.repository.ItemRepository;
import application.repository.OrderRepository;
import application.repository.TransactionRepository;

@Service
public class TransactionService {

	/*
	 * 
	 * TODO: ADD USERID AUTHENTICATION
	 * 
	 */
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private TransactionRepository transitionRepository;

	@Transactional(rollbackFor = Exception.class)
	public Transaction service(List<ItemDTO> itemDTO, long user_id) {

		// convert DTO to list of items
		List<Item> items = dtoToItem(itemDTO);

		// create a transaction entry in the table
		Transaction transaction = new Transaction();
		if (user_id > 0)
			transaction.setUser_id(user_id);
//		System.out.println("user id"+user_id);
		transaction.setOrder_date(LocalDateTime.now());
		transaction.setTotal(getTotal(items));
//		System.out.println("\n\n "+" transaction to be posted"+transaction.getTotal()+transaction.getOrder_date()+"\n\n");
		//here locked out
		 	transitionRepository.flush();	
		    transitionRepository.save(transaction);
		    transitionRepository.flush();
//		    System.out.println("\n\n transaction posted \n\n");


		long transaction_id = transaction.getId();
		
//		System.out.println("\n\n "+" transaction completed"+"\n\n");
		for (Item item : items) {
			// create transaction entries
			orderRepository.save(new Order(transaction_id, item.getId(), item.getPrice(),
					(item.getPrice() * item.getQuantity()), item.getQuantity()));

			// update item quantity in item table
			itemRepository.findById(item.getId()).map(i -> {
				i.setQuantity(i.getQuantity() - item.getQuantity());
				if (i.getQuantity() < 0)
					throw new ItemExceedingStockException("Order for" + item.getYear() + " " + item.getBrand() + " "
							+ item.getShape() + " exceeds the available quantity");
				return itemRepository.save(i);
			}).orElseThrow(() -> new ItemNotFoundException(item.getId()));

		}
		return transaction;
	}

	private List<Item> dtoToItem(List<ItemDTO> itemDTO) {
		List<Long> ids = new ArrayList<>();
		for (ItemDTO dto : itemDTO)
			ids.add(dto.getId());

		List<Item> items = itemRepository.findAllById(ids);
		for (Item i : items) {
			for (ItemDTO dto : itemDTO) {
				if (i.getId().equals(dto.getId())) {
					i.setQuantity(dto.getQuantity());
					break;
				}
			}
//			System.out.println("details about item"+i.getMileage()+" "+i.getPrice()+" "+i.getId()+);
		}
//		System.out.println("\n\n Total of "+items.size()+" \n\n");
		return items;
	}

	private double getTotal(List<Item> items) {
		double total = 0;

		for (Item item : items) {
			total += item.getPrice() * item.getQuantity();
		}
		return total;
	}

}
