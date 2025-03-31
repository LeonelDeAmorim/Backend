package application.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import application.exception.ReviewNotFoundException;
import application.model.Review;
import application.repository.ReviewRepository;
import application.service.ReviewService;

//changed from restcontroller to display functionality will change when frontend developed
//@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReviewController {


@Autowired
private ReviewService service;


//@GetMapping("/reviews")
//public String reviews(Model model) {
//List<Review> reviews = repository.findAll();
//model.addAttribute("reviews", reviews);
//return "reviews";
//}

@GetMapping("/reviews")
public List<Review> reviews() {
return service.getAll();
}


@PostMapping("/reviews")
ResponseEntity<Review> newReview(@RequestBody Review newReview) {
return service.newReview(newReview);
}

@GetMapping("/reviews/{id}")
ResponseEntity<Review> one(@PathVariable long id) {
return service.singleReview(id);
}

@GetMapping("/userReviews/{id}")
List<Review> userReview(@PathVariable Long id) {
return service.userReview(id);
}

@GetMapping("/itemReviews/{id}")
List<Review> itemReviews(@PathVariable Long id) {
return service.itemReviews(id);
}

@DeleteMapping("/reviews/{id}")
void deleteReview(@PathVariable Long id) {
service.delete(id);
}

@PutMapping("/reviews/{id}")
ResponseEntity<Review> replaceReview(@RequestBody Review newReview, @PathVariable Long id) {
return service.updateReview(newReview, id);
}
}