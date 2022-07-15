package ru.javaprojects.skillaggregator.to;


import ru.javaprojects.skillaggregator.model.Selection;

import java.time.LocalDate;
import java.util.Map;

public class SkillReportTo {
    private String professionName;
    private String city;
    private LocalDate date;
    private int analyzedVacanciesAmount;
    private Selection selection;
    private Map<String, Integer> skillStatistic;

    public SkillReportTo(String professionName, String city, LocalDate date, int analyzedVacanciesAmount,
                         Selection selection, Map<String, Integer> skillStatistic) {
        this.professionName = professionName;
        this.city = city;
        this.date = date;
        this.analyzedVacanciesAmount = analyzedVacanciesAmount;
        this.selection = selection;
        this.skillStatistic = skillStatistic;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAnalyzedVacanciesAmount() {
        return analyzedVacanciesAmount;
    }

    public void setAnalyzedVacanciesAmount(int analyzedVacanciesAmount) {
        this.analyzedVacanciesAmount = analyzedVacanciesAmount;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public Map<String, Integer> getSkillStatistic() {
        return skillStatistic;
    }

    public void setSkillStatistic(Map<String, Integer> skillStatistic) {
        this.skillStatistic = skillStatistic;
    }
}