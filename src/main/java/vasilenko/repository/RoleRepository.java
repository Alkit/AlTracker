package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vasilenko.model.Role;

/**
 * Created by Alkit on 1/7/2017.
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {

    public Role findRoleByRoleName(String roleName);
}
