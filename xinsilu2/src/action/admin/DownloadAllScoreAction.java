package action.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DownloadAllScoreDAO;
import domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.DownloadAllScoreService;
import util.XLSXUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

/**
 * @author widesui
 * @version 1.0
 * @date 2021/7/13 22:00
 */
public class DownloadAllScoreAction extends ActionSupport {

    private int year;
    private ArrayList<Integer> years;
    private ArrayList<ArrayList<DownloadAllScore>> allScoreList;
    private ArrayList<ArrayList<DownloadApplyAllScore>> applyAllScoreList;
    private ArrayList<DownloadApplyResultScore> applyResultScoreList;
    private ArrayList<ArrayList<DownloadInnoAllScore>> innoAllScoreList;
    private ArrayList<DownloadInnoResultScore> innoResultScoreList;
    private ArrayList<ArrayList<DownloadPaperAllScore>> paperAllScoreList;
    private ArrayList<DownloadPaperResultScore> paperResultScoreList;
    private ArrayList<ArrayList<DownloadFinalAllScore>> finalAllScoreList;
    private ArrayList<DownloadFinalResultScore> finalResultScoreList;
    private ArrayList<ArrayList<DownloadFinalAllScore>> finalPreAllScoreList;
    private ArrayList<DownloadFinalResultScore> finalPreResultScoreList;
    private DownloadAllScoreService downloadAllScoreService;
    private ArrayList<Files> fileList;

    private String researchType;
    private String scoreType;
    private String haveJudgeName;

    private String file1;
    private String file2;

    private ActionContext context;
    private Map<String, Object> session;

    private static Logger logger = LogManager.getLogger(DownloadAllScoreAction.class);

    public DownloadAllScoreAction() {
        downloadAllScoreService= new DownloadAllScoreService();
    }

    public String downloadInnovationScore(){
        return downloadInnoScore();
    }

    public String downloadPaperScore(){
        return downloadPapScore();
    }

    public String downloadFinalScore(){
        return downloadFinScore();
    }

    public String downloadFinalPreScore(){
        return downloadFinPreScore();
    }

    public String downloadAllScoreJSP() {
        years = downloadAllScoreService.getYearsFromResearchScore();
        if (years.size() == 0) {
            return SUCCESS;
        }
        if (year == 0) {
            year = years.get(years.size() - 1);
        }
        return SUCCESS;
    }

    public String getDataScoreFile() throws IOException {
        //file1 = WEBResearchEnrollFilesDIR + "2021/分数/2021教研论文分数表.xlsx";
        //file2 = WEBResearchEnrollFilesDIR + "2021/分数/2021结题报告分数表.xlsx";
        file1 = "/upload/xinsilu/researchEnroll/2021/分数/2021教研论文分数表.xlsx";
        file2 = "/upload/xinsilu/researchEnroll/2021/分数/2021结题报告分数表.xlsx";

        return SUCCESS;
    }

