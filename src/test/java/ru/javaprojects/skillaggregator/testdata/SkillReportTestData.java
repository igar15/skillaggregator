package ru.javaprojects.skillaggregator.testdata;

import ru.javaprojects.skillaggregator.model.SkillReport;

import java.time.LocalDate;
import java.util.Map;

import static ru.javaprojects.skillaggregator.model.Selection.FIRST_100_VACANCIES;

public class SkillReportTestData {
    public static final String JAVA_PROFESSION_NAME = "JAVA";
    public static final String SELLER_PROFESSION_NAME = "SELLER";
    public static final String MANAGER_PROFESSION_NAME = "MANAGER";
    public static final String NOT_EXISTED_PROFESSION_NAME = "XYZ_ABC";
    public static final String MOSCOW_CITY = "MOSCOW";
    public static final String LONDON_CITY = "LONDON";
    public static final int JAVA_SKILL_REPORT_ID = 100000;
    public static final int SELLER_SKILL_REPORT_ID = 100001;
    public static SkillReport javaSkillReport;
    public static SkillReport sellerSkillReport;
    public static SkillReport managerSkillReport;
    public static final int VACANCIES_AMOUNT = 100;
    public static final int MANAGER_VACANCIES_AMOUNT = 5;

    static {
        createSkillReports();
    }

    private static void createSkillReports() {
        Map<String, Long> javaSkillReportSkillCounter = Map.of("JAVA", 100L, "ENGLISH", 25L, "SPRING", 90L, "HIBERNATE", 70L,
                "SQL", 50L, "DOCKER", 10L);
        javaSkillReport = new SkillReport(JAVA_SKILL_REPORT_ID, JAVA_PROFESSION_NAME, MOSCOW_CITY, LocalDate.now(), VACANCIES_AMOUNT,
                javaSkillReportSkillCounter, FIRST_100_VACANCIES);
        Map<String, Long> sellerSkillReportSkillCounter = Map.of("SALES", 80L, "POLITENESS", 70L, "ARITHMETIC", 20L);
        sellerSkillReport = new SkillReport(SELLER_SKILL_REPORT_ID, SELLER_PROFESSION_NAME, LONDON_CITY, LocalDate.now(), VACANCIES_AMOUNT,
                sellerSkillReportSkillCounter, FIRST_100_VACANCIES);
        Map<String, Long> managerSkillReportSkillCounter = Map.of("LEADING", 5L, "SALES", 4L, "MANAGEMENT", 4L,
                "ENGLISH", 2L, "GERMAN", 1L, "MATH", 1L);
        managerSkillReport = new SkillReport(MANAGER_PROFESSION_NAME, MOSCOW_CITY, MANAGER_VACANCIES_AMOUNT,
                managerSkillReportSkillCounter, FIRST_100_VACANCIES);

    }
}