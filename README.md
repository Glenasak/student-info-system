## 一、项目概述

### 1. 项目名称

学生信息管理系统（Student Information Management System, SIMS）

### 2. 核心功能

- **学生信息管理**：新增、查询、修改、删除学生基本信息（学号、姓名、性别、年龄、专业、班级等）
- **课程管理**：维护课程信息（课程号、课程名、学分、授课教师等）
- **成绩管理**：录入、查询学生课程成绩
- **数据统计**：按专业 / 班级统计学生数量、课程平均分等
- **用户登录**：支持管理员 / 教师角色登录（基础权限控制）

### 3. 技术栈

- **开发语言**：Java 8+（兼容性好，Swing 支持稳定）
- **构建工具**：Maven 3.6+（依赖管理、打包便捷）
- **GUI 框架**：Swing（Java 内置，轻量、跨平台）
- **数据库**：Apache Derby（嵌入式数据库，无需额外安装，适合小型应用）
- **数据库连接**：JDBC（Java 原生，无需额外 ORM 框架，简化开发）
- **测试工具**：JUnit 5（单元测试）、Swing UI 测试（可选）

## 二、系统设计

### 1. 架构设计（分层架构）

采用经典的 **三层架构**，职责清晰、易于维护：

| 分层         | 职责                                                   | 核心组件                                                     |
| ------------ | ------------------------------------------------------ | ------------------------------------------------------------ |
| 表现层（UI） | 负责用户交互，展示数据、接收用户操作                   | JFrame（主窗口）、JPanel（面板）、JTable（数据表格）、JButton（按钮）等 Swing 组件 |
| 业务逻辑层   | 处理核心业务逻辑（如数据校验、业务规则判断、事务管理） | 服务接口（如`StudentService`）、服务实现类（如`StudentServiceImpl`） |
| 数据访问层   | 负责与数据库交互，执行 CRUD 操作                       | DAO 接口（如`StudentDAO`）、DAO 实现类（如`StudentDAOImpl`）、JDBC 工具类 |
| 实体层       | 封装业务实体（如学生、课程、成绩），映射数据库表结构   | 实体类（如`Student`、`Course`、`Score`）                     |
| 工具层       | 提供通用工具类（如 JDBC 连接、日期格式化、常量定义）   | `JDBCUtils`、`DateUtils`、`Constants`                        |

### 2. 核心流程示例（以 “新增学生” 为例）

1. 表现层：用户在 “新增学生” 窗口输入信息，点击 “保存” 按钮
2. 表现层：验证输入合法性（如学号非空、年龄格式正确），不合法则提示错误
3. 表现层：调用业务逻辑层的`studentService.addStudent(student)`方法
4. 业务逻辑层：进一步校验业务规则（如学号是否已存在），通过后调用数据访问层
5. 数据访问层：执行 SQL 插入操作，将学生信息存入数据库
6. 数据访问层：返回操作结果（成功 / 失败），业务逻辑层传递给表现层
7. 表现层：显示操作结果（如 “新增成功” 提示框）

## 三、数据库设计

使用 Derby 嵌入式数据库，数据库名为 `sims_db`，核心表结构如下：

### 1. 用户表（users）

| 字段名      | 类型        | 约束                      | 说明                  |
| ----------- | ----------- | ------------------------- | --------------------- |
| user_id     | INT         | PRIMARY KEY AUTOINCREMENT | 用户 ID（自增）       |
| username    | VARCHAR(20) | NOT NULL UNIQUE           | 用户名（登录账号）    |
| password    | VARCHAR(20) | NOT NULL                  | 密码（简单加密存储）  |
| role        | VARCHAR(10) | NOT NULL                  | 角色（admin/teacher） |
| create_time | TIMESTAMP   | DEFAULT CURRENT_TIMESTAMP | 创建时间              |

### 2. 学生表（students）

