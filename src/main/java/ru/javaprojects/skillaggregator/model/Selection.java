package ru.javaprojects.skillaggregator.model;

public enum Selection {
    FIRST_100(1, "First 100 vacancies"),
    FIRST_500(5, "First 500 vacancies"),
    FIRST_2000(20, "First 2000 vacancies");

    private int pages;
    private String description;

    Selection(int pages, String description) {
        this.pages = pages;
        this.description = description;
    }

    public int getPageAmount() {
        return pages;
    }

    public String getDescription() {
        return description;
    }
}