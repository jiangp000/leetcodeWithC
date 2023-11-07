package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import domain.ResearchScore;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ResearchEnrollService;
import service.ResearchScoreService;

import java.util.*;

/**
 * Created by jinggu on 19/5/2.
 */
public class JudgeFinalScoreAction extends ActionSupport {

    private ArrayList<ResearchEnroll> researchEnrollList;
    private ResearchScore researchScore;
    private ResearchEnroll researchEnroll;

    private ArrayList<String> caseSorts;
    private String caseSort;

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

    private String judgeScore1;
    private String judgeScore2;
    private String judgeScore3;
    private String judgeScore4;
    private String judgeScore5;
    private String judgeScore6;
    private String judgeScore7;
    private String judgeScore8;
    private String judgeScore9;
    private String submitSave;
    private String preScore;
    private String judgeScore10;
    private String score;
    private int id;
    private String jsonStr; // 用于前端发请求时序列化的jsonStr的传输
    private String isPass;
    private ArrayList<String> isPassList;
    private String applyId;
    private String applyPreComment;

    public JudgeFinalScoreAction() {
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

    public String getUploadScores(){
        return SUCCESS;
    }

    // 将上传的评分表分数批量写入数据库
    public String submitAllScores() {
        Object jid = session.get("judge");
//        int judgeId = 1;
        int judgeId = (int) jid;
        JSONObject obj = JSONObject.fromObject(jsonStr);
        Set set = obj.keySet();
        // TODO 需要根据阶段限制可以提交的分数项
        scoreStageControl = researchEnrollService.getScoreStage( "结题报告");
        if(scoreStageControl.equals("0")){  // 不允许提交
            return SUCCESS;
        }
        // 结题报告阶段各项分数
        String fields = "10,10,10,10,10,10,10,5,5,20";
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
//        int judgeId = 1;
        int judgeId = (int) jid;
        this.researchScore.setJudgeId(judgeId);
        researchEnrollList = researchEnrollService.getResearchFinalListByType("结题报告");
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        scoreStageControl = researchEnrollService.getScoreStage( "结题报告");

        List<String> caseSortType = Arrays.asList("无","优秀","通过","不通过","延期");
        caseSorts.addAll(caseSortType);

        judgeScore1 = "10";
        judgeScore2 = "10";
        judgeScore3 = "10";
        judgeScore4 = "10";
        judgeScore5 = "10";
        judgeScore6 = "10";
        judgeScore7 = "10";
        judgeScore8 = "5";
        judgeScore9 = "5";
        preScore = "0";
        judgeScore10 = "20";
        submitSave = "0";

        List<String> isPassTemp = Arrays.asList("尚未初审","通过","不通过","修改提交");
        isPassList.addAll(isPassTemp);
        applyId = "";
        applyPreComment = "无";

        this.researchScore.setComment("无");

        return SUCCESS;

    }

    public String finalJSP() {
        if(researchEnrollList.size()==0) return NONE;
        Object jid = session.get("judge");
//        int judgeId = 1;
        int judgeId = (int) jid;
        researchEnroll = researchEnrollList.get(id - 1);
        int researchEnrollId = researchEnroll.getId();

        String teamMembers = researchEnroll.getTeamMember();

        String resultTeamMember = "";
        if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,院系;,,院系;,,院系;,,院系")){
            researchEnroll.setTeamMember(researchEnroll.getUsername());
        }else{
            String[] members = teamMembers.split(";");
            String teamNames = researchEnroll.getUsername()+"，";
            for(int i = 0; i < members.length; i++){
                if(!members[i].equals(",,") && !members[i].equals(",,院系")){
                    teamNames += members[i].split(",")[1] + "，";
                }
            }
            int teamNum = teamNames.length();
            resultTeamMember = teamNames.substring(0, teamNum - 1);
            researchEnroll.setTeamMember(resultTeamMember);
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
        String caseSortTemp = researchEnroll.getCaseSort();
        if(caseSortTemp != null && !caseSortTemp.equals("") ){
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
                judgeScore9 = scores[8];
                preScore = String.valueOf(Integer.parseInt(judgeScore1)
                        + Integer.parseInt(judgeScore2) + Integer.parseInt(judgeScore3)
                        + Integer.parseInt(judgeScore4) + Integer.parseInt(judgeScore5)
                        + Integer.parseInt(judgeScore6) + Integer.parseInt(judgeScore7)
                        + Integer.parseInt(judgeScore8) + Integer.parseInt(judgeScore9));
            /*preScore = String.valueOf(Integer.parseInt(judgeScore4) + Integer.parseInt(judgeScore5)
                    + Integer.parseInt(judgeScore6) + Integer.parseInt(judgeScore7)
                    + Integer.parseInt(judgeScore8) + Integer.parseInt(judgeScore9));*/
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
                judgeScore8 = "5";
                judgeScore9 = "5";
                preScore = "80";
                judgeScore10 = "20";
                submitSave = researchScore.getSubmitSave();

                score = "100";

                researchScore.setComment("无");
            }
        }

        String[] files = researchEnroll.getFilePath().split(";");
        int filesLength = files.length;
        String[] preFiles = files[files.length-1].split("}");
        if(researchEnroll.getFilePath().contains("}")){
            filesLength = filesLength - 1;
        }
        if(researchEnroll.getFilePath().contains("申请报告")
                && researchEnroll.getFilePath().contains("中期报告")){
            file1 = files[0];
            file2 = files[1];
            file3 = files[1];
            file4 = files[1];
            file5 = files[1];
            file6 = files[1];
            if(files.length >= 3){
                file3 = files[2];
            }
            if(preFiles.length>2){
                file6 = preFiles[2];
            }
        }else{
            file1 = files[1];
            file2 = files[1];
            file3 = files[1];
            file4 = files[1];
            file5 = files[1];
            file6 = files[1];
            if(preFiles.length>2){
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
        }


        return SUCCESS;
    }

    public String submitFinScore() {
        Object jid = session.get("judge");
//        int judgeId = 1;
        int judgeId = (int) jid;
        String comment = researchScore.getComment();
        if(judgeScore1 == null){
            judgeScore1 = "10";
        }
        if(judgeScore2 == null){
            judgeScore2 = "10";
        }
        if(judgeScore3 == null){
            judgeScore3 = "10";
        }
        String scores = "[" + judgeScore1 + "," + judgeScore2 + "," + judgeScore3 + "," + judgeScore4 + "," + judgeScore5
                + "," + judgeScore6 + "," + judgeScore7 + "," + judgeScore8 + "," + judgeScore9 + "," + judgeScore10 + "]";
        researchScore.setComment(scores + comment);
        researchScore.setSubmitSave(submitSave);
        researchScore.setDocumentScore(0);
        researchScore.setVideoScore(0);
        if(researchScore.getScore() == 0){
            researchScore.setScore(100);
        }

        int researchEnrollId = researchEnrollList.get(id - 1).getId();

        researchScore.setId(researchScoreService.isScored(judgeId, researchEnrollId));
        researchScore.setJudgeId(judgeId);
        researchScore.setResearchEnrollId(researchEnrollId);

        researchEnroll.setCaseSort(caseSort);
        if(judgeId == 2){
            researchEnroll.setResearchType("结题报告");
            researchEnroll.setIsPass(isPass);
            researchEnroll.setApplyId(applyId);
            researchEnroll.setApplyPreComment(applyPreComment);
            if(isPass != null && isPass.equals("修改提交")){
                researchEnroll.setSubmitSave("0");
            }
            researchEnrollService.updateResearchEnrollPre(researchEnroll);

            if(scoreStageControl.equals("1") && researchEnroll.getProjectNo() != null && !researchEnroll.getProjectNo().equals("")){
                researchScoreService.updateResearchFinalProjectNo(researchEnroll);
            }else if(scoreStageControl.equals("3")){
                ArrayList<ResearchScore> researchScores = researchScoreService.getResearchScoreListByResearchEnrollId(researchEnrollId);
                for(int i = 0; i < researchScores.size(); i++){
                    ResearchScore updateResearchScore = researchScores.get(i);
                    String updateComment = updateResearchScore.getComment();
                    double scoreResult = 0;
                    String commentResult = "[" + judgeScore1 + "," + judgeScore2 + "," + judgeScore3 + ",";
                    scoreResult += Integer.parseInt(judgeScore1) + Integer.parseInt(judgeScore2) +Integer.parseInt(judgeScore3);
                    String [] updateScores = updateComment.substring(updateComment.indexOf("[") + 1,updateComment.indexOf("]")).split(",");
                    for(int j = 3; j < updateScores.length; j++){
                        commentResult += updateScores[j] + ",";
                        scoreResult += Integer.parseInt(updateScores[j]);
                    }
                    commentResult = commentResult.substring(0, commentResult.length()-1);
                    commentResult += "]" + updateComment.substring(updateComment.indexOf("]")+1);

                    updateResearchScore.setComment(commentResult);
                    updateResearchScore.setScore(scoreResult);
                    researchScoreService.updateResearchAdminScore(updateResearchScore);
                }
                //更新奖项
                researchScoreService.updateResearchFinalJudgeRes(researchEnroll);
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

    public String getScoreStageControl() {
        return scoreStageControl;
    }

    public void setScoreStageControl(String scoreStageControl) {
        this.scoreStageControl = scoreStageControl;
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

    public String getPreScore() {
        return preScore;
    }

    public void setPreScore(String preScore) {
        this.preScore = preScore;
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

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
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


}