| 字段名         | 类型        | 约束                              | 说明                 |
| -------------- | ----------- | --------------------------------- | -------------------- |
| student_id     | VARCHAR(10) | PRIMARY KEY                       | 学号（如 2023001）   |
| name           | VARCHAR(20) | NOT NULL                          | 姓名                 |
| gender         | VARCHAR(2)  | CHECK (gender IN (' 男 ',' 女 ')) | 性别                 |
| age            | INT         | CHECK (age > 0 AND age < 100)     | 年龄                 |
| major          | VARCHAR(30) | NOT NULL                          | 专业（如计算机科学） |
| class_name     | VARCHAR(20) | NOT NULL                          | 班级（如计科 2301）  |
| admission_date | DATE        | NOT NULL                          | 入学日期             |
| phone          | VARCHAR(11) | UNIQUE                            | 手机号（可选）       |
| email          | VARCHAR(50) | UNIQUE                            | 邮箱（可选）         |

### 3. 课程表（courses）

| 字段名      | 类型        | 约束                        | 说明                   |
| ----------- | ----------- | --------------------------- | ---------------------- |
| course_id   | VARCHAR(10) | PRIMARY KEY                 | 课程号（如 CS101）     |
| course_name | VARCHAR(50) | NOT NULL                    | 课程名（如 Java 编程） |
| credit      | INT         | NOT NULL CHECK (credit > 0) | 学分                   |
| teacher     | VARCHAR(20) | NOT NULL                    | 授课教师               |
| semester    | VARCHAR(10) | NOT NULL                    | 学期（如 2023-2024-1） |

### 4. 成绩表（scores）

| 字段名     | 类型        | 约束                                                 | 说明            |
| ---------- | ----------- | ---------------------------------------------------- | --------------- |
| score_id   | INT         | PRIMARY KEY AUTOINCREMENT                            | 成绩 ID（自增） |
| student_id | VARCHAR(10) | NOT NULL FOREIGN KEY REFERENCES students(student_id) | 学号（外键）    |
| course_id  | VARCHAR(10) | NOT NULL FOREIGN KEY REFERENCES courses(course_id)   | 课程号（外键）  |
| score      | INT         | CHECK (score >= 0 AND score <= 100)                  | 分数            |
| exam_date  | DATE        | NOT NULL                                             | 考试日期        |

### 5. 数据库初始化 SQL

在项目启动时执行，用于创建表和初始化测试数据（如管理员账号）：

```sql
-- 创建用户表
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    role VARCHAR(10) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建学生表
CREATE TABLE students (
    student_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    gender VARCHAR(2) CHECK (gender IN ('男', '女')),
    age INT CHECK (age > 0 AND age < 100),
    major VARCHAR(30) NOT NULL,
    class_name VARCHAR(20) NOT NULL,
    admission_date DATE NOT NULL,
    phone VARCHAR(11) UNIQUE,
    email VARCHAR(50) UNIQUE
);

-- 创建课程表
CREATE TABLE courses (
    course_id VARCHAR(10) PRIMARY KEY,
    course_name VARCHAR(50) NOT NULL,
    credit INT NOT NULL CHECK (credit > 0),
    teacher VARCHAR(20) NOT NULL,
    semester VARCHAR(10) NOT NULL
);

-- 创建成绩表
CREATE TABLE scores (
    score_id INT PRIMARY KEY AUTOINCREMENT,
    student_id VARCHAR(10) NOT NULL,
    course_id VARCHAR(10) NOT NULL,
    score INT CHECK (score >= 0 AND score <= 100),
    exam_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    UNIQUE (student_id, course_id) -- 一个学生一门课只能有一个成绩
);

-- 初始化管理员账号（username: admin, password: 123456）
INSERT INTO users (username, password, role) VALUES ('admin', '123456', 'admin');
```

## 四、项目结构（Maven 标准结构）

