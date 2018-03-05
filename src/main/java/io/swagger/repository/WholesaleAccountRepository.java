package io.swagger.repository;

import io.swagger.model.WholesaleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WholesaleAccountRepository extends JpaRepository<WholesaleAccount, Long> {
    //Nothing is needed here. save(), findAll(), findOne(), delete() are provided by JpaRepository
}
