package ru.javaprojects.skillaggregator.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.javaprojects.skillaggregator.model.SkillReport;
import ru.javaprojects.skillaggregator.repository.SkillReportRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.javaprojects.skillaggregator.model.Selection.FIRST_100_VACANCIES;
import static ru.javaprojects.skillaggregator.testdata.SkillReportTestData.*;
import static ru.javaprojects.skillaggregator.testdata.VacancyTestData.*;

@SpringBootTest
@ActiveProfiles("dev")
@Sql(scripts = "classpath:data.sql", config = @SqlConfig(encoding = "UTF-8"))
class SkillReportServiceTest {

    @Autowired
    private SkillReportService service;

    @Autowired
    private SkillReportRepository repository;

    @MockBean
    private VacancyService vacancyService;

    @Test
    void getSkillReportForTodayWhenFoundInDb() {
        SkillReport dbSkillReport = service.getSkillReportForToday(JAVA_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES);
        assertThat(dbSkillReport).usingRecursiveComparison().isEqualTo(javaSkillReport);
    }

    @Test
    void getSkillReportForTodayWhenNotFoundInDb() {
        Mockito.when(vacancyService.getVacancyIds(MANAGER_PROFESSION_NAME, MOSCOW_CITY, ONE_PAGE))
                .thenReturn(Set.of(VACANCY_1_ID, VACANCY_2_ID, VACANCY_3_ID, VACANCY_4_ID, VACANCY_5_ID));
        Mockito.when(vacancyService.getKeySkills(VACANCY_1_ID)).thenReturn(VACANCY_1_KEY_SKILLS);
        Mockito.when(vacancyService.getKeySkills(VACANCY_2_ID)).thenReturn(VACANCY_2_KEY_SKILLS);
        Mockito.when(vacancyService.getKeySkills(VACANCY_3_ID)).thenReturn(VACANCY_3_KEY_SKILLS);
        Mockito.when(vacancyService.getKeySkills(VACANCY_4_ID)).thenReturn(VACANCY_4_KEY_SKILLS);
        Mockito.when(vacancyService.getKeySkills(VACANCY_5_ID)).thenReturn(VACANCY_5_KEY_SKILLS);
        SkillReport skillReport = service.getSkillReportForToday(MANAGER_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES);
        assertThat(skillReport).usingRecursiveComparison().ignoringFields("id").isEqualTo(managerSkillReport);
    }

    @Test
    void getSkillReportWhenNoVacanciesFound() {
        Mockito.when(vacancyService.getVacancyIds(NOT_EXISTED_PROFESSION_NAME, MOSCOW_CITY, ONE_PAGE))
                .thenReturn(Collections.emptySet());
        assertThrows(VacanciesNotFoundException.class,
                () -> service.getSkillReportForToday(NOT_EXISTED_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES));
    }

    @Test
    void getAllSkillReportsForToday() {
        List<SkillReport> skillReports = service.getAllSkillReportsForToday();
        assertThat(skillReports).usingRecursiveComparison().isEqualTo(List.of(javaSkillReport, sellerSkillReport));
    }

    @Test
    void deleteSkillReportForToday() {
        service.deleteSkillReportForToday(JAVA_PROFESSION_NAME, MOSCOW_CITY, FIRST_100_VACANCIES);
        assertFalse(repository.findByProfessionNameAndCityAndDateAndSelection(JAVA_PROFESSION_NAME, MOSCOW_CITY,
                LocalDate.now(), FIRST_100_VACANCIES).isPresent());
    }

    @Test
    void deleteAllSkillReportsForToday() {
        service.deleteAllSkillReportsForToday();
        assertTrue(service.getAllSkillReportsForToday().isEmpty());
    }
}