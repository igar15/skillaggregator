package ru.javaprojects.skillaggregator.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaprojects.skillaggregator.testdata.SkillReportTestData.javaSkillReport;
import static ru.javaprojects.skillaggregator.testdata.SkillReportTestData.javaSkillReportTo;

class SkillReportUtilTest {

    @Test
    void createTo() {
        assertThat(SkillReportUtil.createTo(javaSkillReport)).usingRecursiveComparison().isEqualTo(javaSkillReportTo);
    }
}