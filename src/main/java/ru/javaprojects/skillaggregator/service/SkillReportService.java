package ru.javaprojects.skillaggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaprojects.skillaggregator.model.Selection;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillReportService {

    @Autowired
    private VacancyService vacancyService;


    public void getSkillReport(String professionName, String city, Selection selection) {
        checkParamsOnNull(professionName, city, selection);
        //TODO
        //check report for today in database

        //if report not found
        createSkillReport(professionName, city, selection);
    }

    private void checkParamsOnNull(String professionName, String city, Selection selection) {
        Assert.notNull(professionName, "professionName must not be null");
        Assert.notNull(city, "city must not be null");
        Assert.notNull(selection, "selection must not be null");
    }

    private void createSkillReport(String professionName, String city, Selection selection) {
        Set<String> vacancyIds = vacancyService.getVacancyIds(professionName, city, selection.getPageAmount());
        Map<String, Long> skillCounter = vacancyIds.stream()
                .flatMap(vacancyId -> vacancyService.getKeySkills(vacancyId).stream())
                .collect(Collectors.groupingBy(keySkill -> keySkill, Collectors.counting()));
        System.out.println(skillCounter);
        System.out.println(vacancyIds.size());

    }

}
