package dao;

import domain.Teacher;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by jinggu on 19/5/2.
 */
public class TeacherDAO {
    //    private String getTeacherListFromProjectEnrollByYear =
//            "select distinct userId,username from project_enroll where DATE_FORMAT(submit_time,'%Y')=? ";
//    private String getTeacherListFromTrainEnrollByYear = "" +
//            "select distinct userId,username from train_enroll where DATE_FORMAT(submit_time,'%Y')=?";
    // 只有提交了课题报告的教师才会出现在作品展示里
    private String getYearsFromResearchEnrollSql =
            "select distinct DATE_FORMAT(submit_time,'%Y') from research_enroll order by  DATE_FORMAT(submit_time,'%Y');";
    private String getTeacherListFromResearchEnrollByYearSql =
            "select distinct user_id,user_name from research_enroll where DATE_FORMAT(submit_time,'%Y')=?";
    private String getTeacherFromProjectEnrollByUserIdSql =
            "select user_id,user_name,department,email,funds from project_enroll where user_id=?" +
                    "and DATE_FORMAT(submit_time,'%Y')=?";
    private String getTeacherFromResearchEnrollByUserIdSql =
            "select title,file_path from research_enroll where user_id=? and DATE_FORMAT(submit_time,'%Y')=?";
    private String getTeacherFromProjectEnrollByUserIdAndDateSql =
            "select user_id,user_name,title from project_enroll where user_id=? and submit_time >?";

    public ArrayList<Integer> getYearsFromResearchEnroll() {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getYearsFromResearchEnrollSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Teacher> getTeacherListFromResearchEnrollByYear(int year) {
        ArrayList<Teacher> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getTeacherListFromResearchEnrollByYearSql);
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setUserId(resultSet.getString(1));
                teacher.setUsername(resultSet.getString(2));
                list.add(teacher);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Teacher getTeacherByUserId(String id, int year) {
        Teacher teacher = new Teacher();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getTeacherFromProjectEnrollByUserIdSql);
            preparedStatement.setString(1, id);
            preparedStatement.setInt(2, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher.setUserId(resultSet.getString(1));
                teacher.setUsername(resultSet.getString(2));
                teacher.setDepartment(resultSet.getString(3));
                teacher.setEmail(resultSet.getString(4));
                teacher.setFounds(resultSet.getFloat(5));
            }

            preparedStatement = connection.prepareStatement(getTeacherFromResearchEnrollByUserIdSql);
            preparedStatement.setString(1, id);
            preparedStatement.setInt(2, year);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher.setTitle(resultSet.getString(1));
                teacher.setFilePath(resultSet.getString(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;

    }

    public Teacher getTeacherByUserIdAndDate(String id, Date date) {
        Teacher teacher = new Teacher();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getTeacherFromProjectEnrollByUserIdAndDateSql);
            preparedStatement.setString(1, id);
            preparedStatement.setDate(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher.setUserId(resultSet.getString(1));
                teacher.setUsername(resultSet.getString(2));
                teacher.setTitle(resultSet.getString(3));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;

    }
    public static void main(String[] args) {
        String id = "00000623458";
        int year = 2016;
        Teacher teacher = new TeacherDAO().getTeacherByUserId(id, year);
        System.out.print(teacher.toString());
    }


}
