package io.swagger.repository;

import io.swagger.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {
    //Nothing is needed here. save(), findAll(), findOne(), delete() are provided by JpaRepository
}