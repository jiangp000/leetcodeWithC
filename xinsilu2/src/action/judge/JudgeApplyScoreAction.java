package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import domain.ResearchScore;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ResearchEnrollService;
import service.ResearchScoreService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

/**
 * Created by jinggu on 19/5/2.
 */
public class JudgeApplyScoreAction extends ActionSupport {

    private ArrayList<ResearchEnroll> researchEnrollList;
    private ResearchScore researchScore;
    private ResearchEnroll researchEnroll;

    private ResearchEnrollService researchEnrollService;
    private ResearchScoreService researchScoreService;

    private ActionContext context;
    private Map<String, Object> session;

    private String scoreStageControl;

    private String file1;
    private String file2;
    private String file3;
    private String file4;
    private String file5;
    private String file6;
    private String fileEthic1;
    private String fileEthic2;

    private String judgeScore1;
    private String judgeScore2;
    private String judgeScore3;
    private String judgeScore4;
    private String judgeScore5;
    private String judgeScore6;
    private String judgeScore7;
    private String judgeScore8;
    private String submitSave;
    private String preScore;
    private String judgeScore9;
    private String score;
    private String isPass;
    private String applyId;
    private String applyPreComment;
    private int id;
    private String jsonStr; // 用于前端发请求时序列化的jsonStr的传输

    public JudgeApplyScoreAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        researchEnrollService = new ResearchEnrollService();
        researchScoreService = new ResearchScoreService();