```plaintext
sims/
├─ pom.xml                -- Maven配置文件（依赖、打包、编译插件等）
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ com/
│  │  │     └─ sims/
│  │  │        ├─ SimMain.java          -- 项目入口类（启动主窗口）
│  │  │        ├─ entity/               -- 实体类
│  │  │        │  ├─ Student.java
│  │  │        │  ├─ Course.java
│  │  │        │  ├─ Score.java
│  │  │        │  └─ User.java
│  │  │        ├─ dao/                  -- DAO层（数据访问）
│  │  │        │  ├─ StudentDAO.java
│  │  │        │  ├─ impl/
│  │  │        │  │  └─ StudentDAOImpl.java
│  │  │        │  ├─ CourseDAO.java
│  │  │        │  ├─ ScoreDAO.java
│  │  │        │  └─ UserDAO.java
│  │  │        ├─ service/             -- 服务层（业务逻辑）
│  │  │        │  ├─ StudentService.java
│  │  │        │  ├─ impl/
│  │  │        │  │  └─ StudentServiceImpl.java
│  │  │        │  ├─ CourseService.java
│  │  │        │  └─ ScoreService.java
│  │  │        ├─ ui/                   -- UI层（Swing界面）
│  │  │        │  ├─ LoginFrame.java    -- 登录窗口
│  │  │        │  ├─ MainFrame.java     -- 主窗口（系统首页）
│  │  │        │  ├─ student/           -- 学生相关界面
│  │  │        │  │  ├─ StudentListPanel.java  -- 学生列表面板
│  │  │        │  │  └─ AddStudentFrame.java   -- 新增学生窗口
│  │  │        │  ├─ course/            -- 课程相关界面
│  │  │        │  └─ score/             -- 成绩相关界面
│  │  │        └─ util/                 -- 工具类
│  │  │           ├─ JDBCUtils.java     -- JDBC连接工具
│  │  │           ├─ DateUtils.java     -- 日期格式化工具
│  │  │           └─ Constants.java     -- 常量定义（如数据库URL）
│  │  └─ resources/                     -- 资源文件
│  │     ├─ db/
│  │     │  └─ init.sql                 -- 数据库初始化SQL
│  │     └─ images/                     -- 图片资源（如logo、图标）
│  └─ test/                             -- 测试代码
│     └─ java/
│        └─ com/
│           └─ sims/
│              ├─ dao/                  -- DAO层测试
│              └─ service/             -- 服务层测试
└─ target/                              -- Maven构建输出目录（jar包、class文件等）
```

## 五、核心代码实现

### 1. Maven 配置（pom.xml）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.sims</groupId>
    <artifactId>student-info-management-system</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>Student Information Management System</name>
    <description>A simple SIMS using Java, Swing, and Derby</description>

    <!-- 依赖配置 -->
    <dependencies>
        <!-- Derby数据库驱动 -->
        <dependency>
            <groupId>org.apache.derby</groupId>
            <artifactId>derby</artifactId>
            <version>10.15.2.0</version>
        </dependency>

        <!-- JUnit 5测试 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <!-- 构建配置 -->
    <build>
        <plugins>
            <!-- Java编译插件（指定JDK版本） -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>

            <!-- 打包插件（生成可执行jar包） -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <!-- 指定项目入口类 -->
                            <mainClass>com.sims.SimMain</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 2. 工具类：JDBCUtils（数据库连接工具）

```java
package com.sims.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    // Derby嵌入式数据库URL（数据库文件存储在项目根目录的db文件夹下）
    private static final String URL = "jdbc:derby:db/sims_db;create=true";
    // 数据库用户名（嵌入式Derby默认无需用户名密码，可省略）
    private static final String USER = "";
    private static final String PASSWORD = "";

    static {
        try {
            // 加载Derby驱动（Derby 10.15+可自动加载，此处为兼容旧版本）
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Derby驱动加载失败！");
        }
    }

    /**
     * 获取数据库连接
     * @return Connection对象
     * @throws SQLException 数据库连接异常
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 关闭数据库连接（简化版，实际开发可扩展关闭Statement、ResultSet）
     * @param conn 数据库连接对象
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 3. 实体类：Student（学生实体）

```java
package com.sims.entity;

import java.util.Date;

public class Student {
    private String studentId;   // 学号（主键）
    private String name;        // 姓名
    private String gender;      // 性别
    private Integer age;        // 年龄
    private String major;       // 专业
    private String className;   // 班级
    private Date admissionDate; // 入学日期
    private String phone;       // 手机号
    private String email;       // 邮箱

    // 构造方法（无参、全参）
    public Student() {}

    public Student(String studentId, String name, String gender, Integer age, 
                   String major, String className, Date admissionDate, String phone, String email) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.major = major;
        this.className = className;
        this.admissionDate = admissionDate;
        this.phone = phone;
        this.email = email;
    }

    // Getter和Setter方法（省略部分，实际开发需补全）
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    // ... 其他属性的Getter/Setter

    // toString()方法（便于调试）
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", className='" + className + '\'' +
                ", admissionDate=" + admissionDate +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
