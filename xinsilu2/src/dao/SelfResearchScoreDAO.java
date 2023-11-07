package dao;

import domain.*;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by widesui on 20/7/12.
 */
public class SelfResearchScoreDAO {
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
            "    where judge.is2021Jugde = 1 " +
            ") " +
            "group by mix.id " +
            "order by mix.groupId;";

    private String getSelfResult = "select c.projectNo as projectNo,c.userid as userid,c.username as username, c.title as title,c.video_score as vidio_score,\n" +
            "substring_index(c.scorelist,',',1) as videoScore1,\n" +
            "substring_index(substring_index(c.scorelist,',',-8),',',1) as videoScore2,\n" +
            "substring_index(substring_index(c.scorelist,',',-7),',',1) as videoScore3,\n" +
            "substring_index(substring_index(c.scorelist,',',-6),',',1) as videoScore4,\n" +
            "substring_index(substring_index(c.scorelist,',',-5),',',1) as videoScore5,\n" +
            "substring_index(substring_index(c.scorelist,',',-4),',',1) as documentScore1,\n" +
            "substring_index(substring_index(c.scorelist,',',-3),',',1) as documentScore2,\n" +
            "substring_index(substring_index(c.scorelist,',',-2),',',1) as documentScore3,\n" +
            "substring_index(c.scorelist,',',-1) as documentScore4,\n" +
            "c.document_score as document_score,\n" +
            "c.score as score,\n" +
            "c.assessment as assessment,\n" +
            "c.researchtype as researchType\n" +
            "from \n" +
            "(select a.projectNo as projectNo,a.user_id as userid,a.user_name as username, a.title as title,a.research_type as researchtype,\n" +
            "b.judge_id as judge_id,b.video_score as video_score,b.document_score as document_score,b.score as score,\n" +
            "substring_index(substring_index(b.comment,'[',-1),']',1) as scorelist,\n" +
            "substring_index(substring_index(b.comment,'[',-1),']',-1) as assessment\n" +
            "from \n" +
            "(select e.id as enroll_id,e.user_id,e.user_name,e.title,e.research_type,e.projectNo from research_enroll e where e.user_id = ? and year(e.submit_time) = ? and e.research_type=?)a\n" +
            "left join research_score b\n" +
            "on a.enroll_id = b.research_enroll_id)c";

    private String getSelfInnoScoresql = "select c.projectNo as projectNo,c.userid as userid,c.username as username,c.dept_name as dept_name,c.team_member as team_member,c.title as title,\n" +
            "c.project_apply_sort,c.case_sort,c.project_sort,c.isPass,c.apply_pre_comment,\n" +
            "substring_index(c.scorelist,',',1) as judgeScore1,\n" +
            "substring_index(substring_index(c.scorelist,',',-13),',',1) as judgeScore2,\n" +
            "substring_index(substring_index(c.scorelist,',',-12),',',1) as judgeScore3,\n" +
            "substring_index(substring_index(c.scorelist,',',-11),',',1) as judgeScore4,\n" +
            "substring_index(substring_index(c.scorelist,',',-10),',',1) as judgeScore5,\n" +
            "substring_index(substring_index(c.scorelist,',',-9),',',1) as judgeScore6,\n" +
            "substring_index(substring_index(c.scorelist,',',-8),',',1) as judgeScore7,\n" +
            "substring_index(substring_index(c.scorelist,',',-7),',',1) as judgeScore8,\n" +
            "substring_index(substring_index(c.scorelist,',',-6),',',1) as judgeScore9,\n" +
            "substring_index(substring_index(c.scorelist,',',-5),',',1) as judgeScore10,\n" +
            "substring_index(substring_index(c.scorelist,',',-4),',',1) as judgeScore11,\n" +
            "substring_index(substring_index(c.scorelist,',',-3),',',1) as judgeScore12,\n" +
            "substring_index(substring_index(c.scorelist,',',-2),',',1) as judgeScore13,\n" +
            "substring_index(substring_index(c.scorelist,',',-1),',',1) as judgeScore14,\n" +
            "c.score as score,\n" +
            "c.assessment as assessment,\n" +
            "c.research_type as research_type\n" +
            "from \n" +
            "(select a.projectNo as projectNo,a.user_id as userid,a.user_name as username, a.title as title,a.project_apply_sort,a.case_sort,a.project_sort,a.isPass,a.apply_pre_comment,a.research_type as research_type,\n" +
            "b.judge_id as judge_id,b.video_score as video_score,b.document_score as document_score,b.score as score,\n" +
            "substring_index(substring_index(b.comment,'[',-1),']',1) as scorelist,\n" +
            "substring_index(substring_index(b.comment,'[',-1),']',-1) as assessment,\n" +
            "a.dept_name as dept_name,a.team_member as team_member\n" +
            "from \n" +
            "(select e.id as enroll_id,e.user_id,e.user_name,e.title,e.project_apply_sort,e.case_sort,e.project_sort,e.isPass,e.apply_pre_comment,e.research_type,e.projectNo,e.dept_name,e.team_member from research_enroll e where e.user_id =? and year(e.submit_time) =? and research_type='创新大赛')a\n" +
            "left join (select research_score.* from research_score,judge where (if('网评分数'=?,'1','2')=SUBSTRING(judge.is2021Judge,2,1) or if('网评分数'=?,'2','3')=SUBSTRING(judge.is2021Judge,2,1)) and research_score.judge_id=judge.id)b\n" +
            "on a.enroll_id = b.research_enroll_id)c\n" +
            "order by c.judge_id asc;";