        researchEnrollList = new ArrayList<ResearchEnroll>();
        researchScore = new ResearchScore();
        researchEnrollList();
    }

    public String getUploadScores(){
        return SUCCESS;
    }

    // 将上传的评分表分数批量写入数据库
    public String submitAllScores() {
        Object jid = session.get("judge");
        int judgeId = (int) jid;
        JSONObject obj = JSONObject.fromObject(jsonStr);
        Set set = obj.keySet();
        scoreStageControl = researchEnrollService.getScoreStage( "项目申报");
        if(scoreStageControl.equals("0")){  // 不允许提交
            return SUCCESS;
        }
        // 项目申报阶段各项分数
        String fields = "10,10,10,10,10,10,10,10,20";
        for(Object s:set){
            Object val = obj.get(s);
            JSONObject innerObj = JSONObject.fromObject(val);
            JSONArray scores = (JSONArray) innerObj.get("scores");
            String comment = (String) innerObj.get("comment");
            String projectID = (String) innerObj.get("id");
            int totalScore = 0;
            String scoreStr = "[";
            int index = 0;
            boolean scoreFlag = false;
            for(Object ss:scores){
                int score = Integer.parseInt((String) ss);
                int maxScore = Integer.parseInt(fields.split(",")[index]);
                index++;
                // 超过限定值的分数不提交
                if(score>maxScore||score<0){
                    scoreFlag=true;
                    break;
                }
                totalScore+=score;
                scoreStr+=ss+",";
            }
            if(scoreFlag) continue;
            scoreStr = scoreStr.substring(0,scoreStr.length()-1)+"]"+comment;
            // 如果评分为满分且评价是尚未评分，认为是没评分
            if(totalScore==100&&comment.equals("尚未评分")){
                continue;
            }
            ArrayList<ResearchScore> res = researchScoreService.getResearchScoreByJudgeIdAndResearchEnrollId(judgeId, Integer.parseInt(projectID));
            if(res.size()>0){
                ResearchScore target = res.get(0);
                if(target.getSubmitSave().equals("1")) continue;    // 已经保存的评分也不能批量修改
                target.setScore(totalScore);
                target.setComment(scoreStr);
                researchScoreService.updateResearchScore(target);
            }else{
                // 未找到这一条记录，新建一条评分
                ResearchScore researchScore1 = new ResearchScore();
                researchScore1.setComment(scoreStr);
                researchScore1.setVideoScore(0);
                researchScore1.setDocumentScore(0);
                researchScore1.setResearchEnrollId(Integer.parseInt(projectID));
                researchScore1.setJudgeId(judgeId);
                researchScore1.setScore(totalScore);
                researchScore1.setSubmitSave("0");
                researchScoreService.insertResearchScore(researchScore1);
            }
        }
        return SUCCESS;
    }

    private String researchEnrollList() {
        Object jid = session.get("judge");
        int judgeId = (int) jid;
        this.researchScore.setJudgeId(judgeId);
        researchEnrollList = researchEnrollService.getResearchEnrollListByApply("项目申报");
        scoreStageControl = researchEnrollService.getScoreStage("项目申报");

        judgeScore1 = "10";
        judgeScore2 = "10";
        judgeScore3 = "10";
        judgeScore4 = "10";
        judgeScore5 = "10";
        judgeScore6 = "10";
        judgeScore7 = "10";
        judgeScore8 = "10";
        preScore = "0";
        judgeScore9 = "20";
        submitSave = "0";
        isPass = "通过";
        applyId = "";
        applyPreComment = "无";
        this.researchScore.setComment("无");

        return SUCCESS;

    }

    public String applyJSP() {
        if(researchEnrollList.size()==0){
            return NONE;
        }
        Object jid = session.get("judge");
        int judgeId = (int) jid;
        researchEnroll = researchEnrollList.get(id - 1);
        int researchEnrollId = researchEnroll.getId();

        String teamMembers = researchEnroll.getTeamMember();
        String resultTeamMember = "";
        if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,无;,,无;,,无;,,无") ||
                teamMembers.equals(",,院系;,,院系;,,院系;,,院系")){
            researchEnroll.setTeamMember("无");
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
            researchEnroll.setTeamMember(resultTeamMember);
        }

        if(researchEnroll.getCaseSort() != null&& !researchEnroll.getCaseSort().equals("")){
            researchEnroll.setCaseSort(researchEnroll.getCaseSort().substring(0,researchEnroll.getCaseSort().length()-1));
        }

        if(researchEnroll.getIsPass() != null && !researchEnroll.getIsPass().equals("")){
            isPass = researchEnroll.getIsPass();
        }
        if(researchEnroll.getApplyId() != null && !researchEnroll.getApplyId().equals("")){
            applyId = researchEnroll.getApplyId();
        }
        if(researchEnroll.getApplyPreComment() != null
                && !researchEnroll.getApplyPreComment().equals("")){
            applyPreComment = researchEnroll.getApplyPreComment();
        }

        ArrayList<ResearchScore> researchScoresList = researchScoreService.getResearchScoreByJudgeIdAndResearchEnrollId(judgeId,researchEnrollId);

        if(researchScoresList.size() != 0){
            if(researchEnroll.getIsPass()!= null && researchEnroll.getIsPass().equals("通过")){
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

                preScore = String.valueOf(Integer.parseInt(judgeScore1)
                        + Integer.parseInt(judgeScore2) + Integer.parseInt(judgeScore3)
                        + Integer.parseInt(judgeScore4) + Integer.parseInt(judgeScore5)
                        + Integer.parseInt(judgeScore6) + Integer.parseInt(judgeScore7)
                        + Integer.parseInt(judgeScore8));
            /*preScore = String.valueOf(Integer.parseInt(judgeScore4) + Integer.parseInt(judgeScore5)
                    + Integer.parseInt(judgeScore6) + Integer.parseInt(judgeScore7)
                    + Integer.parseInt(judgeScore8) + Integer.parseInt(judgeScore9));*/
                judgeScore9 = scores[9];
                submitSave = researchScore.getSubmitSave();

                score = String.valueOf((int)(researchScore.getScore()));


                researchScore.setComment(comment.substring(comment.indexOf("]")+1));
            }else{
                researchScore = researchScoresList.get(0);
                String comment = researchScore.getComment();
                String [] scores = comment.substring(comment.indexOf("[") + 1,comment.indexOf("]")).split(",");
                judgeScore1 = "0";
                judgeScore2 = "0";
                judgeScore3 = "0";
                judgeScore4 = "0";
                judgeScore5 = "0";
                judgeScore6 = "0";
                judgeScore7 = "0";
                judgeScore8 = "0";
                preScore = "0";
                judgeScore9 = "0";
                submitSave = researchScore.getSubmitSave();
                score = "0";
                researchScore.setComment("无");
            }

        }
        /*if(researchEnroll.getUsername().equals("王健平")){
            researchEnroll.setProjectNo("2020YX009");
        }*/
        String[] files = researchEnroll.getFilePath().split(";");
        String[] ethicFiles = researchEnroll.getProjectEthicPath().split(";");
        if(ethicFiles.length >= 2){
            fileEthic1 = ethicFiles[0];
            fileEthic2 = ethicFiles[1];
        }
        int filesLength = files.length;
        String[] preFiles = files[files.length-1].split("}");
        if(researchEnroll.getFilePath().contains("}")){
            filesLength = filesLength - 1;
        }
        if(filesLength > 1){
            file1 = files[1];
            file2 = files[1];
            file3 = files[1];
            file4 = files[1];
            file5 = files[1];
        }
        if(preFiles.length > 2){
            file6 = preFiles[2];
        }

        if(filesLength == 3){
            file2 = files[2];
        }else if(filesLength == 4){
            file2 = files[2];
            file3 = files[3];
        }else if(filesLength == 5){
            file2 = files[2];
            file3 = files[3];
            file4 = files[4];
        }else if(filesLength == 6){
            file2 = files[2];
            file3 = files[3];
            file4 = files[4];
            file5 = files[5];
        }

        return SUCCESS;
    }

    public String submitAppScore() {
        Object jid = session.get("judge");
        int judgeId = (int) jid;
        if(judgeId == 2){
            researchEnroll = researchEnrollList.get(id - 1);
            researchEnroll.setIsPass(isPass);
            researchEnroll.setApplyId(applyId);
            researchEnroll.setApplyPreComment(applyPreComment);
            researchEnrollService.updateApplyResearchEnroll(researchEnroll);

            String comment = researchScore.getComment();
            String scores = "[" + judgeScore1 + "," + judgeScore2 + "," + judgeScore3 + "," + judgeScore4 + ","
                    + judgeScore5 + "," + judgeScore6 + "," + judgeScore7 + "," + judgeScore8 + "," + preScore + ","
                    + judgeScore9 + "]";

            researchScore.setComment(scores + comment);
            researchScore.setSubmitSave(submitSave);
            researchScore.setDocumentScore(0);
            researchScore.setVideoScore(0);
            researchScore.setScore(Double.parseDouble(score));


            int researchEnrollId = researchEnrollList.get(id - 1).getId();


            researchScore.setId(researchScoreService.isScored(judgeId, researchEnrollId));
            researchScore.setJudgeId(judgeId);
            researchScore.setResearchEnrollId(researchEnrollId);

            if (researchScore.getId() != 0) {
                researchScoreService.updateResearchScore(researchScore);
            } else {
                researchScoreService.insertResearchScore(researchScore);
            }
            id = (id % researchEnrollList.size()) + 1;
            return SUCCESS;
        }else{
            String comment = researchScore.getComment();
            String scores = "[" + judgeScore1 + "," + judgeScore2 + "," + judgeScore3 + "," + judgeScore4 + ","
                    + judgeScore5 + "," + judgeScore6 + "," + judgeScore7 + "," + judgeScore8 + "," + preScore + ","
                    + judgeScore9 + "]";
            researchScore.setComment(scores + comment);
            researchScore.setSubmitSave(submitSave);
            researchScore.setDocumentScore(0);
            researchScore.setVideoScore(0);
            researchScore.setScore(Double.parseDouble(score));

            int researchEnrollId = researchEnrollList.get(id - 1).getId();


            researchScore.setId(researchScoreService.isScored(judgeId, researchEnrollId));
            researchScore.setJudgeId(judgeId);
            researchScore.setResearchEnrollId(researchEnrollId);

            if (researchScore.getId() != 0) {
                researchScoreService.updateResearchScore(researchScore);
            } else {
                researchScoreService.insertResearchScore(researchScore);
            }
            id = (id % researchEnrollList.size()) + 1;
            return SUCCESS;
        }

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

    public String getFile3() {
        return file3;
    }

    public void setFile3(String file3) {
        this.file3 = file3;
    }

    public String getFile4() {
        return file4;
    }

    public void setFile4(String file4) {
        this.file4 = file4;
    }

    public String getFile5() {
        return file5;
    }

    public void setFile5(String file5) {
        this.file5 = file5;
    }

    public String getFile6() {
        return file6;
    }

    public void setFile6(String file6) {
        this.file6 = file6;
    }

    public String getFileEthic1() {
        return fileEthic1;
    }

    public void setFileEthic1(String fileEthic1) {
        this.fileEthic1 = fileEthic1;
    }

    public String getFileEthic2() {
        return fileEthic2;
    }

    public void setFileEthic2(String fileEthic2) {
        this.fileEthic2 = fileEthic2;
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

    public String getScoreStageControl() {
        return scoreStageControl;
    }

    public void setScoreStageControl(String scoreStageControl) {
        this.scoreStageControl = scoreStageControl;
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

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
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

    public String getPreScore() {
        return preScore;
    }

    public void setPreScore(String preScore) {
        this.preScore = preScore;
    }

    public String getSubmitSave() {
        return submitSave;
    }

    public void setSubmitSave(String submitSave) {
        this.submitSave = submitSave;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

}
