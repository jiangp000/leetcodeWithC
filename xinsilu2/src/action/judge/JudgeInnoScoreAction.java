package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import domain.ResearchScore;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ApplyFilesService;
import service.ResearchEnrollService;
import service.ResearchScoreService;
import util.UploadUtil;

import java.util.*;

/**
 * Created by jinggu on 19/5/2.
 */
public class JudgeInnoScoreAction extends ActionSupport {

    private ArrayList<ResearchEnroll> researchEnrollList;
    private ResearchScore researchScore;
    private ResearchEnroll researchEnroll;

    private ResearchEnrollService researchEnrollService;
    private ResearchScoreService researchScoreService;
    private ApplyFilesService applyFilesService;

    private ActionContext context;
    private Map<String, Object> session;

    private String scoreStageControl;

    private String file1;
    private String file2;
    private String file3;
    private String file4;
    private String file5;
    private String file6;
    private String file7;
    private String file8;
    private String file9;
    private String file10;
    private String file11;
    private String file12;
    private String file13;
    private String file14;  //延期申请


    private String judgeScore1;
    private String judgeScore2;
    private String judgeScore3;
    private String judgeScore4;
    private String judgeScore5;
    private String videoScore;
    private String judgeScore6;
    private String judgeScore7;
    private String judgeScore8;
    private String judgeScore9;
    private String documentScore;
    private String preScore;
    private String judgeScore10;
    private String judgeScore11;
    private String judgeScore12;
    private String judgeScore13;
    private String judgeScore14;
    private String pptScore;

    private String submitSave;
    private String score;
    private String isPass;
    private ArrayList<String> isPassList;
    private String applyId;
    private String applyPreComment;
    private int id;
    private String jsonStr; // 用于前端发请求时序列化的jsonStr的传输

    public JudgeInnoScoreAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        researchEnrollService = new ResearchEnrollService();
        researchScoreService = new ResearchScoreService();
        applyFilesService = new ApplyFilesService();

        researchEnrollList = new ArrayList<>();
        researchScore = new ResearchScore();

