package ru.javaprojects.skillaggregator.model;

public enum Selection {
    FIRST_100_VACANCIES(1),
    FIRST_500_VACANCIES(5),
    FIRST_2000_VACANCIES(20);

    private int pages;

    Selection(int pages) {
        this.pages = pages;
    }

    public int getPageAmount() {
        return pages;
    }
}