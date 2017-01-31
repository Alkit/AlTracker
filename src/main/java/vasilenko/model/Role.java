package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Role {
    private Integer roleId;
    private String roleName;
    private String description;
    private Collection<Employee> employeesByRoleId;

    public Role(Integer roleId,String roleName, String description) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
    }

    public Role(){

    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return new EqualsBuilder()
                .append(roleId, role.roleId)
                .append(roleName, role.roleName)
                .append(description, role.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(roleId)
                .append(roleName)
                .append(description)
                .toHashCode();
    }

    @OneToMany(mappedBy = "roleByRole")
    public Collection<Employee> getEmployeesByRoleId() {
        return employeesByRoleId;
    }

    public void setEmployeesByRoleId(Collection<Employee> employeesByRoleId) {
        this.employeesByRoleId = employeesByRoleId;
    }
}
