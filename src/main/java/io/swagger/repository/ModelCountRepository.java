package io.swagger.repository;
import io.swagger.model.ModelCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelCountRepository extends JpaRepository<ModelCount, Long> {
    //Nothing is needed here. save(), findAll(), findOne(), delete() are provided by JpaRepository
}
