package dao;

import domain.ResearchEnroll;
import domain.ResearchScore;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jinggu on 19/4/28.
 */
public class ResearchScoreDAO {
    private String insertSql = "insert into research_score (research_enroll_id,judge_id,video_score,document_score,score,comment,submit_save) values (?,?,?,?,?,?,?);";

    private String updateSql = "update research_score set video_score = ?, document_score = ?, score = ?, comment=?, submit_save=? where id = ?";

    private String updateAdminJudgeSql = "update research_score set  comment=?, score=? where id = ?";

    private String updateScoreSaveStageSql = "update research_score set submit_save=0 where judge_id = (select id from judge where username=?) and TIMESTAMPDIFF(MONTH ,submit_time,now()) < 2";

    private String updatePaperProjectNoSql = "update research_enroll set projectNo=?,case_sort=? where user_id=? and user_name=? and research_type=? and YEAR(submit_time)=?";

    private String updatePaperJudgeResultSql = "update research_enroll set case_sort=? where user_id=? and user_name=? and research_type=? and YEAR(submit_time)=?";

    private String updateFinalProjectNoSql = "update research_enroll set projectNo=?,case_sort=? where user_id=? and user_name=? and research_type=? and YEAR(submit_time)=?";

    private String updateFinalJudgeResultSql = "update research_enroll set case_sort=? where user_id=? and user_name=? and research_type=? and YEAR(submit_time)=?";

    private String deleteSql = "delete from research_score where id = ?";

    private String isScoredSql = "select id from research_score where judge_id=? and research_enroll_id=?";
    private String getAvgScoreListByResearchEnrollIdSql = "select score from research_score where research_enroll_id=?";
    private String getResearchScoreListByJudgeIdSql = "select * from research_score where judge_id=? and unix_timestamp(submit_time)*1000 > ?";
    private String getResearchScoreByJudgeIdAndResearchEnrollIdSql = "select * from research_score where judge_id=? and research_enroll_id=?";

    private String getResearchScoreByResearchEnrollIdSql = "select * from research_score where research_enroll_id=?";


