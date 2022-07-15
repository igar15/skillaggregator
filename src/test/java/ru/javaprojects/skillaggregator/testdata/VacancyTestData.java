package ru.javaprojects.skillaggregator.testdata;

import java.util.Set;

public class VacancyTestData {

    public static final String VACANCY_ID = "111";
    public static final String VACANCY_INFO_JSON_DATA =
            "{ \"id\":" + "\"" + VACANCY_ID + "\"," +
            "  \"premium\": \"false\"," +
            "  \"key_skills\": [\n" +
            "        {\n" +
            "            \"name\": \"Java\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"SQL\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Spring\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"Hibernate\"\n" +
            "        }]\n" +
            "}";
    public static final Set<String> VACANCY_KEY_SKILLS = Set.of("JAVA", "SQL", "SPRING", "HIBERNATE");

    public static final String PROFESSION_NAME = "worker";
    public static final String CITY = "moscow";
    public static final int FIRST_PAGE = 0;
    public static final int SECOND_PAGE = 1;
    public static final int THIRD_PAGE = 2;
    public static final int ONE_PAGE_AMOUNT = 1;
    public static final int THREE_PAGE_AMOUNT = 3;
    public static final int FIVE_PAGE_AMOUNT = 5;
    public static final String VACANCY_1_ID = "0001";
    public static final String VACANCY_2_ID = "0002";
    public static final String VACANCY_3_ID = "0003";
    public static final String VACANCY_4_ID = "0004";
    public static final String VACANCY_5_ID = "0005";
    public static final String VACANCIES_PAGE_FIRST_JSON_DATA =
            "{ \"found\": \"5\"," +
            "  \"pages\": \"3\"," +
            "  \"page\": \"0\"," +
            "  \"per_page\": \"2\"," +
            "  \"items\": [\n" +
            "        {\n" +
            "            \"id\": \"" + VACANCY_1_ID + "\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"" + VACANCY_2_ID + "\"\n" +
            "        }]\n" +
            "}";
    public static final String VACANCIES_PAGE_SECOND_JSON_DATA =
            "{ \"found\": \"5\"," +
            "  \"pages\": \"3\"," +
            "  \"page\": \"1\"," +
            "  \"per_page\": \"2\"," +
            "  \"items\": [\n" +
            "        {\n" +
            "            \"id\": \"" + VACANCY_3_ID + "\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"" + VACANCY_4_ID + "\"\n" +
            "        }]\n" +
            "}";
    public static final String VACANCIES_PAGE_THIRD_JSON_DATA =
            "{ \"found\": \"5\"," +
            "  \"pages\": \"3\"," +
            "  \"page\": \"2\"," +
            "  \"per_page\": \"2\"," +
            "  \"items\": [\n" +
            "        {\n" +
            "            \"id\": \"" + VACANCY_5_ID + "\"\n" +
            "        }]\n" +
            "}";
    public static final String VACANCIES_PAGE_WITHOUT_VACANCIES =
            "{ \"found\": \"0\"," +
            "  \"pages\": \"1\"," +
            "  \"page\": \"0\"," +
            "  \"per_page\": \"100\"," +
            "  \"items\": []\n" +
            "}";
}
