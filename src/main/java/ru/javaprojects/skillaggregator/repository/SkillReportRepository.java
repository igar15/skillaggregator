package ru.javaprojects.skillaggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaprojects.skillaggregator.model.Selection;
import ru.javaprojects.skillaggregator.model.SkillReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SkillReportRepository extends JpaRepository<SkillReport, Integer> {

    Optional<SkillReport> findByProfessionNameAndCityAndDateAndSelection(String professionName, String city,
                                                                         LocalDate date, Selection selection);

    List<SkillReport> findAllByDate(LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM SkillReport s WHERE s.date=:date")
    void deleteAllByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM SkillReport s WHERE s.professionName=:professionName AND s.city=:city AND s.date=:date AND s.selection=:selection")
    void deleteByNameAndCityAndDateAndSelection(@Param("professionName") String professionName, @Param("city") String city,
                                                @Param("date") LocalDate date, @Param("selection") Selection selection);
}
