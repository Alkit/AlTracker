package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vasilenko.model.Qualification;

/**
 * Created by Alkit on 1/9/2017.
 */
public interface QualificationRepository extends JpaRepository<Qualification,Integer> {
}
