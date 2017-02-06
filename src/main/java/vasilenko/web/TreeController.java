package vasilenko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import vasilenko.model.Project;
import vasilenko.model.Sprint;
import vasilenko.repository.ProjectRepository;
import vasilenko.repository.SprintRepository;
import vasilenko.repository.impl.JPASprintRepository;
import vasilenko.serivces.ProjectTreeService;
import vasilenko.serivces.ProjectTreeServiceImpl;
import vasilenko.serivces.dto.TreeNode;

import java.net.URLDecoder;
import java.util.Comparator;
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
    public int addSprint(@RequestBody String newSprint) throws Exception
    {
        System.out.println("executed");
        project = projectRepository.findOne(projecID);
        newSprint = URLDecoder.decode(newSprint,"UTF-8");
        newSprint = newSprint.replace("+","").replace("=","");
        List<Sprint> sprints = project.getSprintsByProjectId().stream().sorted(Comparator.comparing(Sprint::getSprintNumber)).collect(Collectors.toList());
        List<String> sprintNames = sprints.stream().map( s -> s.getSprintName()).collect(Collectors.toList());
        System.out.println(sprintNames);
        Sprint lastSprint = sprints.get(sprints.size()-1);
        if(sprintNames.contains(newSprint)){
             throw new HttpMediaTypeNotAcceptableException("Sprint already exist");
        }
        else{
            System.out.println("Saving");
            sprintRepository.save(new Sprint(null,newSprint,false,
                    lastSprint.getSprintNumber()+1,project,lastSprint));
        }
        return 1;
    }

    @RequestMapping(value = "/sprint/delete", method = RequestMethod.DELETE)
    public void deleteSprint(@RequestBody String sprintId){
        int id = Integer.parseInt(sprintId.substring(1));
        project = projectRepository.findOne(projecID);
        Sprint sprint = project.getSprintsByProjectId().stream().filter(s -> s.getSprintId().equals(id)).findFirst().get();
        sprintRepository.delete(sprint);
    }
}
