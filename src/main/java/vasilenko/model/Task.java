package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Task {
    private Integer taskId;
    private String taskName;
    private Short estimateHours;
    private Date beginDate;
    private Date endDate;
    private Boolean accepted;
    private Date acceptedAt;
    private Integer hoursSpented;
    private Short number;
    private Task taskBySubFor;
    private Qualification qualificationByReqQual;
    private Sprint sprintBySprintId;
    private Employee employeeByExecutor;

    public Task() {
    }

    public Task(Integer taskId, String taskName, Short estimateHours, Date beginDate, Date endDate,
                Short number,Sprint sprintBySprintId,
                Qualification qualificationByReqQual, Task taskBySubFor) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.estimateHours = estimateHours;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.number = number;
        this.taskBySubFor = taskBySubFor;
        this.qualificationByReqQual = qualificationByReqQual;
        this.sprintBySprintId = sprintBySprintId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "task_id")
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "estimate_hours")
    public Short getEstimateHours() {
        return estimateHours;
    }

    public void setEstimateHours(Short estimateHours) {
        this.estimateHours = estimateHours;
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

    @Basic
    @Column(name = "accepted")
    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    @Basic
    @Column(name = "accepted_at")
    public Date getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(Date acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    @Basic
    @Column(name = "hours_spented")
    public Integer getHoursSpented() {
        return hoursSpented;
    }

    public void setHoursSpented(Integer hoursSpented) {
        this.hoursSpented = hoursSpented;
    }

    @Basic
    @Column(name = "number")
    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return new EqualsBuilder()
                .append(taskId, task.taskId)
                .append(taskName, task.taskName)
                .append(estimateHours, task.estimateHours)
                .append(beginDate, task.beginDate)
                .append(endDate, task.endDate)
                .append(accepted, task.accepted)
                .append(acceptedAt, task.acceptedAt)
                .append(hoursSpented, task.hoursSpented)
                .append(number, task.number)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(taskId)
                .append(taskName)
                .append(estimateHours)
                .append(beginDate)
                .append(endDate)
                .append(accepted)
                .append(acceptedAt)
                .append(hoursSpented)
                .append(number)
                .toHashCode();
    }

    @ManyToOne
    @JoinColumn(name = "sub_for", referencedColumnName = "task_id")
    public Task getTaskBySubFor() {
        return taskBySubFor;
    }

    public void setTaskBySubFor(Task taskBySubFor) {
        this.taskBySubFor = taskBySubFor;
    }

    @ManyToOne
    @JoinColumn(name = "req_qual", referencedColumnName = "qual_id")
    public Qualification getQualificationByReqQual() {
        return qualificationByReqQual;
    }

    public void setQualificationByReqQual(Qualification qualificationByReqQual) {
        this.qualificationByReqQual = qualificationByReqQual;
    }

    @ManyToOne
    @JoinColumn(name = "sprint_id", referencedColumnName = "sprint_id")
    public Sprint getSprintBySprintId() {
        return sprintBySprintId;
    }

    public void setSprintBySprintId(Sprint sprintBySprintId) {
        this.sprintBySprintId = sprintBySprintId;
    }

    @ManyToOne
    @JoinColumn(name = "executor", referencedColumnName = "emp_id")
    public Employee getEmployeeByExecutor() {
        return employeeByExecutor;
    }

    public void setEmployeeByExecutor(Employee employeeByExecutor) {
        this.employeeByExecutor = employeeByExecutor;
    }
}
