package dao;

import domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CSVUtil;
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
public class DownloadAllScoreDAO {
    private static Logger logger = LogManager.getLogger(DownloadAllScoreDAO.class);

    private String getYearsFromResearchScorelSql = "select distinct DATE_FORMAT(submit_time,'%Y') from research_score;";

    private String getResult = "select IFNULL(mix.user_id, ?), (select username from judge where judge.user_id = ?), e.user_id, e.user_name, e.title, IFNULL(mix.video_score, 0), IFNULL(mix.document_score, 0), IFNULL(mix.video_score*0.3 + mix.document_score*0.3, 0), IFNULL(mix.comment, '[0,0,0,0,0,0,0,0,0]尚未评分'), e.group " +
            "from research_enroll e " +
            "left join ( " +
            "    select s.judge_id as judge_id, j.user_id as user_id, j.username as username, s.research_enroll_id as research_enroll_id, s.video_score as video_score, s.document_score as document_score, s.comment as comment, s.submit_time as submit_time " +
            "    from research_score s, judge j " +
            "    where s.judge_id = j.id " +
            "    ) mix " +
            "on e.id = mix.research_enroll_id and mix.user_id = ? " +
            "where year(e.submit_time) = ? " +
            "order by e.group;";

    private String getAllApplyScores = "select IFNULL(a.judge_userid,?) as judge_userid,(select username from judge where user_id=?) as judge_name,b.apply_id as apply_id,b.isPass,b.projectApplySort,b.caseSort,b.projectSort,b.department,b.teamMember,b.applyPreComment,b.projectEthicSort,b.userCv,\n" +
            "b.user_id as user_id,b.user_name as user_name,b.title as title,IFNULL(a.comment,'[0,0,0,0,0,0,0,0,0,0]尚未评分') as comment,\n" +
            "IFNULL(a.score,0) as score\n" +
            "from\n" +
            "(select c.research_enroll_id as research_enroll_id,c.judge_id as judge_id,j.user_id as judge_userid,j.username as judge_name,c.score as score,c.comment as comment\n" +
            "from research_score c,judge j where j.user_id=? and c.judge_id=j.id and YEAR(submit_time)=?)a\n" +
            "right join\n" +
            "(select d.id as id,d.isPass as isPass,d.project_apply_sort as projectApplySort,d.case_sort as caseSort,d.project_sort as projectSort,d.apply_id as apply_id,d.user_id as user_id,d.user_name as user_name,d.dept_name as department,d.title as title,d.team_member as teamMember,d.apply_pre_comment as applyPreComment,d.projectNo as projectNo,d.project_ethic_sort as projectEthicSort,d.user_cv as userCv,d.pre_sort\n" +
            "from research_enroll d where d.research_type=? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01')) and if('applyPre'=?,pre_sort>0,true))b\n" +
            "on a.research_enroll_id=b.id order by case when SUBSTRING(apply_id,7,2)='ZD' then 1 when SUBSTRING(apply_id,7,2)='YX' then 2 when SUBSTRING(apply_id,7,2)='YB' then 3 else 4 end,SUBSTRING(apply_id,9,3) asc";


    private String getAllInnoScores = "select IFNULL(a.judge_userid,?) as judge_userid,(select username from judge where user_id=?) as judge_name,b.apply_id as apply_id,b.isPass,b.projectApplySort,b.caseSort,b.projectSort,b.department,b.teamMember,b.applyPreComment,\n" +
            "b.user_id as user_id,b.user_name as user_name,b.title as title,IFNULL(a.comment,'[0,0,0,0,0,0,0,0,0,0,0,0,0,0]尚未评分') as comment,\n" +
            "IFNULL(a.score,0) as score,?\n" +
            "from\n" +
            "(select c.research_enroll_id as research_enroll_id,c.judge_id as judge_id,j.user_id as judge_userid,j.username as judge_name,c.score as score,c.comment as comment\n" +
            "from research_score c,judge j where j.user_id=? and c.judge_id=j.id and YEAR(submit_time)=? and month(c.submit_time)>6)a\n" +
            "right join\n" +
            "(select d.id as id,d.isPass as isPass,d.project_apply_sort as projectApplySort,d.case_sort as caseSort,d.project_sort as projectSort,d.apply_id as apply_id,d.user_id as user_id,d.user_name as user_name,d.dept_name as department,d.title as title,d.team_member as teamMember,d.apply_pre_comment as applyPreComment,d.projectNo as projectNo\n" +
            "from research_enroll d where d.research_type=? and YEAR(submit_time)=? and if('现评分数'=?,pre_sort='1',true))b\n" +
            "on a.research_enroll_id=b.id order by case when SUBSTRING(apply_id,8,2)='YX' then 1 when SUBSTRING(apply_id,8,2)='LG' then 2 when SUBSTRING(apply_id,8,2)='WS' then 3 when SUBSTRING(apply_id,8,2)='ZH' then 4 else 5 end,SUBSTRING(apply_id,10,2) asc";


    private String getAllPaperScores = "select IFNULL(a.judge_userid,?) as judge_userid,(select username from judge where user_id=?) as judge_name,b.projectNo as projectNo,\n" +
            "b.user_id as user_id,b.user_name as user_name,b.dept_name,b.title as title,IFNULL(a.comment,'[0,0,0,0,0,0,0,0,0,0]尚未评分') as comment,\n" +
            "IFNULL(a.score,0) as score\n" +
            "from\n" +
            "(select c.research_enroll_id as research_enroll_id,c.judge_id as judge_id,j.user_id as judge_userid,j.username as judge_name,c.score as score,c.comment as comment\n" +
            "from research_score c,judge j where j.user_id=? and c.judge_id=j.id and YEAR(submit_time)=? and month(c.submit_time)>8)a\n" +
            "right join\n" +
            "(select d.id as id,d.user_id as user_id,d.user_name as user_name,d.title as title,d.projectNo as projectNo,d.dept_name\n" +
            "from research_enroll d where d.research_type=? and YEAR(submit_time)=?)b\n" +
            "on a.research_enroll_id=b.id order by SUBSTRING(b.projectNo,1,4) asc,SUBSTRING(b.projectNo,5,2) desc,SUBSTRING(b.projectNo,7,3) asc";
    private String getAllFinalScores = "select IFNULL(a.judge_userid,?) as judge_userid,(select username from judge where user_id=?) as judge_name,b.projectNo as projectNo,\n" +
            "b.user_id as user_id,b.user_name as user_name,b.dept_name,b.title as title,IFNULL(a.comment,'[0,0,0,0,0,0,0,0,0,0]尚未评分') as comment,\n" +
            "IFNULL(a.score,0) as score,project_sort\n" +
            "from\n" +
            "(select c.research_enroll_id as research_enroll_id,c.judge_id as judge_id,j.user_id as judge_userid,j.username as judge_name,c.score as score,c.comment as comment\n" +
            "from research_score c,judge j where j.user_id=? and c.judge_id=j.id and YEAR(submit_time)=? and month(c.submit_time)>8)a\n" +
            "right join\n" +
            "(select d.id as id,d.user_id as user_id,d.user_name as user_name,d.dept_name,d.title as title,d.projectNo as projectNo,project_sort\n" +
            "from research_enroll d where d.research_type=? and YEAR(submit_time)=?)b\n" +
            "on a.research_enroll_id=b.id order by project_sort asc,SUBSTRING(b.projectNo,1,4) asc,SUBSTRING(b.projectNo,5,2) desc,SUBSTRING(b.projectNo,7,3) asc";