    public void insertResearchScore(ResearchScore researchScore) {
        Connection connection = DBUtil.getConnection();
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = formatter.format(calendar.getTime());
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, researchScore.getResearchEnrollId());
            preparedStatement.setInt(2, researchScore.getJudgeId());
            preparedStatement.setInt(3, researchScore.getVideoScore());
            preparedStatement.setInt(4, researchScore.getDocumentScore());
            preparedStatement.setDouble(5, researchScore.getScore());
            preparedStatement.setString(6, researchScore.getComment());
            preparedStatement.setString(7, researchScore.getSubmitSave());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateResearchScore(ResearchScore researchScore) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, researchScore.getVideoScore());
            preparedStatement.setInt(2, researchScore.getDocumentScore());
            preparedStatement.setDouble(3, researchScore.getScore());
            preparedStatement.setString(4, researchScore.getComment());
            preparedStatement.setString(5, researchScore.getSubmitSave());
            preparedStatement.setInt(6, researchScore.getId());

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新 管理员评分的 前三个分数的成绩，对所有评委的评分前三个成绩都更新
    public void updateResearchAdminJudgeScore(ResearchScore researchScore) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateAdminJudgeSql);
            preparedStatement.setString(1, researchScore.getComment());
            preparedStatement.setDouble(2, researchScore.getScore());
            preparedStatement.setInt(3, researchScore.getId());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新 教研论文 项目编号
    public void updateResearchPaperProjectNo(ResearchEnroll researchEnroll) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updatePaperProjectNoSql);
            preparedStatement.setString(1, researchEnroll.getProjectNo());
            preparedStatement.setString(2, researchEnroll.getCaseSort());
            preparedStatement.setString(3, researchEnroll.getUserId());
            preparedStatement.setString(4, researchEnroll.getUsername());
            preparedStatement.setString(5, researchEnroll.getResearchType());
            preparedStatement.setInt(6, year);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新 教研论文 最终 评审结果
    public void updateResearchPaperJudgeResult(ResearchEnroll researchEnroll) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updatePaperJudgeResultSql);
            preparedStatement.setString(1, researchEnroll.getCaseSort());
            preparedStatement.setString(2, researchEnroll.getUserId());
            preparedStatement.setString(3, researchEnroll.getUsername());
            preparedStatement.setString(4, researchEnroll.getResearchType());
            preparedStatement.setInt(5, year);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新结题报告 项目编号
    public void updateResearchFinalProjectNo(ResearchEnroll researchEnroll) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateFinalProjectNoSql);
            preparedStatement.setString(1, researchEnroll.getProjectNo());
            preparedStatement.setString(2, researchEnroll.getCaseSort());
            preparedStatement.setString(3, researchEnroll.getUserId());
            preparedStatement.setString(4, researchEnroll.getUsername());
            preparedStatement.setString(5, researchEnroll.getResearchType());
            preparedStatement.setInt(6, year);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新结题报告最终 评审结果
    public void updateResearchFinalJudgeResult(ResearchEnroll researchEnroll) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateFinalJudgeResultSql);
            preparedStatement.setString(1, researchEnroll.getCaseSort());
            preparedStatement.setString(2, researchEnroll.getUserId());
            preparedStatement.setString(3, researchEnroll.getUsername());
            preparedStatement.setString(4, researchEnroll.getResearchType());
            preparedStatement.setInt(5, year);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新评委教师的保存提交状态
    public void updateScoreSave(String year, String researchType, String judgeName) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateScoreSaveStageSql);
            preparedStatement.setString(1, judgeName);

            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteResearchScore(int id) {
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

    public int isScored(int judgeId, int researchEnrollId) {
        Connection connection = DBUtil.getConnection();
        int researchScoreId = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(isScoredSql);
            preparedStatement.setInt(1, judgeId);
            preparedStatement.setLong(2, researchEnrollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                researchScoreId = resultSet.getInt(1);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return researchScoreId;
    }

    public int getAvgScoreListByResearchEnrollId(int researchEnrollId) {
        int sum = 0;
        int num = 0;

        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAvgScoreListByResearchEnrollIdSql);
            preparedStatement.setInt(1, researchEnrollId);
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

    public ArrayList<ResearchScore> getResearchScoreListByJudgeId(int judgeId) {
        ArrayList<ResearchScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.add(Calendar.DAY_OF_YEAR, -60);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchScoreListByJudgeIdSql);
            preparedStatement.setInt(1, judgeId);
            preparedStatement.setLong(2, date.getTime().getTime());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ResearchScore researchScore = getResearchScoreFromResultSet(resultSet);
                list.add(researchScore);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ResearchScore> getResearchScoreByJudgeIdAndResearchEnrollId(int judgeId, int researchEnrollId) {
        ArrayList<ResearchScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchScoreByJudgeIdAndResearchEnrollIdSql);
            preparedStatement.setInt(1, judgeId);
            preparedStatement.setInt(2, researchEnrollId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ResearchScore researchScore = getResearchScoreFromResultSet(resultSet);
                list.add(researchScore);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //更新管理员前三个分数时获取 该教师 之前所有评委评的分数列表
    public ArrayList<ResearchScore> getResearchScoreByResearchEnrollId(int researchEnrollId) {
        ArrayList<ResearchScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchScoreByResearchEnrollIdSql);
            preparedStatement.setInt(1, researchEnrollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ResearchScore researchScore = getResearchScoreFromResultSet(resultSet);
                list.add(researchScore);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ResearchScore getResearchScoreFromResultSet(ResultSet resultSet) throws SQLException {
        ResearchScore researchScore = new ResearchScore();
        researchScore.setId(resultSet.getInt(1));
        researchScore.setResearchEnrollId(resultSet.getInt(2));
        researchScore.setJudgeId(resultSet.getInt(3));
        researchScore.setVideoScore(resultSet.getInt(4));
        researchScore.setDocumentScore(resultSet.getInt(5));
        researchScore.setScore(resultSet.getDouble(6));
        researchScore.setComment(resultSet.getString(7));
        researchScore.setSubmitSave(resultSet.getString(9));
        return researchScore;
    }


    public static void main(String args[]) {

        ResearchScoreDAO dao = new ResearchScoreDAO();
        System.out.println(dao.isScored(1,4));
        ArrayList<ResearchScore> list = dao.getResearchScoreListByJudgeId(1);
        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getFilePath());
            System.out.println(list.get(i).getScore());
        }


    }


}
