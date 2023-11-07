package dao;

import domain.ResearchEnroll;
import domain.SelfResearchScore;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by widesui on 20/7/12.
 */
public class ResearchFileDownloadDAO {
    private String getYearsFromResearchEnrollSql =
            "select distinct DATE_FORMAT(submit_time,'%Y') from research_enroll order by DATE_FORMAT(submit_time,'%Y');";

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
            "    where judge.is2021Jugde = 1 " +
            ") " +
            "group by mix.id " +
            "order by mix.groupId;";



    private String getStagesql = "select e.research_type as research_type from research_enroll e where e.user_id = ? and year(e.submit_time) = ?";

    private String getApplyResFileListByIdYearSql = "select id,submit_time,user_id,user_name," +
            "projectNo,title,file_path,user_cv,research_type,score,output_zipfile_path" +
            " from research_enroll " +
            "where user_id = ? and research_type=? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01'))";

    private String getResFileListByIdYearSql = "select id,submit_time,user_id,user_name," +
            "projectNo,title,file_path,user_cv,research_type,score,output_zipfile_path" +
            " from research_enroll " +
            "where year(submit_time) = ? and user_id = ? and research_type=?";

    private String getResFileListByYearSql = "select id,submit_time,user_id,user_name," +
            "projectNo,title,file_path,user_cv,research_type,score,output_zipfile_path from research_enroll " +
            "where year(submit_time) = ? order by user_name,research_type";

    //20211006 得到教师界面个人项目的文件
    public ArrayList<ResearchEnroll> getResFileByIdYear(String userId, int year, String researchType) {

        ArrayList<ResearchEnroll> list = new ArrayList<ResearchEnroll>();
        Connection connection = DBUtil.getConnection();
        try {
            // 通过教师工号、姓名和年份查询该教师在这一年提交过的材料
            PreparedStatement preparedStatement;
            if(researchType.equals("项目申报")){
                preparedStatement = connection.prepareStatement(getApplyResFileListByIdYearSql);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, researchType);
                preparedStatement.setInt(3, year-1);
                preparedStatement.setInt(4, year);
            }else{
                preparedStatement = connection.prepareStatement(getResFileListByIdYearSql);
                preparedStatement.setInt(1, year);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3, researchType);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getResearchEnrollFromResultSet(resultSet));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //20211006 得到教师界面个人项目的文件
    public ArrayList<ResearchEnroll> getResFileByYear(int year) {



        ArrayList<ResearchEnroll> list = new ArrayList<ResearchEnroll>();
        Connection connection = DBUtil.getConnection();
        try {
            // 通过教师工号、姓名和年份查询该教师在这一年提交过的材料
            PreparedStatement preparedStatement = connection.prepareStatement(getResFileListByYearSql);
            preparedStatement.setInt(1, year);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getResearchEnrollFromResultSet(resultSet));
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

    private ResearchEnroll getResearchEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        ResearchEnroll researchEnroll = new ResearchEnroll();
        researchEnroll.setId(resultSet.getInt(1));
        researchEnroll.setSubmitTime(resultSet.getDate(2));
        researchEnroll.setUserId(resultSet.getString(3));
        researchEnroll.setUsername(resultSet.getString(4));
        researchEnroll.setProjectNo(resultSet.getString(5));
        researchEnroll.setTitle(resultSet.getString(6));
        researchEnroll.setFilePath(resultSet.getString(7));
        researchEnroll.setUserCV(resultSet.getString(8));
        researchEnroll.setResearchType(resultSet.getString(9));
        researchEnroll.setScore(resultSet.getInt(10));
        researchEnroll.setOutputZipFilePath(resultSet.getString(11));
        return researchEnroll;
    }

    // For Test
    public static void main(String args[]) {
        ResearchFileDownloadDAO dao = new ResearchFileDownloadDAO();

        /*ArrayList<ResultGet> list = dao.getResultByYear(2021);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitle());
        }*/

    }
}
