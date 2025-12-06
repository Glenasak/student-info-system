package com.cjlu.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cjlu.entity.Student;
import com.cjlu.service.StudentService;

import org.slf4j.*;
import com.cjlu.dao.impl.StudentDaoImpl;

public class StudentServiceImpl implements StudentService {

    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    // Data access object
    private StudentDaoImpl studentDao = new StudentDaoImpl();

    // Add student
    @Override
    public void addStudent(Student student) {
        try {
            // Ensure the student table exists
            if (!studentDao.isStudentTableExists()) {
                studentDao.createStudentTable();
                logger.info("Student table did not exist; created table automatically.");
            }
            studentDao.addStudent(student);
            logger.info("Student created successfully: {}", student);
        } catch (Exception e) {
            logger.error("Failed to create student: {}", student, e);
            throw e;
        }
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        try {
            studentDao.deleteStudent(studentId);
            logger.info("Student deleted successfully: {}", studentId);
        } catch (Exception e) {
            logger.error("Failed to delete student: {}", studentId, e);
            throw e;
        }
    }

    @Override
    public void updateStudentById(Student student) {
        try {
            studentDao.updateStudent(student);
            logger.info("Student updated successfully: {}", student);
        } catch (Exception e) {
            logger.error("Failed to update student: {}", student, e);
            throw e;
        }
    }

    @Override
    public Student getStudentById(Integer studentId) {
        try {
            Student student = studentDao.getStudentById(studentId);
            logger.info("Student retrieved successfully: {}", studentId);
            return student;
        } catch (Exception e) {
            logger.error("Failed to retrieve student: {}", studentId, e);
            throw e;
        }
    }

    @Override
    public Student getStudentByName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByName'");
    }

    @Override
    public List<Student> getAllStudents() {
       try {
            // Ensure the student table exists
            if (!studentDao.isStudentTableExists()) {
                studentDao.createStudentTable();
                logger.info("Student table did not exist; created table automatically.");
            }
            List<Student> students = studentDao.getAllStudents();
            logger.info("Retrieved all students successfully.");
            return students;
        } catch (Exception e) {
            logger.error("Failed to retrieve all students", e);
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByMajor(String major) {
        try {
            List<Student> students = studentDao.findStudentsByMajor(major);
            logger.info("Students retrieved successfully by major: {}", major);
            return students;
        } catch (Exception e) {
            logger.error("Failed to retrieve students by major: {}", major, e);
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByClassName(String className) {
        try {
            List<Student> students = studentDao.findStudentsByClass(className);
            logger.info("Students retrieved successfully by class: {}", className);
            return students;
        } catch (Exception e) {
            logger.error("Failed to retrieve students by class: {}", className, e);
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByAgeRange(int minAge, int maxAge) {
        try {
            List<Student> students = studentDao.findStudentsByAgeRange(minAge, maxAge);
            logger.info("Students retrieved successfully by age range: {} - {}", minAge, maxAge);
            return students;
        } catch (Exception e) {
            logger.error("Failed to retrieve students by age range: {} - {}", minAge, maxAge, e);
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByAdmissionDateRange(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByAdmissionDateRange'");
    }

    @Override
    public Student getStudentByPhone(String phone) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByPhone'");
    }

    @Override
    public Student getStudentByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByEmail'");
    }

    @Override
    public int getStudentCount() {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentCount'");
    }

    @Override
    public List<Student> getStudentsByPage(int pageNumber, int pageSize) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByPage'");
    }

    @Override
    public Map<String, Integer> getStudentCountByMajor() {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentCountByMajor'");
    }

    @Override
    public Map<String, Integer> getStudentCountByClassName() {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentCountByClassName'");
    }

    @Override
    public void addStudentsBatch(List<Student> students) {
        throw new UnsupportedOperationException("Unimplemented method 'addStudentsBatch'");
    }

    @Override
    public void deleteStudentsBatch(List<Integer> studentIds) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudentsBatch'");
    }

    @Override
    public void updateStudentsBatch(List<Student> students) {
        throw new UnsupportedOperationException("Unimplemented method 'updateStudentsBatch'");
    }

    @Override
    public List<Student> searchStudentsByKeyword(String keyword) {
        throw new UnsupportedOperationException("Unimplemented method 'searchStudentsByKeyword'");
    }

    @Override
    public byte[] generateStudentReport() {
        throw new UnsupportedOperationException("Unimplemented method 'generateStudentReport'");
    }

    // Fuzzy search students by name
    public List<Student> searchStudentsByName(String name) {
        try {
            return studentDao.searchStudentsByName(name);
        } catch (Exception e) {
            logger.error("Failed to fuzzy search students by name: {}", e.getMessage());
            return null;
        }
    }

    // Retrieve students by class name
    public List<Student> getStudentsByClass(String className) {
        try {
            return studentDao.getStudentsByClass(className);
        } catch (Exception e) {
            logger.error("Failed to retrieve students by class: {}", e.getMessage());
            return null;
        }
    }

    // Fuzzy search students by major and class name
    @Override
    public List<Student> findStudentsByMajorAndClass(String major, String className) {
        try {
            return studentDao.findStudentsByMajorAndClass(major, className);
        } catch (Exception e) {
            logger.error("Failed to fuzzy search students by major and class: {}", e.getMessage());
            return null;
        }
    }
}