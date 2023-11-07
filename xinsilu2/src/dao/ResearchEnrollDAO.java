package dao;

import domain.ResearchEnroll;
import domain.Teacher;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by jinggu on 19/4/28.
 */
public class ResearchEnrollDAO {
    private String getResearchApplicantAll = "select id,user_id,user_name,dept_name from research_enroll";
    private String updateResearchEnrollSaveStatus = "update research_enroll set submit_save = 0 where user_id=? and research_type=? and YEAR(submit_time)=?";
    private String deleteEnrollSql = "delete from research_enroll where user_id = ? and research_type = ? and TIMESTAMPDIFF(MONTH ,submit_time,now()) < 2;";

    private String deleteEnrollByUserIdSql = "delete from research_enroll where user_id = ?;";
    private String insertEnrollSql = "insert into research_enroll (user_id,user_name,title,file_path, user_cv,research_type,projectNo,output_zipfile_path,dept_name,project_sort,team_member,project_apply_sort,submit_save) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private String insertResearchEnrollSql = "insert into research_enroll (user_id,user_name,title,file_path,user_cv,research_type,projectNo,output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,submit_save,delay_sort, project_title, isPass, apply_pre_comment, apply_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private String updateResearchEnrollSql = "update research_enroll set user_id=?,user_name=?,title=?,file_path=?,user_cv=?,research_type=?,projectNo=?,output_zipfile_path=?,dept_name=?,project_sort=?,case_sort=?,team_member=?,project_apply_sort=?,submit_save=?,delay_sort=?,project_title=? where id=?";

    private String updateSubmitSaveSql = "update research_enroll set submit_save=? where id=?";
    private String updateEnrollScoreSql = "update research_enroll set score = ? where id = ?";

    private String getResearchEnrollByIdSql = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type from research_enroll where id = ?";

    private String getResearchEnrollByUserIdSql = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type," +
            "projectNo,output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where user_id=? and research_type=? and YEAR(submit_time)=?";

    private String getApplyResearchEnrollByUserIdSql = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type," +
            "projectNo,output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where user_id=? and research_type=? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01'))";

    private String getResearchEnrollIdByUserIdSql = "select id from research_enroll where user_id=? and research_type=? and YEAR(submit_time)=?";

    private String getResearchEnrollRecentSql = "select * from research_enroll where unix_timestamp(submit_time)*1000 > ?";
    private String getEnrollListByYearSql = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type," +
            "projectNo,output_zipfile_path,dept_name,project_sort,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where DATE_FORMAT(submit_time,'%Y')=? ";
    private String getYearsFromResearchEnrollSql =
            "select distinct DATE_FORMAT(submit_time,'%Y') from research_enroll order by DATE_FORMAT(submit_time,'%Y');";

    private String getResearchEnrollByDateSql = "select * from research_enroll where submit_time > ?";

    private String getResearchEnrollByTypeSql = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type,projectNo," +
            "output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where research_type = ? and YEAR(submit_time)=? " +
            "order by case when SUBSTRING(apply_id,8,2)='YX' then 1 when SUBSTRING(apply_id,8,2)='LG' then 2 when SUBSTRING(apply_id,8,2)='WS' then 3 when SUBSTRING(apply_id,8,2)='ZH' then 4 else 5 end,SUBSTRING(apply_id,10,2) asc";

    private String getResearchPaperByTypeSql = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type,projectNo," +
            "output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where research_type = ? and YEAR(submit_time)=? " +
            "order by case when SUBSTRING(apply_id,5,2)='ZD' then 1 when SUBSTRING(apply_id,5,2)='YX' then 2 when (SUBSTRING(apply_id,5,2)='YB') then 3 when (SUBSTRING(apply_id,5,2)='LW') then 4 else 5 end,SUBSTRING(apply_id,1,4) asc,SUBSTRING(apply_id,7,2) asc";
    private String getResearchFinalByTypeSql = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type,projectNo," +
            "output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where research_type = ? and YEAR(submit_time)=? " +
            "order by project_sort asc,case when SUBSTRING(apply_id,5,2)='ZD' then 1 when SUBSTRING(apply_id,5,2)='YX' then 2 when (SUBSTRING(apply_id,5,2)='YB') then 3 when (SUBSTRING(apply_id,5,2)='LW') then 4 else 5 end,SUBSTRING(apply_id,1,4) asc,SUBSTRING(apply_id,7,2) asc";

    private String getResearchApplyInfoByTypeSql1 = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type,projectNo," +
            "output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where research_type = ? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01')) " +
            "order by SUBSTRING(projectNo,5,2) desc,SUBSTRING(projectNo,7,3) asc";
    private String getResearchApplyInfoByTypeSql = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type,projectNo," +
            "output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where research_type = ? and YEAR(submit_time)=? " +
            "order by SUBSTRING(projectNo,5,2) desc,SUBSTRING(projectNo,7,3) asc";


    private String getResearchEnrollByApplySql1 = "select * from research_enroll where research_type = ? and (project_apply_sort=? or project_apply_sort=?)" +
            "order by isPass desc,project_apply_sort desc,substring(apply_id,1,2) desc,substring(apply_id,3,3) asc";
    //查询项目申报阶段现场汇报的15位老师
    private String getResearchEnrollByApplySqlPre = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type,projectNo," +
            "output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where research_type = ? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01')) and (project_apply_sort=? or project_apply_sort=?)" +
            "order by cast(pre_sort as SIGNED INTEGER) asc,isPass desc,project_apply_sort desc,substring(apply_id,1,2) desc,substring(apply_id,3,3) asc";


    private String getResearchEnrollByApplySql2 = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type,projectNo," +
            "output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where research_type = ? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01')) and project_apply_sort=?" +
            "order by isPass desc,project_apply_sort desc,substring(apply_id,3,3) asc";
    private String getResearchEnrollByApplySql3 = "select id,submit_time,user_id,user_name,title,file_path,user_cv,score,research_type,projectNo," +
            "output_zipfile_path,dept_name,project_sort,paper_abstract,case_sort,paper_userid,paper_username,paper_userdept,user_title,user_phone,user_mail,team_member,project_ethic_sort,project_ethic_path,project_apply_sort,isPass,apply_pre_comment,apply_id,pre_sort,submit_save,delay_sort,project_title " +
            "from research_enroll where research_type = ? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01'))" +
            "order by cast(pre_sort as SIGNED INTEGER) desc,isPass desc,case when SUBSTRING(apply_id,7,2)='ZD' then 1 when SUBSTRING(apply_id,7,2)='YX' then 2 when (SUBSTRING(apply_id,7,2)='YB') then 3 else 4 end,substring(apply_id,9,3) asc";


    private String selectPreFilePath = "select title,file_path,projectNo,output_zipfile_path,dept_name,project_sort,team_member,id" +
            " from research_enroll " +
            "where YEAR(submit_time)=? and user_name=? and user_id=? and research_type=?";
    private String selectApplyPreFilePath = "select title,file_path,apply_id,project_apply_sort,isPass,id" +
            " from research_enroll " +
            "where YEAR(submit_time)=? and user_name=? and user_id=? and research_type=?";

    private String updatePreEnrollSql = "update research_enroll set file_path=? " +
            "where user_name=? and user_id=? and research_type=? and YEAR(submit_time)=?";

    private String updateApplyPreEnrollSql = "update research_enroll set file_path=?" +
            "where user_name=? and user_id=? and research_type=? and TIMESTAMPDIFF(MONTH ,submit_time,now()) < 2";

    private String updateInnoPreEnrollSql = "update research_enroll set file_path=?" +
            "where user_name=? and user_id=? and research_type=? and YEAR(submit_time)=?";

    private String updateApplyEnrollSql = "update research_enroll set isPass=?,apply_pre_comment=?,apply_id=? " +
            "where user_id=? and user_name=? and research_type=? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01'))";

    private String updateEnrollSql = "update research_enroll set isPass=?,apply_pre_comment=?,apply_id=?,submit_save=? " +
            "where user_id=? and user_name=? and research_type=? and YEAR(submit_time)=?";

    private String getSumitStageControl = "select submit_stage_control from stage_control " +
            "where year=? and research_type=?";
    private String getScoreStageControl = "select score_stage_control from stage_control " +
            "where year=? and research_type=?";

    private String getResearchEnrollByYearAndType = "select id,user_id,user_name,title,projectNo,dept_name,team_member "+
            "from research_enroll where research_type=? and YEAR(submit_time)=?";

    private String getInnoResearchEnrollByYearAndType = "select id,user_id,user_name,title,projectNo,dept_name,team_member,"+
        "isPass,project_apply_sort,case_sort,project_sort,apply_pre_comment,user_cv,apply_id "+
            "from research_enroll where research_type=? and YEAR(submit_time)=?"+
            "order by case when SUBSTRING(apply_id,8,2)='LG' then 1 when SUBSTRING(apply_id,8,2)='WS' then 2 when SUBSTRING(apply_id,8,2)='YX' then 3 when SUBSTRING(apply_id,8,2)='ZH' then 4 else 5 end,SUBSTRING(apply_id,10,2) asc";

    private String getInnoResearchFilePathByYearAndUserId = "select file_path"+
            "from research_enroll where research_type=? and YEAR(submit_time)=? and user_id=?";


    public ArrayList<ResearchEnroll> getInnoResearchEnrollByYearAndType(String researchType, String year){
        Connection connection = DBUtil.getConnection();
        ArrayList<ResearchEnroll> res = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getInnoResearchEnrollByYearAndType);
            preparedStatement.setString(1,researchType);
            preparedStatement.setString(2,year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ResearchEnroll researchEnroll = new ResearchEnroll();
                researchEnroll.setId(resultSet.getInt(1));
                researchEnroll.setUserId(resultSet.getString(2));
                researchEnroll.setUsername(resultSet.getString(3));
                researchEnroll.setTitle(resultSet.getString(4));
                researchEnroll.setProjectNo(resultSet.getString(5));
                researchEnroll.setDeptName(resultSet.getString(6));
                researchEnroll.setIsPass(resultSet.getString(8));
                researchEnroll.setProjectApplySort(resultSet.getString(9));
                researchEnroll.setCaseSort(resultSet.getString(10));
                researchEnroll.setProjectSort(resultSet.getString(11));
                researchEnroll.setApplyPreComment(resultSet.getString(12));
                researchEnroll.setUserCV(resultSet.getString(13));
                researchEnroll.setApplyId(resultSet.getString(14));
                String teamMemberTemp = resultSet.getString(7);
                String teamMember = "";
                if(!teamMemberTemp.equals(",,;,,;,,;,,")
                        && !teamMemberTemp.equals(",,院系;,,院系;,,院系;,,院系")
                        && teamMemberTemp.split(";").length >= 5
                        && !teamMemberTemp.equals(",,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者")){
                    for(int i = 0; i < 5; i++){
                        if(!teamMemberTemp.split(";")[i] .equals(",,院系,")
                                && !teamMemberTemp.split(";")[i] .equals(",,院系,非通讯作者")){
                            String[] members = teamMemberTemp.split(";")[i].split(",");
                            teamMember+=members[1]+" ";
                        }
                    }
                }
                // 没有团队成员，只包含自己
                if(teamMember.length()==0){
                    teamMember = resultSet.getString(3);
                }
                researchEnroll.setTeamMember(teamMember);
                res.add(researchEnroll);
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public ArrayList<ResearchEnroll> getResearchEnrollByYearAndType(String researchType, String year){
        Connection connection = DBUtil.getConnection();
        ArrayList<ResearchEnroll> res = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchEnrollByYearAndType);
            preparedStatement.setString(1,researchType);
            preparedStatement.setString(2,year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ResearchEnroll researchEnroll = new ResearchEnroll();
                researchEnroll.setId(resultSet.getInt(1));
                researchEnroll.setUserId(resultSet.getString(2));
                researchEnroll.setUsername(resultSet.getString(3));
                researchEnroll.setTitle(resultSet.getString(4));
                researchEnroll.setProjectNo(resultSet.getString(5));
                researchEnroll.setDeptName(resultSet.getString(6));
                String teamMemberTemp = resultSet.getString(7);
                String teamMember = "";
                if(!teamMemberTemp.equals(",,;,,;,,;,,")
                        && !teamMemberTemp.equals(",,院系;,,院系;,,院系;,,院系")
                        && teamMemberTemp.split(";").length >= 5
                        && !teamMemberTemp.equals(",,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者")){
                    for(int i = 0; i < 5; i++){
                        if(!teamMemberTemp.split(";")[i] .equals(",,院系,")
                                && !teamMemberTemp.split(";")[i] .equals(",,院系,非通讯作者")){
                            String[] members = teamMemberTemp.split(";")[i].split(",");
                            teamMember+=members[1]+" ";
                        }
                    }
                }
                // 没有团队成员，只包含自己
                if(teamMember.length()==0){
                    teamMember = resultSet.getString(3);
                }
                researchEnroll.setTeamMember(teamMember);
                res.add(researchEnroll);
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public void updateResearchEnrollSaveStatus(String uid,String researchType,String year) {
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateResearchEnrollSaveStatus);
            preparedStatement.setString(1,uid);
            preparedStatement.setString(2,researchType);
            preparedStatement.setString(3,year);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Teacher> getResearchApplicantAll(){
        ArrayList<Teacher> res = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchApplicantAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            // 用userID唯一标识申请者
            HashSet<String> hs = new HashSet<>();
            while(resultSet.next()){
                // 重复的id直接跳过
                if(hs.contains(resultSet.getString(2)))continue;
                hs.add(resultSet.getString(2));
                Teacher teacher = new Teacher();
                teacher.setUserId(resultSet.getString(2));
                teacher.setUsername(resultSet.getString(3));
                teacher.setDepartment(resultSet.getString(4));
                res.add(teacher);
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public void insertResearchEnrollData(ResearchEnroll researchEnroll) {

        ResearchEnroll researchEnrollOld = getResearchEnrollByUserId(researchEnroll.getUserId(), researchEnroll.getResearchType());
        researchEnroll.setIsPass(researchEnrollOld.getIsPass());
        researchEnroll.setApplyPreComment(researchEnrollOld.getApplyPreComment());
        researchEnroll.setApplyId(researchEnrollOld.getApplyId());

        Connection connection = DBUtil.getConnection();
        try {
            /*一个user_id两个月内每类申请只能提交一次*/
            PreparedStatement preparedStatement = connection.prepareStatement(deleteEnrollSql);
            preparedStatement.setString(1, researchEnroll.getUserId());
            preparedStatement.setString(2, researchEnroll.getResearchType());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(insertResearchEnrollSql);
            preparedStatement.setString(1, researchEnroll.getUserId());
            preparedStatement.setString(2, researchEnroll.getUsername());
            preparedStatement.setString(3, researchEnroll.getTitle());
            preparedStatement.setString(4, researchEnroll.getFilePath());
            preparedStatement.setString(5, researchEnroll.getUserCV());
            preparedStatement.setString(6, researchEnroll.getResearchType());
            preparedStatement.setString(7, researchEnroll.getProjectNo());
            preparedStatement.setString(8, researchEnroll.getOutputZipFilePath());
            preparedStatement.setString(9, researchEnroll.getDeptName());
            preparedStatement.setString(10, researchEnroll.getProjectSort());
            preparedStatement.setString(11, researchEnroll.getPaperAbstract());
            preparedStatement.setString(12, researchEnroll.getCaseSort());
            preparedStatement.setString(13, researchEnroll.getPaperUserid());
            preparedStatement.setString(14, researchEnroll.getPaperUsername());
            preparedStatement.setString(15, researchEnroll.getPaperUserdept());
            preparedStatement.setString(16, researchEnroll.getUserTitle());
            preparedStatement.setString(17, researchEnroll.getUserPhone());
            preparedStatement.setString(18, researchEnroll.getUserMail());
            preparedStatement.setString(19, researchEnroll.getTeamMember());
            preparedStatement.setString(20, researchEnroll.getProjectEthicSort());
            preparedStatement.setString(21, researchEnroll.getProjectEthicPath());
            preparedStatement.setString(22, researchEnroll.getProjectApplySort());
            preparedStatement.setString(23, researchEnroll.getSubmitSave());
            preparedStatement.setString(24, researchEnroll.getDelaySort());
            preparedStatement.setString(25, researchEnroll.getProjectTitle());
            preparedStatement.setString(26, researchEnroll.getIsPass());
            preparedStatement.setString(27, researchEnroll.getApplyId());
            preparedStatement.setString(28, researchEnroll.getApplyPreComment());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateSubmitSave(int id, String submitSave){
        Connection connection = DBUtil.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateSubmitSaveSql);
            preparedStatement.setString(1,submitSave);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //更新教师提交资料
    public void updateResearchEnrollData(ResearchEnroll researchEnroll) {
        Connection connection = DBUtil.getConnection();
        try {
            /*一个user_id两个月内每类申请只能提交一次*/
            PreparedStatement preparedStatement = connection.prepareStatement(updateResearchEnrollSql);
            preparedStatement.setString(1, researchEnroll.getUserId());
            preparedStatement.setString(2, researchEnroll.getUsername());
            preparedStatement.setString(3, researchEnroll.getTitle());
            preparedStatement.setString(4, researchEnroll.getFilePath());
            preparedStatement.setString(5, researchEnroll.getUserCV());
            preparedStatement.setString(6, researchEnroll.getResearchType());
            preparedStatement.setString(7, researchEnroll.getProjectNo());
            preparedStatement.setString(8, researchEnroll.getOutputZipFilePath());
            preparedStatement.setString(9, researchEnroll.getDeptName());
            preparedStatement.setString(10, researchEnroll.getProjectSort());
            preparedStatement.setString(11, researchEnroll.getCaseSort());
            preparedStatement.setString(12, researchEnroll.getTeamMember());
            preparedStatement.setString(13, researchEnroll.getProjectApplySort());
            preparedStatement.setString(14, researchEnroll.getSubmitSave());
            preparedStatement.setString(15, researchEnroll.getDelaySort());
            preparedStatement.setString(16, researchEnroll.getProjectTitle());
            preparedStatement.setInt(17, researchEnroll.getId());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getApplyBeforeData(String userName, String userId) {
        String fileBeforeData = "";
        Connection connection = DBUtil.getConnection();
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR)-1);

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(selectApplyPreFilePath);
            preparedStatement.setString(1, year);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3,userId);
            preparedStatement.setString(4, "项目申报");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                fileBeforeData = resultSet.getString(1) +"]"
                        + resultSet.getString(2) +"]"
                        + resultSet.getString(3) +"]"
                        + resultSet.getString(4) +"]"
                        + resultSet.getString(5)+"]"
                        + resultSet.getString(6);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileBeforeData;

    }

    public String getFinalBeforeData(String userName, String userId) {
        String fileBeforeData = "";
        Connection connection = DBUtil.getConnection();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(selectPreFilePath);
            preparedStatement.setInt(1, year);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3,userId);
            preparedStatement.setString(4, "结题报告");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                fileBeforeData = resultSet.getString(1) +"]"
                        + resultSet.getString(2) +"]"
                        + resultSet.getString(3) +"]"
                        + resultSet.getString(4) +"]"
                        + resultSet.getString(5) +"]"
                        + resultSet.getString(6) +"]"
                        + resultSet.getString(7) +"]"
                        + resultSet.getString(8);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileBeforeData;

    }
    //项目申报提交现场汇报时更新数据库数据
    public void updateAppPreResearchEnroll(ResearchEnroll researchEnroll) {
        Connection connection = DBUtil.getConnection();

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        //项目申报提交时间为前一年12月和今年1月
        if(researchEnroll.getResearchType().contains("项目申报") && currentMonth == 11){
            currentYear = currentYear + 1;
        }
        int year = currentYear;

        try {
            /*一个user_id两个月内每类申请只能提交一次*/
            PreparedStatement preparedStatement = connection.prepareStatement(updateApplyPreEnrollSql);
            preparedStatement.setString(1, researchEnroll.getFilePath());
            preparedStatement.setString(2, researchEnroll.getUsername());
            preparedStatement.setString(3, researchEnroll.getUserId());
            preparedStatement.setString(4, researchEnroll.getResearchType());
            //preparedStatement.setInt(5, year);

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //创新大赛提交现场汇报时更新数据库数据
    public void updateInnoPreResearchEnroll(ResearchEnroll researchEnroll) {
        Connection connection = DBUtil.getConnection();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        try {
            /*一个user_id两个月内每类申请只能提交一次*/
            PreparedStatement preparedStatement = connection.prepareStatement(updateInnoPreEnrollSql);
            preparedStatement.setString(1, researchEnroll.getFilePath());
            preparedStatement.setString(2, researchEnroll.getUsername());
            preparedStatement.setString(3, researchEnroll.getUserId());
            preparedStatement.setString(4, researchEnroll.getResearchType());
            preparedStatement.setInt(5, year);

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //结题报告提交现场汇报时更细数据库数据
    public void updatePreResearchEnroll(ResearchEnroll researchEnroll) {
        Connection connection = DBUtil.getConnection();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        try {
            /*一个user_id两个月内每类申请只能提交一次*/
            PreparedStatement preparedStatement = connection.prepareStatement(updatePreEnrollSql);
            preparedStatement.setString(1, researchEnroll.getFilePath());
            preparedStatement.setString(2, researchEnroll.getUsername());
            preparedStatement.setString(3, researchEnroll.getUserId());
            preparedStatement.setString(4, researchEnroll.getResearchType());
            preparedStatement.setInt(5, year);

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //项目申请 初审结果更新
    public void updateApplyResearchEnroll(ResearchEnroll researchEnroll) {
        Connection connection = DBUtil.getConnection();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        //项目申报提交时间为前一年12月和今年1月
        if(researchEnroll.getResearchType().contains("项目申报") && currentMonth == 11){
            currentYear = currentYear + 1;
        }

        try {
            /*一个user_id两个月内每类申请只能提交一次*/
            PreparedStatement preparedStatement = connection.prepareStatement(updateApplyEnrollSql);
            preparedStatement.setString(1, researchEnroll.getIsPass());
            preparedStatement.setString(2, researchEnroll.getApplyPreComment());
            preparedStatement.setString(3, researchEnroll.getApplyId());
            preparedStatement.setString(4, researchEnroll.getUserId());
            preparedStatement.setString(5, researchEnroll.getUsername());
            preparedStatement.setString(6, researchEnroll.getResearchType());
            preparedStatement.setInt(7, currentYear-1);
            preparedStatement.setInt(8, currentYear);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //大赛申请 初审结果更新
    public void updateResearchEnrollPre(ResearchEnroll researchEnroll) {
        Connection connection = DBUtil.getConnection();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        //int year = 2021;
        try {
            /*一个user_id两个月内每类申请只能提交一次*/
            PreparedStatement preparedStatement = connection.prepareStatement(updateEnrollSql);
            preparedStatement.setString(1, researchEnroll.getIsPass());
            preparedStatement.setString(2, researchEnroll.getApplyPreComment());
            preparedStatement.setString(3, researchEnroll.getApplyId());
            preparedStatement.setString(4, researchEnroll.getSubmitSave());
            preparedStatement.setString(5, researchEnroll.getUserId());
            preparedStatement.setString(6, researchEnroll.getUsername());
            preparedStatement.setString(7, researchEnroll.getResearchType());
            preparedStatement.setInt(8, year);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateResearchEnroll(ResearchEnroll researchEnroll) {
        Connection connection = DBUtil.getConnection();
        try {
            /*一个user_id两个月内每类申请只能提交一次*/
            PreparedStatement preparedStatement = connection.prepareStatement(deleteEnrollByUserIdSql);
            preparedStatement.setString(1, researchEnroll.getUserId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(insertEnrollSql);
            preparedStatement.setString(1, researchEnroll.getUserId());
            preparedStatement.setString(2, researchEnroll.getUsername());
            preparedStatement.setString(3, researchEnroll.getTitle());
            preparedStatement.setString(4, researchEnroll.getFilePath());
            preparedStatement.setString(5, researchEnroll.getUserCV());
            preparedStatement.setString(6, researchEnroll.getResearchType());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateResearchEnrollScore(int score, int id) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateEnrollScoreSql);
            preparedStatement.setInt(1, score);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResearchEnroll getResearchEnrollById(int id) {
        ResearchEnroll researchEnroll = new ResearchEnroll();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchEnrollByIdSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                researchEnroll = getProjectEnrollFromResultSet(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return researchEnroll;

    }

    //查询教师提交页面保存过的数据
    public ResearchEnroll getResearchEnrollByUserId(String userId,String researchType) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);

        ResearchEnroll researchEnroll = new ResearchEnroll();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement;
            ResultSet resultSet = null;
            if(researchType.equals("项目申报")){
                //项目申报提交时间为前一年12月和今年1月。
                //日期变为明年后，年份要减1，查找去年的12月份和今年的1月份的数据
                if(currentMonth != 11){
                    year = year - 1;
                }
                preparedStatement = connection.prepareStatement(getApplyResearchEnrollByUserIdSql);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, researchType);
                preparedStatement.setInt(3, year);
                preparedStatement.setInt(4, year + 1);
                resultSet = preparedStatement.executeQuery();
            }else{
                preparedStatement = connection.prepareStatement(getResearchEnrollByUserIdSql);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, researchType);
                preparedStatement.setInt(3, year);
                resultSet = preparedStatement.executeQuery();
            }

            if (resultSet.next()) {
                researchEnroll = getProjectEnrollFromResultSet(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return researchEnroll;

    }

    //查询教师提交页面保存过的数据
    public int getResearchEnrollIdByUserId(String userId,String researchType) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        int submitId = 0;
        ResearchEnroll researchEnroll = new ResearchEnroll();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchEnrollIdByUserIdSql);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, researchType);
            preparedStatement.setInt(3, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                submitId = resultSet.getInt(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submitId;

    }

    public ArrayList<ResearchEnroll> getResearchEnrollListRecent() {
        ArrayList<ResearchEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.add(Calendar.DAY_OF_YEAR, -60);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchEnrollRecentSql);
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

    public ArrayList<ResearchEnroll> getResearchEnrollListByDate(java.sql.Date date) {
        ArrayList<ResearchEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchEnrollByDateSql);
            preparedStatement.setDate(1, date);
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

    //查询课题研究申报表
    public ArrayList<ResearchEnroll> getResearchApplyInfoByType(String researchType, int year) {
        Calendar cal = Calendar.getInstance();
        //int year = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);

        ArrayList<ResearchEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement;
            if(researchType.equals("项目申报")){
                preparedStatement = connection.prepareStatement(getResearchApplyInfoByTypeSql1);
                preparedStatement.setString(1, researchType);
                preparedStatement.setInt(2, year-1);
                preparedStatement.setInt(3, year);
            }else{
                preparedStatement = connection.prepareStatement(getResearchApplyInfoByTypeSql);
                preparedStatement.setString(1, researchType);
                preparedStatement.setInt(2, year);
            }

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

    //获取 教研论文 评委界面提交数据列表
    public ArrayList<ResearchEnroll> getResearchPapListByType(String researchType) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        ArrayList<ResearchEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchPaperByTypeSql);
            preparedStatement.setString(1, researchType);
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

    //获取 结题报告 评委界面提交数据列表
    public ArrayList<ResearchEnroll> getResearchFinListByType(String researchType) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        ArrayList<ResearchEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchFinalByTypeSql);
            preparedStatement.setString(1, researchType);
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

    public ArrayList<ResearchEnroll> getResearchEnrollListByType(String researchType) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        ArrayList<ResearchEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getResearchEnrollByTypeSql);
            preparedStatement.setString(1, researchType);
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

    //项目申报评分页面打开时 初始化获取项目申报的所有数据
    public ArrayList<ResearchEnroll> getResearchEnrollListByApply(String researchType) {
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);
        //项目申报提交时间为前一年12月和今年1月
        if(researchType.equals("项目申报") && currentMonth == 11){
            currentYear = currentYear + 1;
        }

        ArrayList<ResearchEnroll> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement;
            preparedStatement= connection.prepareStatement(getResearchEnrollByApplySql3);
            preparedStatement.setString(1, researchType);
            preparedStatement.setInt(2, currentYear-1);
            preparedStatement.setInt(3, currentYear);


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

    public ArrayList<ResearchEnroll> getResearchEnrollListByYear(int year) {
        ArrayList<ResearchEnroll> list = new ArrayList<>();
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

    //通过年份和项目类型确定获得当前阶段，进而控制教师提交界面
    public String getSubmitStageBystage(String year, String researchType) {
        String submitStageControl = "0";
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getSumitStageControl);
            preparedStatement.setString(1, year);
            preparedStatement.setString(2, researchType);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                submitStageControl = resultSet.getString(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submitStageControl;

    }

    //通过年份和项目类型确定获得当前阶段，进而控制评委评分界面
    public String getScoreStageBystage(String researchType) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        //项目申报提交时间为前一年12月和今年1月
        if(researchType.equals("项目申报") && currentMonth == 11){
            year = year + 1;
        }
        String scoreStageControl = "";
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getScoreStageControl);
            preparedStatement.setInt(1, year);
            preparedStatement.setString(2, researchType);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                scoreStageControl = resultSet.getString(1);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreStageControl;

    }

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

    private ResearchEnroll getProjectEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        ResearchEnroll researchEnroll = new ResearchEnroll();
        researchEnroll.setId(resultSet.getInt(1));
        researchEnroll.setSubmitTime(resultSet.getDate(2));
        researchEnroll.setUserId(resultSet.getString(3));
        researchEnroll.setUsername(resultSet.getString(4));
        researchEnroll.setTitle(resultSet.getString(5));
        researchEnroll.setFilePath(resultSet.getString(6));
        researchEnroll.setUserCV(resultSet.getString(7));
        researchEnroll.setScore(resultSet.getInt(8));
        researchEnroll.setResearchType(resultSet.getString(9));
        researchEnroll.setProjectNo(resultSet.getString(10));
        researchEnroll.setOutputZipFilePath(resultSet.getString(11));
        researchEnroll.setDeptName(resultSet.getString(12));
        researchEnroll.setProjectSort(resultSet.getString(13));
        researchEnroll.setPaperAbstract(resultSet.getString(14));
        researchEnroll.setCaseSort(resultSet.getString(15));
        researchEnroll.setPaperUserid(resultSet.getString(16));
        researchEnroll.setPaperUsername(resultSet.getString(17));
        researchEnroll.setPaperUserdept(resultSet.getString(18));
        researchEnroll.setUserTitle(resultSet.getString(19));
        researchEnroll.setUserPhone(resultSet.getString(20));
        researchEnroll.setUserMail(resultSet.getString(21));
        researchEnroll.setTeamMember(resultSet.getString(22));
        researchEnroll.setProjectEthicSort(resultSet.getString(23));
        researchEnroll.setProjectEthicPath(resultSet.getString(24));
        researchEnroll.setProjectApplySort(resultSet.getString(25));
        researchEnroll.setIsPass(resultSet.getString(26));
        researchEnroll.setApplyPreComment(resultSet.getString(27));
        researchEnroll.setApplyId(resultSet.getString(28));
        researchEnroll.setApplyTime(resultSet.getString(29));
        researchEnroll.setSubmitSave(resultSet.getString(30));
        researchEnroll.setDelaySort(resultSet.getString(31));
        researchEnroll.setProjectTitle(resultSet.getString(32));
        return researchEnroll;
    }

    public ResearchEnroll getInnoResearchFilePathByYearAndUserId(String userId, String year){
        Connection connection = DBUtil.getConnection();
        ResearchEnroll res = new ResearchEnroll();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(getInnoResearchFilePathByYearAndUserId);
            preparedStatement.setString(1,"创新大赛");
            preparedStatement.setString(2, year);
            preparedStatement.setString(3, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                res.setFilePath(resultSet.getString(1));
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    // For Test
    public static void main(String args[]) {
        ResearchEnrollDAO dao = new ResearchEnrollDAO();
        ResearchEnroll enroll = dao.getInnoResearchFilePathByYearAndUserId("1800011747", "2023");
        System.out.println(enroll.getFilePath());

    }
}
