package dao;

import domain.DownloadInnoAllScore;
import domain.DownloadJudgeScore;
import domain.DownloadScore;
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
public class DownloadScoreDAO {
    private String getYearsFromResearchScoreSql = "select distinct DATE_FORMAT(s.submit_time,'%Y') " +
            "from research_score s, judge j " +
            "where s.judge_id = j.id and j.user_id = ?";

    private String getResult = "select e.user_id, e.user_name, e.title, IFNULL(mix.video_score, 0), IFNULL(mix.document_score, 0), IFNULL(mix.video_score*0.3 + mix.document_score*0.3, 0), IFNULL(mix.comment, '[0,0,0,0,0,0,0,0,0]尚未评分'), e.group\n" +
            "from research_enroll e\n" +
            "left join (\n" +
            "    select s.judge_id as judge_id, j.user_id as user_id, s.research_enroll_id as research_enroll_id, s.video_score as video_score, s.document_score as document_score, s.comment as comment, s.submit_time as submit_time\n" +
            "    from research_score s, judge j\n" +
            "    where s.judge_id = j.id\n" +
            "    ) mix\n" +
            "on e.id = mix.research_enroll_id and mix.user_id = ?\n" +
            "where year(e.submit_time) = ?\n" +
            "order by substr(e.group, 5, 2), mix.video_score*0.3 + mix.document_score*0.3 desc, e.group;";


    private String getFinalJudgeScoreResult = "select b.id as projectID,b.projectNo as projectNo,b.userId as userId,b.userName as userName,b.department as department,b.title as title,b.teamMember as teamMember,f.comment as comment,f.score as score,project_sort\n" +
            "from \n" +
            "(select a.id as id,a.user_id as userId,a.user_name as userName,a.title as title,project_sort,a.projectNo as projectNo,a.dept_name as department,a.team_member as teamMember\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and YEAR(a.submit_time)=?)b\n" +
            "left join \n" +
            "(select e.research_enroll_id as researchEnrollId,e.judge_id as judgeId,e.score as score,e.comment as comment\n" +
            "from\n" +
            "(select c.id as id\n" +
            "from judge c \n" +
            "where c.user_id=?)d\n" +
            "left join research_score e\n" +
            "on d.id=e.judge_id)f\n" +
            "on b.id=f.researchEnrollId\n" +
            "order by project_sort asc,SUBSTRING(b.projectNo,1,4) asc,SUBSTRING(b.projectNo,5,2) desc,SUBSTRING(b.projectNo,7,3) asc";

    private String getJudgeScoreResult = "select b.id as projectID,b.projectNo as projectNo,b.userId as userId,b.userName as userName,b.department as department,b.title as title,b.teamMember as teamMember,f.comment as comment,f.score as score\n" +
            "from \n" +
            "(select a.id as id,a.user_id as userId,a.user_name as userName,a.title as title,a.projectNo as projectNo,a.dept_name as department,a.team_member as teamMember\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and YEAR(a.submit_time)=?)b\n" +
            "left join \n" +
            "(select e.research_enroll_id as researchEnrollId,e.judge_id as judgeId,e.score as score,e.comment as comment\n" +
            "from\n" +
            "(select c.id as id\n" +
            "from judge c \n" +
            "where c.user_id=?)d\n" +
            "left join research_score e\n" +
            "on d.id=e.judge_id)f\n" +
            "on b.id=f.researchEnrollId\n" +
            "order by SUBSTRING(b.projectNo,1,4) asc,SUBSTRING(b.projectNo,5,2) desc,SUBSTRING(b.projectNo,7,3) asc";

    private String getInnoJudgeScoreResult = "select b.id as projectID,b.apply_id as projectNo,b.isPass,b.projectApplySort,b.caseSort,b.projectSort,b.userId as userId,b.userName as userName,b.department as department,b.title as title,b.teamMember as teamMember,f.comment as comment,f.score as score,b.applyPreComment as applyPreComment,b.isPass,b.user_cv\n" +
            "from \n" +
            "(select a.id as id,a.isPass as isPass,a.project_apply_sort as projectApplySort,a.case_sort as caseSort,a.project_sort as projectSort,a.apply_id as apply_id,a.apply_pre_comment as applyPreComment,a.user_id as userId,a.user_name as userName,a.user_cv,a.title as title,a.projectNo as projectNo,a.dept_name as department,a.team_member as teamMember\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and YEAR(a.submit_time)=? and if('现评分数'=?,pre_sort='1',true))b\n" +
            "left join \n" +
            "(select e.research_enroll_id as researchEnrollId,e.judge_id as judgeId,e.score as score,e.comment as comment\n" +
            "from\n" +
            "(select c.id as id\n" +
            "from judge c \n" +
            "where c.user_id=?)d\n" +
            "left join research_score e\n" +
            "on d.id=e.judge_id)f\n" +
            "on b.id=f.researchEnrollId\n" +
            "order by case when SUBSTRING(apply_id,8,2)='LG' then 1 when SUBSTRING(apply_id,8,2)='WS' then 2 when SUBSTRING(apply_id,8,2)='YX' then 3 when SUBSTRING(apply_id,8,2)='ZH' then 4 else 5 end,SUBSTRING(apply_id,10,2) asc";

