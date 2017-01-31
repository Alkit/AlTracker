package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Employee {
    private Integer empId;
    private String firstName;
    private String lastName;
    private Role roleByRole;
    private String email;
    private String password;
    private String type;
    private Qualification qualificationByQualification;
    private Collection<EmployerTimeRequest> employerTimeRequestsByEmpId;
    private Collection<Project> projectsByEmpId;
    private Collection<Task> tasksByEmpId;

    public Employee(Integer empId, String firstName, String lastName, Role roleByRole,
                    Qualification qualificationByQualification, String email, String type) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleByRole = roleByRole;
        this.qualificationByQualification = qualificationByQualification;
        this.email = email;
        this.type = type;
    }

    public Employee() {
    }

    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
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

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder()
                .append(empId, employee.empId)
                .append(firstName, employee.firstName)
                .append(lastName, employee.lastName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(empId)
                .append(firstName)
                .append(lastName)
                .toHashCode();
    }

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "role_id")
    public Role getRoleByRole() {
        return roleByRole;
    }

    public void setRoleByRole(Role roleByRole) {
        this.roleByRole = roleByRole;
    }

    @ManyToOne
    @JoinColumn(name = "qualification", referencedColumnName = "qual_id")

    public Qualification getQualificationByQualification() {
        return qualificationByQualification;
    }

    public void setQualificationByQualification(Qualification qualificationByQualification) {
        this.qualificationByQualification = qualificationByQualification;
    }

    @OneToMany(mappedBy = "employeeByEmpId")
    public Collection<EmployerTimeRequest> getEmployerTimeRequestsByEmpId() {
        return employerTimeRequestsByEmpId;
    }

    public void setEmployerTimeRequestsByEmpId(Collection<EmployerTimeRequest> employerTimeRequestsByEmpId) {
        this.employerTimeRequestsByEmpId = employerTimeRequestsByEmpId;
    }

    @OneToMany(mappedBy = "employeeByProjectManager")
    public Collection<Project> getProjectsByEmpId() {
        return projectsByEmpId;
    }

    public void setProjectsByEmpId(Collection<Project> projectsByEmpId) {
        this.projectsByEmpId = projectsByEmpId;
    }

    @OneToMany(mappedBy = "employeeByExecutor")
    public Collection<Task> getTasksByEmpId() {
        return tasksByEmpId;
    }

    public void setTasksByEmpId(Collection<Task> tasksByEmpId) {
        this.tasksByEmpId = tasksByEmpId;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
