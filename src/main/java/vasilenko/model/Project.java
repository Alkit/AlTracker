package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;


@Entity
public class Project {
    private Integer projectId;
    private String projectName;
    private Date beginDate;
    private Date endDate;
    private Customer customerByCustomer;
    private Employee employeeByProjectManager;
    private Collection<Sprint> sprintsByProjectId;

    public Project(Integer projectId,String projectName, Date beginDate, Date endDate,
                   Customer customerByCustomer, Employee employeeByProjectManager) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.customerByCustomer = customerByCustomer;
        this.employeeByProjectManager = employeeByProjectManager;
    }

    public Project() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "begin_date")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return new EqualsBuilder()
                .append(projectId, project.projectId)
                .append(projectName, project.projectName)
                .append(beginDate, project.beginDate)
                .append(endDate, project.endDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(projectId)
                .append(projectName)
                .append(beginDate)
                .append(endDate)
                .toHashCode();
    }

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    public Customer getCustomerByCustomer() {
        return customerByCustomer;
    }

    public void setCustomerByCustomer(Customer customerByCustomer) {
        this.customerByCustomer = customerByCustomer;
    }

    @ManyToOne
    @JoinColumn(name = "project_manager", referencedColumnName = "emp_id")
    public Employee getEmployeeByProjectManager() {
        return employeeByProjectManager;
    }

    public void setEmployeeByProjectManager(Employee employeeByProjectManager) {
        this.employeeByProjectManager = employeeByProjectManager;
    }

    @OneToMany(mappedBy = "projectByProjectId")
    public Collection<Sprint> getSprintsByProjectId() {
        return sprintsByProjectId;
    }

    public void setSprintsByProjectId(Collection<Sprint> sprintsByProjectId) {
        this.sprintsByProjectId = sprintsByProjectId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", customerByCustomer=" + customerByCustomer +
                ", employeeByProjectManager=" + employeeByProjectManager +
                ", sprintsByProjectId=" + sprintsByProjectId +
                '}';
    }
}
