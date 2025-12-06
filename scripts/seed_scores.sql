connect 'jdbc:derby:MyDB;create=true;user=Admin;password=123456';

-- 清理旧的测试数据（忽略错误继续执行）
-- 如果表不存在，会报错，可忽略
-- 删除指定范围内的成绩和学生记录，避免重复主键
DELETE FROM scores WHERE student_id BETWEEN 20001 AND 20005;
DELETE FROM students WHERE student_id BETWEEN 20001 AND 20005;

-- 插入测试学生
INSERT INTO Students (student_id, name, age, gender, major, class, admission_date, phone, email) VALUES
  (20001, 'Li Hua', 20, 'M', 'Computer Science', 'CS-2201', DATE('2023-09-01'), '13800000001', 'lihua@example.com');
INSERT INTO Students (student_id, name, age, gender, major, class, admission_date, phone, email) VALUES
  (20002, 'Zhang Wei', 21, 'M', 'Computer Science', 'CS-2201', DATE('2022-09-01'), '13800000002', 'zhangwei@example.com');
INSERT INTO Students (student_id, name, age, gender, major, class, admission_date, phone, email) VALUES
  (20003, 'Chen Jie', 20, 'F', 'Information Systems', 'IS-2202', DATE('2023-09-01'), '13800000003', 'chenjie@example.com');
INSERT INTO Students (student_id, name, age, gender, major, class, admission_date, phone, email) VALUES
  (20004, 'Wang Fang', 19, 'F', 'Information Systems', 'IS-2202', DATE('2024-09-01'), '13800000004', 'wangfang@example.com');
INSERT INTO Students (student_id, name, age, gender, major, class, admission_date, phone, email) VALUES
  (20005, 'Liu Yang', 22, 'M', 'Software Engineering', 'SE-2101', DATE('2021-09-01'), '13800000005', 'liuyang@example.com');

-- 插入测试成绩
INSERT INTO scores (student_id, course_code, score, exam_date) VALUES
  (20001, '1001', 95.5, DATE('2024-06-18'));
INSERT INTO scores (student_id, course_code, score, exam_date) VALUES
  (20001, '1002', 83.0, DATE('2024-05-30'));
INSERT INTO scores (student_id, course_code, score, exam_date) VALUES
  (20002, '1005', 88.0, DATE('2024-06-10'));
INSERT INTO scores (student_id, course_code, score, exam_date) VALUES
  (20003, '1007', 91.0, DATE('2024-06-05'));
INSERT INTO scores (student_id, course_code, score, exam_date) VALUES
  (20003, '1004', 76.5, DATE('2024-05-25'));
INSERT INTO scores (student_id, course_code, score, exam_date) VALUES
  (20004, '1007', 89.5, DATE('2024-06-05'));
INSERT INTO scores (student_id, course_code, score, exam_date) VALUES
  (20005, '1008', 72.0, NULL);
INSERT INTO scores (student_id, course_code, score, exam_date) VALUES
  (20005, '1003', 81.0, DATE('2024-04-22'));
COMMIT;

exit;
