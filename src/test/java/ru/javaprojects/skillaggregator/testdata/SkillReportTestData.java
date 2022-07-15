package ru.javaprojects.skillaggregator.testdata;

import ru.javaprojects.skillaggregator.model.SkillReport;
import ru.javaprojects.skillaggregator.to.SkillReportTo;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static ru.javaprojects.skillaggregator.model.Selection.FIRST_100;

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
    public static SkillReportTo javaSkillReportTo;

    static {
        createSkillReports();
        createSkillReportTo();
    }

    private static void createSkillReports() {
        Map<String, Integer> javaSkillReportSkillCounter = Map.of("JAVA", 100, "ENGLISH", 25, "SPRING", 90, "HIBERNATE", 70,
                "SQL", 50, "DOCKER", 10);
        javaSkillReport = new SkillReport(JAVA_SKILL_REPORT_ID, JAVA_PROFESSION_NAME, MOSCOW_CITY, LocalDate.now(), VACANCIES_AMOUNT,
                javaSkillReportSkillCounter, FIRST_100);
        Map<String, Integer> sellerSkillReportSkillCounter = Map.of("SALES", 80, "POLITENESS", 70, "ARITHMETIC", 20);
        sellerSkillReport = new SkillReport(SELLER_SKILL_REPORT_ID, SELLER_PROFESSION_NAME, LONDON_CITY, LocalDate.now(), VACANCIES_AMOUNT,
                sellerSkillReportSkillCounter, FIRST_100);
        Map<String, Integer> managerSkillReportSkillCounter = Map.of("LEADING", 5, "SALES", 4, "MANAGEMENT", 4,
                "ENGLISH", 2, "GERMAN", 1, "MATH", 1);
        managerSkillReport = new SkillReport(MANAGER_PROFESSION_NAME, MOSCOW_CITY, MANAGER_VACANCIES_AMOUNT,
                managerSkillReportSkillCounter, FIRST_100);
    }

    private static void createSkillReportTo() {
        Map<String, Integer> javaSkillStatistic = new LinkedHashMap<>();
        javaSkillStatistic.put("JAVA", 100);
        javaSkillStatistic.put("SPRING", 90);
        javaSkillStatistic.put("HIBERNATE", 70);
        javaSkillStatistic.put("SQL", 50);
        javaSkillStatistic.put("ENGLISH", 25);
        javaSkillStatistic.put("DOCKER", 10);
        javaSkillReportTo = new SkillReportTo("Java", "Moscow", LocalDate.now(), VACANCIES_AMOUNT,
                FIRST_100.getDescription(), javaSkillStatistic);
    }
}