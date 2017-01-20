package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vasilenko.model.Task;


public interface TaskRepository extends JpaRepository<Task,Integer>{
}
