package vasilenko.repository.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vasilenko.model.*;
import vasilenko.repository.SprintRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;


@Repository
@Transactional
public class JPASprintRepository implements SprintRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Task> getAllTasksBySprintId(int sprinid){
        Query query = entityManager.createQuery("select t FROM Task as t WHERE t.sprintBySprintId.sprintId=:sprintId ");
        query.setParameter("sprintId", sprinid);
        return query.getResultList();
    }

    public List<Sprint> getAllSprintOfProject(int projectId){
        Query query = entityManager.createQuery("SELECT s FROM Sprint as s WHERE s.projectByProjectId.projectId=:projectId");
        query.setParameter("projectId",projectId);
        return query.getResultList();
    }

    @Override
    public List<EmployerTimeRequest> getAllTimeRequestsByProject(int projectId) {
        Query query = entityManager
                .createQuery("SELECT etr FROM EmployerTimeRequest as etr INNER JOIN etr.taskByTaskId as t" +
                        " INNER JOIN t.sprintBySprintId as s INNER JOIN s.projectByProjectId as p" +
                        " WHERE p.projectId=:pId");
        query.setParameter("pId",projectId);
        return query.getResultList();
    }

    public Sprint findSprintById(int sprintId){
        Query query = entityManager.createQuery("Select s FROM Sprint as s WHERE s.sprintId=:sprintid");
        query.setParameter("sprintid",sprintId);
        return (Sprint) query.getSingleResult();
    }

    public boolean save(Sprint sprint){
        entityManager.merge(sprint);
        entityManager.flush();
        return entityManager.contains(sprint);
    }

    public boolean delete(Sprint sprint){
        entityManager.remove(entityManager.contains(sprint) ? sprint : entityManager.merge(sprint));
        return !entityManager.contains(sprint);
    }

    public List<Task> testJoin(){
        Query query = entityManager.createQuery("Select t FROM Task as t INNER JOIN t.employeeByExecutor as e WHERE e.empId = 5");
        return query.getResultList();
    }
}
