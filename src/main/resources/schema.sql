DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS skill_reports;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE skill_reports
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    profession_name VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    analyzed_vacancies_amount INTEGER NOT NULL,
    date DATE DEFAULT now() NOT NULL,
    selection VARCHAR NOT NULL
);
CREATE UNIQUE INDEX skill_reports_unique_profession_name_city_date_selection_idx ON skill_reports (profession_name, city, date, selection);

CREATE TABLE skills
(
    skill_report_id INTEGER NOT NULL,
    skill_name VARCHAR NOT NULL,
    skill_counter INTEGER NOT NULL,
    FOREIGN KEY (skill_report_id) REFERENCES skill_reports (id) ON DELETE CASCADE
);