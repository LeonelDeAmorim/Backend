package application.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.exception.ItemNotFoundException;
import application.model.Item;
import application.repository.ItemRepository;

import java.util.List;

@RestController  // Use RestController for API responses
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    private final ItemRepository repository;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    public List<Item> all() {
        return repository.findAll();  // Return the list of items directly as JSON
    }

    @PostMapping("/items")
    public Item newItem(@RequestBody Item newItem) {
        return repository.save(newItem);
    }

    @GetMapping("/items/{id}")
    public Item one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @PutMapping("/items/{id}")
    public Item replaceItem(@RequestBody Item newItem, @PathVariable Long id) {
        return repository.findById(id).map(item -> {
            item.setBrand(newItem.getBrand());
            item.setColor(newItem.getColor());
            item.setCondition(newItem.getCondition());
            item.setMileage(newItem.getMileage());
            item.setPrice(newItem.getPrice());
            item.setQuantity(newItem.getQuantity());  // Fixing quantity setting
            item.setShape(newItem.getShape());
            item.setYear(newItem.getYear());
            return repository.save(item);
        }).orElseGet(() -> {
            return repository.save(newItem);
        });
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
