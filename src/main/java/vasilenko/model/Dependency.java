package vasilenko.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;


@Entity
public class Dependency {
    private Integer depId;
    private Task taskByTaskId;
    private Task taskByDependTask;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "dep_id")
    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Dependency that = (Dependency) o;

        return new EqualsBuilder()
                .append(depId, that.depId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(depId)
                .toHashCode();
    }

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id", nullable = false)
    public Task getTaskByTaskId() {
        return taskByTaskId;
    }

    public void setTaskByTaskId(Task taskByTaskId) {
        this.taskByTaskId = taskByTaskId;
    }

    @ManyToOne
    @JoinColumn(name = "depend_task", referencedColumnName = "task_id", nullable = false)
    public Task getTaskByDependTask() {
        return taskByDependTask;
    }

    public void setTaskByDependTask(Task taskByDependTask) {
        this.taskByDependTask = taskByDependTask;
    }
}
