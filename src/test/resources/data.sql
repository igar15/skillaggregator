DELETE FROM skills;
DELETE FROM skill_reports;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO skill_reports (profession_name, city, analyzed_vacancies_amount, selection)
VALUES ('JAVA', 'MOSCOW', 100, 'FIRST_100_VACANCIES'),
       ('SELLER', 'LONDON', 100, 'FIRST_100_VACANCIES');

INSERT INTO skills (skill_report_id, skill_name, skill_counter)
VALUES (100000, 'JAVA', 100),
       (100000, 'SPRING', 90),
       (100000, 'HIBERNATE', 70),
       (100000, 'SQL', 50),
       (100000, 'ENGLISH', 25),
       (100000, 'DOCKER', 10),
       (100001, 'SALES', 80),
       (100001, 'POLITENESS', 70),
       (100001, 'ARITHMETIC', 20);