        isPassList = new ArrayList<>();
        researchEnrollList();
        scoreStageControl = researchEnrollService.getScoreStage( "创新大赛");
    }

    public String getUploadScores(){
        return SUCCESS;
    }

    // 将上传的评分表分数批量写入数据库
    public String submitAllScores() {
        Object jid = session.get("judge");
//        int judgeId = 2;
        int judgeId = (int) jid;
        JSONObject obj = JSONObject.fromObject(jsonStr);
        Set set = obj.keySet();
        boolean pptScore = false;   // 是否为现评
        if(scoreStageControl.equals("0")){  // 不允许提交
            return SUCCESS;
        }
        // 创新大赛阶段各项分数 需要区分网评和现评阶段
        String fields = "4,8,8,6,4,8,8,8,6";
        if(scoreStageControl.equals("3")){
            fields = "6,8,8,8,10";
            pptScore = true;
        }
        for(Object s:set){
            Object val = obj.get(s);
            JSONObject innerObj = JSONObject.fromObject(val);
            JSONArray scores = (JSONArray) innerObj.get("scores");
            String comment = (String) innerObj.get("comment");
            String projectID = (String) innerObj.get("id");
            ArrayList<ResearchScore> res = researchScoreService.getResearchScoreByJudgeIdAndResearchEnrollId(judgeId, Integer.parseInt(projectID));

            String scoreStr = "[";
            String scoreStrSuffix = "6,8,8,8,10,";
            int otherPartScore = 0; // 记录另一个阶段的分数，最后写在总分上
            int documentScore = 0;
            int videoScore = 0;
            ResearchScore target = null;
            if(pptScore){   // 现评 保留查出来的预评分数 如果没有对应的记录，自动用满分记录预评分数
                if(res.size()>0){
                    target = res.get(0);
                    if(target.getSubmitSave().equals("1")) continue;    // 已经保存的评分也不能批量修改
                    String prevComment = target.getComment();
                    String [] prevScores = prevComment.substring(prevComment.indexOf("[") + 1,prevComment.indexOf("]")).split(",");
                    for(int i = 0; i < 5; i++){
                        scoreStr+=prevScores[i]+",";
                        videoScore+=Integer.parseInt(prevScores[i]);
                    }
                    for(int i = 5; i < 9; i++){
                        scoreStr+=prevScores[i]+",";
                        documentScore+=Integer.parseInt(prevScores[i]);
                    }
                    otherPartScore = target.getDocumentScore()+target.getVideoScore();
                }else{  // 没有找到记录，预评记作满分
                    scoreStr = "[4,8,8,6,4,8,8,8,6";
                    otherPartScore = 60;
                    videoScore = 30;
                    documentScore =30;
                }
            }else{  // 预评 现评阶段分数统一设置为满分
                if(res.size()>0){
                    target = res.get(0);
                    if(target.getSubmitSave().equals("1")) continue;
                }
                otherPartScore = 40;
            }
            int totalScore = 0;
            int totalMaxScore = 0;
            int index = 0;
            boolean scoreFlag = false;
            for(Object ss:scores){
                int score = Integer.parseInt((String) ss);
                int maxScore = Integer.parseInt(fields.split(",")[index]);
                totalMaxScore+=maxScore;
                index++;
                // 超过限定值的分数不提交
                if(score>maxScore||score<0){
                    scoreFlag=true;
                    break;
                }
                totalScore+=score;
                scoreStr +=ss+",";
                // 网评还需要记录小分
                if(!pptScore){
                    if(index<6){
                        videoScore+=score;
                    }else{
                        documentScore+=score;
                    }
                }
            }
            if(scoreFlag) continue;
            if(!pptScore) scoreStr+=scoreStrSuffix; // 网评阶段将默认现评分数添加在后面
            scoreStr = scoreStr.substring(0, scoreStr.length()-1)+"]"+comment;
            // 如果评分为满分且评价字段没改，认为是没评分
            if(totalScore==totalMaxScore&&comment.equals("尚未评分")){
                continue;
            }
            totalScore+=otherPartScore;

            if(res.size()>0){
                target.setScore(totalScore);
                target.setComment(scoreStr);
                if(!pptScore){
                    target.setDocumentScore(documentScore);
                    target.setVideoScore(videoScore);
                }
                researchScoreService.updateResearchScore(target);
            }else{
                // 未找到这一条记录，新建一条评分
                ResearchScore researchScore1 = new ResearchScore();
                researchScore1.setComment(scoreStr);
                researchScore1.setVideoScore(videoScore);
                researchScore1.setDocumentScore(documentScore);
                researchScore1.setResearchEnrollId(Integer.parseInt(projectID));
                researchScore1.setJudgeId(judgeId);
                researchScore1.setScore(totalScore);
                researchScore1.setSubmitSave("0");
                researchScoreService.insertResearchScore(researchScore1);
            }
        }
        return SUCCESS;
    }

    // 评分，很多提交记录，导出列表
    private String researchEnrollList() {
        Object jid = session.get("judge");
//        int judgeId = 2;
        int judgeId = (int) jid;
        this.researchScore.setJudgeId(judgeId);

        researchEnrollList = researchEnrollService.getResearchEnrollListByType("创新大赛");

        judgeScore1 = "4";
        judgeScore2 = "8";
        judgeScore3 = "8";
        judgeScore4 = "6";
        judgeScore5 = "4";
        videoScore = "30";
        judgeScore6 = "8";
        judgeScore7 = "8";
        judgeScore8 = "8";
        judgeScore9 = "6";
        documentScore = "30";
        preScore = "60";
        judgeScore10 = "6";
        judgeScore11 = "8";
        judgeScore12 = "8";
        judgeScore13 = "8";
        judgeScore14 = "10";
        pptScore = "40";
        score = "100";
        submitSave = "0";
        //isPass = "通过";
        List<String> isPassTemp = Arrays.asList("尚未初审","通过","不通过","修改提交");
        isPassList.addAll(isPassTemp);
        applyId = "";
        applyPreComment = "无";
        this.researchScore.setComment("无");


        return SUCCESS;

    }

    public String innoJSP() {
        if(researchEnrollList.size()==0){
            return NONE;
        }
        Object jid = session.get("judge");
//        int judgeId = 2;
        int judgeId = (int) jid;
        researchEnroll = researchEnrollList.get(id - 1);
        int researchEnrollId = researchEnroll.getId();

        String teamMembers = researchEnroll.getTeamMember();
        String resultTeamMember = "";
        if(teamMembers.equals(",,;,,;,,;,,") || teamMembers.equals(",,院系;,,院系;,,院系;,,院系")){
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
                videoScore = String.valueOf(researchScore.getVideoScore());
                documentScore = String.valueOf(researchScore.getDocumentScore());
                preScore = String.valueOf(researchScore.getVideoScore() + researchScore.getDocumentScore());

                judgeScore10 = scores[9];
                judgeScore11 = scores[10];
                judgeScore12 = scores[11];
                judgeScore13 = scores[12];
                judgeScore14 = scores[13];
                pptScore = String.valueOf(Integer.valueOf(scores[9]) + Integer.valueOf(scores[10])+ Integer.valueOf(scores[11])
                        + Integer.valueOf(scores[12]) + Integer.valueOf(scores[13]));
                submitSave = researchScore.getSubmitSave();

                score = String.valueOf((int)researchScore.getScore());

                researchScore.setComment(comment.substring(comment.indexOf("]")+1));
            }else{
                researchScore = researchScoresList.get(0);
                String comment = researchScore.getComment();
                String [] scores = comment.substring(comment.indexOf("[") + 1,comment.indexOf("]")).split(",");
                judgeScore1 = "4";
                judgeScore2 = "8";
                judgeScore3 = "8";
                judgeScore4 = "6";
                judgeScore5 = "4";
                videoScore = "30";
                judgeScore6 = "8";
                judgeScore7 = "8";
                judgeScore8 = "8";
                judgeScore9 = "6";
                documentScore = "30";
                preScore = "60";
                judgeScore10 = "6";
                judgeScore11 = "8";
                judgeScore12 = "8";
                judgeScore13 = "8";
                judgeScore14 = "10";
                pptScore = "40";

                submitSave = researchScore.getSubmitSave();
                score = "100";
                researchScore.setComment("无");
            }
        }

        // file1 申报书 file2 创新成果报告 file3 创新成果支撑材料目录 file4 案例视频1 file5 案例视频2 file6 视频信息表 file7 案例设计教案 file8 案例课件 file9 大纲
        // file10~12 相关材料1~3 file13 现场汇报材料
        // file14 延期申请材料
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String applyUserID = researchEnroll.getUserId();
        ArrayList<String> res = applyFilesService.getFilePaths(applyUserID,"创新大赛",year);
        if(res.size()==0) return SUCCESS;
        String fileRootPath = res.get(1);
        for(int i = 2; i < 19; i++){
            String fileName = res.get(i);
            if(fileName!=null&&fileName.length()>0){
                fileName = UploadUtil.fetchFileByNamePath(fileRootPath,fileName);
            }else{
                fileName = "";
            }
            fileName = fileName.replace("\\","/");  // win上文件格式替换
            res.set(i,fileName);
        }
        file1 = res.get(3);     // 申报书pdf
        file2 = res.get(4);     // 成果报告
        file3 = res.get(5);     // 材料目录
        file4 = res.get(6);     // 案例视频1
        file5 = res.get(7);     // 案例视频2
        file6 = res.get(8);     // 视频信息表
        file7 = res.get(9);     // 教案
        file8 = res.get(10);    // 课件pptx
        file9 = res.get(12);    // 大纲
        file10 = res.get(13);   // 相关材料1
        file11 = res.get(14);   // 相关材料2
        file12 = res.get(15);   // 相关材料3
        file13 = res.get(17);   // 现场汇报材料pdf
        file14 = res.get(18);   // 延期申请


        return SUCCESS;
    }

    public String submitInnoScore() {
        Object jid = session.get("judge");
//        int judgeId = 2;
        int judgeId = (int) jid;
        if(judgeId == 2){
            researchEnroll = researchEnrollList.get(id - 1);
            researchEnroll.setIsPass(isPass);
            researchEnroll.setApplyId(applyId);
            researchEnroll.setApplyPreComment(applyPreComment);
            if(isPass != null && isPass.equals("修改提交")){
                researchEnroll.setSubmitSave("0");
            }
            researchEnrollService.updateResearchEnrollPre(researchEnroll);

            String comment = researchScore.getComment();

            String scores = "[" + judgeScore1 + "," + judgeScore2 + "," + judgeScore3 + "," + judgeScore4 + "," +
                    judgeScore5 + "," + judgeScore6 + "," + judgeScore7 + "," + judgeScore8 + "," + judgeScore9 + ","+
                    judgeScore10 + "," + judgeScore11 + "," + judgeScore12 + "," + judgeScore13 + "," + judgeScore14 + "]";

            researchScore.setComment(scores + comment);
            researchScore.setSubmitSave(submitSave);

            researchScore.setVideoScore(Integer.parseInt(videoScore));
            researchScore.setDocumentScore(Integer.parseInt(documentScore));
            researchScore.setScore(Double.parseDouble(score));
            if(researchScore.getScore() == 0){
                researchScore.setScore(100);
            }


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

            String scores = "[" + judgeScore1 + "," + judgeScore2 + "," + judgeScore3 + "," + judgeScore4 + "," +
                    judgeScore5 + "," + judgeScore6 + "," + judgeScore7 + "," + judgeScore8 + "," + judgeScore9 + ","+
                    judgeScore10 + "," + judgeScore11 + "," + judgeScore12 + "," + judgeScore13 + "," + judgeScore14 + "]";

            researchScore.setComment(scores + comment);
            researchScore.setSubmitSave(submitSave);

            researchScore.setVideoScore(Integer.parseInt(videoScore));
            researchScore.setDocumentScore(Integer.parseInt(documentScore));
            researchScore.setScore(Double.parseDouble(score));
            if(researchScore.getScore() == 0){
                researchScore.setScore(100);
            }

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

    public String getFile3() {
        return file3;
    }

    public String getFile4() {
        return file4;
    }

    public String getFile5() {
        return file5;
    }

    public String getScoreStageControl() {
        return scoreStageControl;
    }

    public void setScoreStageControl(String scoreStageControl) {
        this.scoreStageControl = scoreStageControl;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public void setFile3(String file3) {
        this.file2 = file3;
    }

    public void setFile4(String file4) {
        this.file1 = file4;
    }

    public void setFile5(String file5) {
        this.file2 = file5;
    }

    public String getFile6() {
        return file6;
    }

    public void setFile6(String file6) {
        this.file6 = file6;
    }

    public String getFile7() {
        return file7;
    }

    public void setFile7(String file7) {
        this.file7 = file7;
    }

    public String getFile8() {
        return file8;
    }

    public void setFile8(String file8) {
        this.file8 = file8;
    }

    public String getFile9() {
        return file9;
    }

    public void setFile9(String file9) {
        this.file9 = file9;
    }

    public String getFile10() {
        return file10;
    }

    public void setFile10(String file10) {
        this.file10 = file10;
    }

    public String getFile11() {
        return file11;
    }

    public void setFile11(String file11) {
        this.file11 = file11;
    }

    public String getFile12() {
        return file12;
    }

    public void setFile12(String file12) {
        this.file12 = file12;
    }

    public String getFile13() {
        return file13;
    }

    public void setFile13(String file13) {
        this.file13 = file13;
    }

    public String getFile14() {
        return file14;
    }

    public void setFile14(String file14) {
        this.file14 = file14;
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

    public String getDocumentScore() {
        return documentScore;
    }

    public void setDocumentScore(String documentScore) {
        this.documentScore = documentScore;
    }

    public String getVideoScore() {
        return videoScore;
    }

    public void setVideoScore(String videoScore) {
        this.videoScore = videoScore;
    }

    public String getJudgeScore10() {
        return judgeScore10;
    }

    public void setJudgeScore10(String judgeScore10) {
        this.judgeScore10 = judgeScore10;
    }

    public String getJudgeScore11() {
        return judgeScore11;
    }

    public void setJudgeScore11(String judgeScore11) {
        this.judgeScore11 = judgeScore11;
    }

    public String getJudgeScore12() {
        return judgeScore12;
    }

    public void setJudgeScore12(String judgeScore12) {
        this.judgeScore12 = judgeScore12;
    }

    public String getJudgeScore13() {
        return judgeScore13;
    }

    public void setJudgeScore13(String judgeScore13) {
        this.judgeScore13 = judgeScore13;
    }

    public String getJudgeScore14() {
        return judgeScore14;
    }

    public void setJudgeScore14(String judgeScore14) {
        this.judgeScore14 = judgeScore14;
    }

    public String getPptScore() {
        return pptScore;
    }

    public void setPptScore(String pptScore) {
        this.pptScore = pptScore;
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
