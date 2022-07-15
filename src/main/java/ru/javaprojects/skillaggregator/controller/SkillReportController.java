package ru.javaprojects.skillaggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javaprojects.skillaggregator.model.Selection;
import ru.javaprojects.skillaggregator.model.SkillReport;
import ru.javaprojects.skillaggregator.service.SkillReportService;
import ru.javaprojects.skillaggregator.service.VacancyService;

@RestController
public class SkillReportController {

    @Autowired
    private SkillReportService skillReportService;

    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/test")
    public SkillReport test(@RequestParam("professionName") String professionName, @RequestParam("city") String city,
                            @RequestParam("selection") Selection selection) {
        return skillReportService.getSkillReportForToday(professionName, city, selection);
    }

    @GetMapping("/notfound/{id}")
    public void testNotFound(@PathVariable("id") String id) {
        vacancyService.getKeySkills(id);
    }
}
