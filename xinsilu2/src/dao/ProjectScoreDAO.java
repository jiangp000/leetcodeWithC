package dao;

import domain.ProjectScore;
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
public class ProjectScoreDAO {
    private String insertSql = "insert into project_score (project_enroll_id,judge_id,video_score,document_score,comment) values (?,?,?,?,?);";

    private String updateSql = "update project_score set video_score = ?, document_score = ?, comment=? where id = ?";

    private String deleteSql = "delete from project_score where id = ?";

    private String isScoredSql = "select id from project_score where judge_id=? and project_enroll_id=?";
    private String getAvgScoreListByProjectEnrollIdSql = "select score from project_score where project_enroll_id=?";
    private String getProjectScoreListByJudgeIdSql = "select * from project_score where judge_id=? and unix_timestamp(submit_time)*1000 > ?";


    public void insertProjectScore(ProjectScore projectScore) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, projectScore.getProjectEnrollId());
            preparedStatement.setInt(2, projectScore.getJudgeId());
            preparedStatement.setInt(3, projectScore.getVideoScore());
            preparedStatement.setInt(4, projectScore.getDocumentScore());
            preparedStatement.setString(5, projectScore.getComment());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProjectScore(ProjectScore projectScore) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, projectScore.getVideoScore());
            preparedStatement.setInt(2, projectScore.getDocumentScore());
            preparedStatement.setString(3, projectScore.getComment());
            preparedStatement.setInt(4, projectScore.getId());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteProjectScore(int id) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int isScored(int judgeId, int projectEnrollId) {
        Connection connection = DBUtil.getConnection();
        int projectScoreId = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(isScoredSql);
            preparedStatement.setInt(1, judgeId);
            preparedStatement.setInt(2, projectEnrollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                projectScoreId = resultSet.getInt(1);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectScoreId;
    }


    public int getAvgScoreListByProjectEnrollId(int projectEnrollId) {
        int sum = 0;
        int num = 0;

        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAvgScoreListByProjectEnrollIdSql);
            preparedStatement.setInt(1, projectEnrollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sum += resultSet.getInt(1);
                num += 1;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (num == 0) {
            return sum;
        }
        return sum / num;
    }

    public ArrayList<ProjectScore> getProjectScoreListByJudgeId(int judgeId) {
        /*查询该评委近两个月的评分情况*/
        ArrayList<ProjectScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.add(Calendar.DAY_OF_YEAR, -60);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getProjectScoreListByJudgeIdSql);
            preparedStatement.setInt(1, judgeId);
            preparedStatement.setLong(2, date.getTime().getTime());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProjectScore projectScore = getProjectScoreFromResultSet(resultSet);
                list.add(projectScore);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ProjectScore getProjectScoreFromResultSet(ResultSet resultSet) throws SQLException {
        ProjectScore projectScore = new ProjectScore();
        projectScore.setId(resultSet.getInt(1));
        projectScore.setProjectEnrollId(resultSet.getInt(2));
        projectScore.setJudgeId(resultSet.getInt(3));
        projectScore.setScore(resultSet.getInt(4));
        projectScore.setComment(resultSet.getString(5));
        return projectScore;
    }


    public static void main(String args[]) {

        ProjectScoreDAO dao = new ProjectScoreDAO();
        System.out.print(dao.isScored(2,3));


    }


}
