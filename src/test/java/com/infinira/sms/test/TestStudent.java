package com.infinira.sms.test;

import com.infinira.sms.model.Student;
import com.infinira.sms.enums.Gender;
import com.infinira.sms.enums.BloodGroup;
import com.infinira.sms.util.DBService;

import com.infinira.sms.service.IStudentService;
import com.infinira.sms.service.StudentService;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TestStudent {
	

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        conn = DBService.getInstance().getConnection();
        if (conn.isValid(10)) {
            System.out.println("========Connected to Database=========");
        } else {
            System.out.println("Failed to Connect");
        }
        //System.out.println("========Insert Stduent=========");
        //insertStudent();

		//System.out.println("========Update Stduent=========");
		//updateStudent();
		//System.out.println("========Get Stduent By ID=========");
		//getStudentByID();

        //System.out.println("========Get All Stduent(s=========");
        //getAllStudents();

        System.out.println("========Delete Student=========");
        deleteStudent();
    }

    public static void insertStudent() {
        Student stu = new Student("Neeraj", "Pandey");
        stu.setMiddleName("Kumari");
        stu.setDob(LocalDate.of(2012, 5, 30));
        String specifiedGender = "FeMale";
        stu.setGender(Gender.getGenderValue(specifiedGender));
        stu.setBloodGroup(BloodGroup.getBloodGroupValue("A-"));
        stu.setPhone("+91 7923792232");
        stu.setEmailID("supdey6y@mail.com");
        stu.setFatherName("R P Pandey");
        stu.setFatherPhone("+91 234423423");
        stu.setIdentityType("AADHAR");
        stu.setIdentityNumber("4123 5342 3232");
        stu.setNationality("Indian");
		stu.setAddressLine1("Rampur Castle");
		stu.setAddressLine2("Block 2");
		stu.setCity("HYD");
		stu.setState("AP");
		stu.setCountry("India");
		stu.setPostalCode("541001");

        IStudentService studentService = new StudentService();
        int createdStudent = studentService.insertStudent(stu);

        if (createdStudent == 0) {
            System.out.println("Failed to create: " + stu.getStudentID() + " " + stu.getFirstName() + stu.getLastName());
        } else {
            System.out.println("Successfully created : " + stu.getStudentID() + " " + stu.getFirstName() + " " + stu.getLastName());
        }
    }

    public static void updateStudent() {
        Student stu = new Student("Sarvesh", "Pandey");
		stu.setStudentID(3);
        stu.setMiddleName("Kumar");
        String specifiedGender = "M";
        stu.setGender(Gender.getGenderValue(specifiedGender));
        stu.setEmailID("rey6y@mail.com");
		stu.setPhone("+91 45398910");
		stu.setAddressLine1("Father Street");
		stu.setCity("Chennai");
		stu.setState("TN");
		stu.setCountry("India");
		stu.setPostalCode("621002");
        IStudentService studentService = new StudentService();
        studentService.updateStudent(stu);
    }

    public static void getStudentByID() {
        IStudentService studentService = new StudentService();
        Student getStudentDetail = studentService.getStudentByID(399);
		
        System.out.println("Student ID : " + getStudentDetail.getStudentID());
        System.out.println("Student First Name : " + getStudentDetail.getFirstName());
		
		if(getStudentDetail.getMiddleName()!=null){
			System.out.println("Student Middle Name : " + getStudentDetail.getMiddleName());
		}
		
        System.out.println("Student Last Name :  " + getStudentDetail.getLastName());
        System.out.println("Student DOB : " + getStudentDetail.getDob());
        System.out.println("Student Age : " + getStudentDetail.getAge());
		System.out.println("Student BloodGroup: " + (getStudentDetail.getBloodGroup() != null ? getStudentDetail.getBloodGroup().toString() : "Not Available"));
        System.out.println("Student Gender : " + getStudentDetail.getGender());       
        System.out.println("Student Phone : " + getStudentDetail.getPhone());
        System.out.println("Student Email_ID : " + getStudentDetail.getEmailID());
        System.out.println("Student Father's Name : " + getStudentDetail.getFatherName());
        System.out.println("Student Father Phone : " + getStudentDetail.getFatherPhone());
        System.out.println("Student Idenetity Type : " + getStudentDetail.getIdentityType());
        System.out.println("Student Idenetity Number : " + getStudentDetail.getIdentityNumber());
        System.out.println("Student Nationality : " + getStudentDetail.getNationality());
        
		System.out.println("Student Address : " + getStudentDetail.getAddressLine1() + ", " + (getStudentDetail.getAddressLine2() != null ? getStudentDetail.getAddressLine2() : "") + ", " + getStudentDetail.getCity() + ", " + getStudentDetail.getState() + ", " + getStudentDetail.getCountry() + ", " + getStudentDetail.getPostalCode());
    }

    public static void getAllStudents() {

        IStudentService studentService = new StudentService();
        List<Student> allStudents = studentService.getAllStudents();

        for (Student getStudentDetail: allStudents) {

        System.out.println("Student ID : " + getStudentDetail.getStudentID());
        System.out.println("Student First Name : " + getStudentDetail.getFirstName());
		
		if(getStudentDetail.getMiddleName()!=null){
			System.out.println("Student Middle Name : " + getStudentDetail.getMiddleName());
		}
		
        System.out.println("Student Last Name :  " + getStudentDetail.getLastName());
        System.out.println("Student DOB : " + getStudentDetail.getDob());
        System.out.println("Student Age : " + getStudentDetail.getAge());
		System.out.println("Student BloodGroup: " + (getStudentDetail.getBloodGroup() != null ? getStudentDetail.getBloodGroup().toString() : "Not Available"));
        System.out.println("Student Gender : " + getStudentDetail.getGender());       
        System.out.println("Student Phone : " + getStudentDetail.getPhone());
        System.out.println("Student Email_ID : " + getStudentDetail.getEmailID());
        System.out.println("Student Father's Name : " + getStudentDetail.getFatherName());
        System.out.println("Student Father Phone : " + getStudentDetail.getFatherPhone());
        System.out.println("Student Idenetity Type : " + getStudentDetail.getIdentityType());
        System.out.println("Student Idenetity Number : " + getStudentDetail.getIdentityNumber());
        System.out.println("Student Nationality : " + getStudentDetail.getNationality());
        
		System.out.println("Student Address : " + getStudentDetail.getAddressLine1() + ", " + (getStudentDetail.getAddressLine2() != null ? getStudentDetail.getAddressLine2() : "") + ", " + getStudentDetail.getCity() + ", " + getStudentDetail.getState() + ", " + getStudentDetail.getCountry() + ", " + getStudentDetail.getPostalCode()+ "\n");
        }
    }

    public static void deleteStudent() {
        IStudentService studentService = new StudentService();
        int deletedStudent = studentService.deleteStudent(12);
    }
}