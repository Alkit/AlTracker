package vasilenko.repository;

import vasilenko.model.Sprint;
import vasilenko.model.Task;

import java.util.List;

public interface SprintRepository {
    public List<Task> getAllTasksBySprintId(int sprinid);
    public List<Sprint> getAllSprintOfProject(int projectId);
    public Sprint findSprintById(int sprintId);
    public boolean save(Sprint sprint);
    public boolean delete(Sprint sprint);

}
