package vasilenko.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import vasilenko.model.Employee;
import vasilenko.model.Task;
import vasilenko.repository.EmployeeRepository;
import vasilenko.repository.TaskRepository;

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

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
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
 //   public String getTimeRequestPage
}
