package ru.javaprojects.skillaggregator.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.javaprojects.skillaggregator.service.SkillReportService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javaprojects.skillaggregator.model.Selection.FIRST_100;
import static ru.javaprojects.skillaggregator.testdata.SkillReportTestData.*;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class SkillReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillReportService service;

    @Test
    void showHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getSkillReport() throws Exception {
        Mockito.when(service.getSkillReportForToday(JAVA_PROFESSION_NAME, MOSCOW_CITY, FIRST_100))
                .thenReturn(javaSkillReport);
        mockMvc.perform(get("/makeSkillReport")
                .param(PROFESSION_NAME_PARAM, JAVA_PROFESSION_NAME)
                .param(CITY_PARAM, MOSCOW_CITY)
                .param(SELECTION_PARAM, FIRST_100.name()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("skillReportTo", javaSkillReportTo))
                .andExpect(view().name("skillReport"));
    }

    @Test
    void getSkillReportWhenProfessionNameIsBlank() throws Exception {
        mockMvc.perform(get("/makeSkillReport")
                .param(PROFESSION_NAME_PARAM, "")
                .param(CITY_PARAM, MOSCOW_CITY)
                .param(SELECTION_PARAM, FIRST_100.name()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("professionName", ""))
                .andExpect(model().attribute("city", MOSCOW_CITY))
                .andExpect(view().name("noVacancies"));
    }

    @Test
    void getSkillReportWhenVacanciesNotFound() throws Exception {
        Mockito.when(service.getSkillReportForToday(NOT_EXISTED_PROFESSION_NAME, MOSCOW_CITY, FIRST_100))
                .thenReturn(emptySkillReport);
        mockMvc.perform(get("/makeSkillReport")
                .param(PROFESSION_NAME_PARAM, NOT_EXISTED_PROFESSION_NAME)
                .param(CITY_PARAM, MOSCOW_CITY)
                .param(SELECTION_PARAM, FIRST_100.name()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("professionName", NOT_EXISTED_PROFESSION_NAME))
                .andExpect(model().attribute("city", MOSCOW_CITY))
                .andExpect(view().name("noVacancies"));
    }

    @Test
    void notFoundRequest() throws Exception {
        mockMvc.perform(get("/xyz"))
                .andExpect(status().isNotFound());
    }
}