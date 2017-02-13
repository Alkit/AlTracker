package vasilenko.serivces;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vasilenko.util.Templates;

import javax.sql.DataSource;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

@Service
public class ReportService {

    @Autowired
    private DataSource ds;
    private OutputStream outputStream;
    private String projectName;
    private String sql;
    private JasperReportBuilder report;

    public void setup(String projectName, int projectId, OutputStream outputStream) {
        this.outputStream = outputStream;
        this.projectName = projectName;
        sql = "SELECT sprint_name,task_name,estimate_hours,accepted,task.begin_date,task.end_date,hours_spented" +
                " FROM task INNER JOIN sprint ON task.sprint_id=sprint.sprint_id INNER JOIN project ON project.project_id=sprint.project_id" +
                " WHERE project.project_id="+ projectId +" ORDER BY sprint_name";
    }

    public void build() throws SQLException {
        Connection connection = ds.getConnection();
        TextColumnBuilder<Integer> estimateHoursColumn = col.column("Estimate Hours", "estimate_hours", type.integerType());
        AggregationSubtotalBuilder<Integer> estimateHoursSum = sbt.sum(estimateHoursColumn)
                .setLabel("Estimate  Sum");
        TextColumnBuilder<Integer> actualHoursColumn = col.column("Spent hours", "hours_spented", type.integerType());
        AggregationSubtotalBuilder<Integer> actualHoursSum = sbt.sum(actualHoursColumn)
                .setLabel("Actual  Sum");
        TextColumnBuilder<String> taskColumn = col.column("Task", "task_name", type.stringType());
        TextColumnBuilder<String> sprintColumn = col.column("Sprint", "sprint_name", type.stringType());
        ColumnGroupBuilder sprintGroup = grp.group(sprintColumn)
                .setTitleWidth(35)
                .setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
                .showColumnHeaderAndFooter();
        try{
             report = report()
                .setTemplate(Templates.reportTemplate)
                    .title(Templates.createTitleComponent(projectName))
                    .setShowColumnTitle(false)
                    .columns(
                            taskColumn,
                            col.booleanColumn("Accepted","accepted").setComponentType(BooleanComponentType.TEXT_YES_NO),
                            col.column("Begin date","begin_date",  type.dateType()),
                            col.column("End date","end_date",  type.dateType()),
                            estimateHoursColumn,
                            actualHoursColumn
                    )
                    .groupBy(sprintGroup)
                    .subtotalsAtSummary(estimateHoursSum,actualHoursSum)
                    .setDataSource(sql,ds.getConnection())
                    .summary(
                            cht.barChart()
                                    .setTitle("Hours spend per sprint")
                                    .setCategory(sprintColumn)
                                    .series(
                                            cht.serie(actualHoursColumn).setSeries(sprintColumn))
                                    .setCategoryAxisFormat(
                                            cht.axisFormat().setLabel("Sprints")));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void toPdf() throws DRException {
        report.toPdf(outputStream);
    }

    public void toXlsx() throws DRException {
        report.toXlsx(outputStream);
    }
}
