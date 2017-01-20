package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Alkit on 1/8/2017.
 */
@Entity
public class Customer {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Customer(Integer customerId, String firstName, String lastName, String email, String phoneNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Customer(){

    }

    private Collection<Project> projectsByCustomerId;

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return new EqualsBuilder()
                .append(customerId, customer.customerId)
                .append(firstName, customer.firstName)
                .append(lastName, customer.lastName)
                .append(email, customer.email)
                .append(phoneNumber, customer.phoneNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(customerId)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(phoneNumber)
                .toHashCode();
    }

    @OneToMany(mappedBy = "customerByCustomer")
    public Collection<Project> getProjectsByCustomerId() {
        return projectsByCustomerId;
    }

    public void setProjectsByCustomerId(Collection<Project> projectsByCustomerId) {
        this.projectsByCustomerId = projectsByCustomerId;
    }
}
