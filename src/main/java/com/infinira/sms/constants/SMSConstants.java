package com.infinira.sms.constants;

public interface SMSConstants {
   
	public static final String SQL_INSERT_STUDENT = "INSERT INTO STUDENT(FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, BLOODGROUP, GENDER, PHONE, EMAIL_ID, FATHER_NAME, FATHER_PHONE, IDENTITY_TYPE, IDENTITY_NUMBER, NATIONALITY, ADDRESS_LINE1, ADDRESS_LINE2, CITY, STATE, COUNTRY, POSTAL_CODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
//  public static final String SQL_CHECK_ID_EXISTENCE = "SELECT STUDENT_ID FROM STUDENT WHERE STUDENT_ID = ?";

    public static final String SQL_UPDATE_STUDENT = "UPDATE STUDENT SET FIRST_NAME=?, MIDDLE_NAME=?, LAST_NAME=?, GENDER = ?, EMAIL_ID= ?, PHONE = ?, ADDRESS_LINE1=?, CITY= ?, STATE = ?,COUNTRY = ?, POSTAL_CODE = ? WHERE STUDENT_ID=?"; 
	
    public static final String SQL_GET_STUDENT_BY_ID = "SELECT STUDENT_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, BLOODGROUP, GENDER, PHONE, EMAIL_ID, FATHER_NAME, FATHER_PHONE, IDENTITY_TYPE, IDENTITY_NUMBER, NATIONALITY, ADDRESS_LINE1, ADDRESS_LINE2, CITY, STATE, COUNTRY, POSTAL_CODE, CREATED_DATE, UPDATED_DATE FROM STUDENT WHERE STUDENT_ID= ?";

    public static final String SQL_GET_ALL_STUDENT = "SELECT STUDENT_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, BLOODGROUP, GENDER, PHONE, EMAIL_ID, FATHER_NAME, FATHER_PHONE, IDENTITY_TYPE, IDENTITY_NUMBER, NATIONALITY, ADDRESS_LINE1, ADDRESS_LINE2, CITY, STATE, COUNTRY, POSTAL_CODE, CREATED_DATE, UPDATED_DATE FROM STUDENT";

    public static final String SQL_DELETE_STUDENT = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";

	public static final String STUDENT_ID = "student_id";
    public static final String FIRST_NAME = "first_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String LAST_NAME = "last_name";
    public static final String DOB = "dob";
    public static final String GENDER = "gender";
    public static final String BLOODGROUP = "bloodGroup";
    public static final String PHONE = "phone";
    public static final String EMAIL_ID = "email_id";
    public static final String FATHER_NAME = "father_name";
    public static final String FATHER_PHONE = "father_phone";
    public static final String IDENTITY_TYPE = "identity_type";
    public static final String IDENTITY_NUMBER = "identity_number";
    public static final String NATIONALITY = "nationality";
    public static final String ADDRESS_LINE1 = "address_line1";
    public static final String ADDRESS_LINE2 = "address_line2";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String COUNTRY = "country";
    public static final String POSTAL_CODE = "postal_code";	
    public static final String CREATED_DATE = "created_date";
    public static final String UPDATED_DATE= "updated_date";
}