package com.infinira.sms.service;

import com.infinira.sms.model.Student;
import com.infinira.sms.service.IStudentService;
import com.infinira.sms.dao.StudentDAO;
import java.util.Map;
import java.util.List;

public class StudentService implements IStudentService {

    @Override
    public int insertStudent(Student student) {
        return StudentDAO.insertStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        StudentDAO.updateStudent(student);
    }

    @Override
    public Student getStudentByID(int studentID) {
        return StudentDAO.getStudentByID(studentID);
    }

    @Override
    public List<Student> getAllStudents() {
        return StudentDAO.getAllStudent();
    }

    @Override
    public int deleteStudent(int studentID) {
        return StudentDAO.deleteStudent(studentID);
    }
}