    private String getSelfPaperResult = "select c.projectNo as projectNo,c.userid as userid,c.username as username,c.dept_name as dept_name,c.team_member as team_member,c.title as title,\n" +
            "substring_index(c.scorelist,',',1) as judgeScore1,\n" +
            "substring_index(substring_index(c.scorelist,',',-10),',',1) as judgeScore2,\n" +
            "substring_index(substring_index(c.scorelist,',',-9),',',1) as judgeScore3,\n" +
            "substring_index(substring_index(c.scorelist,',',-8),',',1) as judgeScore4,\n" +
            "substring_index(substring_index(c.scorelist,',',-7),',',1) as judgeScore5,\n" +
            "substring_index(substring_index(c.scorelist,',',-6),',',1) as judgeScore6,\n" +
            "substring_index(substring_index(c.scorelist,',',-5),',',1) as judgeScore7,\n" +
            "substring_index(substring_index(c.scorelist,',',-4),',',1) as judgeScore8,\n" +
            "substring_index(substring_index(c.scorelist,',',-3),',',1) as judgeScore9,\n" +
            "substring_index(substring_index(c.scorelist,',',-2),',',1) as judgeScore10,\n" +
            "c.score as score,\n" +
            "c.assessment as assessment,\n" +
            "c.research_type as research_type\n" +
            "from \n" +
            "(select a.projectNo as projectNo,a.user_id as userid,a.user_name as username, a.title as title,a.research_type as research_type,\n" +
            "b.judge_id as judge_id,b.video_score as video_score,b.document_score as document_score,b.score as score,\n" +
            "substring_index(substring_index(b.comment,'[',-1),']',1) as scorelist,\n" +
            "substring_index(substring_index(b.comment,'[',-1),']',-1) as assessment,\n" +
            "a.dept_name as dept_name,a.team_member as team_member\n" +
            "from \n" +
            "(select e.id as enroll_id,e.user_id,e.user_name,e.title,e.research_type,e.projectNo,e.dept_name,e.team_member from research_enroll e where e.user_id =? and year(e.submit_time) =? and research_type=?)a\n" +
            "left join (select research_score.* from research_score,judge where if('教研论文'=?,SUBSTRING(judge.is2021Judge,4,1),SUBSTRING(judge.is2021Judge,5,1))='1' and research_score.judge_id=judge.id)b\n" +
            "on a.enroll_id = b.research_enroll_id)c\n" +
            "order by c.judge_id asc;";


    private String getStagesql = "select e.research_type as research_type from research_enroll e where e.user_id = ? and year(e.submit_time) = ?";

