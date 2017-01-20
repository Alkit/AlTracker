package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vasilenko.model.Project;

/**
 * Created by Alkit on 1/11/2017.
 */
public interface ProjectRepository extends JpaRepository<Project,Integer> {

}