```

### 4. DAO 层：StudentDAO（学生数据访问接口）

```java
package com.sims.dao;

import com.sims.entity.Student;
import java.util.List;

public interface StudentDAO {
    /**
     * 新增学生
     * @param student 学生对象
     * @return 影响行数（1：成功，0：失败）
     */
    int addStudent(Student student);

    /**
     * 根据学号删除学生
     * @param studentId 学号
     * @return 影响行数
     */
    int deleteStudentByStudentId(String studentId);

    /**
     * 更新学生信息
     * @param student 学生对象（包含更新后的信息）
     * @return 影响行数
     */
    int updateStudent(Student student);

    /**
     * 根据学号查询学生
     * @param studentId 学号
     * @return 学生对象（null：未找到）
     */
    Student getStudentByStudentId(String studentId);

    /**
     * 查询所有学生
     * @return 学生列表
     */
    List<Student> getAllStudents();

    /**
     * 根据条件模糊查询学生（如姓名、专业）
     * @param condition 查询条件（key：字段名，value：查询值）
     * @return 学生列表
     */
    List<Student> getStudentsByCondition(java.util.Map<String, Object> condition);
}
```

### 5. DAO 实现类：StudentDAOImpl

```java
package com.sims.dao.impl;

import com.sims.dao.StudentDAO;
import com.sims.entity.Student;
import com.sims.util.JDBCUtils;
import com.sims.util.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public int addStudent(Student student) {
        String sql = "INSERT INTO students (student_id, name, gender, age, major, class_name, admission_date, phone, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            // 设置SQL参数（注意：索引从1开始）
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGender());
            pstmt.setInt(4, student.getAge());
            pstmt.setString(5, student.getMajor());
            pstmt.setString(6, student.getClassName());
            // 日期格式化（Date → String，Derby支持'yyyy-MM-dd'格式）
            pstmt.setString(7, DateUtils.formatDate(student.getAdmissionDate(), "yyyy-MM-dd"));
            pstmt.setString(8, student.getPhone());
            pstmt.setString(9, student.getEmail());

            return pstmt.executeUpdate(); // 执行插入，返回影响行数
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // 关闭资源（实际开发可封装工具方法）
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeConnection(conn);
        }
    }

    @Override
    public int deleteStudentByStudentId(String studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            JDBCUtils.closeConnection(conn);
        }
    }

    // 其他方法（updateStudent、getStudentByStudentId、getAllStudents等）类似，此处省略...

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Student> studentList = new ArrayList<>();

        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // 遍历结果集，封装为Student对象
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setAge(rs.getInt("age"));
                student.setMajor(rs.getString("major"));
                student.setClassName(rs.getString("class_name"));
                // 日期解析（String → Date）
                student.setAdmissionDate(DateUtils.parseDate(rs.getString("admission_date"), "yyyy-MM-dd"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            JDBCUtils.closeConnection(conn);
        }
        return studentList;
    }
}
```

### 6. 服务层：StudentService（业务逻辑接口）

```java
package com.sims.service;

import com.sims.entity.Student;
import java.util.List;

public interface StudentService {
    /**
     * 新增学生（包含业务校验）
     * @param student 学生对象
     * @return 操作结果（成功/失败信息）
     */
    String addStudent(Student student);

    /**
     * 删除学生（校验是否存在关联成绩）
     * @param studentId 学号
     * @return 操作结果
     */
    String deleteStudent(String studentId);

    /**
     * 更新学生信息
     * @param student 学生对象
     * @return 操作结果
     */
    String updateStudent(Student student);

    /**
     * 查询所有学生
     * @return 学生列表
     */
    List<Student> getAllStudents();

    // 其他业务方法...
}
```

### 7. 服务实现类：StudentServiceImpl

```java
package com.sims.service.impl;

