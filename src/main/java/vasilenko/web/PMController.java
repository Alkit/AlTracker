package vasilenko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vasilenko.model.Employee;
import vasilenko.model.Project;
import vasilenko.model.Sprint;
import vasilenko.model.Task;
import vasilenko.repository.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alkit on 1/15/2017.
 */
@Controller
@RequestMapping("/pm")
@SessionAttributes("projectId")
public class PMController {
    private int pId;

    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private SprintRepository sprintRepository;
    private TaskRepository taskRepository;
    private QualificationRepository qualificationRepository;

    @Autowired
    public PMController(EmployeeRepository employeeRepository,ProjectRepository projectRepository,
                        SprintRepository sprintRepository,TaskRepository taskRepository,QualificationRepository qualificationRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.sprintRepository = sprintRepository;
        this.taskRepository =  taskRepository;
        this.qualificationRepository = qualificationRepository;
    }

    @GetMapping
    public String getProjects(Model model, Principal principal){
        Employee emp = employeeRepository.findEmployeeByEmail(principal.getName());
        List<Project> projects = emp.getProjectsByEmpId().stream().collect(Collectors.toList());
        model.addAttribute("projects",projects);
        return "pm";
    }

    @GetMapping("/details")
    public String projectDetails(Model model, Principal principal){
        Project project = projectRepository.findOne(pId);
        model.addAttribute("sprints",project.getSprintsByProjectId());
        model.addAttribute("quals", qualificationRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "pm/projectdetail";
    }

    @PostMapping("/post/{projectId}")
    public String addProjectId(@PathVariable int projectId, Model model){
        pId = projectId;
        model.addAttribute("projectId",projectId);
        return "redirect:/pm/details";
    }


    @GetMapping("/project/{projectId}")
    public String loadProjectInfo(@PathVariable int projectId, Model model){
        Project project = projectRepository.findOne(projectId);
        model.addAttribute("size", project.getSprintsByProjectId().size());
        model.addAttribute("project",project);
        return "pm/bodyParts/projectInfo";
    }

    @GetMapping("/sprint/{sprintId}")
    public String getSprint(@PathVariable int sprintId,Model model){
        Sprint sprint = sprintRepository.findSprintById(sprintId);
        model.addAttribute("taskNumber",sprint.getTasksBySprintId().size());
        model.addAttribute("sprint",sprint);
        return "pm/bodyParts/sprintInfo";
    }

    @GetMapping("/task/{taskId}")
    public String getTask(@PathVariable int taskId, Model model){
        Task task = taskRepository.findOne(taskId);
        model.addAttribute("task",task);
        return "pm/bodyParts/taskinfo";
    }
}
