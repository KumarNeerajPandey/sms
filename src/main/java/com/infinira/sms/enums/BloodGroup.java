package com.infinira.sms.enums;

public enum BloodGroup {
	A_POSITIVE("A+"),
	B_POSITIVE("B+"),
	O_POSITIVE("O+"),
	AB_POSITIVE("AB+"),
	A_NEGATIVE("A-"),
	B_NEGATIVE("B-"),
	O_NEGATIVE("O-"),
	AB_NEGATIVE("AB-");

    private String bloodGroup;

    BloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public static BloodGroup getBloodGroupValue(String input) {
        for (BloodGroup bg: BloodGroup.values()) {
            if (bg.getBloodGroup().equalsIgnoreCase(input)) {
                return bg;
            } else if (bg.name().equalsIgnoreCase(input)) {
                return bg;
            }
        }
        throw new IllegalArgumentException("Invalid BloodGroup provided: [" + input + "]."); 
    }
}