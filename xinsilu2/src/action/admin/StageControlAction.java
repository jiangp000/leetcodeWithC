package action.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.*;
import service.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author widesui
 * @version 1.0
 * @date 2021/7/13 22:00
 */
public class StageControlAction extends ActionSupport {

    private int year;
    private ArrayList<Integer> years;
    private String researchType;
    private String stageType;
    private String scoreStageControl;
    private String submitStageControl;
    private DownloadAllScoreService downloadAllScoreService;
    private StageControlService stageControlService;
    private ResearchScoreService researchScoreService;
    private ResearchEnrollService researchEnrollService;
    private JudgeService judgeService;
    private String judgeID;
    private String applicant;
    private ArrayList<Files> fileList;
    private StageControl stageControl;

    private String judgeName;

    private ActionContext context;
    private Map<String, Object> session;
    private ArrayList<String> judgeList;

    public StageControlAction() {

        downloadAllScoreService= new DownloadAllScoreService();
        stageControlService = new StageControlService();
        researchScoreService = new ResearchScoreService();
        judgeService = new JudgeService();
        stageControl = new StageControl();
        researchEnrollService = new ResearchEnrollService();
        judgeList = new ArrayList<>();
    }

    public String revokeJudgeSubmitJSP() {
        years = downloadAllScoreService.getYearsFromResearchScore();
        if(years.size()!=0){
            if(year==0){
                year = years.get(years.size()-1);
            }
        }
        ArrayList<Judge> judges = judgeService.getJudgeAll();
        for(Judge judge:judges){
            if(judge.getDepartment()==null){
                String judgeStr = judge.getUsername()+" "+judge.getUserId();
                judgeList.add(judgeStr);
            }else{
                String judgeStr = judge.getUsername()+" "+judge.getUserId()+" "+judge.getDepartment();
                judgeList.add(judgeStr);
            }
        }
        return SUCCESS;
    }

    public String revokeJudgeSubmit() {
        String[] judge = judgeID.split(" ");
        if(judge.length<2){
            return SUCCESS;
        }
        String judgeUsername = judge[0];
        researchScoreService.updateScoreSaveStage(String.valueOf(year), researchType, judgeUsername);
        return SUCCESS;
    }

    public String revokeTeacherSubmitJSP(){
        years = downloadAllScoreService.getYearsFromResearchScore();
        if(years.size()!=0){
            if(year==0){
                year = years.get(years.size()-1);
            }
        }
        // 找到所有提交过申请的用户
        ArrayList<Teacher> teachers = researchEnrollService.getResearchApplicantAll();
        for(Teacher t:teachers){
            String teacherStr = t.getUsername()+" "+t.getUserId()+" "+t.getDepartment();
            judgeList.add(teacherStr);
        }
        return SUCCESS;
    }

    public String revokeTeacherSubmit(){
        String[] teacher = applicant.split(" ");
        if(teacher.length!=3){
            return SUCCESS;
        }
        String uid = teacher[1];
        researchEnrollService.updateResearchEnrollSaveStatus(uid,researchType, String.valueOf(year));
        return SUCCESS;
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

    public String updateSubmitStage(){
        stageControl.setYear(String.valueOf(year));
        stageControl.setResearchType(researchType);
        submitStageControl = "0";
        if(stageType.equals("关闭")){
            submitStageControl = "0";
        }else if(stageType.equals("资料提交")){
            submitStageControl = "1";
        }else if(stageType.equals("现场汇报")){
            submitStageControl = "2";
        }
        stageControl.setSubmitStageControl(submitStageControl);
        stageControlService.updateSubmitStageByStage(stageControl);
        return SUCCESS;
    }

    public String updateScoreStage(){

        stageControl.setYear(String.valueOf(year));
        stageControl.setResearchType(researchType);
        scoreStageControl = "0";
        if(stageType.equals("关闭")){
            scoreStageControl = "0";
        }else if(stageType.equals("初审")){
            scoreStageControl = "1";
        }else if(stageType.equals("网评")){
            scoreStageControl = "2";
        }else if(stageType.equals("现评")){
            scoreStageControl = "3";
        }
        stageControl.setScoreStageControl(scoreStageControl);
        stageControlService.updateScoreStageByStage(stageControl);
//        if(!judgeName.equals("无")){
//            researchScoreService.updateScoreSaveStage(String.valueOf(year), researchType, judgeName);
//        }
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


    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }

    public String getScoreStageControl() {
        return scoreStageControl;
    }

    public void setScoreStageControl(String scoreStageControl) {
        this.scoreStageControl = scoreStageControl;
    }

    public String getSubmitStageControl() {
        return submitStageControl;
    }

    public void setSubmitStageControl(String submitStageControl) {
        this.submitStageControl = submitStageControl;
    }

    public void setDownloadAllScoreService(DownloadAllScoreService downloadAllScoreService) {
        this.downloadAllScoreService = downloadAllScoreService;
    }

    public StageControl getStageControl() {
        return stageControl;
    }

    public void setStageControl(StageControl stageControl) {
        this.stageControl = stageControl;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }


    public DownloadAllScoreService getDownloadAllScoreService() {
        return downloadAllScoreService;
    }

    public StageControlService getStageControlService() {
        return stageControlService;
    }

    public void setStageControlService(StageControlService stageControlService) {
        this.stageControlService = stageControlService;
    }

    public ResearchScoreService getResearchScoreService() {
        return researchScoreService;
    }

    public void setResearchScoreService(ResearchScoreService researchScoreService) {
        this.researchScoreService = researchScoreService;
    }

    public ResearchEnrollService getResearchEnrollService() {
        return researchEnrollService;
    }

    public void setResearchEnrollService(ResearchEnrollService researchEnrollService) {
        this.researchEnrollService = researchEnrollService;
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

    public ArrayList<Files> getFileList() {
        return fileList;
    }
    public ArrayList<String> getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(ArrayList<String> judgeList) {
        this.judgeList = judgeList;
    }

    public JudgeService getJudgeService() {
        return judgeService;
    }

    public void setJudgeService(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    public void setJudgeID(String judgeID){this.judgeID=judgeID;}

    public String getJudgeID(){return this.judgeID;}
    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
}