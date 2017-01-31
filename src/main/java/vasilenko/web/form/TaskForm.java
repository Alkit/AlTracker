package vasilenko.web.form;

import java.sql.Date;

public class TaskForm {
    private String taskName;
    private Integer estimateHours;
    private Date beginDate;
    private Date endDate;
    private Integer executor;
    private Integer qualification;
    private Integer sprint;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getEstimateHours() {
        return estimateHours;
    }

    public void setEstimateHours(Integer estimateHours) {
        this.estimateHours = estimateHours;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getExecutor() {
        return executor;
    }

    public void setExecutor(Integer executor) {
        this.executor = executor;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public Integer getSprint() {
        return sprint;
    }

    public void setSprint(Integer sprint) {
        this.sprint = sprint;
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "taskName='" + taskName + '\'' +
                ", estimateHours=" + estimateHours +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", executor=" + executor +
                ", qualification=" + qualification +
                ", sprint=" + sprint +
                '}';
    }

}