    //获得项目申报阶段评一般材料的评委用户id
    private String getYBJudge = "select user_id from judge where substring(is2021Judge,1,1)='1'";

    //获得项目申报阶段评重点优先材料的评委用户id
    private String getZDYXJudge = "select user_id from judge where substring(is2021Judge,1,1)='2'";


    private String getApplyJudgeScoreResult = "select b.id as projectID,apply_id as projectNo,b.userId as userId,b.userName as userName,b.department as department,b.title as title,\n" +
            "b.teamMember as teamMember,f.comment as comment,f.score as score,project_apply_sort,isPass,apply_pre_comment,apply_id\n" +
            "from \n" +
            "(select a.id as id,a.user_id as userId,a.user_name as userName,a.title as title,a.projectNo as projectNo,a.dept_name as department,\n" +
            "a.team_member as teamMember,project_apply_sort,isPass,apply_pre_comment,apply_id\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01'))" +
            "and if(?='',isPass='通过',if(?='一般',project_apply_sort='一般',project_apply_sort!='一般')) and isPass='通过')b\n" +
            "left join \n" +
            "(select e.research_enroll_id as researchEnrollId,e.judge_id as judgeId,e.score as score,e.comment as comment\n" +
            "from\n" +
            "(select c.id as id\n" +
            "from judge c \n" +
            "where c.user_id=?)d\n" +
            "left join research_score e\n" +
            "on d.id=e.judge_id)f\n" +
            "on b.id=f.researchEnrollId\n" +
            "order by isPass desc,case when SUBSTRING(apply_id,7,2)='ZD' then 1 when SUBSTRING(apply_id,7,2)='YX' then 2 when (SUBSTRING(apply_id,7,2)='YB') then 3 else 4 end,substring(apply_id,9,3) asc\n";


    public ArrayList<DownloadScore> getScoreByYear(int year, String userId) {
        ArrayList<DownloadScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResult);
            preparedStatement.setString(1, userId);
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

