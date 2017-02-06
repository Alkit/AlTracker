package vasilenko.repository;

import vasilenko.model.Dependency;
import vasilenko.model.EmployerTimeRequest;
import vasilenko.model.Sprint;
import vasilenko.model.Task;

import java.util.List;

public interface SprintRepository {
    public List<Task> getAllTasksBySprintId(int sprinid);
    public List<Sprint> getAllSprintOfProject(int projectId);
    public List<EmployerTimeRequest> getAllTimeRequestsByProject(int projectId);
    public Sprint findSprintById(int sprintId);
    public boolean save(Sprint sprint);
    public boolean delete(Sprint sprint);


    public List<Task> testJoin();
}
