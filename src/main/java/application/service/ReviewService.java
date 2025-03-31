package application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import application.model.Review;
import application.repository.ReviewRepository;

@Service
public class ReviewService {

@Autowired
private ReviewRepository repository;

public ResponseEntity<Review> newReview(Review newReview) {
try {
Review saved = repository.save(newReview);
return new ResponseEntity<>(saved, HttpStatus.CREATED);
}catch(Exception e) {
return new ResponseEntity<Review>(HttpStatus.CONFLICT);

}
}

public ResponseEntity<Review> singleReview(long id) {
return repository.findById(id).map(review -> new ResponseEntity<>(review, HttpStatus.OK))
.orElseGet(() -> new ResponseEntity<Review>(HttpStatus.NOT_FOUND));
}

public List<Review> itemReviews(long id) {
List<Review> reviews = new ArrayList<>();
repository.findAll().forEach(review -> {
if (review.getItem_id() == id) {
reviews.add(review);
}
});
return reviews;

}

public List<Review> userReview(long id) {
List<Review> reviews = new ArrayList<>();
repository.findAll().forEach(review -> {
if (review.getUser_id() == id) {
reviews.add(review);
}
});
return reviews;
}

public ResponseEntity<Review> updateReview(Review newReview, long id) {
return repository.findById(id).map(review -> {
review.setComment(newReview.getComment());
review.setCreated(newReview.getCreated());
review.setRating(newReview.getRating());
Review updatedReview = repository.save(review);
return new ResponseEntity<Review>(updatedReview, HttpStatus.OK);
}).orElseGet(() -> {
return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
});
}

public List<Review> getAll() {
return repository.findAll();
}

public void delete(Long id) {
repository.deleteById(id);

}
}