    //获取评委下载的分数列表
    public ArrayList<DownloadInnoAllScore> getScoreByYearType(int year, String userId, String researchType) {
        ArrayList<DownloadInnoAllScore> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Connection connection = DBUtil.getConnection();
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(getJudgeScoreResult);
            PreparedStatement preparedStatement;
//            PreparedStatement preparedStatementApply1 = null;
//            PreparedStatement preparedStatementApply2 = null;
//            ResultSet resultSetApply1 = null;
//            ResultSet resultSetApply2 = null;
//            ArrayList<String> applyYBJudgeList = new ArrayList<>();
//            ArrayList<String> applyZDYXJudgeList = new ArrayList<>();

            if(researchType.equals("项目申报")){
//                preparedStatementApply1 = connection.prepareStatement(getYBJudge);
//                resultSetApply1 = preparedStatementApply1.executeQuery();
//                while (resultSetApply1.next()) {
//                    applyYBJudgeList.add(resultSetApply1.getString(1));
//                }
//                preparedStatementApply2 = connection.prepareStatement(getZDYXJudge);
//                resultSetApply2 = preparedStatementApply2.executeQuery();
//                while (resultSetApply2.next()) {
//                    applyZDYXJudgeList.add(resultSetApply2.getString(1));
//                }
//
                String projectApplySort = "";
                /*String projectApplySort = "一般";
                if(userId.equals("0006170278")){
                    projectApplySort = "";
                }else if(applyYBJudgeList.contains(userId)){
                    projectApplySort = "一般";
                }else if(applyZDYXJudgeList.contains(userId)){
                    projectApplySort = "重点优先";
                }*/

                /*if(userId.equals("0006170278")){
                    projectApplySort = "";
                }else if(userId.equals("0006152012") || userId.equals("0016170088") ||
                        userId.equals("0006174035") || userId.equals("0006182077") ||
                        userId.equals("0006167289") || userId.equals("0006181096") ||
                        userId.equals("0006183064") || applyYBJudgeList.contains(userId)){
                    projectApplySort = "一般";
                }else if(userId.equals("0006161023") || userId.equals("0006166389") ||
                        userId.equals("0006172062") || userId.equals("0016165139") ||
                        userId.equals("0016154044") || userId.equals("0006165271") ||
                        userId.equals("2101210152")){
                    projectApplySort = "重点优先";
                }*/
                preparedStatement = connection.prepareStatement(getApplyJudgeScoreResult);
                preparedStatement.setString(1, researchType);
                preparedStatement.setInt(2, year-1);
                preparedStatement.setInt(3, year);
                preparedStatement.setString(4, projectApplySort);
                preparedStatement.setString(5, projectApplySort);
                preparedStatement.setString(6, userId);
            }else if(researchType.equals("结题报告")){
                preparedStatement = connection.prepareStatement(getFinalJudgeScoreResult);
                preparedStatement.setString(1, researchType);
                preparedStatement.setInt(2,year);
                preparedStatement.setString(3, userId);
            } else{
                preparedStatement = connection.prepareStatement(getJudgeScoreResult);
                preparedStatement.setString(1, researchType);
                preparedStatement.setInt(2,year);
                preparedStatement.setString(3, userId);
            }


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DownloadInnoAllScore d = getJudgeScoreFromResultSet(resultSet);
                if(d.isScored()) {
                    list.add(d);
                }
            }
//            DBUtil.closeResultSet(resultSetApply1);
//            DBUtil.closePreparedStatement(preparedStatementApply1);
//            DBUtil.closeResultSet(resultSetApply2);
//            DBUtil.closePreparedStatement(preparedStatementApply2);
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取创新大赛阶段 现评 评委下载的分数
    public ArrayList<DownloadInnoAllScore> getInnoScoreByYearType(int year, String userId, String researchType, String scorePhase) {
        ArrayList<DownloadInnoAllScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(getJudgeScoreResult);
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(getInnoJudgeScoreResult);
            preparedStatement.setString(1, researchType);
            preparedStatement.setInt(2, year);
            preparedStatement.setString(3, scorePhase);
            preparedStatement.setString(4, userId);

            //preparedStatement.setString(4, "1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(getInnoJudgeScoreFromResultSet(resultSet));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



    public ArrayList<Integer> getYearsFromResearchScore(String userId) {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getYearsFromResearchScoreSql);
            preparedStatement.setString(1, userId);
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

    // 封装除创新大赛外的分数
    private DownloadInnoAllScore getJudgeScoreFromResultSet(ResultSet resultSet) throws SQLException {
        DownloadInnoAllScore downloadInnoAllScore = new DownloadInnoAllScore();
        downloadInnoAllScore.setProjectID(resultSet.getString(1));
        downloadInnoAllScore.setProjectNo(resultSet.getString(2));
        downloadInnoAllScore.setUserId(resultSet.getString(3));
        downloadInnoAllScore.setUsername(resultSet.getString(4));
        downloadInnoAllScore.setDepartment(resultSet.getString(5));
        downloadInnoAllScore.setTitle(resultSet.getString(6));
        downloadInnoAllScore.setScore(resultSet.getInt(9));
        String teamMembers = resultSet.getString(7);

        String resultTeamMember;
        String usernameMember = resultSet.getString(4);

            // 如果没有团队成员，则将申报者名字写入团队成员中
            if(teamMembers != null){
                if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,无;,,无;,,无;,,无")
                   || teamMembers.equals(",,院系,;,,院系,;,,院系,;,,院系,")
                   || teamMembers.equals(",,院系;,,院系;,,院系;,,院系")
                   || teamMembers.equals(",,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者")){
                    downloadInnoAllScore.setTeamMember(usernameMember);
                }else{
                    String[] members = teamMembers.split(";");
                    String teamNames = "";
                    for(int i = 0; i < members.length; i++){
                        if(!members[i].equals(",,") && !members[i].equals(",,院系,")
                            && !members[i].equals(",,院系") && !members[i].equals(",,无")
                            && !members[i].equals(",,院系,非通讯作者")){
                            teamNames += members[i].split(",")[1] + ",";
                        }
                    }
                    int teamNum = teamNames.length();
                    resultTeamMember = teamNames.substring(0, teamNum - 1);
                    downloadInnoAllScore.setTeamMember(resultTeamMember);
                }
            }else{
                downloadInnoAllScore.setTeamMember(usernameMember);
            }
        // 将评论里面的分数拆开
        // [9,35,16,16,5,24,26,23,18]采用混合式教学，课程资料完整。教学案例设计特色展示不足，课堂实录视频过长，效果不佳。
        String comment = resultSet.getString(8);

        int index = 0;
        if(comment == null){
            index = 0;
        }else{
            index = comment.indexOf(']');
        }
        if(index > 1){
            String score = comment.substring(1, index);
            String[] scores = score.split(",");
            int nums = scores.length;
            // 如果小分的数量正好是9个，则设置小分
            if(nums == 10){
                downloadInnoAllScore.setJudgeScore1(Integer.parseInt(scores[0]));
                downloadInnoAllScore.setJudgeScore2(Integer.parseInt(scores[1]));
                downloadInnoAllScore.setJudgeScore3(Integer.parseInt(scores[2]));
                downloadInnoAllScore.setJudgeScore4(Integer.parseInt(scores[3]));
                downloadInnoAllScore.setJudgeScore5(Integer.parseInt(scores[4]));
                downloadInnoAllScore.setJudgeScore6(Integer.parseInt(scores[5]));
                downloadInnoAllScore.setJudgeScore7(Integer.parseInt(scores[6]));
                downloadInnoAllScore.setJudgeScore8(Integer.parseInt(scores[7]));
                downloadInnoAllScore.setJudgeScore9(Integer.parseInt(scores[8]));
                downloadInnoAllScore.setJudgeScore10(Integer.parseInt(scores[9]));
            }
            // 否则就设置为0
            else {
                downloadInnoAllScore.setJudgeScore1(0);
                downloadInnoAllScore.setJudgeScore2(0);
                downloadInnoAllScore.setJudgeScore3(0);
                downloadInnoAllScore.setJudgeScore4(0);
                downloadInnoAllScore.setJudgeScore5(0);
                downloadInnoAllScore.setJudgeScore6(0);
                downloadInnoAllScore.setJudgeScore7(0);
                downloadInnoAllScore.setJudgeScore8(0);
                downloadInnoAllScore.setJudgeScore9(0);
                downloadInnoAllScore.setJudgeScore10(0);
            }
            downloadInnoAllScore.setAssessment(comment.substring(index + 1));
            downloadInnoAllScore.setScored(true);
        }
        else {
            // 找不到评分，说明未评分，返回后删去
            downloadInnoAllScore.setJudgeScore1(0);
            downloadInnoAllScore.setJudgeScore2(0);
            downloadInnoAllScore.setJudgeScore3(0);
            downloadInnoAllScore.setJudgeScore4(0);
            downloadInnoAllScore.setJudgeScore5(0);
            downloadInnoAllScore.setJudgeScore6(0);
            downloadInnoAllScore.setJudgeScore7(0);
            downloadInnoAllScore.setJudgeScore8(0);
            downloadInnoAllScore.setJudgeScore9(0);
            downloadInnoAllScore.setJudgeScore10(0);
            downloadInnoAllScore.setAssessment("评论错误，请联系管理员。");
            downloadInnoAllScore.setScored(false);
        }
        return downloadInnoAllScore;
    }

