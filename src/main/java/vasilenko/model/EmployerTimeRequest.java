package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Created by Alkit on 1/8/2017.
 */
@Entity
@Table(name = "employer_time_request", schema = "public", catalog = "Tracker")
public class EmployerTimeRequest {
    private Integer etr;
    private Short timeAmountHours;
    private Boolean confirmed;
    private Employee employeeByEmpId;
    private Task taskByTaskId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "etr")
    public Integer getEtr() {
        return etr;
    }

    public void setEtr(Integer etr) {
        this.etr = etr;
    }

    @Basic
    @Column(name = "time_amount_hours")
    public Short getTimeAmountHours() {
        return timeAmountHours;
    }

    public void setTimeAmountHours(Short timeAmountHours) {
        this.timeAmountHours = timeAmountHours;
    }

    @Basic
    @Column(name = "confirmed")
    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EmployerTimeRequest that = (EmployerTimeRequest) o;

        return new EqualsBuilder()
                .append(etr, that.etr)
                .append(timeAmountHours, that.timeAmountHours)
                .append(confirmed, that.confirmed)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(etr)
                .append(timeAmountHours)
                .append(confirmed)
                .toHashCode();
    }

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", nullable = false)
    public Employee getEmployeeByEmpId() {
        return employeeByEmpId;
    }

    public void setEmployeeByEmpId(Employee employeeByEmpId) {
        this.employeeByEmpId = employeeByEmpId;
    }

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id", nullable = false)
    public Task getTaskByTaskId() {
        return taskByTaskId;
    }

    public void setTaskByTaskId(Task taskByTaskId) {
        this.taskByTaskId = taskByTaskId;
    }
}
