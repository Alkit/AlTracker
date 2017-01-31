package vasilenko.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import vasilenko.model.Dependency;
import vasilenko.model.Employee;
import vasilenko.model.Task;
import vasilenko.repository.DependencyRepository;
import vasilenko.repository.EmployeeRepository;
import vasilenko.repository.TaskRepository;
import vasilenko.repository.impl.JDBCRepository;

import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private String empEmail;

    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;
    private JDBCRepository jdbcRepository;
    private DependencyRepository dependencyRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, TaskRepository taskRepository,
                              JDBCRepository jdbcRepository, DependencyRepository dependencyRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.jdbcRepository = jdbcRepository;
        this.dependencyRepository = dependencyRepository;
    }

    @GetMapping
    public String home(Principal principal)
    {
        empEmail = principal.getName();
        return "employee";
    }


    @GetMapping("/mytasks")
    public String getTasks(Model model, Principal principal){
        Employee e = employeeRepository.findEmployeeByEmail(principal.getName());
        Collection<Task> taskCollection = e.getTasksByEmpId();
        List<Task> tasks = taskCollection.stream().filter(task -> task.getAccepted() != null)
                .filter(task -> task.getAccepted())
                .filter(task -> task.getHoursSpented() == null)
                .collect(Collectors.toList());
        model.addAttribute("tasks",tasks);
        return "employee/tasks";
    }

    @GetMapping("/props")
    public String getProposedTasks(Model model, Principal principal){
        Employee e = employeeRepository.findEmployeeByEmail(principal.getName());
        Collection<Task> taskCollection = e.getTasksByEmpId();
        List<Task> tasks =  taskCollection.stream().filter(task -> task.getAccepted() == null)
                .collect(Collectors.toList());
        model.addAttribute("tasks",tasks);
        return "employee/proposed";
    }

    @GetMapping("/request")
    public String requestPage(Model model){
        Employee employee = employeeRepository.findEmployeeByEmail(empEmail);
        model.addAttribute("requires",employee.getEmployerTimeRequestsByEmpId());
        return "employee/requests";
    }

    @PostMapping("/acceptTask/{taskId}")
    public String acceptTask(@PathVariable int taskId){
        Employee emp = employeeRepository.findEmployeeByEmail(empEmail);
        Task task = emp.getTasksByEmpId().stream().filter(e -> e.getTaskId() == taskId).findFirst().get();
        task.setAccepted(true);
        task.setAcceptedAt(new Date(Calendar.getInstance().getTimeInMillis()));
        taskRepository.save(task);
        return "redirect:/employee/props";
    }

    @PostMapping("/requestTime")
    public String requestTime(@RequestBody MultiValueMap<String,String> formData){
        Employee employee = employeeRepository.findEmployeeByEmail(empEmail);
        int time = Integer.parseInt(formData.get("time").get(0));
        int taskId = Integer.parseInt(formData.get("taskIdForRequest").get(0));
        System.out.println(time);
        System.out.println(taskId);
        jdbcRepository.addTimeRequest(employee.getEmpId(),taskId,time);
        return "redirect:/employee/mytasks";
    }

    @PostMapping("/confirmTask")
    public String confirmTaskComplete(@RequestBody MultiValueMap<String,String> formData){
        int taskId = Integer.parseInt(formData.get("taskForComplete").get(0));
        int timeAmount = Integer.parseInt(formData.get("timeSpent").get(0));
        boolean dependTaskCompleted = true;
        Task task = taskRepository.findOne(taskId);
        List<Dependency> list = dependencyRepository.findDependancyByTaskByTaskId(task);
        for(Dependency dependency: list){
            if(dependency.getTaskByDependTask().getHoursSpented() == null){
                dependTaskCompleted = false;
            }
        }

        if(dependTaskCompleted){
        task.setHoursSpented(timeAmount);
        taskRepository.save(task);
        return "redirect:/employee/mytasks";
        }

        else return "redirect:/error";
    }
}