    //将mysql查询出的评委分数封装成DownloadInnoJudgeScore类,创新大赛阶段
    private DownloadInnoAllScore getInnoJudgeScoreFromResultSet(ResultSet resultSet) throws SQLException {
        DownloadInnoAllScore downloadInnoAllScore = new DownloadInnoAllScore();
        downloadInnoAllScore.setProjectID(resultSet.getString(1));
        downloadInnoAllScore.setProjectNo(resultSet.getString(2));
        downloadInnoAllScore.setIsPass(resultSet.getString(3));
        downloadInnoAllScore.setProjectApplySort(resultSet.getString(4));
        downloadInnoAllScore.setCaseSort(resultSet.getString(5));
        downloadInnoAllScore.setProjectSort(resultSet.getString(6));
        downloadInnoAllScore.setUserId(resultSet.getString(7));
        downloadInnoAllScore.setUsername(resultSet.getString(8));
        downloadInnoAllScore.setDepartment(resultSet.getString(9));
        downloadInnoAllScore.setTitle(resultSet.getString(10));
        downloadInnoAllScore.setScore(resultSet.getDouble(13));
        downloadInnoAllScore.setApplyPreComment(resultSet.getString(14));
        downloadInnoAllScore.setIsPass(resultSet.getString(15));
        downloadInnoAllScore.setUserCv(resultSet.getString(16));
        String teamMembers = resultSet.getString(11);

        String resultTeamMember;
        if(teamMembers != null){
            if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,无;,,无;,,无;,,无") ||
                teamMembers.equals(",,院系;,,院系;,,院系;,,院系")){
                downloadInnoAllScore.setTeamMember("无");
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
                downloadInnoAllScore.setTeamMember(resultTeamMember);
            }
        }else{
            downloadInnoAllScore.setTeamMember("无");
        }


