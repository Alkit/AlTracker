package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vasilenko.model.Customer;

import java.util.List;

/**
 * Created by Alkit on 1/9/2017.
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer findCustomerByFirstNameAndLastName(String firstName, String lastName);


}
