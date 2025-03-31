package application.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {

private @Id
@GeneratedValue(strategy = GenerationType.AUTO) Long id;
private long user_id;
private long item_id;
private double rating;
private String comment;
private LocalDateTime created;

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public long getUser_id() {
return user_id;
}

public void setUser_id(long user_id) {
this.user_id = user_id;
}

public long getItem_id() {
return item_id;
}

public void setItem_id(long item_id) {
this.item_id = item_id;
}

public double getRating() {
return rating;
}

public void setRating(double rating) {
this.rating = rating;
}

public String getComment() {
return comment;
}

public void setComment(String comment) {
this.comment = comment;
}

public LocalDateTime getCreated() {
return created;
}

public void setCreated(LocalDateTime created) {
this.created = created;
}
}