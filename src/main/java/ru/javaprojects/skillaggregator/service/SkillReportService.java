package ru.javaprojects.skillaggregator.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaprojects.skillaggregator.model.Selection;
import ru.javaprojects.skillaggregator.model.SkillReport;
import ru.javaprojects.skillaggregator.repository.SkillReportRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillReportService {

    private VacancyService vacancyService;
    private SkillReportRepository repository;

    public SkillReportService(VacancyService vacancyService, SkillReportRepository repository) {
        this.vacancyService = vacancyService;
        this.repository = repository;
    }

    public SkillReport getSkillReportForToday(String professionName, String city, Selection selection) {
        checkParamsOnNull(professionName, city, selection);
        professionName = professionName.toUpperCase();
        city = city.toUpperCase();
        Optional<SkillReport> optionalSkillReport =
                repository.findByProfessionNameAndCityAndDateAndSelection(professionName, city, LocalDate.now(), selection);
        if (optionalSkillReport.isPresent()) {
            return optionalSkillReport.get();
        } else {
            return createSkillReport(professionName, city, selection);
        }
    }

    private void checkParamsOnNull(String professionName, String city, Selection selection) {
        Assert.notNull(professionName, "professionName must not be null");
        Assert.notNull(city, "city must not be null");
        Assert.notNull(selection, "selection must not be null");
    }

    private SkillReport createSkillReport(String professionName, String city, Selection selection) {
        Set<String> vacancyIds = vacancyService.getVacancyIds(professionName, city, selection.getPageAmount());
        if (vacancyIds.isEmpty()) {
            return emptySkillReport();
        }
        Map<String, Integer> skillCounter = vacancyIds.stream()
                .flatMap(vacancyId -> vacancyService.getKeySkills(vacancyId).stream())
                .collect(Collectors.groupingBy(keySkill -> keySkill, Collectors.summingInt(keySkill -> 1)));
        SkillReport skillReport = new SkillReport(professionName, city, vacancyIds.size(), skillCounter, selection);
        return saveSkillReport(skillReport);
    }

    private SkillReport emptySkillReport() {
        return new SkillReport();
    }

    private SkillReport saveSkillReport(SkillReport skillReport) {
        try {
            return repository.save(skillReport);
        } catch (DataAccessException e) {
            return repository.findByProfessionNameAndCityAndDateAndSelection(skillReport.getProfessionName(),
                    skillReport.getCity(), LocalDate.now(), skillReport.getSelection()).get();
        }
    }

    public List<SkillReport> getAllSkillReportsForToday() {
        return repository.findAllByDate(LocalDate.now());
    }

    public void deleteSkillReportForToday(String professionName, String city, Selection selection) {
        checkParamsOnNull(professionName, city, selection);
        repository.deleteByNameAndCityAndDateAndSelection(professionName.toUpperCase(), city.toUpperCase(),
                LocalDate.now(), selection);
    }

    public void deleteAllSkillReportsForToday() {
        repository.deleteAllByDate(LocalDate.now());
    }
}