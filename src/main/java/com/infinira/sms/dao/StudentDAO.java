package com.infinira.sms.dao;

import com.infinira.sms.model.Student;
import com.infinira.sms.util.DBService;
import com.infinira.sms.util.LoggerService;
import com.infinira.sms.util.SMSUtil;
import static com.infinira.sms.constants.SMSConstants.*;
import com.infinira.sms.enums.Gender;
import com.infinira.sms.enums.BloodGroup;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap; 

public class StudentDAO {

    private static DBService dbService = DBService.getInstance();
	private static SMSUtil Util =  SMSUtil.getInstance();
    private static LoggerService logger = LoggerService.getInstance();

    public static int insertStudent(Student student) {
		
		PreparedStatement ps = null;
        ResultSet rs = null;
        int studentID = 0;
        Connection conn = null;
        try {
			conn = dbService.getConnection();
            ps = conn.prepareStatement(SQL_INSERT_STUDENT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getMiddleName());
            ps.setString(3, student.getLastName());
            ps.setDate(4, java.sql.Date.valueOf(student.getDob()));
			ps.setObject(5, student.getBloodGroup(), Types.OTHER);
            ps.setObject(6, student.getGender(), Types.OTHER);
            ps.setString(7, student.getPhone());
            ps.setString(8, student.getEmailID());
            ps.setString(9, student.getFatherName());
            ps.setString(10, student.getFatherPhone());
            ps.setString(11, student.getIdentityType());
            ps.setString(12, student.getIdentityNumber());
            ps.setString(13, student.getNationality());
            ps.setString(14, student.getAddressLine1());
            ps.setString(15, student.getAddressLine2());
            ps.setString(16, student.getCity());
            ps.setString(17, student.getState());
            ps.setString(18, student.getCountry());
            ps.setString(19, student.getPostalCode());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                studentID = rs.getInt(1);
                student.setStudentID(studentID);
            };
        } catch (SQLException ex) {	
			throw Util.getException("SMS_001",ex,true,student.getFirstName(),student.getLastName());			
        } finally {
            DBService.closeResources(rs, ps, conn);
        }
        return studentID;
    }
	
	public static void updateStudent(Student student) {

        PreparedStatement ps = null;
        Connection conn = null;
		int updatedStatus = 0;
        try {
			conn = dbService.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE_STUDENT);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getMiddleName());
            ps.setString(3, student.getLastName());
            ps.setObject(4, student.getGender(), Types.OTHER);
            ps.setString(5, student.getEmailID());
            ps.setString(6, student.getPhone());
			ps.setString(7, student.getAddressLine1());
            ps.setString(8, student.getCity());
            ps.setString(9, student.getState());
            ps.setString(10, student.getCountry());
            ps.setString(11, student.getPostalCode());
            ps.setInt(12, student.getStudentID());
            updatedStatus = ps.executeUpdate();			
			if(updatedStatus == 0) {
				Util.getException("SMS_002",null,true,student.getStudentID());
			} 
        } catch (SQLException ex) {
			throw Util.getException("SMS_003",ex,true,student.getFirstName(),student.getLastName(),
				student.getStudentID());
        } finally {
            DBService.closeResources(null, ps, conn);
        }
    }

    public static Student getStudentByID(int studentID) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        Student student = null;
        try {
			conn = dbService.getConnection();
            ps = conn.prepareStatement(SQL_GET_STUDENT_BY_ID);
            ps.setInt(1, studentID);
            rs = ps.executeQuery();
			
            if (!rs.next()) {
				Util.getException("SMS_004",null,true,studentID);
			} else {
                student = new Student(rs.getString(FIRST_NAME), rs.getString(LAST_NAME));
                student.setStudentID(rs.getInt(STUDENT_ID));
                student.setMiddleName(rs.getString(MIDDLE_NAME));
                student.setDob(LocalDate.parse(rs.getString(DOB)));
				String bloodGroupString = rs.getString(BLOODGROUP);		
				
                if (bloodGroupString != null && !bloodGroupString.isEmpty()) {
                    student.setBloodGroup(BloodGroup.getBloodGroupValue(bloodGroupString));
                };
				
                student.setGender(Gender.getGenderValue(rs.getString(GENDER)));
                student.setPhone(rs.getString(PHONE));
                student.setEmailID(rs.getString(EMAIL_ID));
                student.setFatherName(rs.getString(FATHER_NAME));
                student.setFatherPhone(rs.getString(FATHER_PHONE));
                student.setIdentityType(rs.getString(IDENTITY_TYPE));
                student.setIdentityNumber(rs.getString(IDENTITY_NUMBER));
                student.setNationality(rs.getString(NATIONALITY));
                student.setAddressLine1(rs.getString(ADDRESS_LINE1));
                student.setAddressLine2(rs.getString(ADDRESS_LINE2));
                student.setCity(rs.getString(CITY));
                student.setState(rs.getString(STATE));
                student.setCountry(rs.getString(COUNTRY));
                student.setPostalCode(rs.getString(POSTAL_CODE)); 				
				java.sql.Timestamp createdDate = rs.getTimestamp(CREATED_DATE);
				java.sql.Timestamp updatedDate = rs.getTimestamp(UPDATED_DATE);
				student.setCreatedDate(createdDate);
				student.setUpdatedDate(updatedDate);	               
            }
        } catch (SQLException ex) {
			throw Util.getException("SMS_005",ex,true,student.getStudentID());
		} finally {
            DBService.closeResources(rs, ps, conn);
        }
        return student;
    }

    public static int deleteStudent(int studentID) {

        PreparedStatement ps = null;
        Connection conn = null;
        int deletedStudent = 0;
        try {
			conn = dbService.getConnection();
            ps = conn.prepareStatement(SQL_DELETE_STUDENT);
            ps.setInt(1, studentID);
            deletedStudent = ps.executeUpdate();
			if(deletedStudent == 0) {
				Util.getException("SMS_006",null,true,studentID);
			}
        } catch (SQLException ex) {
			throw Util.getException("SMS_007",ex,true,studentID);
        } finally {
            DBService.closeResources(null, ps, conn);
        }
		//Util.info("SMS_008", studentID);
        return studentID;
    }

    public static List <Student> getAllStudent() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        List <Student> allStudents = new ArrayList <> ();
        Student student = null;
        try {
			conn = dbService.getConnection();
            ps = conn.prepareStatement(SQL_GET_ALL_STUDENT);
            rs = ps.executeQuery();
            while (rs.next()) {
                student = new Student(rs.getString(FIRST_NAME), rs.getString(LAST_NAME));
                student.setStudentID(rs.getInt(STUDENT_ID));
                student.setMiddleName(rs.getString(MIDDLE_NAME));
                student.setDob(LocalDate.parse(rs.getString(DOB)));
				String bloodGroupString = rs.getString(BLOODGROUP);		
				
                if (bloodGroupString != null && !bloodGroupString.isEmpty()) {
                    student.setBloodGroup(BloodGroup.getBloodGroupValue(bloodGroupString));
                };
				
                student.setGender(Gender.getGenderValue(rs.getString(GENDER)));
                student.setPhone(rs.getString(PHONE));
                student.setEmailID(rs.getString(EMAIL_ID));
                student.setFatherName(rs.getString(FATHER_NAME));
                student.setFatherPhone(rs.getString(FATHER_PHONE));
                student.setIdentityType(rs.getString(IDENTITY_TYPE));
                student.setIdentityNumber(rs.getString(IDENTITY_NUMBER));
                student.setNationality(rs.getString(NATIONALITY));
                student.setAddressLine1(rs.getString(ADDRESS_LINE1));
                student.setAddressLine2(rs.getString(ADDRESS_LINE2));
                student.setCity(rs.getString(CITY));
                student.setState(rs.getString(STATE));
                student.setCountry(rs.getString(COUNTRY));
                student.setPostalCode(rs.getString(POSTAL_CODE));				
				java.sql.Timestamp createdDate = rs.getTimestamp(CREATED_DATE);
				java.sql.Timestamp updatedDate = rs.getTimestamp(UPDATED_DATE);
				student.setCreatedDate(createdDate);
				student.setUpdatedDate(updatedDate);	
				
				allStudents.add(student);
            }
        } catch (SQLException ex) {
			throw Util.getException("SMS_009",ex,true);
        } finally {
            DBService.closeResources(rs, ps, conn);
        }
        return allStudents;
    }
}