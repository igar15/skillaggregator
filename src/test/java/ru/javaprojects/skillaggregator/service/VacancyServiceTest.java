package ru.javaprojects.skillaggregator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static ru.javaprojects.skillaggregator.service.VacancyService.VACANCIES_PAGE_URL_PATTERN;
import static ru.javaprojects.skillaggregator.service.VacancyService.VACANCY_INFO_URL_PATTERN;
import static ru.javaprojects.skillaggregator.testdata.VacancyTestData.*;

@SpringBootTest
@ActiveProfiles("dev")
class VacancyServiceTest {

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void getKeySkills() {
        prepareMockServerForOkResponse(String.format(VACANCY_INFO_URL_PATTERN, VACANCY_ID), VACANCY_INFO_JSON_DATA);
        Set<String> keySkills = vacancyService.getKeySkills(VACANCY_ID);
        mockServer.verify();
        assertEquals(VACANCY_KEY_SKILLS, keySkills);
    }

    @Test
    void getKeySkillsWhenVacancyNotFound() {
        mockServer.expect(ExpectedCount.once(),
                MockRestRequestMatchers.requestTo(String.format(VACANCY_INFO_URL_PATTERN, VACANCY_ID)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND)
                );
        Set<String> keySkills = vacancyService.getKeySkills(VACANCY_ID);
        mockServer.verify();
        assertEquals(Collections.emptySet(), keySkills);
    }

    @Test
    void getKeySkillsWhenServerUnavailable() {
        mockServer.expect(ExpectedCount.once(),
                MockRestRequestMatchers.requestTo(String.format(VACANCY_INFO_URL_PATTERN, VACANCY_ID)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.SERVICE_UNAVAILABLE)
                );
        assertThrows(HttpServerErrorException.class, () -> vacancyService.getKeySkills(VACANCY_ID));
        mockServer.verify();
    }

    @Test
    void getVacancyIdsWhenSelectionEqualsToAvailablePages() {
        prepareMockServerForOkResponse(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, FIRST_PAGE_NUMBER), VACANCIES_PAGE_FIRST_JSON_DATA);
        prepareMockServerForOkResponse(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, SECOND_PAGE_NUMBER), VACANCIES_PAGE_SECOND_JSON_DATA);
        prepareMockServerForOkResponse(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, THIRD_PAGE_NUMBER), VACANCIES_PAGE_THIRD_JSON_DATA);
        Set<String> vacancyIds = vacancyService.getVacancyIds(PROFESSION_NAME, CITY, THREE_PAGES);
        mockServer.verify();
        assertEquals(Set.of(VACANCY_1_ID, VACANCY_2_ID, VACANCY_3_ID, VACANCY_4_ID, VACANCY_5_ID), vacancyIds);
    }

    @Test
    void getVacancyIdsWhenSelectionLessThanAvailablePages() {
        prepareMockServerForOkResponse(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, FIRST_PAGE_NUMBER), VACANCIES_PAGE_FIRST_JSON_DATA);
        Set<String> vacancyIds = vacancyService.getVacancyIds(PROFESSION_NAME, CITY, ONE_PAGE);
        mockServer.verify();
        assertEquals(Set.of(VACANCY_1_ID, VACANCY_2_ID), vacancyIds);
    }

    @Test
    void getVacancyIdsWhenSelectionMoreThanAvailablePages() {
        prepareMockServerForOkResponse(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, FIRST_PAGE_NUMBER), VACANCIES_PAGE_FIRST_JSON_DATA);
        prepareMockServerForOkResponse(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, SECOND_PAGE_NUMBER), VACANCIES_PAGE_SECOND_JSON_DATA);
        prepareMockServerForOkResponse(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, THIRD_PAGE_NUMBER), VACANCIES_PAGE_THIRD_JSON_DATA);
        Set<String> vacancyIds = vacancyService.getVacancyIds(PROFESSION_NAME, CITY, FIVE_PAGES);
        mockServer.verify();
        assertEquals(Set.of(VACANCY_1_ID, VACANCY_2_ID, VACANCY_3_ID, VACANCY_4_ID, VACANCY_5_ID), vacancyIds);
    }

    @Test
    void getVacancyIdsWhenVacanciesNotFound() {
        prepareMockServerForOkResponse(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, FIRST_PAGE_NUMBER), VACANCIES_PAGE_WITHOUT_VACANCIES);
        Set<String> vacancyIds = vacancyService.getVacancyIds(PROFESSION_NAME, CITY, FIVE_PAGES);
        mockServer.verify();
        assertEquals(Collections.emptySet(), vacancyIds);
    }

    @Test
    void getVacancyIdsWhenServerUnavailable() {
        mockServer.expect(ExpectedCount.once(),
                MockRestRequestMatchers.requestTo(String.format(VACANCIES_PAGE_URL_PATTERN, PROFESSION_NAME, CITY, FIRST_PAGE_NUMBER)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.SERVICE_UNAVAILABLE)
                );
        assertThrows(HttpServerErrorException.class, () -> vacancyService.getVacancyIds(PROFESSION_NAME, CITY, FIVE_PAGES));
        mockServer.verify();
    }

    private void prepareMockServerForOkResponse(String uri, String content) {
        mockServer.expect(ExpectedCount.once(),
                MockRestRequestMatchers.requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(content)
                );
    }
}