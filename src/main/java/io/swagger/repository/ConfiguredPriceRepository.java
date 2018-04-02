package io.swagger.repository;
import io.swagger.model.ConfiguredPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguredPriceRepository extends JpaRepository<ConfiguredPrice, Long> {
    //Nothing is needed here. save(), findAll(), findOne(), delete() are provided by JpaRepository
}