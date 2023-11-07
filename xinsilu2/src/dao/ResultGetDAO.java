package dao;

import domain.ResultGet;
import domain.ResultScore;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by widesui on 20/7/12.
 */
public class ResultGetDAO {
    private String getYearsFromResearchScorelSql =
            "select distinct DATE_FORMAT(submit_time,'%Y') from research_score order by DATE_FORMAT(submit_time,'%Y');";

//    private String getResult = "select e.user_id as userId, e.user_name as userName, e.title as title, avg(s.video_score)*0.3 as videoScore, avg(s.document_score)*0.3 as documentScore, avg(s.video_score)*0.3 + avg(s.document_score)*0.3 as score\n" +
//            "from research_score s, research_enroll e " +
//            "where e.id = s.research_enroll_id and year(s.submit_time) = ? " +
//            "group by s.research_enroll_id " +
//            "order by avg(s.video_score)*0.3 + avg(s.document_score)*0.3 desc";

    private String getResult = "select mix.user_id, mix.user_name, mix.title, if(count(mix.video_score) > 2, (sum(mix.video_score)-max(mix.video_score)-min(mix.video_score))/(count(mix.video_score)-2)*0.3, 0), if(count(mix.document_score) > 2, (sum(mix.document_score)-max(mix.document_score)-min(mix.document_score))/(count(mix.document_score)-2)*0.3, 0), if(count(mix.document_score) > 2, (sum(mix.video_score)-max(mix.video_score)-min(mix.video_score))/(count(mix.video_score)-2)*0.3 + (sum(mix.document_score)-max(mix.document_score)-min(mix.document_score))/(count(mix.document_score)-2)*0.3, 0), count(mix.video_score), mix.groupId " +
            "from ( " +
            "         select s.judge_id as judge_id, e.user_id as user_id, e.user_name as user_name, e.id as id, e.title as title, s.video_score as video_score, s.document_score as document_score, e.group as groupId " +
            "         from research_enroll e " +
            "                  left join research_score s " +
            "                            on e.id = s.research_enroll_id and year(s.submit_time) = ? " +
            "         where year(e.submit_time) = ? " +
            "     ) mix " +
            "where mix.judge_id in( " +
            "    select judge.id " +
            "    from judge " +
            "    where judge.is2021Judge =? " +
            ") " +
            "group by mix.id " +
            "order by mix.groupId;";

    private String getPaperFinalResult = "select d.user_id as user_id,d.user_name as user_name,d.title as title,\n" +
            "d.projectNo as project_no,d.dept_name as dept_name,d.research_type as research_type,\n" +
            "round(if(count(score)>2,(sum(d.score)-max(d.score)-min(d.score))/(count(d.score)-2),0),2),count(d.score) as times\n" +
            "from\n" +
            "(select b.user_id as user_id,b.user_name as user_name,b.title as title,b.projectNo as projectNo,\n" +
            "b.dept_name as dept_name,b.research_type as research_type,c.score\n" +
            "from\n" +
            "(select a.id as id,a.user_id as user_id,a.user_name as user_name,a.title as title,a.projectNo as projectNo,\n" +
            "a.dept_name as dept_name,a.research_type as research_type\n" +
            "from research_enroll a\n" +
            "where a.research_type in(?,?) and YEAR(submit_time)=?) b\n" +
            "left join \n" +
            "(select e.research_enroll_id as research_enroll_id,e.score as score\n" +
            "from research_score e\n" +
            "where e.judge_id in (\n" +
            "select id\n" +
            "from judge \n" +
            "where substring(judge.is2021Judge,4,1)='1' or substring(judge.is2021Judge,5,1)='1'\n" +
            "))c\n" +
            "on b.id=c.research_enroll_id)d\n" +
            "group by d.user_id,d.research_type\n" +
            "order by SUBSTRING(d.projectNo,5,2) desc,SUBSTRING(d.projectNo,7,3) asc";



    public ArrayList<ResultGet> getResultByYear(int year) {
        ArrayList<ResultGet> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResult);
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, year);
            preparedStatement.setString(3,"1");
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

    public ArrayList<ResultScore> getPaperFinalScoreByYear(int year) {
        ArrayList<ResultScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getPaperFinalResult);
            preparedStatement.setString(1,"教研论文");
            preparedStatement.setString(2,"结题报告");
            preparedStatement.setInt(3,2021);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getScoreFromResultSet(resultSet));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    //20211006 得到教师界面个人项目的分数
    public ArrayList<ResultGet> getResScoreByYear(int year) {
        ArrayList<ResultGet> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResult);
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, year);
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


    public ArrayList<Integer> getYearsFromResearchScore() {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getYearsFromResearchScorelSql);
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

    private ResultScore getScoreFromResultSet(ResultSet resultSet) throws SQLException{
        ResultScore resultScore = new ResultScore();
        resultScore.setUserId(resultSet.getString(1));
        resultScore.setUsername(resultSet.getString(2));
        resultScore.setTitle(resultSet.getString(3));
        resultScore.setProjectNo(resultSet.getString(4));
        resultScore.setDepartment(resultSet.getString(5));
        resultScore.setResearchType(resultSet.getString(6));
        resultScore.setScore(resultSet.getDouble(7));
        resultScore.setTimes(resultSet.getInt(8));
        return resultScore;
    }

    private ResultGet getProjectEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        ResultGet resultGet = new ResultGet();
        resultGet.setUserId(resultSet.getString(1));
        resultGet.setUsername(resultSet.getString(2));
        resultGet.setTitle(resultSet.getString(3));
        resultGet.setVideoScore(resultSet.getDouble(4));
        resultGet.setDocumentScore(resultSet.getDouble(5));
        resultGet.setScore(resultSet.getDouble(6));
        resultGet.setTimes(resultSet.getInt(7));
        resultGet.setGroupId(resultSet.getString(8));
        return resultGet;
    }

    // For Test
    public static void main(String args[]) {
        ResultGetDAO dao = new ResultGetDAO();

        ArrayList<ResultGet> list = dao.getResultByYear(2021);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitle());
        }

    }
}
