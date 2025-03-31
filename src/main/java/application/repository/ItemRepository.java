package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import application.model.Item;

@Repository
public interface ItemRepository extends  JpaRepository<Item, Long> {

}
