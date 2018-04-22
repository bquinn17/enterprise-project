package io.swagger.repository;
import io.swagger.model.RetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailOrderRepository extends JpaRepository<RetailOrder, Long> {
    //Nothing is needed here. save(), findAll(), findOne(), delete() are provided by JpaRepository

    List<RetailOrder> findByStatusNot(RetailOrder.StatusEnum status);
}
