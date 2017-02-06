package vasilenko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import vasilenko.model.*;
import vasilenko.repository.*;
import vasilenko.repository.impl.JDBCRepository;
import vasilenko.web.form.TaskForm;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    private JDBCRepository jdbcRepository;

    @Autowired
    public PMController(EmployeeRepository employeeRepository, ProjectRepository projectRepository,
                        SprintRepository sprintRepository, TaskRepository taskRepository, QualificationRepository qualificationRepository,
                        JDBCRepository jdbcRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.sprintRepository = sprintRepository;
        this.taskRepository =  taskRepository;
        this.qualificationRepository = qualificationRepository;
        this.jdbcRepository = jdbcRepository;
    }

    @GetMapping
    public String getProjects(Model model, Principal principal){
        Employee emp = employeeRepository.findEmployeeByEmail(principal.getName());
        List<Project> projects = emp.getProjectsByEmpId().stream().collect(Collectors.toList());
        model.addAttribute("projects",projects);
        return "pm";
    }

    @GetMapping("/details")
    public String projectDetails(Model model, Principal principal,TaskForm taskForm){
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

    @PostMapping("/add/task")
    public String addTask(TaskForm taskForm){
        jdbcRepository.saveTask(taskForm);
        System.out.println(taskForm);
        return "redirect:/pm/details/";
    }

    @PostMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId){
        taskRepository.delete(taskId);
        return "redirect:/pm/details/";
    }

    @GetMapping("/timerequests")
    public String getTimeRequests(Model model){
        List<EmployerTimeRequest> requests = sprintRepository.getAllTimeRequestsByProject(pId);
        model.addAttribute("requests",requests);
        return "pm/timeRequests";
    }
}
