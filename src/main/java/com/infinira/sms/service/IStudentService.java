package com.infinira.sms.service;

import com.infinira.sms.model.Student;
import java.util.Map;
import java.util.List;

public interface IStudentService {

    int insertStudent(Student student);
    void updateStudent(Student student);
    Student getStudentByID(int studentID);
    List<Student> getAllStudents();
    int deleteStudent(int studentID);
}