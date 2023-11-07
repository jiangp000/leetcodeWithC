package action.research;

import com.opensymphony.xwork2.ActionSupport;
import domain.*;
import service.SelfResearchScoreService;
import util.XLSXUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by widesui on 21/7/12.
 */
public class GetSelfResearchScoreAction extends ActionSupport {
    private int year;
    private String userId;
    private ArrayList<Integer> years;
    private String researchType;
    private String scorePhase;
    private ArrayList<ArrayList<SelfResearchScore>> selfResearchScoreList;
    private ArrayList<TeacherInnoAllScore> selfInnoAllScoreList;
    private ArrayList<ArrayList<TeacherInnoResultScore>> selfInnoResScoreList;
    private ArrayList<SelfPaperScore> selfPaperScoreList;
    private ArrayList<SelfPaperScore> selfFinalScoreList;
    private SelfResearchScoreService selfResearchScoreService;
    private ArrayList<Files> fileList;

    public GetSelfResearchScoreAction() {
        selfResearchScoreService = new SelfResearchScoreService();
    }

    public String getSelfScoreJSP() {
        years = selfResearchScoreService.getYearsFromResearchScore();
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        return SUCCESS;
    }

    public String getSelfScore() {
        //修改这里的filePath时,需要相应修改domain.ResearchEnroll的toCSVStr()
        String filePath = "ResultEnroll/";
        String paperFileName = userId + "-" + String.valueOf(year) + "-" + researchType + "分数表";
        String finalFileName = userId + "-" + String.valueOf(year) + "-" + "结题分数表";
        String fields = "评委,项目编号,校园卡号,负责人,所属院系,论文题目,团队成员姓名,选题意义,理论基础,研究方法," +
                "论据实证,创新性,问题解决,学术能力,教研成果,书写规范,推广价值,总分,总评意见";
        String paperFields = "评委,项目编号,校园卡号,负责人,所属院系,团队成员姓名,论文题目,选题意义,理论基础,研究方法," +
                "论据实证,创新性,问题解决,学术能力,教研成果,书写规范,推广价值,总分,总评意见";
        String finalFields = "评委,项目编号,校园卡号,负责人,所属院系,结题题目,团队成员姓名,项目参与,大赛奖项,论文奖项," +
                "结题报告,完成程度,教改效果,创新程度,示范作用,经费使用,现评结果,总分,总评意见";
        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";
        }
        selfResearchScoreList = selfResearchScoreService.getSelfResearchScoreByIdYear(userId,year);
        ArrayList<ArrayList<SelfResearchScore>> resultGetListCopy = selfResearchScoreService.getSelfResearchScoreByIdYear(userId, year);

        ArrayList<SelfPaperScore> resultGetPapListCopy = new ArrayList<>();
        ArrayList<SelfPaperScore> resultGetFinListCopy = new ArrayList<>();
        if(researchType.equals("教研论文")){
            selfPaperScoreList = selfResearchScoreService.getSelfPaperScoreByIdYear(userId, year, "教研论文");
            resultGetPapListCopy = selfResearchScoreService.getSelfPaperScoreByIdYear(userId, year, "教研论文");

        }else if(researchType.equals("结题报告")){
            selfFinalScoreList = selfResearchScoreService.getSelfPaperScoreByIdYear(userId, year, "结题报告");
            resultGetFinListCopy = selfResearchScoreService.getSelfPaperScoreByIdYear(userId, year, "结题报告");

        }




