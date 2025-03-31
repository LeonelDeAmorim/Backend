package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import application.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

}
