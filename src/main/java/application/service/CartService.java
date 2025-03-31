package application.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CartService {

	private Map<Long, Integer> cartMap = new HashMap<>();

	public void addToCart(Long itemId, int quantity) {

		int currentQty = cartMap.getOrDefault(itemId, 0);
		cartMap.put(itemId, currentQty + quantity);
	}

	public void removeFromCart(Long itemId) {
		cartMap.remove(itemId);
	}

	public void clearCart() {
		cartMap.clear();
	}

	public Map<Long, Integer> getCartContents() {
		return cartMap;
	}
}