        if (!selfResearchScoreList.isEmpty()) {
            try {
                //XLSUtil.createSelfXLSFileWithSheets(resultGetListCopy, fields, filePath,fileName, "domain.SelfResearchScore");
                if(researchType.equals("教研论文")){
                    XLSXUtil.createSelfPaperXLSFileWithOneSheet(resultGetPapListCopy, paperFields,paperFileName);
                }else if(researchType.equals("结题报告")){
                    XLSXUtil.createSelfPaperXLSFileWithOneSheet(resultGetFinListCopy, finalFields,finalFileName);
                }



            } catch (IOException e) {
                e.printStackTrace();
            }



            /*String link = CSVUtil.createCSVFile(resultGetListCopy, fields, filePath, fileName, "domain.ResultGet");
            Files files = new Files();
            files.setFileName(fileName + ".csv");
            files.setFilePath(link);
            fileList.add(files);*/
        }
        return SUCCESS;
    }

    public String getInnoSelfScore() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        //修改这里的filePath时,需要相应修改domain.ResearchEnroll的toCSVStr()
        String filePath = "ResultEnroll/";
        String innoFileName = userId + "-" + String.valueOf(year) + "-" + "创新大赛" + scorePhase + "表";
        String preFields = "评委,大赛编号,初审结果,案例分组,案例类型,项目类型,校园卡号,负责人,所属院系,参赛案例名,团队成员,教学理念,案例内容,教学模式,教学效果,视频质量,案例视频评分总计," +
                "案例目标及需求分析,创新理念及思路,创新方法及途径,创新效果及成果,创新成果报告等材料评分总计,最终网评成绩,初审意见,专家评语";
        String pptFields = "评委,大赛编号,初审结果,案例分组,案例类型,项目类型,校园卡号,负责人,所属院系,参赛案例名,团队成员," +
                "理念与目标,教学内容,过程与方法,考评与反馈,创新应用与现场表现,现评结果,初审意见,专家评语";
        String resFields = "评委,大赛编号,案例分组,案例类型,项目类型,工号,姓名,课程名,院系,团队成员,人数,网评评委1,网评评委2,网评评委3,网评评委4,网评评委5," +
                "现评评委6,现评评委7,现评评委8,现评评委9,现评评委10,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,排名,评分次数";

        fileList = new ArrayList<>();
        /*if (year == 0) {
            return "noyear";
        }*/

        selfInnoAllScoreList = selfResearchScoreService.getSelfInnoScoreByIdYear(userId,year,scorePhase);
        ArrayList<TeacherInnoAllScore> selfInnoAllScoreListCopy = selfResearchScoreService.getSelfInnoScoreByIdYear(userId, year, scorePhase);

        //selfInnoResScoreList = selfResearchScoreService.getSelfInnoResScoreByIdYear(userId,year,scorePhase);
        //ArrayList<ArrayList<TeacherInnoResultScore>> selfInnoResScoreListCopy = selfResearchScoreService.getSelfInnoResScoreByIdYear(userId, year, scorePhase);
        if (!selfInnoAllScoreList.isEmpty()) {
            try {
                //XLSUtil.createSelfXLSFileWithSheets(resultGetListCopy, fields, filePath,fileName, "domain.SelfResearchScore");
                if(scorePhase.equals("网评分数")){
                    XLSXUtil.createSelfInnoPreXLSFileWithOneSheet(selfInnoAllScoreListCopy, preFields, innoFileName);
                }else if(scorePhase.equals("现评分数")){
                    XLSXUtil.createSelfInnoPPTXLSFileWithOneSheet(selfInnoAllScoreListCopy, pptFields,innoFileName);
                }else if(scorePhase.equals("大赛分数")){
                    //XLSUtil.createSelfPaperXLSFileWithOneSheet(selfInnoResScoreListCopy, resFields,innoFileName);
                }



            } catch (IOException e) {
                e.printStackTrace();
            }



            /*String link = CSVUtil.createCSVFile(resultGetListCopy, fields, filePath, fileName, "domain.ResultGet");
            Files files = new Files();
            files.setFileName(fileName + ".csv");
            files.setFilePath(link);
            fileList.add(files);*/
        }
        return SUCCESS;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUserId(){return userId;}

    public void setUserId(String userId){this.userId = userId;}

    public ArrayList<Integer> getYears() {
        return years;
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

    public String getScorePhase() {
        return scorePhase;
    }

    public void setScorePhase(String scorePhase) {
        this.scorePhase = scorePhase;
    }

    public ArrayList<ArrayList<SelfResearchScore>> getSelfResearchScoreList() {
        return selfResearchScoreList;
    }

    public void setSelfResearchScoreList(ArrayList<ArrayList<SelfResearchScore>> selfResearchScoreList) {
        this.selfResearchScoreList = selfResearchScoreList;
    }

    public SelfResearchScoreService getSelfResearchScoreService() {
        return selfResearchScoreService;
    }

    public void setSelfResearchScoreService(SelfResearchScoreService selfResearchScoreService) {
        this.selfResearchScoreService = selfResearchScoreService;
    }

    public ArrayList<Files> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }
}