import com.sims.dao.StudentDAO;
import com.sims.dao.impl.StudentDAOImpl;
import com.sims.entity.Student;
import com.sims.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    // 依赖DAO层对象
    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public String addStudent(Student student) {
        // 1. 业务校验：学号是否已存在
        if (studentDAO.getStudentByStudentId(student.getStudentId()) != null) {
            return "学号 " + student.getStudentId() + " 已存在！";
        }
        // 2. 业务校验：年龄是否合法（15-40岁，根据实际需求调整）
        if (student.getAge() < 15 || student.getAge() > 40) {
            return "年龄必须在15-40岁之间！";
        }
        // 3. 调用DAO层执行新增
        int rows = studentDAO.addStudent(student);
        return rows > 0 ? "新增学生成功！" : "新增学生失败！";
    }

    @Override
    public String deleteStudent(String studentId) {
        // 1. 校验学生是否存在
        if (studentDAO.getStudentByStudentId(studentId) == null) {
            return "学号 " + studentId + " 不存在！";
        }
        // 2. 校验是否有关联成绩（此处需调用ScoreDAO，省略）
        // if (scoreDAO.getScoresByStudentId(studentId).size() > 0) {
        //     return "该学生存在关联成绩，无法删除！";
        // }
        // 3. 调用DAO层执行删除
        int rows = studentDAO.deleteStudentByStudentId(studentId);
        return rows > 0 ? "删除学生成功！" : "删除学生失败！";
    }

    // 其他方法实现...

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}
```

### 8. UI 层：LoginFrame（登录窗口）

```java
package com.sims.ui;

