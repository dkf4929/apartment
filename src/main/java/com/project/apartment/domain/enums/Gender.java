package com.project.apartment.domain.enums;

public enum Gender {
    M("MALE", "남성"), F("FEMALE", "여성");

    private String gender;
    private String name;

    Gender(String gender, String name) {
        this.gender = gender;
        this.name = name;
    }
}
