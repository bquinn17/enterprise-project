package com.erp.sales.repository;

import com.erp.sales.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bquinn on 2/14/18.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // https://docs.spring.io/autorepo/docs/spring-data-jpa/current/api/org/springframework/data/jpa/repository/support/SimpleJpaRepository.html

}




