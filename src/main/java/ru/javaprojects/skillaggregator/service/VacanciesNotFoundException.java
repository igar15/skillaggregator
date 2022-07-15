package ru.javaprojects.skillaggregator.service;

public class VacanciesNotFoundException extends RuntimeException {
    public VacanciesNotFoundException(String message) {
        super(message);
    }
}