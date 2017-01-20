package vasilenko.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import vasilenko.model.Employee;
import vasilenko.model.Task;
import vasilenko.repository.EmployeeRepository;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String home(){
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

 //   public String getTimeRequestPage
}
