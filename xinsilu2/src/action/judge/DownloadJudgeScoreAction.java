package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.*;
import service.DownloadAllScoreService;
import service.DownloadScoreService;
import service.ResearchEnrollService;
import util.XLSXUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author widesui
 * @version 1.0
 * @date 2021/7/13 22:00
 */
public class DownloadJudgeScoreAction extends ActionSupport {

    private int year;
    private String researchType;
    private String userId;
    private ArrayList<Integer> years;
    private ArrayList<DownloadJudgeScore> scoreList;
    private ArrayList<DownloadInnoAllScore> scoreInnoList;
    private ArrayList<ArrayList<DownloadInnoAllScore>> innoAllScoreList;
    private ArrayList<DownloadInnoResultScore> innoResScoreList;
    private DownloadScoreService downloadScoreService;
    private DownloadAllScoreService downloadAllScoreService;
    private ArrayList<Files> fileList;
    private String scoreStageControl;
    private ResearchEnrollService researchEnrollService;
    private String scorePhase;  // 创新大赛的两个阶段
    private ActionContext context;
    private Map<String, Object> session;
    private static Logger logger = Logger.getLogger("downloadJudgeScoreAction");

    public DownloadJudgeScoreAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        downloadScoreService= new DownloadScoreService();
        downloadAllScoreService= new DownloadAllScoreService();
        this.userId = (String)session.get("judge2");
        researchEnrollService = new ResearchEnrollService();
        scoreStageControl = researchEnrollService.getScoreStage( "创新大赛");
    }

    public String downloadScoreJSP() {

        logger.info("userID: " + userId);
        //System.out.println("userId是：" + userId);
        if(userId==null||userId.length()==0){
            userId = "2201210350";
        }
        // todo 是否考虑修改回来
        //years = downloadScoreService.getYearsFromResearchScore(userId);
        Calendar now = Calendar.getInstance();
        int year_now = now.get(Calendar.YEAR);
        years = new ArrayList<>();
        years.add(year_now - 1);
        years.add(year_now);
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        return SUCCESS;
    }

    // 从数据库中查出所有未打分的条目，也添加进list中
    public ArrayList<DownloadInnoAllScore> AddNotScoredItem(ArrayList<DownloadInnoAllScore> scoreList,String researchType,String year){
        ArrayList<DownloadInnoAllScore> res = new ArrayList<>();
        ResearchEnrollService researchEnrollService = new ResearchEnrollService();
        ArrayList<ResearchEnroll> allEnrolls;
        if(researchType.equals("创新大赛")){
            allEnrolls = researchEnrollService.getInnoResearchEnrollByYearAndType(researchType,year);
        }else{
            allEnrolls = researchEnrollService.getResearchEnrollByYearAndType(researchType,year);
        }
        HashSet<String> scoredEnrolls = new HashSet<>();
        for(DownloadInnoAllScore d:scoreList){
            if(d.getAssessment().equals("评论错误，请联系管理员。")){
                continue;
            }
            d.setScored(true);
            scoredEnrolls.add(d.getProjectID());
            res.add(d);
        }
        for(ResearchEnroll r:allEnrolls){
            int projectID = r.getId();
            // 未评分
            if(!scoredEnrolls.contains(String.valueOf(projectID))){
                DownloadInnoAllScore d = new DownloadInnoAllScore();
                d.setScored(false);
                d.setProjectID(String.valueOf(projectID));
                d.setUserId(r.getUserId());
                d.setUsername(r.getUsername());
                d.setTeamMember(r.getTeamMember());
                d.setTitle(r.getTitle());
                d.setDepartment(r.getDeptName());
                d.setProjectNo(r.getProjectNo());
                // 以下是未评分的默认字段
                d.setAssessment("尚未评分");
                d.setScore(100);
                d.setJudgeScore1(10);
                d.setJudgeScore2(10);
                d.setJudgeScore3(10);
                d.setJudgeScore4(10);
                d.setJudgeScore5(10);
                d.setJudgeScore6(10);
                d.setJudgeScore7(10);
                d.setJudgeScore8(10);
                d.setJudgeScore9(10);
                d.setJudgeScore10(10);
                if(researchType.equals("结题报告")){
                    d.setJudgeScore8(5);
                    d.setJudgeScore9(5);
                    d.setJudgeScore10(20);
                }else if(researchType.equals("项目申报")||researchType.equals("中期报告")){
                    d.setJudgeScore9(0);
                    d.setJudgeScore10(20);
                }else if(researchType.equals("创新大赛")){
                    d.setJudgeScore1(4);
                    d.setJudgeScore2(8);
                    d.setJudgeScore3(8);
                    d.setJudgeScore4(6);
                    d.setJudgeScore5(4);
                    d.setJudgeScore6(8);
                    d.setJudgeScore7(8);
                    d.setJudgeScore8(8);
                    d.setJudgeScore9(6);
                    d.setJudgeScore10(6);
                    d.setJudgeScore11(8);
                    d.setJudgeScore12(8);
                    d.setJudgeScore13(8);
                    d.setJudgeScore14(10);
                    d.setProjectNo(r.getApplyId());
                    d.setIsPass(r.getIsPass());
                    d.setProjectApplySort(r.getProjectApplySort());
                    d.setCaseSort(r.getCaseSort());
                    d.setProjectSort(r.getProjectSort());
                    d.setApplyPreComment(r.getApplyPreComment());
                    d.setUserCv(r.getUserCV());
                }
                res.add(d);
            }
        }
        return res;
    }

    // 评委下载评分表 包含各阶段
    public String downloadJudgeScore() throws IOException {
        if(userId==null||userId.length()==0){
            userId = "2201210350";
        }
        String fileName = String.valueOf(userId) + "-" + String.valueOf(year) + "-" + String.valueOf(researchType) + "-" + "打分表";
        String fields = "";
        if(researchType.equals("项目申报")) {
            fields = "项目编号,校园卡号,负责人,所属院系,研究课题,团队成员,问题认识清晰(10),项目目标明确(10),创新意义重大(10),创改思路独特(10),基础成果显著(10),预期成果丰富(10),实施难度很大(10),经费预算合理(10),预评结果,现场汇报(20),总分,总评意见";
        }else if(researchType.equals("创新大赛")){
            System.out.println(scorePhase);
            if(scorePhase.equals("网评")){
                fields ="大赛编号,初审结果,项目类型,案例类型,案例分组,校园卡号,负责人,所属院系,参赛案例名,团队成员,教学理念(4),案例内容(8),教学模式(8),教学效果(6),视频质量(4),案例视频评分总计," +
                        "案例目标及需求分析(8),创新理念及思路(8),创新方法及途径(8),创新效果及成果(6),创新成果报告等材料评分总计,最终网评成绩,初审意见,专家评语";
                fileName = String.valueOf(userId) + "-" + String.valueOf(year) + "-" + String.valueOf(researchType) + "-" + "网评打分表";
            }else{
                fields = "大赛编号,初审结果,项目类型,案例类型,案例分组,校园卡号,负责人,所属院系,参赛案例名,团队成员," +
                        "理念与目标(6),教学内容(8),过程与方法(8),考评与反馈(8),创新应用与现场表现(10),现评结果,初审意见,专家评语";
                fileName = String.valueOf(userId) + "-" + String.valueOf(year) + "-" + String.valueOf(researchType) + "-" + "现评打分表";
            }
        }else if(researchType.equals("中期报告")){
            fields = "项目编号,校园卡号,负责人,所属院系,研究课题,团队成员,问题认识清晰(10),项目目标明确(10),创新意义重大(10),创改思路独特(10),基础成果显著(10),预期成果丰富(10),实施难度很大(10),经费预算合理(10),预评结果,现场汇报(20),总分,总评意见";
        }else if(researchType.equals("教研论文")){
            fields = "项目编号,校园卡号,负责人,所属院系,论文题目,论文作者,选题意义(10),理论基础(10),研究方法(10),论据实证(10),创新性(10),问题解决(10),学术能力(10),教研成果(10),书写规范(10),推广价值(10),总分,总评意见";
        }else if(researchType.equals("结题报告")){
            fields = "项目编号,校园卡号,负责人,工作单位,研究课题题目,团队成员,项目参与(10),大赛奖项(10),论文奖项(10),结题报告(10),完成程度(10),教改效果(10),创新程度(10),示范作用(5),经费使用(5),预评结果,现场汇报(20),总分,总评意见";
        }
        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";
        }
        if(researchType.equals("创新大赛")){
            logger.info("创新大赛已评分数据");
            scoreInnoList = downloadScoreService.getInnoScoreListByYearType(year, userId, researchType, "网评分数");
        }else{
            scoreInnoList = downloadScoreService.getResearchScoreListByYearType(year, userId, researchType);
        }

        if(researchType.equals("创新大赛")){
            logger.info("创新大赛未评分数据");
            scoreInnoList = AddNotScoredItem(scoreInnoList,researchType,String.valueOf(year));
            researchType = researchType+"_"+scorePhase;
            XLSXUtil.createXLSXWithOneSheet(scoreInnoList,researchType,fields,fileName);
        }else{
            scoreInnoList =  AddNotScoredItem(scoreInnoList,researchType, String.valueOf(year));
            XLSXUtil.createXLSXWithOneSheet(scoreInnoList,researchType,fields,fileName);
        }

        return SUCCESS;
    }


    // 下载创新大赛总成绩
    public String downloadInnoResScore() throws IOException {
//        String filePath = "JudgeScore/";
        //userId = "0006170278";
        if(userId==null||userId.length()==0){
            userId = "2201210350";
        }
        String fileName = String.valueOf(year) + "-" + researchType + "-" + "网评打分汇总表";
        System.out.println(fileName);

        String fieldInno = "大赛编号,工号,姓名,课程名,所属院系,团队成员,教学理念,案例内容,教学模式,教学效果,视频质量,案例视频评分总计," +
                    "案例目标及需求分析,创新理念及思路,创新方法及途径,创新效果及成果,创新成果报告等材料评分总计,最终网评成绩,理念与目标,教学内容,过程与方法,考评与反馈,创新应用与现场表现,现评结果,大赛评分总计,专家评语";
        String fieldInnoResult = "大赛编号,案例分组,案例类型,项目类型,工号,姓名,课程名,院系,团队成员,人数,负责人简介,初审意见," +
                    "网评评委1,网评评委2,网评评委3,网评评委4,网评评委5,网评评委6,网评评委7,网评评委8,网评评委9,网评评委10,网评评委11,网评评委12,网评评委13,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,网评评分次数," +
                    "现评评委1,现评评委2,现评评委3,现评评委4,现评评委5,现评评委6,现评评委7,现评原始总分,现评原始平均分,现评加权总分,现评加权平均分,现评评分次数," +
                    "评委原始总分,评委原始平均分,评委加权总分,评委加权平均分,排名";

        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";
        }
        innoAllScoreList = downloadAllScoreService.getInnoJudgeScoreList(year,"创新大赛");
        innoResScoreList = downloadAllScoreService.getInnoResultScoreList(year,"创新大赛");
        String userType = "judge";
        if (!innoAllScoreList.isEmpty()&&!innoResScoreList.isEmpty()) {
            try{
                XLSXUtil.createInnoScoreXLSFileWithSheet(innoAllScoreList,
                        innoResScoreList, fieldInno, fieldInnoResult, fileName, userType);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void setYears(ArrayList<Integer> years) {
        this.years = years;
    }

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

    public void setScoreList(ArrayList<DownloadJudgeScore> scoreList) {
        this.scoreList = scoreList;
    }

    public ArrayList<DownloadInnoAllScore> getScoreInnoList() {
        return scoreInnoList;
    }


    public DownloadAllScoreService getDownloadAllScoreService() {
        return downloadAllScoreService;
    }

    public void setDownloadAllScoreService(DownloadAllScoreService downloadAllScoreService) {
        this.downloadAllScoreService = downloadAllScoreService;
    }

    public void setScoreInnoList(ArrayList<DownloadInnoAllScore> scoreInnoList) {
        this.scoreInnoList = scoreInnoList;
    }

    public void setDownloadScoreService(DownloadScoreService downloadScoreService) {
        this.downloadScoreService = downloadScoreService;
    }

    public ArrayList<DownloadInnoResultScore> getInnoResScoreList() {
        return innoResScoreList;
    }

    public void setInnoResScoreList(ArrayList<DownloadInnoResultScore> innoResScoreList) {
        this.innoResScoreList = innoResScoreList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }

    public int getYear() {
        return year;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<Integer> getYears() {
        return years;
    }

    public ArrayList<DownloadJudgeScore> getScoreList() {
        return scoreList;
    }

    public DownloadScoreService getDownloadScoreService() {
        return downloadScoreService;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public String getScoreStageControl() {
        return scoreStageControl;
    }

    public void setScoreStageControl(String scoreStageControl) {
        this.scoreStageControl = scoreStageControl;
    }

    public String getScorePhase() {
        return scorePhase;
    }

    public void setScorePhase(String scorePhase) {
        this.scorePhase = scorePhase;
    }
}