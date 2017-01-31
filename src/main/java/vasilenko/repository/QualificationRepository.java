package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vasilenko.model.Qualification;


public interface QualificationRepository extends JpaRepository<Qualification,Integer> {
}
