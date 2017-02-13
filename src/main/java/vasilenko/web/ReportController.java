package vasilenko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vasilenko.model.Project;
import vasilenko.repository.ProjectRepository;
import vasilenko.serivces.ReportService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    private ProjectRepository projectRepository;

    public ReportController(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;

    }

    @GetMapping("/pdf/{projectId}")
    public void getPdfReport(HttpServletResponse response,@PathVariable int projectId) throws Exception{
        Project project = projectRepository.findOne(projectId);
        response.setContentType("application/pdf");
        reportService.setup(project.getProjectName(),projectId,response.getOutputStream());
        reportService.build();
        reportService.toPdf();
    }

    @GetMapping("/xls/{projectId}")
    public void getXlsReport(HttpServletResponse response,@PathVariable int projectId) throws Exception{
        Project project = projectRepository.findOne(projectId);
        response.setContentType("application/vnd.ms-excel");
        reportService.setup(project.getProjectName(),projectId,response.getOutputStream());
        reportService.build();
        reportService.toXlsx();
    }


}
