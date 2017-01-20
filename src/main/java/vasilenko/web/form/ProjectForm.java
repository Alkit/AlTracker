package vasilenko.web.form;

import java.sql.Date;

/**
 * Created by Alkit on 1/11/2017.
 */
public class ProjectForm {
    private Integer projectId;
    private String projectName;
    private Date beginDate;
    private Date endDate;
    private int customerId;
    private int projectManagerId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(int projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    @Override
    public String toString() {
        return "ProjectForm{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", customerId=" + customerId +
                ", projectManagerId=" + projectManagerId +
                '}';
    }
}
