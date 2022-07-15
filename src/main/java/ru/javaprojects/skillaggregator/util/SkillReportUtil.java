package ru.javaprojects.skillaggregator.util;


import org.springframework.util.StringUtils;
import ru.javaprojects.skillaggregator.model.SkillReport;
import ru.javaprojects.skillaggregator.to.SkillReportTo;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SkillReportUtil {
    private static final int ONE_HUNDRED_PERCENT = 100;

    public static SkillReportTo createTo(SkillReport skillReport) {
        Map<String, Integer> skillStatistic =
                createSkillStatistic(skillReport.getSkillCounter(), skillReport.getAnalyzedVacanciesAmount());
        return new SkillReportTo(format(skillReport.getProfessionName()), format(skillReport.getCity()), skillReport.getDate(),
                skillReport.getAnalyzedVacanciesAmount(), skillReport.getSelection().getDescription(), skillStatistic);
    }

    private static Map<String, Integer> createSkillStatistic(Map<String, Integer> skillCounter, int analyzedVacanciesAmount) {
        Function<Entry<String, Integer>, Integer> statisticMapper =
                entry -> entry.getValue() * ONE_HUNDRED_PERCENT / analyzedVacanciesAmount;
        return skillCounter.entrySet().stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Entry::getKey, statisticMapper, (k, v) -> v, LinkedHashMap::new));
    }

    private static String format(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    }
}