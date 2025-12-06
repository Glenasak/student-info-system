package com.cjlu.ui;

import com.cjlu.controller.UserController;
import com.cjlu.controller.Impl.UserControllerImpl;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 注册界面
 * @author 孙心坚
 */
public class Register extends JFrame {
    private JFormattedTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    // 引入用户控制器
    private final UserController userController;

    public Register() {
        // 初始化控制器实例
        userController = new UserControllerImpl();
        initComponents(); 
    }

    // 初始化注册界面组件
    private void initComponents() {
        // 窗口基础设置
        setTitle("user register");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // 窗口居中

        // 1. 创建界面组件
        JLabel usernameLabel = new JLabel("username:");
        JLabel passwordLabel = new JLabel("password:");
        JLabel confirmLabel = new JLabel("confirm password:");

        usernameField = new JFormattedTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        JButton registerBtn = new JButton("complete register");
        JButton backBtn = new JButton("Back to Login");

        // 2. 绑定按钮事件
        registerBtn.addActionListener(e -> {
            try {
                doRegister();
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); // 注册逻辑
        backBtn.addActionListener(e -> {
            new login().setVisible(true); // 打开登录界面
            this.dispose(); // 关闭注册界面
        });

        // 3. 布局管理（GridBagLayout 适配性更好）
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12); // 组件间距
        gbc.anchor = GridBagConstraints.CENTER;

        // 添加用户名标签和输入框
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameField, gbc);

        // 添加密码标签和输入框
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordField, gbc);

        // 添加确认密码标签和输入框
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(confirmLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(confirmPasswordField, gbc);

        // 添加按钮
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(registerBtn, gbc);

        gbc.gridx = 2;
        panel.add(backBtn, gbc);

        // 将面板添加到窗口
        getContentPane().add(panel);
    }

    // 核心：注册逻辑处理
        private void doRegister() {

        // 1. 获取输入内容并去空格
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPwd = new String(confirmPasswordField.getPassword());

        // 2. 输入合法性校验（保持不变）
        if (username.isEmpty() || password.isEmpty() || confirmPwd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "The username/password cannot be empty.", "tip", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (username.length() < 3 || username.length() > 20) {
            JOptionPane.showMessageDialog(this, "The username length should be between 3 and 20 characters.", "tip", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "The password length must be no less than 6 characters.", "tip", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!password.equals(confirmPwd)) {
            JOptionPane.showMessageDialog(this, "The two password entries do not match!", "tip", JOptionPane.WARNING_MESSAGE);
            confirmPasswordField.setText("");
            return;
        }

        // 3. 通过控制器层实现注册逻辑
        try {
            // 3.1 检查用户名是否已存在
            if (userController.isUserNameExists(username)) {
                JOptionPane.showMessageDialog(this, "username has already been registered", "tip", JOptionPane.WARNING_MESSAGE);
                usernameField.setText("");
                return;
            }

            // 3.2 调用注册方法（角色默认为普通用户，可根据需求调整）
            userController.register(username, password, "user");
            
            // 3.3 注册成功处理
            JOptionPane.showMessageDialog(this, "registered successfully", "success", JOptionPane.INFORMATION_MESSAGE);
            new login().setVisible(true);
            this.dispose();

        } catch (Exception e) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "registered failed：" + e.getMessage(), "failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 测试入口（不变）
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Register().setVisible(true));
    }
}