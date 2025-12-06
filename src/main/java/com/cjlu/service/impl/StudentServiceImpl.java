package com.cjlu.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cjlu.entity.Student;
import com.cjlu.service.StudentService;

import org.slf4j.*;
import com.cjlu.dao.impl.StudentDaoImpl;

public class StudentServiceImpl implements StudentService {

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    //数据访问对象
    private StudentDaoImpl studentDao = new StudentDaoImpl();

    //添加学生
    @Override
    public void addStudent(Student student) {
        try {
            //检查student表是否存在
            if (!studentDao.isStudentTableExists()) {
                studentDao.createStudentTable();
                logger.info("学生表不存在，已创建学生表");
            }
            studentDao.addStudent(student);
            logger.info("添加学生成功: {}", student);
        } catch (Exception e) {
            logger.error("添加学生失败: {}", student, e);
            throw e;
        }
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        try {
            studentDao.deleteStudent(studentId);
            logger.info("删除学生成功: {}", studentId);
        } catch (Exception e) {
            logger.error("删除学生失败: {}", studentId, e);
            throw e;
        }
    }

    @Override
    public void updateStudentById(Student student) {
        try {
            studentDao.updateStudent(student);
            logger.info("更新学生成功: {}", student);
        } catch (Exception e) {
            logger.error("更新学生失败: {}", student, e);
            throw e;
        }
    }

    @Override
    public Student getStudentById(Integer studentId) {
        try {
            Student student = studentDao.getStudentById(studentId);
            logger.info("查询学生成功: {}", studentId);
            return student;
        } catch (Exception e) {
            logger.error("查询学生失败: {}", studentId, e);
            throw e;
        }
    }

    @Override
    public Student getStudentByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByName'");
    }

    @Override
    public List<Student> getAllStudents() {
       try {
            //检查student表是否存在
            if (!studentDao.isStudentTableExists()) {
                studentDao.createStudentTable();
                logger.info("学生表不存在，已创建学生表");
            }
            List<Student> students = studentDao.getAllStudents();
            logger.info("查询所有学生成功");
            return students;
        } catch (Exception e) {
            logger.error("查询所有学生失败", e);
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByMajor(String major) {
        try {
            List<Student> students = studentDao.findStudentsByMajor(major);
            logger.info("根据专业查询学生成功: {}", major);
            return students;
        } catch (Exception e) {
            logger.error("根据专业查询学生失败: {}", major, e);
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByClassName(String className) {
        try {
            List<Student> students = studentDao.findStudentsByClass(className);
            logger.info("根据班级查询学生成功: {}", className);
            return students;
        } catch (Exception e) {
            logger.error("根据班级查询学生失败: {}", className, e);
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByAgeRange(int minAge, int maxAge) {
        try {
            List<Student> students = studentDao.findStudentsByAgeRange(minAge, maxAge);
            logger.info("根据年龄范围查询学生成功: {} - {}", minAge, maxAge);
            return students;
        } catch (Exception e) {
            logger.error("根据年龄范围查询学生失败: {} - {}", minAge, maxAge, e);
            throw e;
        }
    }

    @Override
    public List<Student> getStudentsByAdmissionDateRange(Date startDate, Date endDate) {
       // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByAdmissionDateRange'");
    }

    @Override
    public Student getStudentByPhone(String phone) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByPhone'");
    }

    @Override
    public Student getStudentByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByEmail'");
    }

    @Override
    public int getStudentCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentCount'");
    }

    @Override
    public List<Student> getStudentsByPage(int pageNumber, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByPage'");
    }

    @Override
    public Map<String, Integer> getStudentCountByMajor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentCountByMajor'");
    }

    @Override
    public Map<String, Integer> getStudentCountByClassName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentCountByClassName'");
    }

    @Override
    public void addStudentsBatch(List<Student> students) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addStudentsBatch'");
    }

    @Override
    public void deleteStudentsBatch(List<Integer> studentIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudentsBatch'");
    }

    @Override
    public void updateStudentsBatch(List<Student> students) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStudentsBatch'");
    }

    @Override
    public List<Student> searchStudentsByKeyword(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchStudentsByKeyword'");
    }

    @Override
    public byte[] generateStudentReport() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateStudentReport'");
    }

    //这个方法的功能是根据姓名模糊查询学生
    public List<Student> searchStudentsByName(String name) {
        try {
            return studentDao.searchStudentsByName(name);
        } catch (Exception e) {
            logger.error("根据姓名模糊查询学生失败: {}", e.getMessage());
            return null;
        }
    }

    //这个方法的功能是根据班级查询学生
    public List<Student> getStudentsByClass(String className) {
        try {
            return studentDao.getStudentsByClass(className);
        } catch (Exception e) {
            logger.error("根据班级查询学生失败: {}", e.getMessage());
            return null;
        }
    }

    //依据major和className模糊查询学生
    @Override
    public List<Student> findStudentsByMajorAndClass(String major, String className) {
        try {
            return studentDao.findStudentsByMajorAndClass(major, className);
        } catch (Exception e) {
            logger.error("依据major和className模糊查询学生失败: {}", e.getMessage());
            return null;
        }
    }
}