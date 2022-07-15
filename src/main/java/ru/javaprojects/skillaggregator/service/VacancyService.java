package ru.javaprojects.skillaggregator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VacancyService {
    public static final String VACANCIES_PAGE_URL_PATTERN = "https://api.hh.ru/vacancies?text=%s+%s&per_page=100&search_field=name&page=%d";
    public static final String VACANCY_INFO_URL_PATTERN = "https://api.hh.ru/vacancies/%s";
    public static final int FIRST_PAGE = 0;

    private RestTemplate restTemplate;

    public VacancyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Set<String> getVacancyIds(String professionName, String city, int pageAmount) {
        VacanciesPage vacanciesPage = restTemplate.getForObject(String.format(VACANCIES_PAGE_URL_PATTERN, professionName, city, FIRST_PAGE), VacanciesPage.class);
        Set<String> vacancyIds = new HashSet<>(vacanciesPage.getVacanciesId());
        pageAmount = Math.min(pageAmount, vacanciesPage.pages);
        for (int i = 1; i < pageAmount; i++) {
            vacanciesPage = restTemplate.getForObject(String.format(VACANCIES_PAGE_URL_PATTERN, professionName, city, i), VacanciesPage.class);
            vacancyIds.addAll(vacanciesPage.getVacanciesId());
        }
        return vacancyIds;
    }

     private static class VacanciesPage {
        private int pages;
        private VacancyId[] items;

        private static class VacancyId {
            private String id;

            public void setId(String id) {
                this.id = id;
            }
        }

         public Set<String> getVacanciesId() {
             return Arrays.stream(items)
                     .map(vacancyId -> vacancyId.id)
                     .collect(Collectors.toSet());
         }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setItems(VacancyId[] items) {
            this.items = items;
        }
    }

    public Set<String> getKeySkills(String vacancyId) {
        try {
            VacancyInfo vacancyInfo = restTemplate.getForObject(String.format(VACANCY_INFO_URL_PATTERN, vacancyId), VacancyInfo.class);
            return vacancyInfo.getKeySkills();
        } catch (HttpClientErrorException.NotFound e) {
            return Collections.emptySet();
        }
    }

    private static class VacancyInfo {
        private KeySkill[] key_skills;

        private static class KeySkill {
            private String name;

            public void setName(String name) {
                this.name = name;
            }
        }

        public Set<String> getKeySkills() {
            return Arrays.stream(key_skills)
                    .map(keySkill -> keySkill.name.toUpperCase())
                    .collect(Collectors.toSet());
        }

        public void setKey_skills(KeySkill[] key_skills) {
            this.key_skills = key_skills;
        }
    }
}
