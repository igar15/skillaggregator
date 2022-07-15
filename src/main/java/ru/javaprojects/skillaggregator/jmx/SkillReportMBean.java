package ru.javaprojects.skillaggregator.jmx;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import ru.javaprojects.skillaggregator.model.Selection;
import ru.javaprojects.skillaggregator.service.SkillReportService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ManagedResource(description = "Skill Report Manage Bean")
public class SkillReportMBean {

    private SkillReportService service;

    public SkillReportMBean(SkillReportService service) {
        this.service = service;
    }

    @ManagedOperation(description = "Get all skill reports for today")
    public List<String> getAllSkillReportsForToday() {
        return service.getAllSkillReportsForToday().stream()
                .map(skillReport -> String.join(", ", skillReport.getProfessionName(), skillReport.getCity(),
                        skillReport.getSelection().toString()))
                .collect(Collectors.toList());
    }

    @ManagedOperation(description = "Delete all skill reports for today")
    public void deleteAllSkillReportsForToday() {
        service.deleteAllSkillReportsForToday();
    }

    @ManagedOperation(description = "Delete skill report for today")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "professionName", description = "Profession name"),
            @ManagedOperationParameter(name = "city", description = "City"),
            @ManagedOperationParameter(name = "selection", description = "Selection (FIRST_100, " +
                                                                         "FIRST_500 or FIRST_2000)")
    })
    public void deleteSkillReportForToday(String name, String city, String selection) {
        service.deleteSkillReportForToday(name, city, Selection.valueOf(selection));
    }
}