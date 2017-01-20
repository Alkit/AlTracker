package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Alkit on 1/8/2017.
 */
@Entity
public class Sprint {
    private Integer sprintId;
    private String sprintName;
    private Boolean completed;
    private Integer sprintNumber;
    private Project projectByProjectId;
    private Sprint sprintByPreviousSprint;
    private Collection<Task> tasksBySprintId;

    public Sprint(Integer sprintId, String sprintName, Boolean completed, Integer sprintNumber,
                  Project projectByProjectId, Sprint sprintByPreviousSprint) {
        this.sprintId = sprintId;
        this.sprintName = sprintName;
        this.completed = completed;
        this.sprintNumber = sprintNumber;
        this.projectByProjectId = projectByProjectId;
        this.sprintByPreviousSprint = sprintByPreviousSprint;
    }

    public Sprint() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sprint_id")
    public Integer getSprintId() {
        return sprintId;
    }

    public void setSprintId(Integer sprintId) {
        this.sprintId = sprintId;
    }

    @Basic
    @Column(name = "sprint_name")
    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    @Basic
    @Column(name = "completed")
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Basic
    @Column(name = "sprint_number")
    public Integer getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Sprint sprint = (Sprint) o;

        return new EqualsBuilder()
                .append(sprintId, sprint.sprintId)
                .append(sprintName, sprint.sprintName)
                .append(completed, sprint.completed)
                .append(sprintNumber, sprint.sprintNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sprintId)
                .append(sprintName)
                .append(completed)
                .append(sprintNumber)
                .toHashCode();
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false)
    public Project getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(Project projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "previous_sprint", referencedColumnName = "sprint_id")
    public Sprint getSprintByPreviousSprint() {
        return sprintByPreviousSprint;
    }

    public void setSprintByPreviousSprint(Sprint sprintByPreviousSprint) {
        this.sprintByPreviousSprint = sprintByPreviousSprint;
    }

    @OneToMany(mappedBy = "sprintBySprintId")
    public Collection<Task> getTasksBySprintId() {
        return tasksBySprintId;
    }

    public void setTasksBySprintId(Collection<Task> tasksBySprintId) {
        this.tasksBySprintId = tasksBySprintId;
    }
}
