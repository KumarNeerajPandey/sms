package com.infinira.sms.model;

import com.infinira.sms.enums.Gender;
import com.infinira.sms.enums.BloodGroup;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.DateTimeException;
import java.util.List;
import java.text.MessageFormat;

public class Student {

    private int studentID;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private int age;
    private Gender gender;
    private BloodGroup bloodGroup;
    private String phone;
    private String emailID;
    private String fatherName;
    private String fatherPhone;
    private String identityType;
    private String identityNumber;
    private String nationality;
	private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public Student(String firstName, String lastName) {
        validate(FIRST_NAME, firstName, NAME_LENGTH);
        validate(LAST_NAME, lastName, NAME_LENGTH);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        validate(STUDENT_ID, studentID, 1);
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validate(FIRST_NAME, firstName, NAME_LENGTH);
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (middleName != null && middleName.length() > NAME_LENGTH) {
            throw new RuntimeException("Middle Name should not be more than " + NAME_LENGTH + ".");
        }
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validate(LAST_NAME, lastName, NAME_LENGTH);
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        validate(DOB, dob);
        this.dob = dob;
    }

    public int getAge() {
        this.age = calculateAge(dob);
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        validate(GENDER, gender);
        this.gender = gender;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        validate(BLOODGROUP, bloodGroup);
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validate(PHONE, phone, PHN_NUM_LENGTH);
        this.phone = phone;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        validate(EMAIL_ID, emailID, EMAIL_ID_LENGTH);
        this.emailID = emailID;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        validate(FATHER_NAME, fatherName, FATHER_NAME_LENGTH);
        this.fatherName = fatherName;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        validate(FATHER_PHONE, fatherPhone, PHN_NUM_LENGTH);
        this.fatherPhone = fatherPhone;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        validate(IDENTITY_TYPE, identityType, IDENTITY_LENGTH);
        this.identityType = identityType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        validate(IDENTITY_NUMBER, identityNumber, IDENTITY_NUM_LENGTH);
        this.identityNumber = identityNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        validate(NATIONALITY, nationality, NATIONALITY_LENGTH);
        this.nationality = nationality;
    }
	
	public String getAddressLine1() {
        return addressLine1;
    }
	
	public void setAddressLine1(String addrsLine1) {
        validate(ADDRS_LINE1, addrsLine1, ADDRS_LINE_LENGTH);
        this.addressLine1 = addrsLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addrsLine2) {
        if (addrsLine2 != null && addrsLine2.length() > ADDRS_LINE_LENGTH) {
            throw new RuntimeException("Address Line2 should not be more than " + ADDRS_LINE_LENGTH + ".");
        }
        this.addressLine2 = addrsLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        validate(CITY, city, LENGTH);
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        validate(STATE, state, LENGTH);
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        validate(COUNTRY, country, LENGTH);
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        validate(POSTAL_CODE, postalCode, POSTAL_LENGTH);
        this.postalCode = postalCode;
    }

	    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }
	
    @Override
    public String toString() {
        return studentID + "," + firstName + "," + middleName + "," + lastName + "," + dob + "," + age + "," + gender + "," + bloodGroup + "," + phone + "," + emailID + "," + fatherName + "," + fatherPhone + "," + identityType + "," + identityNumber + "," + addressLine1 +"," + addressLine2 + "," + city + "," + state + "," + country + "," + postalCode + "\n" ;
    }


    private int calculateAge(LocalDate dob) {
        int age = Period.between(dob, LocalDate.now()).getYears();
        return age;
    }
	
    private void validate(String paramName, Object paramValue, Object... args) {
        if (paramValue == null) {
            throw new RuntimeException(MessageFormat.format(MSG_001, paramName));
        }

        if (paramValue instanceof String value) {
            if (value.isBlank()) {
                throw new RuntimeException(MessageFormat.format(MSG_002, paramName));
            }
            if (args.length > 0) {
                if (args[0] instanceof Integer maxLength) {
                    if (value.length() > maxLength) {
                        throw new RuntimeException(MessageFormat.format(MSG_003, paramName, maxLength));
                    }
                }
            }
        } else if (paramValue instanceof Integer value) {
            if (args.length == 1) {
                if (args[0] instanceof Integer minValue) {
                    if (value < minValue) {
                        throw new RuntimeException(MessageFormat.format(MSG_004, paramName, minValue));
                    }
                }
            } else if (args.length == 2) {
                if (args[0] instanceof Integer minValue && args[1] instanceof Integer maxValue) {
                    if (value < minValue || value > maxValue) {
                        throw new RuntimeException(MessageFormat.format(MSG_005, paramName, minValue, maxValue));
                    }
                }
            }
        } else if (paramValue instanceof LocalDate date) {
            if (date.isAfter(LocalDate.now())) {
                throw new RuntimeException(MessageFormat.format(MSG_006, paramName));
            }
        }
    }

    public static final String STUDENT_ID = "Student ID";
    public static final String FIRST_NAME = "First Name";
    public static final String LAST_NAME = "Last Name";
    public static final String DOB = "DOB";
    public static final String GENDER = "Gender";
    public static final String BLOODGROUP = "BloodGroup";
    public static final String EMAIL_ID = "Email ID";
    public static final String PHONE = "Phone";
    public static final String FATHER_NAME = "Father Name";
    public static final String FATHER_PHONE = "Father Phone";
    public static final String IDENTITY_TYPE = "Identity Type";
    public static final String IDENTITY_NUMBER = "Identity Number";
    public static final String NATIONALITY = "Nationality";
    public static final String ADDRS_LINE1 = "Address Line1";
    public static final String CITY = "City";
    public static final String STATE = "State";
    public static final String COUNTRY = "Country";
    public static final String POSTAL_CODE = "Postal Code";

    public static final int NAME_LENGTH = 64;
    public static final int PHN_NUM_LENGTH = 20;
    public static final int EMAIL_ID_LENGTH = 254;
    public static final int FATHER_NAME_LENGTH = 128;
    public static final int IDENTITY_LENGTH = 64;
    public static final int IDENTITY_NUM_LENGTH = 20;
    public static final int NATIONALITY_LENGTH = 64;
	public static final int ADDRS_LINE_LENGTH = 64;
    public static final int LENGTH = 55;
    public static final int POSTAL_LENGTH = 10;

    public static final String MSG_001 = "{0} can not be null.";
    public static final String MSG_002 = "{0} can not be Empty.";
    public static final String MSG_003 = "{0} can not contain more than {1} chars.";
    public static final String MSG_004 = "{0} should not be less than {1}.";
    public static final String MSG_005 = "{0} should not be less than {1} or more than {2}.";
    public static final String MSG_006 = "{0} should not be after the current date.";
}