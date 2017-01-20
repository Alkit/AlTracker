package vasilenko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vasilenko.model.Project;
import vasilenko.model.Sprint;
import vasilenko.repository.ProjectRepository;
import vasilenko.repository.SprintRepository;
import vasilenko.serivces.ProjectTreeService;
import vasilenko.serivces.ProjectTreeServiceImpl;
import vasilenko.serivces.dto.TreeNode;

import java.net.URLDecoder;
import java.sql.Struct;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TreeController {
    private Project project;
    private int projecID;

    @Autowired
    public TreeController(ProjectTreeServiceImpl projectTreeService, SprintRepository sprintRepository, ProjectRepository projectRepository) {
        this.projectTreeService = projectTreeService;
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
    }

    private ProjectTreeService projectTreeService;
    private SprintRepository sprintRepository;
    private ProjectRepository projectRepository;

    @GetMapping("/api/{pId}")
    public List<TreeNode> getTree(@PathVariable int pId){
        project = projectRepository.findOne(pId);
        projecID = pId;
        return projectTreeService.getProjectTree(pId);
    }

    @PostMapping("/sprint/add")
    public void addSprint(@RequestBody String newSprint) throws Exception
    {
        project = projectRepository.findOne(projecID);
        newSprint = URLDecoder.decode(newSprint,"UTF-8");
        newSprint = newSprint.replace("+"," ").replace("=","");
        List<Sprint> sprints = project.getSprintsByProjectId().stream().sorted((a,b) -> a.getSprintNumber().compareTo(b.getSprintNumber())).collect(Collectors.toList());
        List<String> sprintName = sprints.stream().map( s -> s.getSprintName()).collect(Collectors.toList());
        Sprint lastSprint = sprints.get(sprints.size()-1);
        if(sprintName.contains(newSprint)){
            return;
        }
        else{
            sprintRepository.save(new Sprint(null,newSprint,false,
                    lastSprint.getSprintNumber()+1,project,lastSprint));
        }
        System.out.println(newSprint);
    }

    @RequestMapping(value = "/sprint/delete", method = RequestMethod.DELETE)
    public void deleteSprint(@RequestBody String sprintName){
        project = projectRepository.findOne(projecID);
        Sprint sprint = project.getSprintsByProjectId().stream().filter(s -> s.getSprintName().equals(sprintName)).findFirst().get();
        sprintRepository.delete(sprint);
    }
}