        // 将评论里面的分数拆开
        // [9,35,16,16,5,24,26,23,18]采用混合式教学，课程资料完整。教学案例设计特色展示不足，课堂实录视频过长，效果不佳。
        String comment = resultSet.getString(12);
        int index = 0;
        if(comment == null){
            index = 0;
        }else{
            index = comment.indexOf(']');
        }
        if(index > 1){
            String score = comment.substring(1, index);
            String[] scores = score.split(",");
            int nums = scores.length;
            // 如果小分的数量正好是14个，则设置小分
            if(nums == 14){
                downloadInnoAllScore.setJudgeScore1(Integer.parseInt(scores[0]));
                downloadInnoAllScore.setJudgeScore2(Integer.parseInt(scores[1]));
                downloadInnoAllScore.setJudgeScore3(Integer.parseInt(scores[2]));
                downloadInnoAllScore.setJudgeScore4(Integer.parseInt(scores[3]));
                downloadInnoAllScore.setJudgeScore5(Integer.parseInt(scores[4]));
                int videoScore = 0;
                for(int i = 0; i < 5; i++){
                    videoScore += Integer.parseInt(scores[i]);
                }
                downloadInnoAllScore.setVideoScore(videoScore);
                downloadInnoAllScore.setJudgeScore6(Integer.parseInt(scores[5]));
                downloadInnoAllScore.setJudgeScore7(Integer.parseInt(scores[6]));
                downloadInnoAllScore.setJudgeScore8(Integer.parseInt(scores[7]));
                downloadInnoAllScore.setJudgeScore9(Integer.parseInt(scores[8]));
                int documentScore = 0;
                for(int i = 5; i < 9; i++){
                    documentScore += Integer.parseInt(scores[i]);
                }
                downloadInnoAllScore.setDocumentScore(documentScore);
                double preSocre = (videoScore + documentScore);
                downloadInnoAllScore.setPreScore(preSocre);
                downloadInnoAllScore.setJudgeScore10(Integer.parseInt(scores[9]));
                downloadInnoAllScore.setJudgeScore11(Integer.parseInt(scores[10]));
                downloadInnoAllScore.setJudgeScore12(Integer.parseInt(scores[11]));
                downloadInnoAllScore.setJudgeScore13(Integer.parseInt(scores[12]));
                downloadInnoAllScore.setJudgeScore14(Integer.parseInt(scores[13]));
                int pptScore = 0;
                for(int i = 9; i < 14; i++){
                    pptScore += Integer.parseInt(scores[i]);
                }
                downloadInnoAllScore.setPptScore(pptScore);
            }
            // 否则就设置为-1
            else {
                downloadInnoAllScore.setJudgeScore1(0);
                downloadInnoAllScore.setJudgeScore2(0);
                downloadInnoAllScore.setJudgeScore3(0);
                downloadInnoAllScore.setJudgeScore4(0);
                downloadInnoAllScore.setJudgeScore5(0);
                downloadInnoAllScore.setVideoScore(0);
                downloadInnoAllScore.setJudgeScore6(0);
                downloadInnoAllScore.setJudgeScore7(0);
                downloadInnoAllScore.setJudgeScore8(0);
                downloadInnoAllScore.setJudgeScore9(0);
                downloadInnoAllScore.setDocumentScore(0);
                downloadInnoAllScore.setPreScore(0);
                downloadInnoAllScore.setJudgeScore10(0);
                downloadInnoAllScore.setJudgeScore11(0);
                downloadInnoAllScore.setJudgeScore12(0);
                downloadInnoAllScore.setJudgeScore13(0);
                downloadInnoAllScore.setJudgeScore14(0);
                downloadInnoAllScore.setPptScore(0);
            }
            downloadInnoAllScore.setAssessment(comment.substring(index + 1));
        }
        else {
            downloadInnoAllScore.setJudgeScore1(0);
            downloadInnoAllScore.setJudgeScore2(0);
            downloadInnoAllScore.setJudgeScore3(0);
            downloadInnoAllScore.setJudgeScore4(0);
            downloadInnoAllScore.setJudgeScore5(0);
            downloadInnoAllScore.setVideoScore(0);
            downloadInnoAllScore.setJudgeScore6(0);
            downloadInnoAllScore.setJudgeScore7(0);
            downloadInnoAllScore.setJudgeScore8(0);
            downloadInnoAllScore.setJudgeScore9(0);
            downloadInnoAllScore.setDocumentScore(0);
            downloadInnoAllScore.setPreScore(0);
            downloadInnoAllScore.setJudgeScore10(0);
            downloadInnoAllScore.setJudgeScore11(0);
            downloadInnoAllScore.setJudgeScore12(0);
            downloadInnoAllScore.setJudgeScore13(0);
            downloadInnoAllScore.setJudgeScore14(0);
            downloadInnoAllScore.setPptScore(0);
            downloadInnoAllScore.setAssessment("评论错误，请联系管理员。");
        }
        return downloadInnoAllScore;
    }


    private DownloadScore getProjectEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        DownloadScore downloadScore = new DownloadScore();
        downloadScore.setUserId(resultSet.getString(1));
        downloadScore.setUsername(resultSet.getString(2));
        downloadScore.setTitle(resultSet.getString(3));
        downloadScore.setVideoScore(resultSet.getDouble(4));
        downloadScore.setDocumentScore(resultSet.getDouble(5));
        downloadScore.setScore(resultSet.getDouble(6));
        downloadScore.setComment(resultSet.getString(7));
        downloadScore.setGroup(resultSet.getString(8));
        // 将评论里面的分数拆开
        // [9,35,16,16,5,24,26,23,18]采用混合式教学，课程资料完整。教学案例设计特色展示不足，课堂实录视频过长，效果不佳。
        String comment = downloadScore.getComment();
        int index = comment.indexOf(']');
        if(index > 1){
            String score = comment.substring(1, index);
            String[] scores = score.split(",");
            int nums = scores.length;
            // 如果小分的数量正好是9个，则设置小分
            if(nums == 9){
                downloadScore.setVideoScore1(Integer.parseInt(scores[0]));
                downloadScore.setVideoScore2(Integer.parseInt(scores[1]));
                downloadScore.setVideoScore3(Integer.parseInt(scores[2]));
                downloadScore.setVideoScore4(Integer.parseInt(scores[3]));
                downloadScore.setVideoScore5(Integer.parseInt(scores[4]));
                downloadScore.setDocumentScore1(Integer.parseInt(scores[5]));
                downloadScore.setDocumentScore2(Integer.parseInt(scores[6]));
                downloadScore.setDocumentScore3(Integer.parseInt(scores[7]));
                downloadScore.setDocumentScore4(Integer.parseInt(scores[8]));
            }
            // 否则就设置为-1
            else {
                downloadScore.setVideoScore1(-1);
                downloadScore.setVideoScore2(-1);
                downloadScore.setVideoScore3(-1);
                downloadScore.setVideoScore4(-1);
                downloadScore.setVideoScore5(-1);
                downloadScore.setDocumentScore1(-1);
                downloadScore.setDocumentScore2(-1);
                downloadScore.setDocumentScore3(-1);
                downloadScore.setDocumentScore4(-1);
            }
            downloadScore.setAssessment(comment.substring(index + 1));
        }
        else {
            downloadScore.setVideoScore1(-1);
            downloadScore.setVideoScore2(-1);
            downloadScore.setVideoScore3(-1);
            downloadScore.setVideoScore4(-1);
            downloadScore.setVideoScore5(-1);
            downloadScore.setDocumentScore1(-1);
            downloadScore.setDocumentScore2(-1);
            downloadScore.setDocumentScore3(-1);
            downloadScore.setDocumentScore4(-1);
            downloadScore.setAssessment("评论错误，请联系管理员。");
        }
        return downloadScore;
    }

    // For Test
    public static void main(String args[]) {
        String comment = "null";
        String score = comment.substring(1, comment.indexOf(']'));
        String[] scores = score.split(",");
        System.out.println(scores.length);
    }
}
