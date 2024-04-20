package com.infinira.sms.enums;

public enum Gender {

    MALE("M"), FEMALE("F"), OTHERS("O");

    private String gender;

    Gender(String genderCode) {
        this.gender = genderCode;
    }

    public String getGender() {
        return gender;
    }

    public static Gender getGenderValue(String input) {
        for (Gender gn: Gender.values()) {
            if (gn.getGender().equalsIgnoreCase(input)) {
                return gn;
            } else if (gn.name().equalsIgnoreCase(input)) {
                return gn;
            }
        }
        throw new IllegalArgumentException("Invalid Gender provided: [" + input + "].");
    }
}