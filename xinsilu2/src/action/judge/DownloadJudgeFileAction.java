package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.DownloadJudgeScore;
import domain.Files;
import service.DownloadScoreService;
import service.ResearchEnrollService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static util.UploadUtil.WEBResearchEnrollFilesDIR;

/**
 * @author widesui
 * @version 1.0
 * @date 2021/7/13 22:00
 */
public class DownloadJudgeFileAction extends ActionSupport {

    private int year;
    private String researchType;
    private String userId;
    private ArrayList<Integer> years;
    private ArrayList<DownloadJudgeScore> scoreList;
    private DownloadScoreService downloadScoreService;
    private ArrayList<Files> fileList;

    private ActionContext context;
    private Map<String, Object> session;

    private String file1;
    private String file2;
    private String file3;
    private String scoreStageControl;
    private ResearchEnrollService researchEnrollService;


    public DownloadJudgeFileAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        downloadScoreService= new DownloadScoreService();
        this.userId = (String)session.get("judge2");
        researchEnrollService = new ResearchEnrollService();
        scoreStageControl = researchEnrollService.getScoreStage( "创新大赛");
    }

    public String getJudgeYear() {
        //System.out.println("userId是：" + userId);
        //userId = "0006170278";
        Calendar now = Calendar.getInstance();
        int year_now = now.get(Calendar.YEAR);
        years = new ArrayList<>();
//        years = downloadScoreService.getYearsFromResearchScore(userId);
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

    public String getJudgeFile() throws IOException {
        file1 = "/upload/xinsilu/researchEnroll/" + year +"/"+ researchType + "/" + year + researchType+"资料.zip";
        file2 = WEBResearchEnrollFilesDIR + year + "/项目活动/" + researchType +"/项目活动资料.zip";
        //file2 = WEBResearchEnrollFilesDIR + "2022/项目活动/创新大赛/项目活动资料.zip";
        //file3 = WEBResearchEnrollFilesDIR + year + "/分数/" + year + researchType +"分数表.xlsx";


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

    public void setDownloadScoreService(DownloadScoreService downloadScoreService) {
        this.downloadScoreService = downloadScoreService;
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

    public String getFile3() {
        return file3;
    }

    public void setFile3(String file3) {
        this.file3 = file3;
    }

    public String getScoreStageControl() {
        return scoreStageControl;
    }

    public void setScoreStageControl(String scoreStageControl) {
        this.scoreStageControl = scoreStageControl;
    }

}