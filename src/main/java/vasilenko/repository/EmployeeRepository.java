package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import vasilenko.model.Employee;
import vasilenko.model.Role;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public List<Employee> findEmployeesByRoleByRole(Role role);

    public Employee findEmployeeByEmail(String email);
}