    private String getApplyResultScoreSql = "select distinct r.projectNo,r.user_id,r.user_name,r.dept_name,r.title,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006152012' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge1,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006165271' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge2,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006166389' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge3,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006167289' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge4,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006167390' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge5,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006172062' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge6,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006174035' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge7,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006181096' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge8,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006182077' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge9,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0016165139' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge10,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0016170088' THEN\n" +
            "r.score\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") judge11,\n" +
            "sum(r.score) as originalScoreSum, \n" +
            "round(sum(r.score)/count(r.score),2) as originalScoreAverage,\n" +
            "if(count(r.score) > 2,(sum(r.score)-min(r.score)-max(r.score)),0) as weightedScoreSum,\n" +
            "round(if(count(r.score)>2,(sum(r.score)-min(r.score)-max(r.score))/(count(r.score)-2),0),2) as weightedScoreAverage,\n" +
            "count(r.score) as times,r.teamMember,r.project_sort,r.case_sort,r.user_cv,r.project_ethic_sort,r.project_apply_sort,r.isPass,r.apply_pre_comment,r.apply_id,r.user_title,r.user_phone,r.user_mail\n" +
            "from\n" +
            "(select d.judge_id as judge_id,d.judge_name as judge_name,d.score as score,b.user_id as user_id,\n" +
            "b.user_name as user_name,b.dept_name,b.title as title,b.projectNo as projectNo,b.teamMember,b.project_sort,b.case_sort,b.user_cv,b.project_ethic_sort,b.project_apply_sort,b.isPass,b.apply_pre_comment,b.apply_id,b.user_title,b.user_phone,b.user_mail\n" +
            "from\n" +
            "(select a.id as id,a.user_id as user_id,a.user_name as user_name,a.dept_name,a.title as title,a.apply_id as projectNo,a.team_member as teamMember,project_sort,case_sort,user_cv,project_ethic_sort,project_apply_sort,isPass,apply_pre_comment,apply_id,user_title,user_phone,user_mail\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and ((YEAR(submit_time)=? and month(submit_time)='12') or (YEAR(submit_time)=? and month(submit_time)='01')) and if('applyPre'=?,pre_sort>0,true))b\n" +
            "left join \n" +
            "(select c.research_enroll_id as research_enroll_id,if('applyPre'!=?,\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-10),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-9),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-8),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-7),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-6),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-5),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-4),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-3),',',1),\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-10),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-9),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-8),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-7),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-6),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-5),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-4),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-3),',',1)+\n" +
            "substring_index(substring_index(c.comment,']',1),',',-1)\n" +
            ") as score,c.comment as comment,\n" +
            "j.user_id as judge_id,j.username as judge_name\n" +
            "from research_score c,judge j where substring(j.is2021Judge,1,1)='1' and j.id=c.judge_id)d\n" +
            "on b.id=d.research_enroll_id)r\n" +
            "group by r.user_name,r.projectNo,r.user_id,r.dept_name,r.title,r.teamMember,r.project_sort,r.case_sort,r.user_cv,r.project_ethic_sort,r.project_apply_sort,r.isPass,r.apply_pre_comment,r.apply_id,r.user_title,r.user_phone,r.user_mail\n" +
            "order by (sum(score)-min(score)-max(score))/(count(score)-2) desc,project_sort asc,SUBSTRING(r.apply_id,1,4) asc,SUBSTRING(r.apply_id,5,4) desc,SUBSTRING(r.apply_id,9,3) asc";


    private String getInnoResultScoreSql = "select m.apply_id,m.projectApplySort,m.caseSort,m.projectSort,m.department,m.teamMember,m.user_id,m.user_name,m.title,m.applyPreComment,m.user_cv,m.isPass,\n" +
            "m.preJudge1,m.preJudge2,m.preJudge3,m.preJudge4,m.preJudge5,m.preJudge6,m.preJudge7,m.preJudge8,m.preJudge9,m.preJudge10,m.preJudge11,m.preJudge12,m.preJudge13,\n" +
            "m.preOriginalScoreSum,m.preOriginalScoreAverage,m.preWeightedScoreSum,m.preWeightedScoreAverage,m.preTimes,\n" +
            "n.pptJudge1,n.pptJudge2,n.pptJudge3,n.pptJudge4,n.pptJudge5,n.pptJudge6,n.pptJudge7,\n" +
            "n.pptOriginalScoreSum,n.pptOriginalScoreAverage,n.pptWeightedScoreSum,n.pptWeightedScoreAverage,n.pptTimes,\n" +
            "m.preOriginalScoreSum+n.pptOriginalScoreSum as originalScoreSum,\n" +
            "m.preOriginalScoreAverage+n.pptOriginalScoreAverage as originalScoreAverage,\n" +
            "m.preWeightedScoreSum+pptWeightedScoreSum as weightedScoreSum,\n" +
            "m.preWeightedScoreAverage+n.pptWeightedScoreAverage as weightedScoreAverage\n" +
            "from\n" +
            "(select distinct r.apply_id,r.projectApplySort,r.caseSort,r.projectSort,r.department,r.teamMember,r.user_id,r.user_name,r.title,r.applyPreComment,r.user_cv,r.isPass,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006165271' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge1,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006167390' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge2,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006174035' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge3,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006182077' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge4,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006181096' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge5,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0016170088' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge6,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '2101210152' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge7,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006152012' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge8,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006166389' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge9,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006167289' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge10,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006172062' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge11,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006165048' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge12,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0016165139' THEN\n" +
            "r.preScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") preJudge13,\n" +
            "sum(preScore) as preOriginalScoreSum,\n" +
            "round(sum(preScore)/count(preScore),2) as preOriginalScoreAverage,\n" +
            "if(count(preScore) > 2,(sum(preScore)-min(preScore)-max(preScore)),0) as preWeightedScoreSum,\n" +
            "round(if(count(preScore)>2,(sum(preScore)-min(preScore)-max(preScore))/(count(preScore)-2),0),2) as preWeightedScoreAverage,\n" +
            "count(preScore) as preTimes\n" +
            "from\n" +
            "(select d.judge_id as judge_id,d.judge_name as judge_name,d.score as score,d.comment as comment,b.user_id as user_id,\n" +
            "b.user_name as user_name,b.user_cv,b.title as title,b.projectNo as projectNo,b.apply_id as apply_id,b.isPass as isPass,b.projectApplySort as projectApplySort,b.caseSort as caseSort,b.projectSort as projectSort,b.department as department,b.teamMember as teamMember,b.applyPreComment as applyPreComment,\n" +
            "substring_index(substring_index(d.comment,'[',-1),',',1)+substring_index(substring_index(d.comment,',',-13),',',1)+\n" +
            "substring_index(substring_index(d.comment,',',-12),',',1)+substring_index(substring_index(d.comment,',',-11),',',1)+\n" +
            "substring_index(substring_index(d.comment,',',-10),',',1)+substring_index(substring_index(d.comment,',',-9),',',1)+\n" +
            "substring_index(substring_index(d.comment,',',-8),',',1)+substring_index(substring_index(d.comment,',',-7),',',1)+\n" +
            "substring_index(substring_index(d.comment,',',-6),',',1) as preScore\n" +
            "from\n" +
            "(select a.id as id,a.isPass as isPass,a.project_apply_sort as projectApplySort,a.case_sort as caseSort,a.project_sort as projectSort,a.dept_name as department,a.team_member as teamMember,a.apply_pre_comment as applyPreComment,a.user_id as user_id,a.user_name as user_name,a.user_cv,a.title as title,a.apply_id as apply_id,a.projectNo as projectNo\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and YEAR(a.submit_time)=? and if('现评分数'=?,true,true))b\n" +
            "left join \n" +
            "(select c.research_enroll_id as research_enroll_id,c.score as score,c.comment as comment,\n" +
            "j.user_id as judge_id,j.username as judge_name\n" +
            "from research_score c,judge j where (substring(j.is2021Judge,2,1)='1' or substring(j.is2021Judge,2,1)='2') and j.id=c.judge_id)d\n" +
            "on b.id=d.research_enroll_id)r\n" +
            "group by r.user_name,r.apply_id,r.user_id,r.title,r.projectApplySort,r.caseSort,r.projectSort,r.department,r.teamMember,r.applyPreComment,r.user_cv,r.isPass\n" +
            "order by case when SUBSTRING(apply_id,8,2)='YX' then 1 when SUBSTRING(apply_id,8,2)='LG' then 2 when SUBSTRING(apply_id,8,2)='WS' then 3 when SUBSTRING(apply_id,8,2)='ZH' then 4 else 5 end,SUBSTRING(apply_id,10,2) asc)m\n" +
            "left join \n" +
            "(select distinct r.apply_id,r.projectApplySort,r.caseSort,r.projectSort,r.department,r.teamMember,r.user_id,r.user_name,r.title,r.applyPreComment,r.user_cv,r.isPass,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006152012' THEN\n" +
            "r.pptScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") pptJudge1,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0016170088' THEN\n" +
            "r.pptScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") pptJudge2,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006174035' THEN\n" +
            "r.pptScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") pptJudge3,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006182077' THEN\n" +
            "r.pptScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") pptJudge4,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006165271' THEN\n" +
            "r.pptScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") pptJudge5,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0006181096' THEN\n" +
            "r.pptScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") pptJudge6,\n" +
            "max(\n" +
            "case r.judge_id\n" +
            "  when '0016165139' THEN\n" +
            "r.pptScore\n" +
            "else \n" +
            "0\n" +
            "end\n" +
            ") pptJudge7,\n" +
            "sum(pptScore) as pptOriginalScoreSum,\n" +
            "round(sum(pptScore)/count(pptScore),2) as pptOriginalScoreAverage,\n" +
            "if(count(pptScore) > 2,(sum(pptScore)-min(pptScore)-max(pptScore)),0) as pptWeightedScoreSum,\n" +
            "round(if(count(pptScore)>2,(sum(pptScore)-min(pptScore)-max(pptScore))/(count(pptScore)-2),0),2) as pptWeightedScoreAverage,\n" +
            "count(pptScore) as pptTimes\n" +
            "from\n" +
            "(select d.judge_id as judge_id,d.judge_name as judge_name,d.score as score,d.comment as comment,b.user_id as user_id,\n" +
            "b.user_name as user_name,b.user_cv,b.title as title,b.projectNo as projectNo,b.apply_id as apply_id,b.isPass as isPass,b.projectApplySort as projectApplySort,b.caseSort as caseSort,b.projectSort as projectSort,b.department as department,b.teamMember as teamMember,b.applyPreComment as applyPreComment,\n" +
            "substring_index(substring_index(d.comment,',',-5),',',1)+substring_index(substring_index(d.comment,',',-4),',',1)+\n" +
            "substring_index(substring_index(d.comment,',',-3),',',1)+substring_index(substring_index(d.comment,',',-2),',',1)+\n" +
            "substring_index(substring_index(d.comment,']',1),',',-1) as pptScore\n" +
            "from\n" +
            "(select a.id as id,a.isPass as isPass,a.project_apply_sort as projectApplySort,a.case_sort as caseSort,a.project_sort as projectSort,a.dept_name as department,a.team_member as teamMember,a.apply_pre_comment as applyPreComment,a.user_id as user_id,a.user_name as user_name,a.user_cv,a.title as title,a.apply_id as apply_id,a.projectNo as projectNo\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and YEAR(a.submit_time)=? and if('现评分数'=?,true,true))b\n" +
            "left join \n" +
            "(select c.research_enroll_id as research_enroll_id,c.score as score,c.comment as comment,\n" +
            "j.user_id as judge_id,j.username as judge_name\n" +
            "from research_score c,judge j where (substring(j.is2021Judge,2,1)='2' or substring(j.is2021Judge,2,1)='3') and j.id=c.judge_id)d\n" +
            "on b.id=d.research_enroll_id)r\n" +
            "group by r.user_name,r.apply_id,r.user_id,r.title,r.projectApplySort,r.caseSort,r.projectSort,r.department,r.teamMember,r.applyPreComment,r.user_cv,r.isPass\n" +
            "order by case when SUBSTRING(apply_id,8,2)='YX' then 1 when SUBSTRING(apply_id,8,2)='LG' then 2 when SUBSTRING(apply_id,8,2)='WS' then 3 when SUBSTRING(apply_id,8,2)='ZH' then 4 else 5 end,SUBSTRING(apply_id,10,2) asc)n\n" +
            "on m.user_id=n.user_id and m.user_name=n.user_name and m.apply_id=n.apply_id\n" +
            "order by case when SUBSTRING(m.apply_id,8,2)='YX' then 1 when SUBSTRING(m.apply_id,8,2)='LG' then 2 when SUBSTRING(m.apply_id,8,2)='WS' then 3 when SUBSTRING(m.apply_id,8,2)='ZH' then 4 else 5 end,\n" +
            "if('网评分数'=?,preWeightedScoreAverage,weightedScoreAverage) desc";


    private String getPaperResultScoreSql = "select distinct r.projectNo,r.user_id,r.user_name,r.dept_name,r.title,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006152012' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge1,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006161023' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge2,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006164225' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge3,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006165271' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge4,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006167289' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge5,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006168276' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge6,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006169222' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge7,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0016154044' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge8,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0016170088' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge9,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '2101210152' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge10,\n" +
            "sum(score) as originalScoreSum, \n" +
            "round(sum(score)/count(score),2) as originalScoreAverage,\n" +
            "if(count(score) > 2,(sum(score)-min(score)-max(score)),0) as weightedScoreSum,\n" +
            "round(if(count(score)>2,(sum(score)-min(score)-max(score))/(count(score)-2),0),2) as weightedScoreAverage,\n" +
            "count(score) as times\n" +
            "from\n" +
            "(select d.judge_id as judge_id,d.judge_name as judge_name,d.score as score,b.user_id as user_id,\n" +
            "b.user_name as user_name,b.dept_name,b.title as title,b.projectNo as projectNo\n" +
            "from\n" +
            "(select a.id as id,a.user_id as user_id,a.user_name as user_name,a.dept_name,a.title as title,a.projectNo as projectNo\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and YEAR(a.submit_time)=?)b\n" +
            "left join \n" +
            "(select c.research_enroll_id as research_enroll_id,c.score as score,c.comment as comment,\n" +
            "j.user_id as judge_id,j.username as judge_name\n" +
            "from research_score c,judge j where substring(j.is2021Judge,4,1)='1' and j.id=c.judge_id)d\n" +
            "on b.id=d.research_enroll_id)r\n" +
            "group by r.user_name,r.projectNo,r.user_id,r.dept_name,r.title\n" +
            "order by (sum(score)-min(score)-max(score))/(count(score)-2) desc,SUBSTRING(r.projectNo,1,4) asc,SUBSTRING(r.projectNo,5,2) desc,SUBSTRING(r.projectNo,7,3) asc";

    private String getFinalResultScoreSql = "select distinct r.projectNo,r.user_id,r.user_name,r.dept_name,r.title,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006152012' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge1,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006161023' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge2,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006165271' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge3,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006166389' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge4,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006167289' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge5,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006172062' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge6,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006174035' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge7,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0006181096' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge8,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0016154044' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge9,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0016165139' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge10,\n" +
            "max(\n" +
            "\tcase r.judge_id\n" +
            "  when '0016170088' THEN\n" +
            "\t\t\tr.score\n" +
            "\telse \n" +
            "\t\t\t0\n" +
            "\tend\n" +
            ") judge11,\n" +
            "sum(r.score) as originalScoreSum, \n" +
            "round(sum(r.score)/count(r.score),2) as originalScoreAverage,\n" +
            "if(count(r.score) > 2,(sum(r.score)-min(r.score)-max(r.score)),0) as weightedScoreSum,\n" +
            "round(if(count(r.score)>2,(sum(r.score)-min(r.score)-max(r.score))/(count(r.score)-2),0),2) as weightedScoreAverage,\n" +
            "count(r.score) as times,project_sort\n" +
            "from\n" +
            "(select d.judge_id as judge_id,d.judge_name as judge_name,d.score as score,b.user_id as user_id,\n" +
            "b.user_name as user_name,b.dept_name,b.title as title,b.projectNo as projectNo,project_sort\n" +
            "from\n" +
            "(select a.id as id,a.user_id as user_id,a.user_name as user_name,a.dept_name,a.title as title,a.projectNo as projectNo,project_sort\n" +
            "from research_enroll a\n" +
            "where a.research_type=? and YEAR(a.submit_time)=?)b\n" +
            "left join \n" +
            "(select c.research_enroll_id as research_enroll_id,if('finalPre'!=?,\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-10),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-9),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-8),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-7),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-6),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-5),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-4),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-3),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-2),',',1),\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-10),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-9),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-8),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-7),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-6),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-5),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-4),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-3),',',1)+\n" +
            "substring_index(substring_index(substring_index(c.comment,'[',-1),',',-2),',',1)+\n" +
            "substring_index(substring_index(c.comment,']',1),',',-1)\n" +
            ") as score,c.comment as comment,\n" +
            "j.user_id as judge_id,j.username as judge_name\n" +
            "from research_score c,judge j where substring(j.is2021Judge,5,1)='1' and j.id=c.judge_id)d\n" +
            "on b.id=d.research_enroll_id)r\n" +
            "group by r.user_name,r.projectNo,r.user_id,r.dept_name,r.title,project_sort\n" +
            "order by (sum(score)-min(score)-max(score))/(count(score)-2) desc,project_sort asc,SUBSTRING(r.projectNo,1,4) asc,SUBSTRING(r.projectNo,5,2) desc,SUBSTRING(r.projectNo,7,3) asc";


    private String getJudgesql = "select j.user_id from judge j where j.is2021Judge = 1;";

    private String getApplyJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,1,1)='1' or substring(j.is2021Judge,1,1)='2' order by substring(j.is2021Judge,1,1),user_id";

    private String getInnoPreJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,2,1)='1' or substring(j.is2021Judge,2,1)='2' order by substring(j.is2021Judge,2,1),user_id";

    private String getInnoPPTJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,2,1)='2' or substring(j.is2021Judge,2,1)='3' order by substring(j.is2021Judge,2,1),user_id";
    //private String getInnoPreJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,2,1)='1' order by user_id";
    //private String getInnoPPTJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,2,1)='2' order by user_id";

    private String getPaperJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,4,1)='1' or substring(j.is2021Judge,4,1)='2' order by user_id";
    //private String getPaperJudgeSql = "select j.user_id from judge j where j.id in (22,24,101,102,109,113,114)";
    //private String getPaperJudgeSql = "select j.user_id from judge j where j.id in (22,23,24,25,26,110)";
    private String getFinalJudgeSql = "select j.user_id from judge j where substring(j.is2021Judge,5,1)='1' or substring(j.is2021Judge,5,1)='2' order by user_id";


    //查询项目申报所有  网评  评委小分
    public ArrayList<ArrayList<DownloadApplyAllScore>> getApplyJudgeScores(int year, String researchType, String isPre) {
        ArrayList<ArrayList<DownloadApplyAllScore>> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询有多少评委
            PreparedStatement preparedStatement = connection.prepareStatement(getApplyJudgeSql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            ResultSet resultSet2 = null;
            ResultSet resultSet3 = null;
            ResultSet resultSet4 = null;
            ArrayList<String> applyPreJudges = new ArrayList<>();
            ArrayList<String> applyPPTJudges = new ArrayList<>();
            int innoPreNums = 0, innoPPTNums = 0;
            // 将所有评委的账号放入judges
            while(resultSet1.next()){
                applyPreJudges.add(resultSet1.getString(1));
                ++innoPreNums;
            }

            for(int i = 0;i < innoPreNums; ++i){
                String userId = applyPreJudges.get(i);
                //System.out.println("------------judgeId" + userId);
                preparedStatement = connection.prepareStatement(getAllApplyScores);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3,userId);
                preparedStatement.setInt(4,year);
                preparedStatement.setString(5, researchType);
                preparedStatement.setInt(6, year-1);
                preparedStatement.setInt(7, year);
                preparedStatement.setString(8, isPre);
                //System.out.println("------------sql" + getPaperResult);
                resultSet3 = preparedStatement.executeQuery();
                ArrayList<DownloadApplyAllScore> temp = new ArrayList<>();
                while (resultSet3.next()) {
                    temp.add(getApplyScoresFromResultSet(resultSet3, isPre));
                }
                list.add(temp);
            }

            DBUtil.closeResultSet(resultSet1);
            DBUtil.closeResultSet(resultSet2);
            DBUtil.closeResultSet(resultSet3);
            DBUtil.closeResultSet(resultSet4);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询 项目申报 所有评委分数，得到最后的平均分
    public ArrayList<DownloadApplyResultScore> getApplyResultScores(int year, String researchType, String isPre) {
        ArrayList<DownloadApplyResultScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getApplyResultScoreSql);
            preparedStatement.setString(1,researchType);
            preparedStatement.setInt(2,year-1);
            preparedStatement.setInt(3,year);
            preparedStatement.setString(4, isPre);
            preparedStatement.setString(5, isPre);

            ResultSet resultSet1 = preparedStatement.executeQuery();


            while(resultSet1.next()){
                list.add(getApplyResultFromResultSet(resultSet1,isPre));
            }

            DBUtil.closeResultSet(resultSet1);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询创新大赛所有  网评  评委小分
    public ArrayList<ArrayList<DownloadInnoAllScore>> getInnoPreJudgeScores(int year, String researchType) {
        ArrayList<ArrayList<DownloadInnoAllScore>> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询有多少评委
            PreparedStatement preparedStatement = connection.prepareStatement(getInnoPreJudgeSql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            ResultSet resultSet2 = null;
            ResultSet resultSet3 = null;
            ResultSet resultSet4 = null;
            ArrayList<String> innoPreJudges = new ArrayList<>();
            ArrayList<String> innoPPTJudges = new ArrayList<>();
            int innoPreNums = 0, innoPPTNums = 0;
            // 将所有评委的账号放入judges
            while(resultSet1.next()){
                innoPreJudges.add(resultSet1.getString(1));
                logger.info("innoPreJudges" + innoPreNums + ": " + resultSet1.getString(1));
                ++innoPreNums;
            }

            for(int i = 0;i < innoPreNums; ++i){
                String userId = innoPreJudges.get(i);
                //System.out.println("------------judgeId" + userId);
                preparedStatement = connection.prepareStatement(getAllInnoScores);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3, "网评评委");
                preparedStatement.setString(4,userId);
                preparedStatement.setInt(5,year);
                preparedStatement.setString(6, researchType);
                preparedStatement.setInt(7, year);
                preparedStatement.setString(8, "网评分数");
                //System.out.println("------------sql" + getPaperResult);
                resultSet3 = preparedStatement.executeQuery();
                ArrayList<DownloadInnoAllScore> temp = new ArrayList<>();
                while (resultSet3.next()) {
                    temp.add(getInnoScoresFromResultSet(resultSet3));
                }
                list.add(temp);
            }

            DBUtil.closeResultSet(resultSet1);
            DBUtil.closeResultSet(resultSet2);
            DBUtil.closeResultSet(resultSet3);
            DBUtil.closeResultSet(resultSet4);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询创新大赛所有评委小分
    public ArrayList<ArrayList<DownloadInnoAllScore>> getInnoJudgeScores(int year, String researchType) {
        ArrayList<ArrayList<DownloadInnoAllScore>> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询有多少评委
            PreparedStatement preparedStatement = connection.prepareStatement(getInnoPreJudgeSql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            ResultSet resultSet2 = null;
            ResultSet resultSet3 = null;
            ResultSet resultSet4 = null;
            ArrayList<String> innoPreJudges = new ArrayList<>();
            ArrayList<String> innoPPTJudges = new ArrayList<>();
            int innoPreNums = 0, innoPPTNums = 0;
            // 将所有评委的账号放入judges
            while(resultSet1.next()){
                innoPreJudges.add(resultSet1.getString(1));
                ++innoPreNums;
            }
            preparedStatement = connection.prepareStatement(getInnoPPTJudgeSql);
            resultSet2 = preparedStatement.executeQuery();
            while(resultSet2.next()){
                innoPPTJudges.add(resultSet2.getString(1));
                ++innoPPTNums;
            }

            for(int i = 0;i < innoPreNums; ++i){
                String userId = innoPreJudges.get(i);
                //System.out.println("------------judgeId" + userId);
                preparedStatement = connection.prepareStatement(getAllInnoScores);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3, "网评评委");
                preparedStatement.setString(4,userId);
                preparedStatement.setInt(5,year);
                preparedStatement.setString(6, researchType);
                preparedStatement.setInt(7, year);
                preparedStatement.setString(8, "网评分数");
                //System.out.println("------------sql" + getPaperResult);
                resultSet3 = preparedStatement.executeQuery();
                ArrayList<DownloadInnoAllScore> temp = new ArrayList<>();
                while (resultSet3.next()) {
                    temp.add(getInnoScoresFromResultSet(resultSet3));
                }
                list.add(temp);
            }

            for(int i = 0;i < innoPPTNums; ++i){
                String userId = innoPPTJudges.get(i);
                //System.out.println("------------judgeId" + userId);
                preparedStatement = connection.prepareStatement(getAllInnoScores);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3, "现评评委");
                preparedStatement.setString(4, userId);
                preparedStatement.setInt(5, year);
                preparedStatement.setString(6, researchType);
                preparedStatement.setInt(7, year);
                preparedStatement.setString(8, "现评分数");
                //System.out.println("------------sql" + getPaperResult);
                resultSet4 = preparedStatement.executeQuery();
                ArrayList<DownloadInnoAllScore> temp = new ArrayList<>();
                while (resultSet4.next()) {
                    temp.add(getInnoScoresFromResultSet(resultSet4));
                }
                list.add(temp);
            }

            DBUtil.closeResultSet(resultSet1);
            DBUtil.closeResultSet(resultSet2);
            DBUtil.closeResultSet(resultSet3);
            DBUtil.closeResultSet(resultSet4);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询创新大赛网评评委分数，得到最后的平均分。创新大赛网评分数表
    public ArrayList<DownloadInnoResultScore> getInnoPreScore(int year, String researchType) {
        ArrayList<DownloadInnoResultScore> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        //year = cal.get(Calendar.YEAR);
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getInnoResultScoreSql);
            preparedStatement.setString(1,researchType);
            preparedStatement.setInt(2,year);
            preparedStatement.setString(3, "网评分数");
            preparedStatement.setString(4,researchType);
            preparedStatement.setInt(5,year);
            preparedStatement.setString(6, "网评分数");
            preparedStatement.setString(7, "网评分数");
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while(resultSet1.next()){
                String appId = resultSet1.getString(1);
                if (appId != null && !appId.equals("") && appId.substring(7, 9).equals("YQ"))
                    continue;
                list.add(getInnoResultFromResultSet(resultSet1));
            }


            DBUtil.closeResultSet(resultSet1);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询创新大赛所有评委分数，得到最后的平均分。创新大赛分数表
    public ArrayList<DownloadInnoResultScore> getInnoResultScore(int year, String researchType) {
        ArrayList<DownloadInnoResultScore> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        //year = cal.get(Calendar.YEAR);
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getInnoResultScoreSql);
            preparedStatement.setString(1,researchType);
            preparedStatement.setInt(2,year);
            preparedStatement.setString(3, "现评分数");
            preparedStatement.setString(4,researchType);
            preparedStatement.setInt(5,year);
            preparedStatement.setString(6, "现评分数");
            preparedStatement.setString(7, "现评分数");
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while(resultSet1.next()){
                String appId = resultSet1.getString(1);
                if (appId != null && !appId.equals("") && appId.substring(7, 9).equals("YQ"))
                    continue;
                list.add(getInnoResultFromResultSet(resultSet1));
            }


            DBUtil.closeResultSet(resultSet1);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    //查询教研论文所有评委小分
    public ArrayList<ArrayList<DownloadPaperAllScore>> getPaperJudgeScores(int year, String researchType) {
        ArrayList<ArrayList<DownloadPaperAllScore>> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询有多少评委
            PreparedStatement preparedStatement = connection.prepareStatement(getPaperJudgeSql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            ResultSet resultSet2 = null;
            ArrayList<String> paperJudges = new ArrayList<>();
            int paperNums = 0;
            // 将所有评委的账号放入judges
            while(resultSet1.next()){
                paperJudges.add(resultSet1.getString(1));
                ++paperNums;
            }

            for(int i = 0;i < paperNums; ++i){
                String userId = paperJudges.get(i);
                //System.out.println("------------judgeId" + userId);
                preparedStatement = connection.prepareStatement(getAllPaperScores);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3,userId);
                preparedStatement.setInt(4,year);
                preparedStatement.setString(5, researchType);
                preparedStatement.setInt(6, year);
                //System.out.println("------------sql" + getPaperResult);
                resultSet2 = preparedStatement.executeQuery();
                ArrayList<DownloadPaperAllScore> temp = new ArrayList<>();
                while (resultSet2.next()) {
                    temp.add(getPaperScoresFromResultSet(resultSet2));
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

    //查询教研论文所有评委分数，得到最后的平均分
    public ArrayList<DownloadPaperResultScore> getPaperResultScore(int year, String researchType) {
        ArrayList<DownloadPaperResultScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getPaperResultScoreSql);
            preparedStatement.setString(1,researchType);
            preparedStatement.setInt(2,year);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while(resultSet1.next()){
                list.add(getPaperResultFromResultSet(resultSet1));
            }


            DBUtil.closeResultSet(resultSet1);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查询结题报告所有评委网评小分
    public ArrayList<ArrayList<DownloadFinalAllScore>> getFinalJudgeScores(int year, String researchType, String isPre) {
        ArrayList<ArrayList<DownloadFinalAllScore>> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询有多少评委
            PreparedStatement preparedStatement = connection.prepareStatement(getFinalJudgeSql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            ResultSet resultSet2 = null;
            ArrayList<String> paperJudges = new ArrayList<>();
            int paperNums = 0;
            // 将所有评委的账号放入judges
            while(resultSet1.next()){
                paperJudges.add(resultSet1.getString(1));
                ++paperNums;
            }

            for(int i = 0;i < paperNums; ++i){
                String userId = paperJudges.get(i);
                //System.out.println("------------judgeId" + userId);
                preparedStatement = connection.prepareStatement(getAllFinalScores);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3,userId);
                preparedStatement.setInt(4,year);
                preparedStatement.setString(5, researchType);
                preparedStatement.setInt(6, year);
                //System.out.println("------------sql" + preparedStatement);
                resultSet2 = preparedStatement.executeQuery();
                ArrayList<DownloadFinalAllScore> temp = new ArrayList<>();
                while (resultSet2.next()) {
                    temp.add(getFinalScoresFromResultSet(resultSet2, isPre));
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

    //查询结题报告所有评委分数，得到最后的平均分
    public ArrayList<DownloadFinalResultScore> getFinalResultScore(int year, String researchType, String isPre) {
        ArrayList<DownloadFinalResultScore> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getFinalResultScoreSql);
            preparedStatement.setString(1,researchType);
            preparedStatement.setInt(2,year);
            preparedStatement.setString(3, isPre);

            ResultSet resultSet1 = preparedStatement.executeQuery();


            while(resultSet1.next()){
                list.add(getFinalResultFromResultSet(resultSet1,isPre));
            }

            DBUtil.closeResultSet(resultSet1);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ArrayList<DownloadAllScore>> getScoreByYear(int year) {
        ArrayList<ArrayList<DownloadAllScore>> list = new ArrayList<ArrayList<DownloadAllScore>>();
        Connection connection = DBUtil.getConnection();
        try {
            // 查询有多少评委
            PreparedStatement preparedStatement = connection.prepareStatement(getJudgesql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            ResultSet resultSet2 = null;
            ArrayList<String> judges = new ArrayList<>();
            int nums = 0;
            // 将所有评委的账号放入judges
            while(resultSet1.next()){
                judges.add(resultSet1.getString(1));
                ++nums;
            }
            for(int i = 0;i < nums; ++i){
                String userId = judges.get(i);
//                System.out.println("------------judgeId" + userId);
                preparedStatement = connection.prepareStatement(getResult);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, userId);
                preparedStatement.setString(3, userId);
                preparedStatement.setInt(4, year);
//                System.out.println("------------sql" + getResult);
                resultSet2 = preparedStatement.executeQuery();
                ArrayList<DownloadAllScore> temp = new ArrayList<>();
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


    // 获取所有有评委打分的年度
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

    //得到创新大赛每个评委的分数，统计最后平均分
    private DownloadInnoResultScore getInnoResultFromResultSet(ResultSet resultSet) throws SQLException{
        DownloadInnoResultScore downloadInnoResultScore = new DownloadInnoResultScore();
        downloadInnoResultScore.setProjectNo(resultSet.getString(1));
        downloadInnoResultScore.setProjectApplySort(resultSet.getString(2));
        downloadInnoResultScore.setCaseSort(resultSet.getString(3));
        downloadInnoResultScore.setProjectSort(resultSet.getString(4));
        downloadInnoResultScore.setDepartment(resultSet.getString(5));

        downloadInnoResultScore.setUserId(resultSet.getString(7));
        downloadInnoResultScore.setUserName(resultSet.getString(8));
        downloadInnoResultScore.setTitle(resultSet.getString(9));
        downloadInnoResultScore.setApplyPreComment(resultSet.getString(10));
        downloadInnoResultScore.setUserCv(resultSet.getString(11));
        downloadInnoResultScore.setIsPass(resultSet.getString(12));

        String teamMembers = resultSet.getString(6);
        String resultTeamMember;
        if(teamMembers != null){
            if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,无;,,无;,,无;,,无") ||
                    teamMembers.equals(",,院系;,,院系;,,院系;,,院系")){
                downloadInnoResultScore.setTeamMember("无");
                downloadInnoResultScore.setTeamNumber("0");
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
                downloadInnoResultScore.setTeamMember(resultTeamMember);
                downloadInnoResultScore.setTeamNumber(String.valueOf(teamNames.split(",").length));
            }
        }else{
            downloadInnoResultScore.setTeamMember("无");
            downloadInnoResultScore.setTeamNumber("0");
        }
        String judgeScores = "";
        /*judgeScores = resultSet.getDouble(9) + "," + resultSet.getDouble(10) + "," +
                resultSet.getDouble(11) + "," + resultSet.getDouble(12) + "," +
                resultSet.getDouble(13) + "," + resultSet.getDouble(14) + "," +
                resultSet.getDouble(15) + "," + resultSet.getDouble(16) + "," +
                resultSet.getDouble(17) + "," + resultSet.getDouble(18) + "," +
                resultSet.getDouble(19) + ";" +
                resultSet.getDouble(20) + "," + resultSet.getDouble(21) + "," +
                resultSet.getDouble(22) + "," + resultSet.getDouble(23) + "," +
                resultSet.getDouble(24) + "," + resultSet.getDouble(25) + "," +
                resultSet.getDouble(26) + "," + resultSet.getDouble(27) + "," +
                resultSet.getDouble(28) + "," + resultSet.getDouble(29);

        downloadInnoResultScore.setJudgeScores(judgeScores);*/
        downloadInnoResultScore.setPreJudgeScore1(resultSet.getDouble(13));
        downloadInnoResultScore.setPreJudgeScore2(resultSet.getDouble(14));
        downloadInnoResultScore.setPreJudgeScore3(resultSet.getDouble(15));
        downloadInnoResultScore.setPreJudgeScore4(resultSet.getDouble(16));
        downloadInnoResultScore.setPreJudgeScore5(resultSet.getDouble(17));
        downloadInnoResultScore.setPreJudgeScore6(resultSet.getDouble(18));
        downloadInnoResultScore.setPreJudgeScore7(resultSet.getDouble(19));
        downloadInnoResultScore.setPreJudgeScore8(resultSet.getDouble(20));
        downloadInnoResultScore.setPreJudgeScore9(resultSet.getDouble(21));
        downloadInnoResultScore.setPreJudgeScore10(resultSet.getDouble(22));
        downloadInnoResultScore.setPreJudgeScore11(resultSet.getDouble(23));
        downloadInnoResultScore.setPreJudgeScore12(resultSet.getDouble(24));
        downloadInnoResultScore.setPreJudgeScore13(resultSet.getDouble(25));
        downloadInnoResultScore.setPreOriginalScoreSum(resultSet.getDouble(26));
        downloadInnoResultScore.setPreOriginalScoreAverage(resultSet.getDouble(27));
        downloadInnoResultScore.setPreWeightedScoreSum(resultSet.getDouble(28));
        downloadInnoResultScore.setPreWeightedScoreAverage(resultSet.getDouble(29));
        downloadInnoResultScore.setPreTimes(resultSet.getInt(30));
        downloadInnoResultScore.setPptJudgeScore1(resultSet.getDouble(31));
        downloadInnoResultScore.setPptJudgeScore2(resultSet.getDouble(32));
        downloadInnoResultScore.setPptJudgeScore3(resultSet.getDouble(33));
        downloadInnoResultScore.setPptJudgeScore4(resultSet.getDouble(34));
        downloadInnoResultScore.setPptJudgeScore5(resultSet.getDouble(35));
        downloadInnoResultScore.setPptJudgeScore6(resultSet.getDouble(36));
        downloadInnoResultScore.setPptJudgeScore7(resultSet.getDouble(37));
        downloadInnoResultScore.setPptOriginalScoreSum(resultSet.getDouble(38));
        downloadInnoResultScore.setPptOriginalScoreAverage(resultSet.getDouble(39));
        downloadInnoResultScore.setPptWeightedScoreSum(resultSet.getDouble(40));
        downloadInnoResultScore.setPptWeightedScoreAverage(resultSet.getDouble(41));
        downloadInnoResultScore.setPptTimes(resultSet.getInt(42));
        downloadInnoResultScore.setOriginalScoreSum(resultSet.getDouble(43));
        downloadInnoResultScore.setOriginalScoreAverage(resultSet.getDouble(44));
        downloadInnoResultScore.setWeightedScoreSum(resultSet.getDouble(45));
        downloadInnoResultScore.setWeightedScoreAverage(resultSet.getDouble(46));
        downloadInnoResultScore.setRanks(0);


        return downloadInnoResultScore;
    }

    //得到论文每个评委的分数，统计最后平均分
    private DownloadPaperResultScore getPaperResultFromResultSet(ResultSet resultSet) throws SQLException{
        DownloadPaperResultScore downloadPaperResultScore = new DownloadPaperResultScore();
        downloadPaperResultScore.setProjectNo(resultSet.getString(1));
        downloadPaperResultScore.setUserId(resultSet.getString(2));
        downloadPaperResultScore.setUserName(resultSet.getString(3));
        downloadPaperResultScore.setDeptName(resultSet.getString(4));
        downloadPaperResultScore.setTitle(resultSet.getString(5));
        downloadPaperResultScore.setJudgeScore1(resultSet.getDouble(6));
        downloadPaperResultScore.setJudgeScore2(resultSet.getDouble(7));
        downloadPaperResultScore.setJudgeScore3(resultSet.getDouble(8));
        downloadPaperResultScore.setJudgeScore4(resultSet.getDouble(9));
        downloadPaperResultScore.setJudgeScore5(resultSet.getDouble(10));
        downloadPaperResultScore.setJudgeScore6(resultSet.getDouble(11));
        downloadPaperResultScore.setJudgeScore7(resultSet.getDouble(12));
        downloadPaperResultScore.setJudgeScore8(resultSet.getDouble(13));
        downloadPaperResultScore.setJudgeScore9(resultSet.getDouble(14));
        downloadPaperResultScore.setJudgeScore10(resultSet.getDouble(15));
        downloadPaperResultScore.setOriginalScoreSum(resultSet.getDouble(16));
        downloadPaperResultScore.setOriginalScoreAverage(resultSet.getDouble(17));
        downloadPaperResultScore.setWeightedScoreSum(resultSet.getDouble(18));
        downloadPaperResultScore.setWeightedScoreAverage(resultSet.getDouble(19));
        downloadPaperResultScore.setRanks(0);
        downloadPaperResultScore.setTimes(resultSet.getInt(20));

        return downloadPaperResultScore;
    }

    //得到 项目申报 每个评委的分数
    private DownloadApplyAllScore getApplyScoresFromResultSet(ResultSet resultSet, String isPre) throws SQLException {
        DownloadApplyAllScore downloadApplyAllScore = new DownloadApplyAllScore();
        downloadApplyAllScore.setJudgeId(resultSet.getString(1));
        downloadApplyAllScore.setJudgeName(resultSet.getString(2));
        downloadApplyAllScore.setApplyId(resultSet.getString(3));
        downloadApplyAllScore.setIsPass(resultSet.getString(4));
        downloadApplyAllScore.setProjectApplySort(resultSet.getString(5));
        downloadApplyAllScore.setCaseSort(resultSet.getString(6).substring(0,resultSet.getString(6).length()-1));
        downloadApplyAllScore.setProjectSort(resultSet.getString(7));
        downloadApplyAllScore.setDeptName(resultSet.getString(8));

        downloadApplyAllScore.setApplyPreComment(resultSet.getString(10));
        downloadApplyAllScore.setProjectEthicSort(resultSet.getString(11));
        downloadApplyAllScore.setUserCv(resultSet.getString(12));
        downloadApplyAllScore.setUserId(resultSet.getString(13));
        downloadApplyAllScore.setUserName(resultSet.getString(14));
        downloadApplyAllScore.setTitle(resultSet.getString(15));

        downloadApplyAllScore.setScore(resultSet.getDouble(17));

        String teamMembers = resultSet.getString(9);
        String resultTeamMember;
        if(teamMembers != null){
            if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,无;,,无;,,无;,,无") ||
                    teamMembers.equals(",,院系;,,院系;,,院系;,,院系")){
                downloadApplyAllScore.setTeamMember("无");
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
                downloadApplyAllScore.setTeamMember(resultTeamMember);
            }
        }else{
            downloadApplyAllScore.setTeamMember("无");
        }
        // 将评论里面的分数拆开
        // [9,35,16,16,5,24,26,23,18]采用混合式教学，课程资料完整。教学案例设计特色展示不足，课堂实录视频过长，效果不佳。
        String comment = resultSet.getString(16);
        int index = comment.indexOf(']');
        if(index > 1){
            String score = comment.substring(1, index);
            String[] scores = score.split(",");
            int nums = scores.length;
            // 如果小分的数量正好是11个，则设置小分。最后一位标志是否提交
            if(nums >= 10){
                downloadApplyAllScore.setJudgeScore1(Integer.parseInt(scores[0]));
                downloadApplyAllScore.setJudgeScore2(Integer.parseInt(scores[1]));
                downloadApplyAllScore.setJudgeScore3(Integer.parseInt(scores[2]));
                downloadApplyAllScore.setJudgeScore4(Integer.parseInt(scores[3]));
                downloadApplyAllScore.setJudgeScore5(Integer.parseInt(scores[4]));
                downloadApplyAllScore.setJudgeScore6(Integer.parseInt(scores[5]));
                downloadApplyAllScore.setJudgeScore7(Integer.parseInt(scores[6]));
                downloadApplyAllScore.setJudgeScore8(Integer.parseInt(scores[7]));
                downloadApplyAllScore.setJudgeScore9(Integer.parseInt(scores[8]));
                downloadApplyAllScore.setPreJudgeScore(Integer.parseInt(scores[0]) +
                        Integer.parseInt(scores[1]) + Integer.parseInt(scores[2]) +
                        Integer.parseInt(scores[3]) + Integer.parseInt(scores[4]) +
                        Integer.parseInt(scores[5]) + Integer.parseInt(scores[6]) +
                        Integer.parseInt(scores[7]));
                if(isPre.equals("apply")){
                    downloadApplyAllScore.setJudgeScore10(0);
                }else{
                    downloadApplyAllScore.setJudgeScore10(Integer.parseInt(scores[9]));
                }


            }
            // 否则就设置为-1
            else {
                downloadApplyAllScore.setJudgeScore1(0);
                downloadApplyAllScore.setJudgeScore2(0);
                downloadApplyAllScore.setJudgeScore3(0);
                downloadApplyAllScore.setJudgeScore4(0);
                downloadApplyAllScore.setJudgeScore5(0);
                downloadApplyAllScore.setJudgeScore6(0);
                downloadApplyAllScore.setJudgeScore7(0);
                downloadApplyAllScore.setJudgeScore8(0);
                downloadApplyAllScore.setJudgeScore9(0);
                downloadApplyAllScore.setPreJudgeScore(0);
                downloadApplyAllScore.setJudgeScore10(0);
            }
            downloadApplyAllScore.setAssessment(comment.substring(index + 1));
        } else {
            downloadApplyAllScore.setJudgeScore1(0);
            downloadApplyAllScore.setJudgeScore2(0);
            downloadApplyAllScore.setJudgeScore3(0);
            downloadApplyAllScore.setJudgeScore4(0);
            downloadApplyAllScore.setJudgeScore5(0);
            downloadApplyAllScore.setJudgeScore6(0);
            downloadApplyAllScore.setJudgeScore7(0);
            downloadApplyAllScore.setJudgeScore8(0);
            downloadApplyAllScore.setJudgeScore9(0);
            downloadApplyAllScore.setPreJudgeScore(0);
            downloadApplyAllScore.setJudgeScore10(0);
            downloadApplyAllScore.setAssessment("评论错误，请联系管理员。");
        }
        if(resultSet.getDouble(17)!=0 && isPre.equals("apply")){
            double tempScore = resultSet.getDouble(17);
            tempScore = downloadApplyAllScore.getJudgeScore1() + downloadApplyAllScore.getJudgeScore2() +
                    downloadApplyAllScore.getJudgeScore3() + downloadApplyAllScore.getJudgeScore4() +
                    downloadApplyAllScore.getJudgeScore5() + downloadApplyAllScore.getJudgeScore6() +
                    downloadApplyAllScore.getJudgeScore7() + downloadApplyAllScore.getJudgeScore8();
            downloadApplyAllScore.setScore(tempScore);
        }else if(isPre.equals("applyPre")){
            double tempPreScore = resultSet.getDouble(17);
            tempPreScore = downloadApplyAllScore.getJudgeScore1() + downloadApplyAllScore.getJudgeScore2() +
                    downloadApplyAllScore.getJudgeScore3() + downloadApplyAllScore.getJudgeScore4() +
                    downloadApplyAllScore.getJudgeScore5() + downloadApplyAllScore.getJudgeScore6() +
                    downloadApplyAllScore.getJudgeScore7() + downloadApplyAllScore.getJudgeScore8() +
                    downloadApplyAllScore.getJudgeScore10();
            downloadApplyAllScore.setScore(tempPreScore);
        }else{
            downloadApplyAllScore.setScore(0);
        }
        return downloadApplyAllScore;
    }

    //得到项目申报每个评委的分数，统计最后平均分
    private DownloadApplyResultScore getApplyResultFromResultSet(ResultSet resultSet, String isPre) throws SQLException{
        DownloadApplyResultScore downloadApplyResultScore = new DownloadApplyResultScore();
        downloadApplyResultScore.setProjectNo(resultSet.getString(1));
        downloadApplyResultScore.setUserId(resultSet.getString(2));
        downloadApplyResultScore.setUserName(resultSet.getString(3));
        downloadApplyResultScore.setDeptName(resultSet.getString(4));
        downloadApplyResultScore.setTitle(resultSet.getString(5));
        downloadApplyResultScore.setJudgeScore1(resultSet.getDouble(6));
        downloadApplyResultScore.setJudgeScore2(resultSet.getDouble(7));
        downloadApplyResultScore.setJudgeScore3(resultSet.getDouble(8));
        downloadApplyResultScore.setJudgeScore4(resultSet.getDouble(9));
        downloadApplyResultScore.setJudgeScore5(resultSet.getDouble(10));
        downloadApplyResultScore.setJudgeScore6(resultSet.getDouble(11));
        downloadApplyResultScore.setJudgeScore7(resultSet.getDouble(12));
        downloadApplyResultScore.setJudgeScore8(resultSet.getDouble(13));
        downloadApplyResultScore.setJudgeScore9(resultSet.getDouble(14));
        downloadApplyResultScore.setJudgeScore10(resultSet.getDouble(15));
        downloadApplyResultScore.setJudgeScore11(resultSet.getDouble(16));
        downloadApplyResultScore.setOriginalScoreSum(resultSet.getDouble(17));
        downloadApplyResultScore.setOriginalScoreAverage(resultSet.getDouble(18));
        downloadApplyResultScore.setWeightedScoreSum(resultSet.getDouble(19));
        downloadApplyResultScore.setWeightedScoreAverage(resultSet.getDouble(20));
        downloadApplyResultScore.setRanks(0);
        downloadApplyResultScore.setTimes(resultSet.getInt(21));


        String teamMembers = resultSet.getString(22);
        String resultTeamMember;
        if(teamMembers != null){
            if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,无;,,无;,,无;,,无") ||
                    teamMembers.equals(",,院系;,,院系;,,院系;,,院系")){
                downloadApplyResultScore.setTeamMember("无");
                downloadApplyResultScore.setTeamNumber("0");
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
                downloadApplyResultScore.setTeamMember(resultTeamMember);
                downloadApplyResultScore.setTeamNumber(String.valueOf(teamNames.split(",").length));
            }
        }else{
            downloadApplyResultScore.setTeamMember("无");
            downloadApplyResultScore.setTeamNumber("0");
        }
        downloadApplyResultScore.setProjectSort(resultSet.getString(23));
        downloadApplyResultScore.setCaseSort(resultSet.getString(24).substring(0, resultSet.getString(24).length()-1));
        downloadApplyResultScore.setUserCv(resultSet.getString(25));
        downloadApplyResultScore.setProjectEthicSort(resultSet.getString(26));
        downloadApplyResultScore.setProjectApplySort(resultSet.getString(27));
        downloadApplyResultScore.setIsPass(resultSet.getString(28));
        downloadApplyResultScore.setApplyPreComment(resultSet.getString(29));
        downloadApplyResultScore.setApplyId(resultSet.getString(30));
        downloadApplyResultScore.setUserTitle(resultSet.getString(31));
        downloadApplyResultScore.setUserPhone(resultSet.getString(32));
        downloadApplyResultScore.setUserMail(resultSet.getString(33));

        return downloadApplyResultScore;
    }

    //得到创新大赛每个评委的分数
    private DownloadInnoAllScore getInnoScoresFromResultSet(ResultSet resultSet) throws SQLException {
        DownloadInnoAllScore downloadInnoAllScore = new DownloadInnoAllScore();
        downloadInnoAllScore.setJudgeId(resultSet.getString(1));
        downloadInnoAllScore.setJudgeName(resultSet.getString(2));
        downloadInnoAllScore.setProjectNo(resultSet.getString(3));
        downloadInnoAllScore.setIsPass(resultSet.getString(4));
        downloadInnoAllScore.setProjectApplySort(resultSet.getString(5));
        downloadInnoAllScore.setCaseSort(resultSet.getString(6));
        downloadInnoAllScore.setProjectSort(resultSet.getString(7));
        downloadInnoAllScore.setDepartment(resultSet.getString(8));

        downloadInnoAllScore.setApplyPreComment(resultSet.getString(10));
        downloadInnoAllScore.setUserId(resultSet.getString(11));
        downloadInnoAllScore.setUsername(resultSet.getString(12));
        downloadInnoAllScore.setTitle(resultSet.getString(13));

        downloadInnoAllScore.setScore(resultSet.getDouble(15));
        downloadInnoAllScore.setScorePhase(resultSet.getString(16));

        String teamMembers = resultSet.getString(9);
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
        String comment = resultSet.getString(14);
        int index = comment.indexOf(']');
        if(index > 1){
            String score = comment.substring(1, index);
            String[] scores = score.split(",");
            int nums = scores.length;
            // 如果小分的数量正好是11个，则设置小分。最后一位标志是否提交
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
        } else {
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

    private DownloadPaperAllScore getPaperScoresFromResultSet(ResultSet resultSet) throws SQLException {
        DownloadPaperAllScore downloadPaperAllScore = new DownloadPaperAllScore();
        downloadPaperAllScore.setJudgeId(resultSet.getString(1));
        downloadPaperAllScore.setJudgeName(resultSet.getString(2));
        downloadPaperAllScore.setProjectNo(resultSet.getString(3));
        downloadPaperAllScore.setUserId(resultSet.getString(4));
        downloadPaperAllScore.setUserName(resultSet.getString(5));
        downloadPaperAllScore.setDeptName(resultSet.getString(6));
        downloadPaperAllScore.setTitle(resultSet.getString(7));
        downloadPaperAllScore.setScore(resultSet.getDouble(9));
        // 将评论里面的分数拆开
        // [9,35,16,16,5,24,26,23,18]采用混合式教学，课程资料完整。教学案例设计特色展示不足，课堂实录视频过长，效果不佳。
        String comment = resultSet.getString(8);
        int index = comment.indexOf(']');
        if(index > 1){
            String score = comment.substring(1, index);
            String[] scores = score.split(",");
            int nums = scores.length;
            // 如果小分的数量正好是11个，则设置小分。最后一位标志是否提交
            if(nums == 10){
                downloadPaperAllScore.setJudgeScore1(Integer.parseInt(scores[0]));
                downloadPaperAllScore.setJudgeScore2(Integer.parseInt(scores[1]));
                downloadPaperAllScore.setJudgeScore3(Integer.parseInt(scores[2]));
                downloadPaperAllScore.setJudgeScore4(Integer.parseInt(scores[3]));
                downloadPaperAllScore.setJudgeScore5(Integer.parseInt(scores[4]));
                downloadPaperAllScore.setJudgeScore6(Integer.parseInt(scores[5]));
                downloadPaperAllScore.setJudgeScore7(Integer.parseInt(scores[6]));
                downloadPaperAllScore.setJudgeScore8(Integer.parseInt(scores[7]));
                downloadPaperAllScore.setJudgeScore9(Integer.parseInt(scores[8]));
                downloadPaperAllScore.setJudgeScore10(Integer.parseInt(scores[9]));
            }
            // 否则就设置为-1
            else {
                downloadPaperAllScore.setJudgeScore1(0);
                downloadPaperAllScore.setJudgeScore2(0);
                downloadPaperAllScore.setJudgeScore3(0);
                downloadPaperAllScore.setJudgeScore4(0);
                downloadPaperAllScore.setJudgeScore5(0);
                downloadPaperAllScore.setJudgeScore6(0);
                downloadPaperAllScore.setJudgeScore7(0);
                downloadPaperAllScore.setJudgeScore8(0);
                downloadPaperAllScore.setJudgeScore9(0);
                downloadPaperAllScore.setJudgeScore10(0);
            }
            downloadPaperAllScore.setAssessment(comment.substring(index + 1));
        } else {
            downloadPaperAllScore.setJudgeScore1(0);
            downloadPaperAllScore.setJudgeScore2(0);
            downloadPaperAllScore.setJudgeScore3(0);
            downloadPaperAllScore.setJudgeScore4(0);
            downloadPaperAllScore.setJudgeScore5(0);
            downloadPaperAllScore.setJudgeScore6(0);
            downloadPaperAllScore.setJudgeScore7(0);
            downloadPaperAllScore.setJudgeScore8(0);
            downloadPaperAllScore.setJudgeScore9(0);
            downloadPaperAllScore.setJudgeScore10(0);
            downloadPaperAllScore.setAssessment("评论错误，请联系管理员。");
        }
        return downloadPaperAllScore;
    }

    //得到结题报告每个评委的分数，统计最后平均分
    private DownloadFinalResultScore getFinalResultFromResultSet(ResultSet resultSet, String isPre) throws SQLException{
        DownloadFinalResultScore downloadFinalResultScore = new DownloadFinalResultScore();
        downloadFinalResultScore.setProjectNo(resultSet.getString(1));
        downloadFinalResultScore.setUserId(resultSet.getString(2));
        downloadFinalResultScore.setUserName(resultSet.getString(3));
        downloadFinalResultScore.setDeptName(resultSet.getString(4));
        downloadFinalResultScore.setTitle(resultSet.getString(5));
        downloadFinalResultScore.setJudgeScore1(resultSet.getDouble(6));
        downloadFinalResultScore.setJudgeScore2(resultSet.getDouble(7));
        downloadFinalResultScore.setJudgeScore3(resultSet.getDouble(8));
        downloadFinalResultScore.setJudgeScore4(resultSet.getDouble(9));
        downloadFinalResultScore.setJudgeScore5(resultSet.getDouble(10));
        downloadFinalResultScore.setJudgeScore6(resultSet.getDouble(11));
        downloadFinalResultScore.setJudgeScore7(resultSet.getDouble(12));
        downloadFinalResultScore.setJudgeScore8(resultSet.getDouble(13));
        downloadFinalResultScore.setJudgeScore9(resultSet.getDouble(14));
        downloadFinalResultScore.setJudgeScore10(resultSet.getDouble(15));
        downloadFinalResultScore.setJudgeScore11(resultSet.getDouble(16));
        downloadFinalResultScore.setOriginalScoreSum(resultSet.getDouble(17));
        downloadFinalResultScore.setOriginalScoreAverage(resultSet.getDouble(18));
        downloadFinalResultScore.setWeightedScoreSum(resultSet.getDouble(19));
        downloadFinalResultScore.setWeightedScoreAverage(resultSet.getDouble(20));
        downloadFinalResultScore.setRanks(0);
        downloadFinalResultScore.setTimes(resultSet.getInt(21));

        return downloadFinalResultScore;
    }

    private DownloadFinalAllScore getFinalScoresFromResultSet(ResultSet resultSet, String isPre) throws SQLException {
        DownloadFinalAllScore downloadFinalAllScore = new DownloadFinalAllScore();
        downloadFinalAllScore.setJudgeId(resultSet.getString(1));
        downloadFinalAllScore.setJudgeName(resultSet.getString(2));
        downloadFinalAllScore.setProjectNo(resultSet.getString(3));
        downloadFinalAllScore.setUserId(resultSet.getString(4));
        downloadFinalAllScore.setUserName(resultSet.getString(5));
        downloadFinalAllScore.setDeptName(resultSet.getString(6));
        downloadFinalAllScore.setTitle(resultSet.getString(7));
        /*if(resultSet.getDouble(8)!=0 && isPre.equals("final")){
            downloadFinalAllScore.setScore(resultSet.getDouble(8));
        }else if(isPre.equals("finalPre")){
            int preScore = 20;
            String tempComment = resultSet.getString(7);
            int tempIndex = tempComment.indexOf(']');
            String[] tempScores = tempComment.substring(1,tempIndex).split(",");
            if(tempScores.length==11){
                preScore = Integer.parseInt(tempScores[9]);
            }
            downloadFinalAllScore.setScore(resultSet.getDouble(8) + preScore);
        }else{
            downloadFinalAllScore.setScore(0);
        }*/

        // 将评论里面的分数拆开
        // [9,35,16,16,5,24,26,23,18]采用混合式教学，课程资料完整。教学案例设计特色展示不足，课堂实录视频过长，效果不佳。
        String comment = resultSet.getString(8);
        int index = comment.indexOf(']');
        if(index > 1){
            String score = comment.substring(1, index);
            String[] scores = score.split(",");
            int nums = scores.length;
            // 如果小分的数量正好是11个，则设置小分。最后一位标志是否提交
            if(nums == 10){
                downloadFinalAllScore.setJudgeScore1(Integer.parseInt(scores[0]));
                downloadFinalAllScore.setJudgeScore2(Integer.parseInt(scores[1]));
                downloadFinalAllScore.setJudgeScore3(Integer.parseInt(scores[2]));
                downloadFinalAllScore.setJudgeScore4(Integer.parseInt(scores[3]));
                downloadFinalAllScore.setJudgeScore5(Integer.parseInt(scores[4]));
                downloadFinalAllScore.setJudgeScore6(Integer.parseInt(scores[5]));
                downloadFinalAllScore.setJudgeScore7(Integer.parseInt(scores[6]));
                downloadFinalAllScore.setJudgeScore8(Integer.parseInt(scores[7]));
                downloadFinalAllScore.setJudgeScore9(Integer.parseInt(scores[8]));
                downloadFinalAllScore.setPreJudgeScore(Integer.parseInt(scores[0]) +
                        Integer.parseInt(scores[1]) + Integer.parseInt(scores[2]) +
                        Integer.parseInt(scores[3]) + Integer.parseInt(scores[4]) +
                        Integer.parseInt(scores[5]) + Integer.parseInt(scores[6]) +
                        Integer.parseInt(scores[7]) + Integer.parseInt(scores[8]));
                if(isPre.equals("final")){
                    downloadFinalAllScore.setJudgeScore10(0);
                }else{
                    downloadFinalAllScore.setJudgeScore10(Integer.parseInt(scores[9]));
                }


            }
            // 否则就设置为-1
            else {
                downloadFinalAllScore.setJudgeScore1(0);
                downloadFinalAllScore.setJudgeScore2(0);
                downloadFinalAllScore.setJudgeScore3(0);
                downloadFinalAllScore.setJudgeScore4(0);
                downloadFinalAllScore.setJudgeScore5(0);
                downloadFinalAllScore.setJudgeScore6(0);
                downloadFinalAllScore.setJudgeScore7(0);
                downloadFinalAllScore.setJudgeScore8(0);
                downloadFinalAllScore.setJudgeScore9(0);
                downloadFinalAllScore.setPreJudgeScore(0);
                downloadFinalAllScore.setJudgeScore10(0);
            }
            downloadFinalAllScore.setAssessment(comment.substring(index + 1));
        } else {
            downloadFinalAllScore.setJudgeScore1(0);
            downloadFinalAllScore.setJudgeScore2(0);
            downloadFinalAllScore.setJudgeScore3(0);
            downloadFinalAllScore.setJudgeScore4(0);
            downloadFinalAllScore.setJudgeScore5(0);
            downloadFinalAllScore.setJudgeScore6(0);
            downloadFinalAllScore.setJudgeScore7(0);
            downloadFinalAllScore.setJudgeScore8(0);
            downloadFinalAllScore.setJudgeScore9(0);
            downloadFinalAllScore.setPreJudgeScore(0);
            downloadFinalAllScore.setJudgeScore10(0);
            downloadFinalAllScore.setAssessment("评论错误，请联系管理员。");
        }
        if(resultSet.getDouble(9)!=0 && isPre.equals("final")){
            double tempScore = resultSet.getDouble(9);
            tempScore = downloadFinalAllScore.getJudgeScore1() + downloadFinalAllScore.getJudgeScore2() +
                    downloadFinalAllScore.getJudgeScore3() + downloadFinalAllScore.getJudgeScore4() +
                    downloadFinalAllScore.getJudgeScore5() + downloadFinalAllScore.getJudgeScore6() +
                    downloadFinalAllScore.getJudgeScore7() + downloadFinalAllScore.getJudgeScore8() +
                    downloadFinalAllScore.getJudgeScore9();
            downloadFinalAllScore.setScore(tempScore);
        }else if(isPre.equals("finalPre")){
            double tempPreScore = resultSet.getDouble(9);
            tempPreScore = downloadFinalAllScore.getJudgeScore1() + downloadFinalAllScore.getJudgeScore2() +
                    downloadFinalAllScore.getJudgeScore3() + downloadFinalAllScore.getJudgeScore4() +
                    downloadFinalAllScore.getJudgeScore5() + downloadFinalAllScore.getJudgeScore6() +
                    downloadFinalAllScore.getJudgeScore7() + downloadFinalAllScore.getJudgeScore8() +
                    downloadFinalAllScore.getJudgeScore9() + downloadFinalAllScore.getJudgeScore10();
            downloadFinalAllScore.setScore(tempPreScore);
        }else{
            downloadFinalAllScore.setScore(0);
        }
        return downloadFinalAllScore;
    }

    private DownloadAllScore getProjectEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        DownloadAllScore downloadScore = new DownloadAllScore();
        downloadScore.setJudgeId(resultSet.getString(1));
        downloadScore.setJudgename(resultSet.getString(2));
        downloadScore.setUserId(resultSet.getString(3));
        downloadScore.setUsername(resultSet.getString(4));
        downloadScore.setTitle(resultSet.getString(5));
        downloadScore.setVideoScore(resultSet.getDouble(6));
        downloadScore.setDocumentScore(resultSet.getDouble(7));
        downloadScore.setScore(resultSet.getDouble(8));
        downloadScore.setComment(resultSet.getString(9));
        downloadScore.setGroup(resultSet.getString(10));
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
                downloadScore.setVideoScore1(0);
                downloadScore.setVideoScore2(0);
                downloadScore.setVideoScore3(0);
                downloadScore.setVideoScore4(0);
                downloadScore.setVideoScore5(0);
                downloadScore.setDocumentScore1(0);
                downloadScore.setDocumentScore2(0);
                downloadScore.setDocumentScore3(0);
                downloadScore.setDocumentScore4(0);
            }
            downloadScore.setAssessment(comment.substring(index + 1));
        }
        else {
            downloadScore.setVideoScore1(0);
            downloadScore.setVideoScore2(0);
            downloadScore.setVideoScore3(0);
            downloadScore.setVideoScore4(0);
            downloadScore.setVideoScore5(0);
            downloadScore.setDocumentScore1(0);
            downloadScore.setDocumentScore2(0);
            downloadScore.setDocumentScore3(0);
            downloadScore.setDocumentScore4(0);
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
