package dao;

import domain.*;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by widesui on 20/7/12.
 */
public class StageControlDAO {
    private String getYearsFromResearchScorelSql = "select distinct DATE_FORMAT(submit_time,'%Y') from research_score;";

    private String updateSubmitStageByStage = "update stage_control set submit_stage_control=? " +
            "where year=? and research_type=?;";
    private String updateScoreStageByStage = "update stage_control set score_stage_control=? " +
            "where year=? and research_type=?;";

    private String updateApplyScoreSaveByStage = "update research_score set submit_save=0 where research_enroll_id in(select id from research_enroll where ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01')) and research_type=?)";

    private String updateScoreSaveByStage = "update research_score set submit_save=0 where research_enroll_id in(select id from research_enroll where YEAR(submit_time)=? and research_type=?)";


    private String getJudgesql = "select j.user_id from judge j where j.is2021Judge = 1;";
    private String getInnoPreJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,2,1)='1' or substring(j.is2021Judge,2,1)='2' order by substring(j.is2021Judge,2,1),user_id";
    private String getInnoPPTJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,2,1)='2' or substring(j.is2021Judge,2,1)='3' order by substring(j.is2021Judge,2,1),user_id";
    //private String getInnoPreJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,2,1)='1' order by user_id";
    //private String getInnoPPTJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,2,1)='2' order by user_id";

    private String getPaperJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,4,1)='1' order by user_id";
    //private String getPaperJudgeSql = "select j.user_id from judge j where j.id in (22,24,101,102,109,113,114)";
    //private String getPaperJudgeSql = "select j.user_id from judge j where j.id in (22,23,24,25,26,110)";
    private String getFinalJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,5,1)='1' order by user_id";

    //更新提交阶段控制
    public void updateSubmitStage(StageControl stageControl) {

        Connection connection = DBUtil.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(updateSubmitStageByStage);
            preparedStatement.setString(1, stageControl.getSubmitStageControl());
            preparedStatement.setString(2, stageControl.getYear());
            preparedStatement.setString(3, stageControl.getResearchType());

            preparedStatement.executeUpdate();



            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //更新提交阶段控制
    public void updateScoreStage(StageControl stageControl) {

        Connection connection = DBUtil.getConnection();
        try {

            PreparedStatement preparedStatement;
            PreparedStatement preparedStatement1 = null;

            preparedStatement = connection.prepareStatement(updateScoreStageByStage);
            preparedStatement.setString(1, stageControl.getScoreStageControl());
            preparedStatement.setString(2, stageControl.getYear());
            preparedStatement.setString(3, stageControl.getResearchType());

            if(stageControl.getScoreStageControl().equals("3")){
                if(stageControl.getResearchType().equals("项目申报")){
                    int currentYear = Integer.parseInt(stageControl.getYear());
                    preparedStatement1 = connection.prepareStatement(updateApplyScoreSaveByStage);
                    preparedStatement1.setInt(1, currentYear - 1);
                    preparedStatement1.setInt(2, currentYear);
                    preparedStatement1.setString(3, stageControl.getResearchType());

                }else{
                    preparedStatement1 = connection.prepareStatement(updateScoreSaveByStage);
                    int currentYear = Integer.parseInt(stageControl.getYear());
                    preparedStatement1.setInt(1, currentYear);
                    preparedStatement1.setString(2, stageControl.getResearchType());
                }
                preparedStatement1.executeUpdate();
            }

            preparedStatement.executeUpdate();


            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closePreparedStatement(preparedStatement1);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // For Test
    public static void main(String args[]) {
        String comment = "null";
        String score = comment.substring(1, comment.indexOf(']'));
        String[] scores = score.split(",");
        System.out.println(scores.length);
    }
}
