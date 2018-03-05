package io.swagger.repository;
import io.swagger.model.RetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailOrderRepository extends JpaRepository<RetailOrder, Long> {

}
