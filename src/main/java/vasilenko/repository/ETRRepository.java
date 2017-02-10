package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vasilenko.model.EmployerTimeRequest;


public interface ETRRepository extends JpaRepository<EmployerTimeRequest,Integer>{
}
