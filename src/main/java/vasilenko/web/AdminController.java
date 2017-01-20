package vasilenko.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vasilenko.model.*;
import vasilenko.repository.*;
import vasilenko.web.form.EmployeeForm;
import vasilenko.web.form.ProjectForm;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private ProjectRepository projectRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private QualificationRepository qualificationRepository;

    @Autowired
    public AdminController(ProjectRepository projectRepository, CustomerRepository customerRepository,
     EmployeeRepository employeeRepository,RoleRepository roleRepository, QualificationRepository qualificationRepository) {
        this.projectRepository = projectRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.qualificationRepository = qualificationRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String adminPage(){

        return "admin";
    }

    @RequestMapping("/customers")
    public String getCustomers(Customer customer, Model model){
        model.addAttribute("customers",customerRepository.findAll());

        return "admin/customers";
    }

    @PostMapping("/customers")
    public String saveCustomer(Customer customer){
        customerRepository.save(customer);
        logger.info("added " + customer.toString());
        return "redirect:/admin/customers";
    }

    @GetMapping("/employees")
    public String getEmployees(EmployeeForm employeeForm, Model model){
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("quals",qualificationRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/employees";
    }

    @PostMapping("/employees")
    public String saveEmployee(EmployeeForm employeeForm){
        Role role = roleRepository.findOne(employeeForm.getRoleId());
        Qualification qualification = qualificationRepository.findOne(employeeForm.getQualId());
        employeeRepository.save(new Employee(null,employeeForm.getFirstName(),employeeForm.getLastName()
                ,role,qualification,employeeForm.getEmail(),employeeForm.getType()));
        logger.info("added " + employeeForm.toString());
        return "redirect:/admin/employees";
    }


    @GetMapping("/projects")
    public String getProjects(ProjectForm projectForm, Model model){
        Role role = roleRepository.findRoleByRoleName("Project Manager");
        model.addAttribute("projects",projectRepository.findAll());
        model.addAttribute("employees",employeeRepository.findEmployeesByRoleByRole(role));
        model.addAttribute("customers",customerRepository.findAll());
        return "admin/projects";
    }

    @PostMapping("/projects")
    public String saveProject(ProjectForm projectForm){
        Employee projectManager = employeeRepository.findOne(projectForm.getProjectManagerId());
        Customer customer = customerRepository.findOne(projectForm.getCustomerId());
        projectRepository.save(new Project(null,projectForm.getProjectName(),projectForm.getBeginDate(),
                projectForm.getEndDate(),customer,projectManager));
        logger.info("added " + projectForm.toString());
        return "redirect:/admin/projects";
    }


}
