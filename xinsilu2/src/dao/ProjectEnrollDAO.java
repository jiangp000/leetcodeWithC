package dao;

import domain.ProjectEnroll;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by jinggu on 19/4/28.
 */
public class ProjectEnrollDAO {

    private String deleteEnrollByUserIdSql = "delete from project_enroll where user_id = ? and TIMESTAMPDIFF(MONTH ,submit_time,now()) < 2;";
    private String insertEnrollSql = "insert into project_enroll (user_id,user_name,title,file_path,user_cv,score,department,projectType,p_found,p_month) values (?,?,?,?,?,?,?,?,?,?);";

    private String updateProjectEnrollScoreSql = "update project_enroll set score = ? where id = ?";

    private String getProjectEnrollByIdSql = "select * from project_enroll where id = ?";
    private String getEnrollListByYearSql = "select * from project_enroll where DATE_FORMAT(submit_time,'%Y')=?";
    private String getEnrollListRecentSql = "select * from project_enroll where unix_timestamp(submit_time)*1000 > ?";
    private String getYearsFromProjectEnrollSql =
            "select distinct DATE_FORMAT(submit_time,'%Y') from project_enroll order by DATE_FORMAT(submit_time,'%Y');";

    private String getEnrollByUserIdAndDateSql = "select * from project_enroll where user_id =? and submit_time > ?";

    public void insertProjectEnroll(ProjectEnroll projectEnroll) {
        Connection connection = DBUtil.getConnection();
        try {
            /*一个user_id两个月内只能提交一个项目申请*/
            PreparedStatement preparedStatement = connection.prepareStatement(deleteEnrollByUserIdSql);
            preparedStatement.setString(1, projectEnroll.getUserId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(insertEnrollSql);
            preparedStatement.setString(1, projectEnroll.getUserId());
            preparedStatement.setString(2, projectEnroll.getUsername());
            preparedStatement.setString(3, projectEnroll.getTitle());
            preparedStatement.setString(4, projectEnroll.getFilePath());
            preparedStatement.setString(5, projectEnroll.getUserCV());
            preparedStatement.setInt(6, projectEnroll.getScore());
            preparedStatement.setString(7, projectEnroll.getDepartment());
            preparedStatement.setString(8, projectEnroll.getProjectType());
            preparedStatement.setFloat(9, projectEnroll.getFound());
            preparedStatement.setInt(10, projectEnroll.getMonths());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProjectEnrollScore(int score, int id) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateProjectEnrollScoreSql);
            preparedStatement.setInt(1, score);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ProjectEnroll getProjectEnrollById(int id) {
        Connection connection = DBUtil.getConnection();
        ProjectEnroll projectEnroll = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getProjectEnrollByIdSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                projectEnroll = getProjectEnrollFromResultSet(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectEnroll;

    }

    public ArrayList<ProjectEnroll> getEnrollListByYear(int year) {
        ArrayList<ProjectEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getEnrollListByYearSql);
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getProjectEnrollFromResultSet(resultSet));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ProjectEnroll> getEnrollListRecent() {
        ArrayList<ProjectEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.add(Calendar.DAY_OF_YEAR, -60);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getEnrollListRecentSql);
            preparedStatement.setLong(1, date.getTime().getTime());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getProjectEnrollFromResultSet(resultSet));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ProjectEnroll getEnrollByUserIdAndDate(String userId, java.sql.Date date) {
        ArrayList<ProjectEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getEnrollByUserIdAndDateSql);
            preparedStatement.setString(1, userId);
            preparedStatement.setDate(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getProjectEnrollFromResultSet(resultSet));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.get(0);
    }

    public ArrayList<Integer> getYearsFromProjectEnroll() {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getYearsFromProjectEnrollSql);
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

    private ProjectEnroll getProjectEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        ProjectEnroll projectEnroll = new ProjectEnroll();
        projectEnroll.setId(resultSet.getInt(1));
        projectEnroll.setSubmitTime(resultSet.getDate(2));
        projectEnroll.setUserId(resultSet.getString(3));
        projectEnroll.setUsername(resultSet.getString(4));
        projectEnroll.setTitle(resultSet.getString(5));
        projectEnroll.setFilePath(resultSet.getString(6));
        projectEnroll.setUserCV(resultSet.getString(7));
        projectEnroll.setScore(resultSet.getInt(8));
        projectEnroll.setDepartment(resultSet.getString(9));
        projectEnroll.setProjectType(resultSet.getString(10));
        projectEnroll.setFound(resultSet.getFloat(11));
        projectEnroll.setMonths(resultSet.getInt(12));
        return projectEnroll;
    }

    public static void main(String args[]) {
        ProjectEnrollDAO dao = new ProjectEnrollDAO();

        ProjectEnroll projectEnroll = new ProjectEnroll();
        projectEnroll.setUserId("00000623470");
        projectEnroll.setUsername("测试教师4");
        projectEnroll.setTitle("ceshi");
        projectEnroll.setFilePath("filepath.zip");
        projectEnroll.setUserCV("usercv");
        projectEnroll.setScore(100);
        projectEnroll.setDepartment("软件学院");
        projectEnroll.setProjectType("一般");
        projectEnroll.setFound(4.15f);
        projectEnroll.setMonths(6);


        dao.insertProjectEnroll(projectEnroll);


        ArrayList<ProjectEnroll> list = dao.getEnrollListRecent();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUsername());
        }

    }
}
