package com.cjlu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.cjlu.dao.StudentDao;
import com.cjlu.entity.Student;
import com.cjlu.util.JDBCUtils;
import org.slf4j.*;

public class StudentDaoImpl implements StudentDao {

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    //链接数据库
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    //添加学生
    @Override
    public void addStudent(Student student) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备插入学生的SQL语句
            String insertSQL = "INSERT INTO Students (name, age, gender, major, class, admission_date, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);

            //设置参数
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getMajor());
            preparedStatement.setString(5, student.getClassName());
            preparedStatement.setDate(6, new java.sql.Date(student.getAdmissionDate().getTime()));
            preparedStatement.setString(7, student.getPhone());
            preparedStatement.setString(8, student.getEmail());

            //执行插入操作
            preparedStatement.executeUpdate();
            logger.info("学生添加成功！");

            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);

        } catch (Exception e) {
            logger.error("添加学生时出错：", e);
            e.printStackTrace();
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);
        }
               
    }

    //删除学生
    @Override
    public void deleteStudent(int studentId) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备删除学生的SQL语句
            String deleteSQL = "DELETE FROM Students WHERE student_id = ?";
            preparedStatement = connection.prepareStatement(deleteSQL);

            //设置参数
            preparedStatement.setInt(1, studentId);

            //执行删除操作
            preparedStatement.executeUpdate();
            logger.info("学生删除成功！");

            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);

        } catch (Exception e) {
            logger.error("删除学生时出错：", e);
            e.printStackTrace();
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //更新学生信息
    @Override
    public void updateStudent(Student student) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备更新学生的SQL语句
            String updateSQL = "UPDATE Students SET name = ?, age = ?, gender = ?, major = ?, class = ?, admission_date = ?, phone = ?, email = ? WHERE student_id = ?";
            preparedStatement = connection.prepareStatement(updateSQL);

            //设置参数
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getMajor());
            preparedStatement.setString(5, student.getClassName());
            preparedStatement.setDate(6, new java.sql.Date(student.getAdmissionDate().getTime()));
            preparedStatement.setString(7, student.getPhone());
            preparedStatement.setString(8, student.getEmail());
            preparedStatement.setInt(9, Integer.parseInt(student.getStudentId()));

            //执行更新操作
            preparedStatement.executeUpdate();
            logger.info("学生信息更新成功！");

            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);
    
        } catch (Exception e) {
            logger.error("更新学生信息时出错：", e);
            e.printStackTrace();
            //关闭资源 
            JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //根据学生ID获取学生信息
    @Override
    public Student getStudentById(int studentId) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备查询学生的SQL语句
            String querySQL = "SELECT * FROM Students WHERE student_id = ?";
            preparedStatement = connection.prepareStatement(querySQL);

            //设置参数
            preparedStatement.setInt(1, studentId);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();

            //处理结果集
            if (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                //关闭资源
                JDBCUtils.closeResources(connection, preparedStatement, resultSet);
                return student;
            } else {
                //关闭资源
                JDBCUtils.closeResources(connection, preparedStatement, resultSet);
                return null;
            }
        } catch (Exception e) {
            logger.error("获取学生信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    //获取所有学生信息
    @Override
    public List<Student> getAllStudents() {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备查询所有学生的SQL语句
            String querySQL = "SELECT * FROM Students";
            preparedStatement = connection.prepareStatement(querySQL);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();

            //处理结果集
            List<Student> students = new java.util.ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, resultSet);
            return students;
        } catch (Exception e) {
            logger.error("获取所有学生信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    //根据姓名模糊查询学生信息
    @Override
    public List<Student> findStudentsByName(String name) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备查询学生的SQL语句
            String querySQL = "SELECT * FROM Students WHERE name LIKE ?";
            preparedStatement = connection.prepareStatement(querySQL);

            //设置参数
            preparedStatement.setString(1, "%" + name + "%");

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();

            //处理结果集
            List<Student> students = new java.util.ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }

            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, resultSet);
            return students;
        } catch (Exception e) {
            logger.error("根据姓名查询学生信息时出错：", e);
            e.printStackTrace();
            return null;
        }

    }

    //根据专业查询学生信息
    @Override
    public List<Student> findStudentsByMajor(String major) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备查询学生的SQL语句
            String querySQL = "SELECT * FROM Students WHERE major = ?";
            preparedStatement = connection.prepareStatement(querySQL);

            //设置参数
            preparedStatement.setString(1, major);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();

            //处理结果集
            List<Student> students = new java.util.ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, resultSet);
            return students;
        } catch (Exception e) {
            logger.error("根据专业查询学生信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    //根据年龄范围查询学生信息
    @Override
    public List<Student> findStudentsByAgeRange(int minAge, int maxAge) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备查询学生的SQL语句
            String querySQL = "SELECT * FROM Students WHERE age BETWEEN ? AND ?";
            preparedStatement = connection.prepareStatement(querySQL);

            //设置参数
            preparedStatement.setInt(1, minAge);
            preparedStatement.setInt(2, maxAge);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();

            //处理结果集
            List<Student> students = new java.util.ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, resultSet);
            return students;
        } catch (Exception e) {
            logger.error("根据年龄范围查询学生信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    //根据性别查询学生信息
    @Override
    public List<Student> findStudentsByGender(String gender) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备查询学生的SQL语句
            String querySQL = "SELECT * FROM Students WHERE gender = ?";
            preparedStatement = connection.prepareStatement(querySQL);
            //设置参数
            preparedStatement.setString(1, gender);
            //执行查询操作
            var resultSet = preparedStatement.executeQuery();
            //处理结果集
            List<Student> students = new java.util.ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, resultSet);
            return students;
        } catch (Exception e) {
            logger.error("根据性别查询学生信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    //根据班级查询学生信息
    @Override
    public List<Student> findStudentsByClass(String className) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备查询学生的SQL语句
            String querySQL = "SELECT * FROM Students WHERE class = ?";
            preparedStatement = connection.prepareStatement(querySQL);

            //设置参数
            preparedStatement.setString(1, className);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();

            //处理结果集
            List<Student> students = new java.util.ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, resultSet);
            return students;
        } catch (Exception e) {
            logger.error("根据班级查询学生信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    //根据学院查询学生信息
    @Override
    public List<Student> findStudentsByCollege(String collegeName) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //准备查询学生的SQL语句
            String querySQL = "SELECT * FROM Students WHERE college = ?";
            preparedStatement = connection.prepareStatement(querySQL);

            //设置参数
            preparedStatement.setString(1, collegeName);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();

            //处理结果集
            List<Student> students = new java.util.ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, resultSet);
            return students;
        } catch (Exception e) {
            logger.error("根据学院查询学生信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createStudentTable() {
        //根据Student类的属性来创建学生表
        try {
            //链接数据库
            connection = JDBCUtils.getConnection();
            //创建表的SQL语句
            String createTableSQL = "CREATE TABLE Students (" +
                    "student_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                    "name VARCHAR(100) NOT NULL," +
                    "age INT," +
                    "gender VARCHAR(10)," +
                    "major VARCHAR(100)," +
                    "class VARCHAR(100)," +
                "admission_date DATE NOT NULL," +
                "phone VARCHAR(11) UNIQUE," +
                "email VARCHAR(50) UNIQUE" +
                    ")";
            //准备执行语句
            preparedStatement = connection.prepareStatement(createTableSQL);
            //执行创建表操作
            preparedStatement.executeUpdate();
            logger.info("学生表创建成功！");

            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);

        } catch (Exception e) {
            logger.error("创建学生表时出错：", e);
            e.printStackTrace();
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //这个方法的功能是根据姓名模糊查询学生
    public List<Student> searchStudentsByName(String name) {
        try {
            //调用findStudentsByName方法实现功能
            return findStudentsByName(name);
        } catch (Exception e) {
            logger.error("根据姓名模糊查询学生失败: {}", e.getMessage());
            return null;
        }
    }

    //这个方法的功能是根据班级查询学生
    @Override
    public List<Student> getStudentsByClass(String className) {
        try {
            //调用findStudentsByClass方法实现功能
            return findStudentsByClass(className);
        } catch (Exception e) {
            logger.error("根据班级查询学生失败: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isStudentTableExists() {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();

            //检查学生表是否存在的SQL语句
            String checkTableSQL = "SELECT 1 FROM Students FETCH FIRST ROW ONLY";
            preparedStatement = connection.prepareStatement(checkTableSQL);

            //执行查询操作
            preparedStatement.executeQuery();

            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);

            return true; // 表存在
        } catch (Exception e) {
            logger.error("检查学生表是否存在时出错：", e);
            e.printStackTrace();
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);
            return false; // 表不存在或发生错误
        }
    }

    //依据major和class模糊查询学生，两个属性可以为空
    @Override
    public List<Student> findStudentsByMajorAndClass(String major, String className) {
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            StringBuilder querySQL = new StringBuilder("SELECT * FROM Students WHERE 1=1");
            if (major != null && !major.isEmpty()) {
                querySQL.append(" AND major = ?");
            }
            if (className != null && !className.isEmpty()) {
                querySQL.append(" AND class = ?");
            }
            preparedStatement = connection.prepareStatement(querySQL.toString());
            int paramIndex = 1;
            if (major != null && !major.isEmpty()) {
                preparedStatement.setString(paramIndex++, major);
            }
            if (className != null && !className.isEmpty()) {
                preparedStatement.setString(paramIndex++, className);
            }
            //执行查询操作
            var resultSet = preparedStatement.executeQuery();
            //处理结果集
            List<Student> students = new java.util.ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(String.valueOf(resultSet.getInt("student_id")));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setMajor(resultSet.getString("major"));
                student.setClassName(resultSet.getString("class"));
                student.setAdmissionDate(resultSet.getDate("admission_date"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                students.add(student);
            }
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, resultSet);
            return students;
        } catch (Exception e) {
            logger.error("依据major和class模糊查询学生时出错：", e);
            e.printStackTrace();
            //关闭资源
            JDBCUtils.closeResources(connection, preparedStatement, null);
            return null;
        }
    }
}