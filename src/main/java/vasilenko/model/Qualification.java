package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Qualification {
    private Integer qualId;
    private String qualName;
    private String description;
    private Collection<Employee> employeesByQualId;
    private Collection<Task> tasksByQualId;

    public Qualification(Integer qualId,String qualName, String description) {
        this.qualId = qualId;
        this.qualName = qualName;
        this.description = description;
    }

    public Qualification(){

    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "qual_id")
    public Integer getQualId() {
        return qualId;
    }

    public void setQualId(Integer qualId) {
        this.qualId = qualId;
    }

    @Basic
    @Column(name = "qual_name")
    public String getQualName() {
        return qualName;
    }

    public void setQualName(String qualName) {
        this.qualName = qualName;
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

        Qualification that = (Qualification) o;

        return new EqualsBuilder()
                .append(qualId, that.qualId)
                .append(qualName, that.qualName)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(qualId)
                .append(qualName)
                .append(description)
                .toHashCode();
    }

    @OneToMany(mappedBy = "qualificationByQualification")
    public Collection<Employee> getEmployeesByQualId() {
        return employeesByQualId;
    }

    public void setEmployeesByQualId(Collection<Employee> employeesByQualId) {
        this.employeesByQualId = employeesByQualId;
    }

    @OneToMany(mappedBy = "qualificationByReqQual")
    public Collection<Task> getTasksByQualId() {
        return tasksByQualId;
    }

    public void setTasksByQualId(Collection<Task> tasksByQualId) {
        this.tasksByQualId = tasksByQualId;
    }
}
