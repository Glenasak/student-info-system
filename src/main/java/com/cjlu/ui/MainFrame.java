/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template


/**
 *
 * @author 孙心坚
 */
package com.cjlu.ui;
import com.cjlu.util.JDBCUtils;
import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends javax.swing.JFrame {
    private String currentLoginUser;
    private CardLayout studentCardLayout; // 学生管理标签页的CardLayout

    public MainFrame() {
        initComponents();
        loadStudentData(); // 初始化加载学生数据
    }

    public MainFrame(String loginUser) {
        initComponents();
        this.currentLoginUser = loginUser;
        showLoginUserInfo();
        loadStudentData();
    }

    // 加载学生数据（原方法保留）
    private void loadStudentData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM students";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] row = {
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("class_name"),
                    rs.getString("phone"),
                    rs.getString("major"),
                    rs.getString("admission_date"),
                    rs.getString("email")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load student data" + e.getMessage());
        } finally {
            JDBCUtils.closeResources(conn, pstmt, rs);
        }
    }

    private void showLoginUserInfo() {
        if (currentLoginUser != null && !currentLoginUser.isEmpty()) {
            jMenuItem5.setText("Welcome to log in" + currentLoginUser);
            jMenuItem5.setEnabled(false);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel(); // 学生管理标签页（CardLayout容器）
        ListCard = new javax.swing.JPanel(); // 学生列表卡片
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        AddCard = new javax.swing.JPanel(); // 添加学生卡片
        jLabel2 = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtClassName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMajor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAdmissionDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnAddBack = new javax.swing.JButton();
        btnAddSave = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        UpdateCard = new javax.swing.JPanel(); // 修改学生卡片
        jLabel12 = new javax.swing.JLabel();
        txtStudentID1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtName1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtGender1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtAge1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtClassName1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtPhone1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtMajor1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtAdmissionDate1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JTextField();
        btnAddBack1 = new javax.swing.JButton();
        btnAddSave1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("student information management system");
        setPreferredSize(new java.awt.Dimension(900, 700));

        // 课程管理标签页
        jButton7.setText("get into CourseManagerFrame");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jTabbedPane1.addTab("course management", jPanel1);

        // 成绩管理标签页
        jButton8.setText("get into ScoreManagerFrame");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8);
        jTabbedPane1.addTab("score management", jPanel2);

        // 数据统计标签页
        jButton9.setText("get into StatisticManageFrame");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9);
        jTabbedPane1.addTab("data statistics", jPanel3);

        // ---------------- 学生管理标签页（CardLayout设置） ----------------
        studentCardLayout = new CardLayout();
        jPanel4.setLayout(studentCardLayout);

        // 1. 学生列表卡片（ListCard）
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{
                "student_id", "name", "gender", "age", "class_name", "phone", "major", "admission_date", "email"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        // 列表卡片的按钮布局
        jButton1.setText("Add students");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modify students");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Search for students");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete student");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setText("Reset the query");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton6)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton4)
                        .addComponent(jButton3)
                        .addComponent(jButton6))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ListCardLayout = new javax.swing.GroupLayout(ListCard);
        ListCard.setLayout(ListCardLayout);
        ListCardLayout.setHorizontalGroup(
            ListCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ListCardLayout.setVerticalGroup(
            ListCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ListCardLayout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        jPanel4.add(ListCard, "list");

        // 2. 添加学生卡片（AddCard）
        jLabel11.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18));
        jLabel11.setText("Addstudent");

        jLabel2.setText("student_id");
        jLabel3.setText("name");
        jLabel4.setText("gender");
        jLabel5.setText("age");
        jLabel6.setText("class");
        jLabel7.setText("phone");
        jLabel8.setText("major");
        jLabel9.setText("admission_date");
        jLabel10.setText("email");

        btnAddBack.setText("return");
        btnAddBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBackActionPerformed(evt);
            }
        });

        btnAddSave.setText("save");
        btnAddSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddCardLayout = new javax.swing.GroupLayout(AddCard);
        AddCard.setLayout(AddCardLayout);
        AddCardLayout.setHorizontalGroup(
            AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(AddCardLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AddCardLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AddCardLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AddCardLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AddCardLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(txtMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(txtAdmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AddCardLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AddCardLayout.createSequentialGroup()
                            .addGap(300, 300, 300)
                            .addComponent(btnAddSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(btnAddBack, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(AddCardLayout.createSequentialGroup()
                    .addGap(380, 380, 380)
                    .addComponent(jLabel11)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddCardLayout.setVerticalGroup(
            AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(AddCardLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel11)
                    .addGap(30, 30, 30)
                    .addGroup(AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtAdmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(50, 50, 50)
                    .addGroup(AddCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddBack, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanel4.add(AddCard, "add");

        // 3. 修改学生卡片（UpdateCard）
        jLabel21.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18));
        jLabel21.setText("updateStudent");

        jLabel12.setText("student_id");
        jLabel13.setText("name");
        jLabel14.setText("gender");
        jLabel15.setText("age");
        jLabel16.setText("class_name");
        jLabel17.setText("phone");
        jLabel18.setText("major");
        jLabel19.setText("admission_date");
        jLabel20.setText("email");

        btnAddBack1.setText("return");
        btnAddBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBack1ActionPerformed(evt);
            }
        });

        btnAddSave1.setText("update");
        btnAddSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UpdateCardLayout = new javax.swing.GroupLayout(UpdateCard);
        UpdateCard.setLayout(UpdateCardLayout);
        UpdateCardLayout.setHorizontalGroup(
            UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(UpdateCardLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UpdateCardLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(18, 18, 18)
                            .addComponent(txtStudentID1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel13)
                            .addGap(18, 18, 18)
                            .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(UpdateCardLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(txtGender1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel15)
                            .addGap(18, 18, 18)
                            .addComponent(txtAge1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(UpdateCardLayout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(18, 18, 18)
                            .addComponent(txtClassName1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel17)
                            .addGap(18, 18, 18)
                            .addComponent(txtPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(UpdateCardLayout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addGap(18, 18, 18)
                            .addComponent(txtMajor1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel19)
                            .addGap(18, 18, 18)
                            .addComponent(txtAdmissionDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(UpdateCardLayout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(18, 18, 18)
                            .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(UpdateCardLayout.createSequentialGroup()
                            .addGap(300, 300, 300)
                            .addComponent(btnAddSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(btnAddBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(UpdateCardLayout.createSequentialGroup()
                    .addGap(380, 380, 380)
                    .addComponent(jLabel21)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        UpdateCardLayout.setVerticalGroup(
            UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(UpdateCardLayout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel21)
                    .addGap(30, 30, 30)
                    .addGroup(UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtStudentID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtGender1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(txtAge1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtClassName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(txtPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtMajor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(txtAdmissionDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(50, 50, 50)
                    .addGroup(UpdateCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(200, Short.MAX_VALUE))
        );
        jPanel4.add(UpdateCard, "update");
        // ---------------- 学生管理标签页结束 ----------------

        jTabbedPane1.addTab("student_manage", jPanel4);
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        // 菜单栏
        jMenu1.setText("system_manage");
        jMenuItem1.setText("password_change");
        jMenu1.add(jMenuItem1);
        jMenuItem2.setText("get_out_of_system");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Student_manage");
        jMenuItem3.setText("add_student");
        jMenu2.add(jMenuItem3);
        jMenuBar1.add(jMenu2);

        jMenu3.setText("help");
        jMenuItem4.setText("aboutsystem");
        jMenu3.add(jMenuItem4);
        jMenuBar1.add(jMenu3);

        jMenu8.setText("username");
        jMenuItem5.setText("name");
        jMenu8.add(jMenuItem5);
        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);
        pack();
    }
    // </editor-fold>


    // ---------------- 学生管理相关事件 ----------------
    // 点击“添加学生”按钮：切换到添加卡片
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        studentCardLayout.show(jPanel4, "add");
        // 清空添加表单
        txtStudentID.setText("");
        txtName.setText("");
        txtGender.setText("");
        txtAge.setText("");
        txtClassName.setText("");
        txtPhone.setText("");
        txtMajor.setText("");
        txtAdmissionDate.setText("");
        txtEmail.setText("");
    }

    // 点击“修改学生”按钮：切换到修改卡片，并填充选中行数据
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select the row of the student you want to modify first!");
            return;
        }

        // 填充选中行数据到修改表单
        txtStudentID1.setText(jTable1.getValueAt(selectedRow, 0).toString());
        txtName1.setText(jTable1.getValueAt(selectedRow, 1).toString());
        txtGender1.setText(jTable1.getValueAt(selectedRow, 2).toString());
        txtAge1.setText(jTable1.getValueAt(selectedRow, 3).toString());
        txtClassName1.setText(jTable1.getValueAt(selectedRow, 4).toString());
        txtPhone1.setText(jTable1.getValueAt(selectedRow, 5).toString());
        txtMajor1.setText(jTable1.getValueAt(selectedRow, 6).toString());
        txtAdmissionDate1.setText(jTable1.getValueAt(selectedRow, 7).toString());
        txtEmail1.setText(jTable1.getValueAt(selectedRow, 8).toString());

        studentCardLayout.show(jPanel4, "update");
    }

    // 点击“删除学生”按钮（原逻辑保留）
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select the row of the student you want to delete first!");
            return;
        }

        String studentName = jTable1.getValueAt(selectedRow, 1).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete" + studentName + " ？");
        if (confirm == JOptionPane.YES_OPTION) {
            // 数据库删除操作（补充）
            String studentId = jTable1.getValueAt(selectedRow, 0).toString();
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = JDBCUtils.getConnection();
                String sql = "DELETE FROM students WHERE student_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, studentId);
                pstmt.executeUpdate();
                ((DefaultTableModel) jTable1.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "successfully delete");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "fail to delete" + e.getMessage());
            } finally {
                JDBCUtils.closeResources(conn, pstmt, null);
            }
        }
    }

    // 点击“查询学生”按钮（原逻辑保留）
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        String keyword = JOptionPane.showInputDialog(this, "Please enter your student number or name to make the query:");
        if (keyword == null || keyword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "The query keyword cannot be empty!");
            return;
        }
        keyword = keyword.trim().toLowerCase();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            String studentId = model.getValueAt(i, 0).toString().toLowerCase();
            String studentName = model.getValueAt(i, 1).toString().toLowerCase();
            if (!studentId.contains(keyword) && !studentName.contains(keyword)) {
                model.removeRow(i);
            }
        }
        JOptionPane.showMessageDialog(this, "Query completed! A total of" + model.getRowCount() + " data");
    }

    // 点击“重置查询”按钮（原逻辑保留）
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        loadStudentData();
        JOptionPane.showMessageDialog(this, "The query has been reset and now all student data is displayed!");
    }

    // 点击“添加学生-保存”按钮：插入数据库并更新表格
    private void btnAddSaveActionPerformed(java.awt.event.ActionEvent evt) {
        // 获取表单数据
        String studentId = txtStudentID.getText().trim();
        String name = txtName.getText().trim();
        String gender = txtGender.getText().trim();
        String age = txtAge.getText().trim();
        String className = txtClassName.getText().trim();
        String phone = txtPhone.getText().trim();
        String major = txtMajor.getText().trim();
        String admissionDate = txtAdmissionDate.getText().trim();
        String email = txtEmail.getText().trim();

        // 非空校验
        if (studentId.isEmpty() || name.isEmpty() || gender.isEmpty() || age.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student number, name, gender and age cannot be left blank!");
            return;
        }

        // 插入数据库
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO students (student_id, name, gender, age, class_name, phone, major, admission_date, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, gender);
            pstmt.setInt(4, Integer.parseInt(age));
            pstmt.setString(5, className);
            pstmt.setString(6, phone);
            pstmt.setString(7, major);
            pstmt.setString(8, admissionDate);
            pstmt.setString(9, email);
            pstmt.executeUpdate();

            // 更新表格
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{studentId, name, gender, Integer.parseInt(age), className, phone, major, admissionDate, email});
            JOptionPane.showMessageDialog(this, "successfully added");

            // 切回列表卡片
            studentCardLayout.show(jPanel4, "list");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "successfully faild：" + e.getMessage());
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
    }

    // 点击“添加学生-返回”按钮：切回列表卡片
    private void btnAddBackActionPerformed(java.awt.event.ActionEvent evt) {
        studentCardLayout.show(jPanel4, "list");
    }

    // 点击“修改学生-更新”按钮：更新数据库并刷新表格
    private void btnAddSave1ActionPerformed(java.awt.event.ActionEvent evt) {
        // 获取表单数据
        String studentId = txtStudentID1.getText().trim();
        String name = txtName1.getText().trim();
        String gender = txtGender1.getText().trim();
        String age = txtAge1.getText().trim();
        String className = txtClassName1.getText().trim();
        String phone = txtPhone1.getText().trim();
        String major = txtMajor1.getText().trim();
        String admissionDate = txtAdmissionDate1.getText().trim();
        String email = txtEmail1.getText().trim();

        // 非空校验
        if (name.isEmpty() || gender.isEmpty() || age.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name, gender and age cannot be left blank!");
            return;
        }

        // 更新数据库
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE students SET name=?, gender=?, age=?, class_name=?, phone=?, major=?, admission_date=?, email=? WHERE student_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setInt(3, Integer.parseInt(age));
            pstmt.setString(4, className);
            pstmt.setString(5, phone);
            pstmt.setString(6, major);
            pstmt.setString(7, admissionDate);
            pstmt.setString(8, email);
            pstmt.setString(9, studentId);
            pstmt.executeUpdate();

            // 更新表格选中行
            int selectedRow = jTable1.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setValueAt(name, selectedRow, 1);
            model.setValueAt(gender, selectedRow, 2);
            model.setValueAt(Integer.parseInt(age), selectedRow, 3);
            model.setValueAt(className, selectedRow, 4);
            model.setValueAt(phone, selectedRow, 5);
            model.setValueAt(major, selectedRow, 6);
            model.setValueAt(admissionDate, selectedRow, 7);
            model.setValueAt(email, selectedRow, 8);
            JOptionPane.showMessageDialog(this, "chaged success");

            // 切回列表卡片
            studentCardLayout.show(jPanel4, "list");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "chaged failed" + e.getMessage());
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
    }

    // 点击“修改学生-返回”按钮：切回列表卡片
    private void btnAddBack1ActionPerformed(java.awt.event.ActionEvent evt) {
        studentCardLayout.show(jPanel4, "list");
    }
    // ---------------- 其他事件（原逻辑保留） ----------------
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        CourseManagerFrame courseFrame = new CourseManagerFrame();
        courseFrame.setVisible(true);
    }

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        ScoreManagerJFrame scoreFrame = new ScoreManagerJFrame();
        scoreFrame.setVisible(true);
    }

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
        StatisticManageFrame statisticFrame = new StatisticManageFrame();
        statisticFrame.setVisible(true);
    }


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify
    private javax.swing.JPanel AddCard;
    private javax.swing.JPanel ListCard;
    private javax.swing.JPanel UpdateCard;
    private javax.swing.JButton btnAddBack;
    private javax.swing.JButton btnAddBack1;
    private javax.swing.JButton btnAddSave;
    private javax.swing.JButton btnAddSave1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAdmissionDate;
    private javax.swing.JTextField txtAdmissionDate1;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtAge1;
    private javax.swing.JTextField txtClassName;
    private javax.swing.JTextField txtClassName1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtGender1;
    private javax.swing.JTextField txtMajor;
    private javax.swing.JTextField txtMajor1;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtName1;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhone1;
    private javax.swing.JTextField txtStudentID;
    private javax.swing.JTextField txtStudentID1;
    // End of variables declaration
}