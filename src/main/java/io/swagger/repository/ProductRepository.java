package io.swagger.repository;
import io.swagger.model.Product;
import io.swagger.model.RetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<RetailOrder, Long> {
    //Nothing is needed here. save(), findAll(), findOne(), delete() are provided by JpaRepository
}

