package application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import application.DTO.CartItemRequest;
import application.service.CartService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

  
    @PostMapping("/add")
    public void addToCart(@RequestBody CartItemRequest request) {
        cartService.addToCart(request.getItemId(), request.getQuantity());
    }

   
    @DeleteMapping("/{itemId}")
    public void removeFromCart(@PathVariable Long itemId) {
        cartService.removeFromCart(itemId);
    }

 
    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

 
   //  @GetMapping
   // public Map<Long, Integer> getCartContents() {
   //     return cartService.getCartContents();
   //   }
    //Implement in Front end later
    
    @GetMapping
    public String showCartPage(Model model) {
        model.addAttribute("cartContents", cartService.getCartContents());
        return "cart";  
    }
}