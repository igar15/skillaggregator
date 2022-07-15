package ru.javaprojects.skillaggregator.model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import static ru.javaprojects.skillaggregator.model.Selection.FIRST_100_VACANCIES;

@Entity
@Table(name = "skill_reports", uniqueConstraints = {@UniqueConstraint(columnNames = {"profession_name", "city", "date",
        "selection"}, name = "skill_reports_unique_profession_name_city_date_selection_idx")})
@Access(AccessType.FIELD)
public class SkillReport implements Serializable {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "profession_name")
    private String professionName;

    @Column(name = "city")
    private String city;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Column(name = "analyzed_vacancies_amount")
    private int analyzedVacanciesAmount;

    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @CollectionTable(name = "skills",
            joinColumns = {@JoinColumn(name = "skill_report_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "skill_name")
    @Column(name = "skill_counter")
    private Map<String, Integer> skillCounter;

    @Column(name = "selection")
    @Enumerated(EnumType.STRING)
    private Selection selection = FIRST_100_VACANCIES;

    public SkillReport() {
    }

    public SkillReport(String professionName, String city, int analyzedVacanciesAmount, Map<String, Integer> skillCounter,
                       Selection selection) {
        this(null, professionName, city, LocalDate.now(), analyzedVacanciesAmount, skillCounter, selection);
    }

    public SkillReport(Integer id, String professionName, String city, LocalDate date, int analyzedVacanciesAmount,
                       Map<String, Integer> skillCounter, Selection selection) {
        this.id = id;
        this.professionName = professionName;
        this.city = city;
        this.date = date;
        this.analyzedVacanciesAmount = analyzedVacanciesAmount;
        this.skillCounter = skillCounter;
        this.selection = selection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String name) {
        this.professionName = name;
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

    public void setAnalyzedVacanciesAmount(int vacanciesAmount) {
        this.analyzedVacanciesAmount = vacanciesAmount;
    }

    public Map<String, Integer> getSkillCounter() {
        return skillCounter;
    }

    public void setSkillCounter(Map<String, Integer> skillCounter) {
        this.skillCounter = skillCounter;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        SkillReport that = (SkillReport) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}