import com.sims.entity.User;
import com.sims.service.UserService;
import com.sims.service.impl.UserServiceImpl;
import com.sims.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    // 依赖服务层
    private UserService userService = new UserServiceImpl();

    // UI组件
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton resetBtn;

    public LoginFrame() {
        // 初始化窗口属性
        initFrame();
        // 初始化UI组件
        initComponents();
        // 绑定事件监听器
        bindEvents();
    }

    /**
     * 初始化窗口属性
     */
    private void initFrame() {
        setTitle("学生信息管理系统 - 登录"); // 窗口标题
        setSize(400, 300); // 窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口退出程序
        setLocationRelativeTo(null); // 窗口居中
        setResizable(false); // 禁止窗口 resize
    }

    /**
     * 初始化UI组件
     */
    private void initComponents() {
        // 主面板（使用BorderLayout布局）
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // 内边距

        // 标题面板
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("用户登录");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // 表单面板（使用GridLayout布局，2行2列）
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.add(new JLabel("用户名："));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("密码："));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        // 按钮面板
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        loginBtn = new JButton("登录");
        resetBtn = new JButton("重置");
        loginBtn.setPreferredSize(new Dimension(100, 30));
        resetBtn.setPreferredSize(new Dimension(100, 30));
        btnPanel.add(loginBtn);
        btnPanel.add(resetBtn);

        // 组装面板
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        // 添加主面板到窗口
        add(mainPanel);
    }

    /**
     * 绑定事件监听器
     */
    private void bindEvents() {
        // 登录按钮点击事件
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. 获取输入参数
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // 2. 输入校验
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "用户名或密码不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // 3. 调用服务层校验登录
                User user = userService.login(username, password);

                // 4. 处理登录结果
                if (user != null) {
                    // 登录成功，关闭登录窗口，打开主窗口
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录成功！欢迎回来，" + username + "！", "成功", JOptionPane.INFORMATION_MESSAGE);
                    // 保存当前登录用户信息（可存入全局变量或Session）
                    Constants.CURRENT_USER = user;
                    // 打开主窗口
                    new MainFrame().setVisible(true);
                    // 关闭登录窗口
                    LoginFrame.this.dispose();
                } else {
                    // 登录失败
                    JOptionPane.showMessageDialog(LoginFrame.this, "用户名或密码错误！", "失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 重置按钮点击事件
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    // 测试登录窗口
    public static void main(String[] args) {
        // Swing程序建议在EDT（事件调度线程）中启动
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
```

### 9. 项目入口类：SimMain

```java
package com.sims;

import com.sims.ui.LoginFrame;
import com.sims.util.JDBCUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SimMain {
    public static void main(String[] args) {
        // 1. 初始化数据库（执行init.sql）
        initDatabase();

        // 2. 启动登录窗口
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    /**
     * 初始化数据库（创建表、插入测试数据）
     */
    private static void initDatabase() {
        // 读取init.sql文件内容（此处简化，直接执行SQL语句）
        String initSql = "CREATE TABLE IF NOT EXISTS users (...); " + // 省略完整SQL，实际开发需读取文件
                         "CREATE TABLE IF NOT EXISTS students (...); " +
                         "INSERT INTO users (username, password, role) VALUES ('admin', '123456', 'admin');";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtils.getConnection();
            // 执行多语句SQL（Derby需开启multiStatement=true）
            pstmt = conn.prepareStatement(initSql);
            pstmt.execute();
            System.out.println("数据库初始化成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("数据库初始化失败！");
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            JDBCUtils.closeConnection(conn);
        }
    }
}
```

## 六、开发与测试流程

### 1. 开发步骤

1. **环境准备**：安装 JDK 8+、Maven 3.6+、IDE（IntelliJ IDEA/Eclipse）
2. **创建 Maven 项目**：使用 IDE 创建 Maven 项目，导入上述`pom.xml`配置
3. **编写核心代码**：按 “实体层 → 工具层 → DAO 层 → 服务层 → UI 层” 顺序开发
4. **数据库初始化**：确保`init.sql`执行成功，生成`db/sims_db`数据库文件
5. **功能测试**：逐个测试功能（如登录、新增学生、查询成绩等）

### 2. 测试方法

- **单元测试**：使用 JUnit 5 测试 DAO 层和服务层（如`StudentDAOImplTest`、`StudentServiceImplTest`）

  ```java
  package com.sims.dao;
  
  import com.sims.entity.Student;
  import com.sims.dao.impl.StudentDAOImpl;
  import org.junit.jupiter.api.Test;
  import static org.junit.jupiter.api.Assertions.*;
  
  public class StudentDAOImplTest {
      private StudentDAO studentDAO = new StudentDAOImpl();
  
      @Test
      public void testAddStudent() {
          Student student = new Student("2023001", "张三", "男", 20, "计算机科学", "计科2301", new Date(), "13800138000", "zhangsan@xxx.com");
          int rows = studentDAO.addStudent(student);
          assertEquals(1, rows); // 验证新增成功
      }
  }
  ```

  

- **UI 测试**：手动启动程序，测试窗口显示、按钮点击、数据录入等交互功能

- **数据测试**：使用 Derby 自带的`ij`工具连接数据库，查询表数据是否正确

## 七、打包与运行

### 1. 打包步骤

1. 执行 Maven 命令：`mvn clean package`
2. 在`target`目录下生成可执行 jar 包：`student-info-management-system-1.0-SNAPSHOT.jar`

### 2. 运行方式

1. **命令行运行**：

   ```bash
   java -jar target/student-info-management-system-1.0-SNAPSHOT.jar
   ```

2. **双击运行**：直接双击 jar 包（需确保系统已关联 Java 运行环境）

### 3. 注意事项

- Derby 数据库文件存储在项目根目录的`db/sims_db`文件夹下，打包时需包含该文件夹（可通过 Maven 资源插件配置）
- 若运行时提示 “Derby 驱动加载失败”，检查`pom.xml`中 Derby 依赖是否正确

## 八、扩展与优化建议

1. **功能扩展**：
   - 增加 “批量导入 / 导出学生信息”（支持 Excel 格式，需引入 POI 库）
   - 增加 “密码修改”“用户管理” 功能
   - 增加 “成绩排名”“挂科统计” 等数据统计功能
2. **性能优化**：
   - 数据库连接池：使用 C3P0 或 HikariCP 替代原生 JDBC 连接（减少连接开销）
   - 分页查询：大数据量时使用`LIMIT/OFFSET`实现分页（Derby 支持`FETCH FIRST n ROWS ONLY`）
3. **UI 优化**：
   - 使用`LookAndFeel`美化界面（如`UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")`）
   - 增加图片图标、进度条等组件提升用户体验
4. **安全优化**：
   - 密码加密存储：使用 MD5 + 盐或 SHA256 加密密码（避免明文存储）
   - 输入校验增强：防止 SQL 注入（使用 PreparedStatement 已避免）、XSS 攻击等
5. **代码维护**：
   - 使用日志框架：引入 Log4j2 或 SLF4J 记录系统日志（替代`System.out.println`）
   - 依赖注入：使用 Spring 框架简化组件依赖管理（适合中大型项目）

