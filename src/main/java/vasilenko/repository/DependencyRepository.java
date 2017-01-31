package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vasilenko.model.Dependency;
import vasilenko.model.Task;

import java.util.List;

public interface DependencyRepository extends JpaRepository<Dependency,Integer> {

    public List<Dependency> findDependancyByTaskByTaskId(Task TaskByTaskId);

 }
