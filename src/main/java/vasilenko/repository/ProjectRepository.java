package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vasilenko.model.Project;


public interface ProjectRepository extends JpaRepository<Project,Integer> {

}
