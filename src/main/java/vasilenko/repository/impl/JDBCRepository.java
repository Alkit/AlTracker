package vasilenko.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import vasilenko.web.form.TaskForm;



@Repository
public class JDBCRepository {

    JdbcOperations jdbc;

    @Autowired
    public JDBCRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public void saveTask(TaskForm taskForm){
        Integer max = jdbc.queryForObject("SELECT max(number) maxnumber FROM task", Integer.class);
        if(max == null)
            max = 1;
        jdbc.update("INSERT INTO task (task_name, estimate_hours, begin_date, end_date, req_qual, sprint_id, executor,number)" +
                " VALUES (?,?,?,?,?,?,?,?)",
                taskForm.getTaskName(),
                taskForm.getEstimateHours(),
                taskForm.getBeginDate(),
                taskForm.getEndDate(),
                taskForm.getQualification(),
                taskForm.getSprint(),
                taskForm.getExecutor(),
                max+1);
    }

    public void addTimeRequest(int userId, int taskId, int timeAmount){
        jdbc.update("INSERT INTO employer_time_request (emp_id, task_id, time_amount_hours, confirmed) VALUES (?,?,?,?)",
                userId,taskId,timeAmount,false);
    }

    public int getCountTaskByEmployee(int employee){
        Integer count = jdbc.queryForObject("Select count(*) FROM task INNER JOIN employee ON task.executor=employee.emp_id WHERE employee.emp_id = "+employee,
                 Integer.class);
        return count;
    }

    public int getCountCompletedTaskByEmployee(int employee){
        Integer count = jdbc.queryForObject("SELECT count(*) FROM task INNER JOIN employee ON task.executor=employee.emp_id" +
                " WHERE task.hours_spented IS NOT NULL and employee.emp_id="+employee,Integer.class);
        return count;
    }
}
