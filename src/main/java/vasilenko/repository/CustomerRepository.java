package vasilenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import vasilenko.model.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer findCustomerByFirstNameAndLastName(String firstName, String lastName);


}
