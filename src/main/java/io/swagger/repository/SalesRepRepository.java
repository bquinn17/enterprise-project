package io.swagger.repository;

import io.swagger.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {
    //Nothing is needed here. save(), findAll(), findOne(), delete() are provided by JpaRepository
    List<SalesRep> findSalesRepByRegion(SalesRep.RegionEnum region);
}