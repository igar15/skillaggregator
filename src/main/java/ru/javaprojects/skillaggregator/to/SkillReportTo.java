package ru.javaprojects.skillaggregator.to;


import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class SkillReportTo {
    private String professionName;
    private String city;
    private LocalDate date;
    private int analyzedVacanciesAmount;
    private String selection;
    private Map<String, Integer> skillStatistic;

    public SkillReportTo(String professionName, String city, LocalDate date, int analyzedVacanciesAmount,
                         String selection, Map<String, Integer> skillStatistic) {
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

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public Map<String, Integer> getSkillStatistic() {
        return skillStatistic;
    }

    public void setSkillStatistic(Map<String, Integer> skillStatistic) {
        this.skillStatistic = skillStatistic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillReportTo that = (SkillReportTo) o;
        return analyzedVacanciesAmount == that.analyzedVacanciesAmount &&
                Objects.equals(professionName, that.professionName) &&
                Objects.equals(city, that.city) &&
                Objects.equals(date, that.date) &&
                Objects.equals(selection, that.selection) &&
                Objects.equals(skillStatistic, that.skillStatistic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professionName, city, date, analyzedVacanciesAmount, selection, skillStatistic);
    }
}