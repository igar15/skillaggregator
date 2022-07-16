package ru.javaprojects.skillaggregator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaprojects.skillaggregator.model.Selection;
import ru.javaprojects.skillaggregator.model.SkillReport;
import ru.javaprojects.skillaggregator.service.SkillReportService;
import ru.javaprojects.skillaggregator.util.SkillReportUtil;

@Controller
public class SkillReportController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private SkillReportService skillReportService;

    public SkillReportController(SkillReportService skillReportService) {
        this.skillReportService = skillReportService;
    }

    @GetMapping("/makeSkillReport")
    public String test(@RequestParam("professionName") String professionName, @RequestParam("city") String city,
                              @RequestParam("selection") Selection selection, Model model) {
        log.info("make skill report for {} in city {} with selection {}", professionName, city, selection);
        if (professionName.isBlank()) {
            return noVacanciesPage(professionName, city, model);
        }
        SkillReport skillReport = skillReportService.getSkillReportForToday(professionName, city, selection);
        if (skillReport.getAnalyzedVacanciesAmount() == 0) {
            return noVacanciesPage(professionName, city, model);
        }
        model.addAttribute("skillReportTo", SkillReportUtil.createTo(skillReport));
        return "skillReport";
    }

    private String noVacanciesPage(String professionName, String city, Model model) {
        model.addAttribute("professionName", professionName);
        model.addAttribute("city", city);
        return "noVacancies";
    }
}