    //20211006 得到教师界面个人项目的分数
    public ArrayList<ArrayList<SelfResearchScore>> getSelfResScoreByIdYear(String userId, int year) {
        ArrayList<ArrayList<SelfResearchScore>> list = new ArrayList<ArrayList<SelfResearchScore>>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询该老师参与了几个阶段的提交
            PreparedStatement preparedStatement = connection.prepareStatement(getStagesql);
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, year);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            ResultSet resultSet2 = null;
            ArrayList<String> stages = new ArrayList<>();
            int nums = 0;
            // 将所有阶段放入stages
            while(resultSet1.next()){
                stages.add(resultSet1.getString(1));
                ++nums;
            }
            for(int i = 0;i < nums; ++i){
                String stage = stages.get(i);
                //System.out.println("------------judgeId" + stage);
                preparedStatement = connection.prepareStatement(getSelfResult);
                preparedStatement.setString(1, userId);
                preparedStatement.setInt(2, year);
                preparedStatement.setString(3, stage);
                //System.out.println("------------sql" + getSelfResult);
                resultSet2 = preparedStatement.executeQuery();
                ArrayList<SelfResearchScore> temp = new ArrayList<>();
                while (resultSet2.next()) {
                    temp.add(getProjectEnrollFromResultSet(resultSet2));
                }
                list.add(temp);
            }
            DBUtil.closeResultSet(resultSet1);
            DBUtil.closeResultSet(resultSet2);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 得到创新大赛阶段教师界面个人项目的 网评和现评 分数
    public ArrayList<TeacherInnoAllScore> getSelfInnoScoreResByIdYear(String userId, int year, String scorePhase) {
        ArrayList<TeacherInnoAllScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询该老师参与了几个阶段的提交
            PreparedStatement preparedStatement = connection.prepareStatement(getSelfInnoScoresql);
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, year);
            preparedStatement.setString(3, scorePhase);
            preparedStatement.setString(4, scorePhase);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while(resultSet1.next()){
                list.add(getInnoScoreFromResultSet(resultSet1));
            }