    //下载各个阶段的网评分数统计
    public String downloadScore(){
        Calendar date = Calendar.getInstance();
        //year = Integer.valueOf(date.get(Calendar.YEAR));
//        String filePath = "JudgeScore/";
        String fileName = "";
        String fieldApply = "";
        String fieldApplyResult = "";
        String fieldInno = "";
        String innoPPTFields = "";
        String innoPreFields = "";
        String fieldInnoResult = "";
        String fieldPaper = "";
        String fieldPaperResult = "";
        String fieldFinal = "";
        String filedFinalResult = "";
        String fieldResult = "";
        fileName = "";
        fieldApply = "申请编号,工号,姓名,院系,项目名称,是否通过,初审意见,申请类型,伦理项目,项目方向,团队成员,负责人简介,问题认识清晰,项目目标明确," +
                "创新意义重大,有创新改进思路,已取得显著成果,预期成果丰富,项目实施难度大,经费预算合理,预评结果,现场评分,评分总计,申报建议";
        fieldInno = "大赛编号,项目类型,案例类型,案例分组,工号,姓名,课程名,所属院系,团队成员,教学理念,案例内容,教学模式,教学效果,视频质量,案例视频评分总计," +
                "案例目标及需求分析,创新理念及思路,创新方法及途径,创新效果及成果,创新成果报告等材料评分总计,最终网评成绩,理念与目标,教学内容,过程与方法,考评与反馈,创新应用与现场表现,现评结果,大赛评分总计,专家评语";

        innoPreFields = "大赛编号,项目类型,案例类型,案例分组,工号,负责人,案例名,所属院系,团队成员,教学理念,案例内容,教学模式,教学效果,视频质量,案例视频评分总计," +
                "案例目标及需求分析,创新理念及思路,创新方法及途径,创新效果及成果,创新成果报告等材料评分总计,最终网评成绩,专家评语";

        innoPPTFields = "大赛编号,项目类型,案例类型,案例分组,工号,负责人,案例名,所属院系,团队成员," +
                "理念与目标,教学内容,过程与方法,考评与反馈,创新应用与现场表现,现评结果,专家评语";


        fieldInnoResult = "大赛编号,项目类型,案例类型,案例分组,工号,负责人,案例名,院系,团队成员,人数,负责人简介,初审意见," +
                "网评评委1,网评评委2,网评评委3,网评评委4,网评评委5,网评评委6,网评评委7,网评评委8,网评评委9,网评评委10,网评评委11,网评评委12,网评评委13,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,网评评分次数," +
                "现评评委1,现评评委2,现评评委3,现评评委4,现评评委5,现评评委6,现评评委7,现评原始总分,现评原始平均分,现评加权总分,现评加权平均分,现评评分次数," +
                "评委原始总分,评委原始平均分,评委加权总分,评委加权平均分,排名";
        fieldPaper = "项目编号,工号,姓名,所属院系,课题名,选题意义,理论基础,研究方法,论据实证," +
                "创新性,问题解决,学术能力,教研成果,书写规范,推广价值,网评成绩,评语";
        fieldFinal = "项目编号,工号,姓名,所属院系,课题名,项目参与,大赛奖项,论文奖项,结题报告," +
                "完成程度,教改效果,创新程度,示范作用,经费使用,预评结果,现评结果,网评成绩,评语";
        fieldPaperResult = "项目编号,工号,姓名,所属院系,课程名,评委1,评委2,评委3,评委4,评委5,评委6,评委7,评委8," +
                "评委9,评委10,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,排名,评分次数";
        filedFinalResult = "项目编号,工号,姓名,所属院系,课题名,评委1,评委2,评委3,评委4,评委5,评委6,评委7,评委8," +
                "评委9,评委10,评委11,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,排名,评分次数";

        if (year == 0) {
            return "noyear";
        }
        if(researchType.equals("项目申报")){
            fileName = String.valueOf(year) + "-" + researchType + "-" + "网评分数汇总表";
            String isPre = "applyPre";
            if(scoreType.equals("网评分数")){
                isPre = "apply";
                fileName = String.valueOf(year) + "-" + researchType + "-" + "网评分数汇总表";
            }else{
                isPre = "applyPre";
                fileName = String.valueOf(year) + "-" + researchType + "-" + "最终分数汇总表";
            }
            fieldApplyResult = "申请编号,工号,姓名,院系,项目名称,是否通过,初审意见,申请类型,伦理项目,项目方向,团队成员,人数,负责人简介," +
                    "评委1,评委2,评委3,评委4,评委5,评委6,评委7,评委8,评委9,评委10,评委11," +
                    "原始总分,原始平均分,加权总分,加权平均分,排名,评分次数";
            applyAllScoreList = downloadAllScoreService.getApplyJudgeScoreList(year,"项目申报", isPre);

            applyResultScoreList = downloadAllScoreService.getApplyResultScoreList(year,"项目申报", isPre);

            String userType = "admin";
            if(haveJudgeName.equals("管理员")){
                userType = "admin";
                fileName = fileName + "-管理员";
            }else{
                fileName = fileName + "-给评委";
                userType = "judge";
            }
            if (!applyAllScoreList.isEmpty() && !applyResultScoreList.isEmpty()) {
                try{
                    XLSXUtil.createApplyScoreXLSFileWithSheets(applyAllScoreList, applyResultScoreList, fieldApply, fieldApplyResult, fileName,isPre, userType);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else if(researchType.equals("创新大赛") && scoreType.equals("网评分数")){
            fileName = String.valueOf(year) + "-" + researchType + "-" + "网评分数汇总表";
            fieldInnoResult = "大赛编号,项目类型,案例类型,案例分组,工号,负责人,案例名,院系,团队成员,人数,负责人简介,初审意见," +
                    "网评评委1,网评评委2,网评评委3,网评评委4,网评评委5,网评评委6,网评评委7,网评评委8,网评评委9,网评评委10,网评评委11," +
                    "网评评委12,网评评委13,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,网评评分次数,排名";
            innoAllScoreList = downloadAllScoreService.getInnoPreJudgeScoreList(year,"创新大赛");
            innoResultScoreList = downloadAllScoreService.getInnoPreScoreList(year,"创新大赛");

            String userType = "admin";
            if(haveJudgeName.equals("管理员")){
                userType = "admin";
                fileName = fileName + "-管理员";
            }else{
                fileName = fileName + "-给评委";
                userType = "judge";
            }
            // todo delete
            logger.info("pre innoAllScoreList size: " + innoAllScoreList.size());
            for (int i = 0; i < innoAllScoreList.size(); ++i){
                for (int j = 0; j < innoAllScoreList.get(i).size(); ++i){
                    logger.info(innoAllScoreList.get(i).get(j).toCSVStr());
                }
            }
            if (!innoAllScoreList.isEmpty() && !innoResultScoreList.isEmpty()) {
                try{
                    XLSXUtil.createInnoPreScoreXLSFileWithSheets(innoAllScoreList, innoResultScoreList, innoPreFields, innoPPTFields, fieldInnoResult, fileName, userType);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else if(researchType.equals("创新大赛") && scoreType.equals("最终分数")){
            fileName = String.valueOf(year) + "-" + researchType + "-" + "大赛分数汇总表";
            innoAllScoreList = downloadAllScoreService.getInnoJudgeScoreList(year,"创新大赛");
            innoResultScoreList = downloadAllScoreService.getInnoResultScoreList(year,"创新大赛");
            String userType = "admin";
            if(haveJudgeName.equals("管理员")){
                userType = "admin";
                fileName = fileName + "-管理员";
            }else{
                fileName = fileName + "-给评委";
                userType = "judge";
            }
            // todo delete
            logger.info("final innoAllScoreList size: " + innoAllScoreList.size());
            for (int i = 0; i < innoAllScoreList.size(); ++i){
                for (int j = 0; j < innoAllScoreList.get(i).size(); ++i){
                    logger.info(innoAllScoreList.get(i).get(j).toCSVStr());
                }
            }
            if (!innoAllScoreList.isEmpty() && !innoResultScoreList.isEmpty()) {
                try{
                    XLSXUtil.createInnoScoreXLSFileWithSheets(innoAllScoreList, innoResultScoreList, innoPreFields, innoPPTFields, fieldInnoResult, fileName, userType);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else if(researchType.equals("教研论文")){
            fileName = String.valueOf(year) + "-" + researchType + "-" + "分数汇总表";
            paperAllScoreList = downloadAllScoreService.getPaperJudgeScoreList(year,"教研论文");
            paperResultScoreList = downloadAllScoreService.getPaperResultScoreList(year,"教研论文");
            String userType = "admin";
            if(haveJudgeName.equals("管理员")){
                userType = "admin";
                fileName = fileName + "-管理员";
            }else{
                fileName = fileName + "-给评委";
                userType = "judge";
            }
            if (!paperAllScoreList.isEmpty() && !paperResultScoreList.isEmpty()) {
                try{
                    XLSXUtil.createPaperScoreXLSFileWithSheets(paperAllScoreList, paperResultScoreList, fieldPaper, fieldPaperResult, fileName, userType);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else if(researchType.equals("结题报告")){
            fileName = String.valueOf(year) + "-" + researchType + "-" + "分数汇总表";
            String isPre = "finalPre";
            if(scoreType.equals("网评分数")){
                isPre = "final";
                fileName = String.valueOf(year) + "-" + researchType + "-" + "网评分数汇总表";
            }else{
                isPre = "finalPre";
                fileName = String.valueOf(year) + "-" + researchType + "-" + "最终分数汇总表";
            }
            finalAllScoreList = downloadAllScoreService.getFinalJudgeScoreList(year,"结题报告",isPre);
            finalResultScoreList = downloadAllScoreService.getFinalResultScoreList(year,"结题报告",isPre);
            String userType = "admin";
            if(haveJudgeName.equals("管理员")){
                userType = "admin";
                fileName = fileName + "-管理员";
            }else{
                fileName = fileName + "-给评委";
                userType = "judge";
            }

            if (!finalAllScoreList.isEmpty() && !finalResultScoreList.isEmpty()) {
                try{
                    XLSXUtil.createFinalScoreXLSFileWithSheets(finalAllScoreList, finalResultScoreList, fieldFinal, filedFinalResult, fileName,isPre, userType);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return SUCCESS;
    }

    public String downloadInnoScore(){
//        String filePath = "JudgeScore/";
        String fileName = "";
        String fieldInno = "";
        String innoPPTFields = "";
        String innoPreFields = "";

        innoPreFields = "大赛编号,案例分组,案例类型,项目类型,工号,姓名,课程名,所属院系,团队成员,教学理念,案例内容,教学模式,教学效果,视频质量,案例视频评分总计," +
                "案例目标及需求分析,创新理念及思路,创新方法及途径,创新效果及成果,创新成果报告等材料评分总计,最终网评成绩,专家评语";

        innoPPTFields = "大赛编号,案例分组,案例类型,项目类型,工号,姓名,课程名,所属院系,团队成员," +
                "理念与目标,教学内容,过程与方法,考评与反馈,创新应用与现场表现,现评结果,大赛评分总计,专家评语";

        String fieldInnoResult = "";
        fileName = String.valueOf(year) + "-" + "创新大赛" + "-" + "网评打分汇总表";
        fieldInno = "评委工号,评委姓名,项目编号,工号,姓名,课程名,教学理念,教学内容,教学模式,教学效果,视频质量,案例实录视频评分总计," +
                "教学目标及学情分析,教学创新理念及思路,教学创新方法及途径,教学创新效果及成果,申报书等材料评分总计,最终网评成绩,现评结果,大赛评分总成绩,专家评语";
        fieldInnoResult = "项目编号,项目类型,案例分组,工号,姓名,课程名,院系,团队成员,人数,评委1,评委2,评委3,评委4,评委5,评委6,评委7,评委8," +
                "评委9,评委10,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,排名,评分次数";
        if (year == 0) {
            return "noyear";
        }
        innoAllScoreList = downloadAllScoreService.getInnoJudgeScoreList(year,"创新大赛");
        ArrayList<ArrayList<DownloadInnoAllScore>> innoAllScoreListCopy =
                downloadAllScoreService.getInnoJudgeScoreList(year,"创新大赛");

        innoResultScoreList = downloadAllScoreService.getInnoResultScoreList(year,"创新大赛");
        ArrayList<DownloadInnoResultScore> innoResultScoreListCopy =
                downloadAllScoreService.getInnoResultScoreList(year,"创新大赛");


        String userType = "admin";
        if (!innoAllScoreList.isEmpty() && !innoResultScoreList.isEmpty()) {
            try{
                XLSXUtil.createInnoScoreXLSFileWithSheets(innoAllScoreListCopy,
                        innoResultScoreListCopy, innoPreFields, innoPPTFields, fieldInnoResult, fileName, userType);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return SUCCESS;
    }

    public String downloadPapScore(){
//        String filePath = "JudgeScore/";
        String fileName = "";
        String fieldPaper = "";
        String fieldResult = "";
        fileName = String.valueOf(year) + "-" + "教研论文" + "-" + "网评打分汇总表";
        fieldPaper = "评委工号,评委姓名,项目编号,工号,姓名,课程名,选题意义,理论基础,研究方法,论据实证," +
                "创新性,问题解决,学术能力,教研成果,书写规范,推广价值,网评成绩,评语";
        fieldResult = "项目编号,工号,姓名,课程名,评委1,评委2,评委3,评委4,评委5,评委6,评委7,评委8," +
                "评委9,评委10,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,排名,评分次数";
        if (year == 0) {
            return "noyear";
        }
        paperAllScoreList = downloadAllScoreService.getPaperJudgeScoreList(year,"教研论文");
        ArrayList<ArrayList<DownloadPaperAllScore>> paperAllScoreListCopy =
                downloadAllScoreService.getPaperJudgeScoreList(year,"教研论文");

        paperResultScoreList = downloadAllScoreService.getPaperResultScoreList(year,"教研论文");
        ArrayList<DownloadPaperResultScore> paperResultScoreListCopy =
                downloadAllScoreService.getPaperResultScoreList(year,"教研论文");

        String userType = "admin";
        if (!paperAllScoreList.isEmpty() && !paperResultScoreList.isEmpty()) {
            try{
                XLSXUtil.createPaperScoreXLSFileWithSheets(paperAllScoreListCopy,
                        paperResultScoreListCopy, fieldPaper, fieldResult, fileName, userType);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return SUCCESS;
    }

    public String downloadFinScore(){
//        String filePath = "JudgeScore/";
        String fileName = "";
        String fieldFinal = "";
        String fieldResult = "";
        fileName = String.valueOf(year) + "-" + "结题报告" + "-" + "网评打分汇总表";
        fieldFinal = "评委工号,评委姓名,项目编号,工号,姓名,课程名,项目参与,大赛奖项,论文奖项,结题报告," +
                "完成程度,教改效果,创新程度,示范作用,经费使用,预评结果,现评结果,网评成绩,评语";
        fieldResult = "项目编号,工号,姓名,课程名,评委1,评委2,评委3,评委4,评委5,评委6,评委7,评委8," +
                "评委9,评委10,评委11,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,排名,评分次数";
        if (year == 0) {
            return "noyear";
        }
        finalAllScoreList = downloadAllScoreService.getFinalJudgeScoreList(year,"结题报告","final");
        ArrayList<ArrayList<DownloadFinalAllScore>> finalAllScoreListCopy =
                downloadAllScoreService.getFinalJudgeScoreList(year,"结题报告","final");

        finalResultScoreList = downloadAllScoreService.getFinalResultScoreList(year,"结题报告","final");
        ArrayList<DownloadFinalResultScore> finalResultScoreListCopy =
                downloadAllScoreService.getFinalResultScoreList(year,"结题报告","final");

        String userType = "admin";
        if (!finalAllScoreList.isEmpty() && !finalResultScoreList.isEmpty()) {
            try{
                XLSXUtil.createFinalScoreXLSFileWithSheets(finalAllScoreListCopy,
                        finalResultScoreListCopy, fieldFinal, fieldResult, fileName,"final",userType);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return SUCCESS;
    }

    public String downloadFinPreScore(){
//        String filePath = "JudgeScore/";
        String fileName = "";
        String fieldFinal = "";
        String fieldResult = "";
        fileName = String.valueOf(year) + "-" + "结题报告" + "-" + "全部打分汇总表";
        fieldFinal = "评委工号,评委姓名,项目编号,工号,姓名,课程名,项目参与,大赛奖项,论文奖项,结题报告," +
                "完成程度,教改效果,创新程度,示范作用,经费使用,预评结果,现评结果,网评成绩,评语";
        fieldResult = "项目编号,工号,姓名,课程名,评委1,评委2,评委3,评委4,评委5,评委6,评委7,评委8," +
                "评委9,评委10,评委11,网评原始总分,网评原始平均分,网评加权总分,网评加权平均分,排名,评分次数";
        if (year == 0) {
            return "noyear";
        }
        finalPreAllScoreList = downloadAllScoreService.getFinalJudgeScoreList(year,"结题报告","finalPre");
        ArrayList<ArrayList<DownloadFinalAllScore>> finalPreAllScoreListCopy =
                downloadAllScoreService.getFinalJudgeScoreList(year,"结题报告","finalPre");

        finalPreResultScoreList = downloadAllScoreService.getFinalResultScoreList(year,"结题报告","finalPre");
        ArrayList<DownloadFinalResultScore> finalPreResultScoreListCopy =
                downloadAllScoreService.getFinalResultScoreList(year,"结题报告","finalPre");

        String userType = "admin";
        if (!finalPreAllScoreList.isEmpty() && !finalPreResultScoreList.isEmpty()) {
            try{
                XLSXUtil.createFinalScoreXLSFileWithSheets(finalPreAllScoreListCopy,
                        finalPreResultScoreListCopy, fieldFinal, fieldResult, fileName,"finalPre", userType);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return SUCCESS;
    }


    public String downloadAllScore() throws IOException {
//        String filePath = "JudgeScore/";
        String fileName = String.valueOf(year) + "-" + "评委打分汇总表";
        String fields = "评委工号,评委姓名,作品编号,工号,姓名,课程名,教学理念,教学内容,教学模式,教学效果,视频质量,视频成绩,教学目标,创新理念思路,创新方法途径,创新效果成果,材料成绩,网评成绩,评语";
        fileList = new ArrayList<>();
        if (year == 0) {
            return "noyear";
        }
        allScoreList = downloadAllScoreService.getResearchScoreListByYear(year);
        ArrayList<ArrayList<DownloadAllScore>> scoreListCopy = downloadAllScoreService.getResearchScoreListByYear(year);

        if (!allScoreList.isEmpty()) {
            XLSXUtil.createXLSFileWithSheets(scoreListCopy, fields, fileName);
//            Files files = new Files();
//            files.setFileName(fileName + ".csv");
//            files.setFilePath(link);
//            fileList.add(files);
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

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getHaveJudgeName() {
        return haveJudgeName;
    }

    public void setHaveJudgeName(String haveJudgeName) {
        this.haveJudgeName = haveJudgeName;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public ArrayList<ArrayList<DownloadPaperAllScore>> getPaperAllScoreList() {
        return paperAllScoreList;
    }

    public void setPaperAllScoreList(ArrayList<ArrayList<DownloadPaperAllScore>> paperAllScoreList) {
        this.paperAllScoreList = paperAllScoreList;
    }

    public void setAllScoreList(ArrayList<ArrayList<DownloadAllScore>> allScoreList) {
        this.allScoreList = allScoreList;
    }

    public void setDownloadAllScoreService(DownloadAllScoreService downloadAllScoreService) {
        this.downloadAllScoreService = downloadAllScoreService;
    }

    public void setFileList(ArrayList<Files> fileList) {
        this.fileList = fileList;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<Integer> getYears() {
        return years;
    }

    public ArrayList<ArrayList<DownloadAllScore>> getAllScoreList() {
        return allScoreList;
    }

    public DownloadAllScoreService getDownloadAllScoreService() {
        return downloadAllScoreService;
    }


    public ArrayList<ArrayList<DownloadFinalAllScore>> getFinalPreAllScoreList() {
        return finalPreAllScoreList;
    }

    public void setFinalPreAllScoreList(ArrayList<ArrayList<DownloadFinalAllScore>> finalPreAllScoreList) {
        this.finalPreAllScoreList = finalPreAllScoreList;
    }

    public ArrayList<DownloadFinalResultScore> getFinalPreResultScoreList() {
        return finalPreResultScoreList;
    }

    public void setFinalPreResultScoreList(ArrayList<DownloadFinalResultScore> finalPreResultScoreList) {
        this.finalPreResultScoreList = finalPreResultScoreList;
    }


    public ArrayList<Files> getFileList() {
        return fileList;
    }
}