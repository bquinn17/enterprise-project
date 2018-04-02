package io.swagger.repository;
import io.swagger.model.WholesaleOrder;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WholesaleOrderRepository extends JpaRepository<WholesaleOrder, Long> {
    //Nothing is needed here. save(), findAll(), findOne(), delete() are provided by JpaRepository

    List<WholesaleOrder> findBySalesRepEmployeeId(Long EmployeeId);
}