            DBUtil.closeResultSet(resultSet1);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //20211006 得到教师界面  论文个人项目的分数
    public ArrayList<SelfPaperScore> getSelfPapScoreByIdYear(String userId, int year, String researchType) {
        ArrayList<SelfPaperScore> list = new ArrayList<SelfPaperScore>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询该老师论文评分分数
            PreparedStatement preparedStatement = connection.prepareStatement(getSelfPaperResult);
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, year);
            preparedStatement.setString(3, researchType);
            preparedStatement.setString(4, researchType);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()) {
                list.add(getSelfPaperScoreFromResultSet(resultSet1));
            }


            DBUtil.closeResultSet(resultSet1);
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

    //封装 创新大赛 个人分数 类
    private TeacherInnoAllScore getInnoScoreFromResultSet(ResultSet resultSet) throws SQLException {
        TeacherInnoAllScore resultGet = new TeacherInnoAllScore();
        resultGet.setProjectNo(resultSet.getString(1));
        resultGet.setUserId(resultSet.getString(2));
        resultGet.setUsername(resultSet.getString(3));
        resultGet.setDepartment(resultSet.getString(4));
        String teamMembers = resultSet.getString(5);
        String resultTeamMember = "";
        if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,院系;,,院系;,,院系;,,院系")){

        }else{
            String[] members = teamMembers.split(";");
            String teamNames = "";
            for(int i = 0; i < members.length; i++){
                if(!members[i].equals(",,") && !members[i].equals(",,院系")){
                    teamNames += members[i].split(",")[1] + ",";
                }
            }
            int teamNum = teamNames.length();
            resultTeamMember = teamNames.substring(0, teamNum - 1);
        }
        resultGet.setTeamMember(resultTeamMember);
        resultGet.setTitle(resultSet.getString(6));
        resultGet.setProjectApplySort(resultSet.getString(7));
        resultGet.setCaseSort(resultSet.getString(8));
        resultGet.setProjectSort(resultSet.getString(9));
        resultGet.setIsPass(resultSet.getString(10));
        resultGet.setApplyPreComment(resultSet.getString(11));
        resultGet.setJudgeScore1(resultSet.getDouble(12));
        resultGet.setJudgeScore2(resultSet.getDouble(13));
        resultGet.setJudgeScore3(resultSet.getDouble(14));
        resultGet.setJudgeScore4(resultSet.getDouble(15));
        resultGet.setJudgeScore5(resultSet.getDouble(16));
        Double videoScore = 0.0;
        for(int i = 12; i <= 16; i++){
            videoScore += resultSet.getDouble(i);
        }
        resultGet.setVideoScore(videoScore);
        resultGet.setJudgeScore6(resultSet.getDouble(17));
        resultGet.setJudgeScore7(resultSet.getDouble(18));
        resultGet.setJudgeScore8(resultSet.getDouble(19));
        resultGet.setJudgeScore9(resultSet.getDouble(20));
        Double documentScore = 0.0;
        for(int i = 17; i <= 20; i++){
            documentScore += resultSet.getDouble(i);
        }
        resultGet.setDocumentScore(documentScore);
        resultGet.setPreScore(videoScore + documentScore);
        resultGet.setJudgeScore10(resultSet.getDouble(21));
        resultGet.setJudgeScore11(resultSet.getDouble(22));
        resultGet.setJudgeScore12(resultSet.getDouble(23));
        resultGet.setJudgeScore13(resultSet.getDouble(24));
        resultGet.setJudgeScore14(resultSet.getDouble(25));
        Double pptScore = 0.0;
        for(int i = 21; i <= 25; i++){
            pptScore += resultSet.getDouble(i);
        }
        resultGet.setPptScore(pptScore);
        resultGet.setScore(resultSet.getDouble(26));
        resultGet.setAssessment(resultSet.getString(27));
        return resultGet;
    }

    private SelfResearchScore getProjectEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        SelfResearchScore resultGet = new SelfResearchScore();
        resultGet.setProjectNo(resultSet.getString(1));
        resultGet.setUserId(resultSet.getString(2));
        resultGet.setUsername(resultSet.getString(3));
        resultGet.setTitle(resultSet.getString(4));
        resultGet.setVideoScore(resultSet.getDouble(5));
        resultGet.setVideoScore1(resultSet.getDouble(6));
        resultGet.setVideoScore2(resultSet.getDouble(7));
        resultGet.setVideoScore3(resultSet.getDouble(8));
        resultGet.setVideoScore4(resultSet.getDouble(9));
        resultGet.setVideoScore5(resultSet.getDouble(10));
        resultGet.setDocumentScore1(resultSet.getDouble(11));
        resultGet.setDocumentScore2(resultSet.getDouble(12));
        resultGet.setDocumentScore3(resultSet.getDouble(13));
        resultGet.setDocumentScore4(resultSet.getDouble(14));
        resultGet.setDocumentScore(resultSet.getDouble(15));
        resultGet.setScore(resultSet.getDouble(16));
        resultGet.setAssessment(resultSet.getString(17));
        resultGet.setResearchType(resultSet.getString(18));
        return resultGet;
    }

    //将MySQL查处的每个老师的评分放入SelfResearchScore对象中
    private SelfPaperScore getSelfPaperScoreFromResultSet(ResultSet resultSet) throws SQLException {
        SelfPaperScore resultGet = new SelfPaperScore();
        resultGet.setProjectNo(resultSet.getString(1));
        resultGet.setUserId(resultSet.getString(2));
        resultGet.setUsername(resultSet.getString(3));
        resultGet.setDeptName(resultSet.getString(4));
        String teamMembers = resultSet.getString(5);
        String resultTeamMember;
        if(teamMembers != null){
            if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,无;,,无;,,无;,,无")){
                resultGet.setTeamMember("无");
            }else{
                String[] members = teamMembers.split(";");
                String teamNames = "";
                for(int i = 0; i < members.length; i++){
                    if(!members[i].equals(",,")){
                        teamNames += members[i].split(",")[1] + ",";
                    }
                }
                int teamNum = teamNames.length();
                resultTeamMember = teamNames.substring(0, teamNum - 1);
                resultGet.setTeamMember(resultTeamMember);
            }
        }else{
            resultGet.setTeamMember("无");
        }

        resultGet.setTitle(resultSet.getString(6));
        resultGet.setJudgeScore1(resultSet.getDouble(7));
        resultGet.setJudgeScore2(resultSet.getDouble(8));
        resultGet.setJudgeScore3(resultSet.getDouble(9));
        resultGet.setJudgeScore4(resultSet.getDouble(10));
        resultGet.setJudgeScore5(resultSet.getDouble(11));
        resultGet.setJudgeScore6(resultSet.getDouble(12));
        resultGet.setJudgeScore7(resultSet.getDouble(13));
        resultGet.setJudgeScore8(resultSet.getDouble(14));
        resultGet.setJudgeScore9(resultSet.getDouble(15));
        resultGet.setJudgeScore10(resultSet.getDouble(16));
        resultGet.setScore(resultSet.getDouble(17));
        resultGet.setAssessment(resultSet.getString(18));
        resultGet.setResearchType(resultSet.getString(19));
        return resultGet;
    }

    // For Test
    public static void main(String args[]) {
        SelfResearchScoreDAO dao = new SelfResearchScoreDAO();

        /*ArrayList<ResultGet> list = dao.getResultByYear(2021);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitle());
        }*/

    }
}
