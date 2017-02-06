package vasilenko.serivces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vasilenko.model.Project;
import vasilenko.model.Sprint;
import vasilenko.model.Task;
import vasilenko.repository.ProjectRepository;
import vasilenko.serivces.dto.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Формирует модель проекта в вирде дерева для JStree
 */

@Service
public class ProjectTreeServiceImpl implements ProjectTreeService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<TreeNode> getProjectTree(int projectId) {
        List<TreeNode> tree = new ArrayList<>();
        Project project = projectRepository.findOne(projectId);
        tree.add(new TreeNode("p"+project.getProjectId().toString(),"#",project.getProjectName()));
        List<Sprint> sprints = project.getSprintsByProjectId().stream()
                .sorted((a,b) -> a.getSprintNumber().compareTo(b.getSprintNumber())).collect(Collectors.toList());
        for (Sprint s: sprints){
            tree.add(new TreeNode("s" + s.getSprintId().toString(),"p"+project.getProjectId().toString(),s.getSprintName()));
                List<Task> tasks = s.getTasksBySprintId().stream()
                        .sorted((a, b) -> a.getNumber().compareTo(b.getNumber())).collect(Collectors.toList());
                for (Task t : tasks) {
                    if (t.getTaskBySubFor() == null)
                        tree.add(new TreeNode("t"+t.getTaskId(), "s" + s.getSprintId().toString(), t.getTaskName()));
                    else if(t.getTaskBySubFor() != null) {
                        tree.add(new TreeNode("t"+t.getTaskId(), "t"+t.getTaskBySubFor().getTaskId(), t.getTaskName()));
                    }
                }
        }
        return tree;
    }
}
