package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import domain.ResearchScore;
import service.ResearchEnrollService;
import service.ResearchScoreService;

import java.sql.Date;
import java.util.*;

/**
 * Created by jinggu on 19/5/2.
 */
public class JudgePaperScoreAction extends ActionSupport {

    private ArrayList<ResearchEnroll> researchEnrollList;
    private ResearchScore researchScore;
    private ResearchEnroll researchEnroll;

    private ResearchEnrollService researchEnrollService;
    private ResearchScoreService researchScoreService;

    private ArrayList<String> caseSorts;
    private String caseSort;

    private ActionContext context;
    private Map<String, Object> session;

    private String scoreStageControl;

    private String file1;
    private String file2;

    private String judgeScore1;
    private String judgeScore2;
    private String judgeScore3;
    private String judgeScore4;
    private String judgeScore5;
    private String judgeScore6;
    private String judgeScore7;
    private String judgeScore8;
    private String judgeScore9;
    private String judgeScore10;
    private String submitSave;
    private String score;
    private int id;
    private String isPass;
    private ArrayList<String> isPassList;
    private String applyId;
    private String applyPreComment;
    private String teamMembers;

    public JudgePaperScoreAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        researchEnrollService = new ResearchEnrollService();
        researchScoreService = new ResearchScoreService();
        caseSorts = new ArrayList<>();
        researchEnrollList = new ArrayList<ResearchEnroll>();
        researchScore = new ResearchScore();
        isPassList = new ArrayList<>();
        researchEnrollList();
    }

    private String researchEnrollList() {
//        int judgeId = 2;
        int judgeId = (int) session.get("judge"); //1
        this.researchScore.setJudgeId(judgeId);
        researchEnrollList = researchEnrollService.getResearchPaperListByType("教研论文");
        scoreStageControl = researchEnrollService.getScoreStage("教研论文");
        List<String> caseSortType = Arrays.asList("一等奖","二等奖","三等奖","优秀奖","无");
        caseSorts.addAll(caseSortType);
        judgeScore1 = "10";
        judgeScore2 = "10";
        judgeScore3 = "10";
        judgeScore4 = "10";
        judgeScore5 = "10";
        judgeScore6 = "10";
        judgeScore7 = "10";
        judgeScore8 = "10";
        judgeScore9 = "10";
        judgeScore10 = "10";
        submitSave = "0";
        this.researchScore.setComment("无");

        List<String> isPassTemp = Arrays.asList("尚未初审","通过","不通过","修改提交");
        isPassList.addAll(isPassTemp);
        applyId = "";
        applyPreComment = "无";

        return SUCCESS;

    }

    public String paperJSP() {
        if(researchEnrollList.size()==0) return NONE;
//        int judgeId = 2;
        int judgeId = (int) session.get("judge");  //1
        researchEnroll = researchEnrollList.get(id - 1);
        int researchEnrollId = researchEnroll.getId();
        String teamMembers = researchEnroll.getTeamMember();
        String resultTeamMember = "";
        if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,院系;,,院系;,,院系;,,院系")
            || teamMembers.equals(",,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者")){
            researchEnroll.setTeamMember("无");
        }else{
            String[] members = teamMembers.split(";");
            String teamNames = "";
            for(int i = 0; i < members.length; i++){
                if(!members[i].equals(",,") && !members[i].equals(",,院系") && !members[i].equals(",,院系,非通讯作者")){
                    if(members[i].split(",").length >= 4 && members[i].split(",")[3].equals("通讯作者")){
                        teamNames += members[i].split(",")[1] + "*，";
                    }else{
                        teamNames += members[i].split(",")[1] + "，";
                    }
                }
            }
            int teamNum = teamNames.length();
            resultTeamMember = teamNames.substring(0, teamNum - 1);
            this.teamMembers = resultTeamMember;
            researchEnroll.setTeamMember(teamMembers);
        }
        if(researchEnroll.getIsPass() != null && !researchEnroll.getIsPass().equals("")){
            String isPassTemp = researchEnroll.getIsPass();
            isPassList.remove(isPassTemp);
            isPassList.add(0, isPassTemp);
        }
        if(researchEnroll.getApplyId() != null && !researchEnroll.getApplyId().equals("")){
            applyId = researchEnroll.getApplyId();
        }
        if(researchEnroll.getApplyPreComment() != null
                && !researchEnroll.getApplyPreComment().equals("")){
            applyPreComment = researchEnroll.getApplyPreComment();
        }
        if(researchEnroll.getProjectNo() == null || researchEnroll.getProjectNo() == ""){
            researchEnroll.setProjectNo("无");
        }
        if (researchEnroll.getCaseSort() == null || researchEnroll.getCaseSort() == "")
            researchEnroll.setCaseSort("无");
        String caseSortTemp = researchEnroll.getCaseSort();
        if(caseSortTemp != null){
            caseSorts.remove(caseSortTemp);
            caseSorts.add(0, caseSortTemp);
        }

        ArrayList<ResearchScore> researchScoresList = researchScoreService.getResearchScoreByJudgeIdAndResearchEnrollId(judgeId,researchEnrollId);
        if(researchScoresList.size() != 0){
            if(researchEnroll.getIsPass() != null && (!researchEnroll.getIsPass().equals("不通过"))){
                researchScore = researchScoresList.get(0);
                String comment = researchScore.getComment();
                String [] scores = comment.substring(comment.indexOf("[") + 1,comment.indexOf("]")).split(",");
                judgeScore1 = scores[0];
                judgeScore2 = scores[1];
                judgeScore3 = scores[2];
                judgeScore4 = scores[3];
                judgeScore5 = scores[4];
                judgeScore6 = scores[5];
                judgeScore7 = scores[6];
                judgeScore8 = scores[7];
                judgeScore9 = scores [8];
                judgeScore10 = scores[9];
                submitSave = researchScore.getSubmitSave();
                score = String.valueOf(researchScore.getScore());
                researchScore.setComment(comment.substring(comment.indexOf("]")+1));
            } else {
                researchScore = researchScoresList.get(0);
                String comment = researchScore.getComment();
                String [] scores = comment.substring(comment.indexOf("[") + 1,comment.indexOf("]")).split(",");
                judgeScore1 = "10";
                judgeScore2 = "10";
                judgeScore3 = "10";
                judgeScore4 = "10";
                judgeScore5 = "10";
                judgeScore6 = "10";
                judgeScore7 = "10";
                judgeScore8 = "10";
                judgeScore9 = "10";
                judgeScore10 = "10";
                submitSave = researchScore.getSubmitSave();
                score = "100";
                researchScore.setComment("无");
            }
        }
        String[] files = researchEnroll.getFilePath().split(";");
        file1 = files[1];
        file2 = files[3];
        return SUCCESS;
    }

    public String submitPapScore() {
        String comment = researchScore.getComment();

        String scores = "[" + judgeScore1 + "," + judgeScore2 + "," + judgeScore3 + "," + judgeScore4 + "," + judgeScore5 + "," + judgeScore6 + "," + judgeScore7 + "," + judgeScore8 + "," + judgeScore9 + "," + judgeScore10 + "]";
        researchScore.setSubmitSave(submitSave);
        researchScore.setComment(scores + comment);


        researchScore.setVideoScore(0);
        if(researchScore.getScore() == 0){
            researchScore.setScore(100);
        }
//         int judgeId = 2;
        int judgeId = (int) session.get("judge");//1901210672;
        int researchEnrollId = researchEnrollList.get(id - 1).getId();


        researchScore.setId(researchScoreService.isScored(judgeId, researchEnrollId));
        researchScore.setJudgeId(judgeId);
        researchScore.setResearchEnrollId(researchEnrollId);

        researchEnroll.setCaseSort(caseSort);
        if(judgeId == 2){

            researchEnroll.setIsPass(isPass);
            researchEnroll.setApplyId(applyId);
            researchEnroll.setApplyPreComment(applyPreComment);
            if(isPass != null && isPass.equals("修改提交")){
                researchEnroll.setSubmitSave("0");
            }

            researchEnroll.setResearchType("教研论文");

            researchEnrollService.updateResearchEnrollPre(researchEnroll);
            if(scoreStageControl.equals("1") && researchEnroll.getProjectNo() != null && !researchEnroll.getProjectNo().equals("")){
                researchScoreService.updateResearchPaperProjectNo(researchEnroll);
            }else if(scoreStageControl.equals("3")){
                researchScoreService.updateResearchPaperJudgeRes(researchEnroll);
            }
        }

        if (researchScore.getId() != 0) {
            researchScoreService.updateResearchScore(researchScore);
        } else {
            researchScoreService.insertResearchScore(researchScore);
        }
        id = (id % researchEnrollList.size()) + 1;
        return SUCCESS;
    }

    public String getFile1() {
        return file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public String getScoreStageControl() {
        return scoreStageControl;
    }

    public void setScoreStageControl(String scoreStageControl) {
        this.scoreStageControl = scoreStageControl;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ResearchEnroll> getResearchEnrollList() {
        return researchEnrollList;
    }

    public void setResearchEnrollList(ArrayList<ResearchEnroll> researchEnrollList) {
        this.researchEnrollList = researchEnrollList;
    }

    public ResearchScore getResearchScore() {
        return researchScore;
    }

    public void setResearchScore(ResearchScore researchScore) {
        this.researchScore = researchScore;
    }

    public ResearchEnroll getResearchEnroll() {
        return researchEnroll;
    }

    public void setResearchEnroll(ResearchEnroll researchEnroll) {
        this.researchEnroll = researchEnroll;
    }

    public String getCaseSort() {
        return caseSort;
    }

    public void setCaseSort(String caseSort) {
        this.caseSort = caseSort;
    }

    public ArrayList<String> getCaseSorts() {
        return caseSorts;
    }

    public void setCaseSorts(ArrayList<String> caseSorts) {
        this.caseSorts = caseSorts;
    }

    public String getJudgeScore1(){ return judgeScore1;}

    public void setJudgeScore1(String judgeScore1) {
        this.judgeScore1 = judgeScore1;
    }

    public String getJudgeScore2() {
        return judgeScore2;
    }

    public void setJudgeScore2(String judgeScore2) {
        this.judgeScore2 = judgeScore2;
    }

    public String getJudgeScore3() {
        return judgeScore3;
    }

    public void setJudgeScore3(String judgeScore3) {
        this.judgeScore3 = judgeScore3;
    }

    public String getJudgeScore4() {
        return judgeScore4;
    }

    public void setJudgeScore4(String judgeScore4) {
        this.judgeScore4 = judgeScore4;
    }

    public String getJudgeScore5() {
        return judgeScore5;
    }

    public void setJudgeScore5(String judgeScore5) {
        this.judgeScore5 = judgeScore5;
    }

    public String getJudgeScore6() {
        return judgeScore6;
    }

    public void setJudgeScore6(String judgeScore6) {
        this.judgeScore6 = judgeScore6;
    }

    public String getJudgeScore7() {
        return judgeScore7;
    }

    public void setJudgeScore7(String judgeScore7) {
        this.judgeScore7 = judgeScore7;
    }

    public String getJudgeScore8() {
        return judgeScore8;
    }

    public void setJudgeScore8(String judgeScore8) {
        this.judgeScore8 = judgeScore8;
    }

    public String getJudgeScore9() {
        return judgeScore9;
    }

    public void setJudgeScore9(String judgeScore9) {
        this.judgeScore9 = judgeScore9;
    }

    public String getJudgeScore10() {
        return judgeScore10;
    }

    public void setJudgeScore10(String judgeScore10) {
        this.judgeScore10 = judgeScore10;
    }


    public String getSubmitSave() {
        return submitSave;
    }

    public void setSubmitSave(String submitSave) {
        this.submitSave = submitSave;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public ArrayList<String> getIsPassList() {
        return isPassList;
    }

    public void setIsPassList(ArrayList<String> isPassList) {
        this.isPassList = isPassList;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyPreComment() {
        return applyPreComment;
    }

    public void setApplyPreComment(String applyPreComment) {
        this.applyPreComment = applyPreComment;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }
}
