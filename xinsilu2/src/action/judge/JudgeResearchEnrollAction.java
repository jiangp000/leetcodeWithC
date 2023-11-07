package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import domain.ResearchScore;
import service.ResearchEnrollService;
import service.ResearchScoreService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jinggu on 19/5/2.
 */
public class JudgeResearchEnrollAction extends ActionSupport {

    private ArrayList<ResearchEnroll> researchEnrollList;
    private ResearchScore researchScore;
    private ResearchEnroll researchEnroll;

    private ResearchEnrollService researchEnrollService;
    private ResearchScoreService researchScoreService;

    private ActionContext context;
    private Map<String, Object> session;


    private String file1;
    private String file2;
    private String file3;
    private String file4;
    private String file5;
    private String file6;
    private String file7;
    private String file8;

    private String videoScore1;
    private String videoScore2;
    private String videoScore3;
    private String videoScore4;
    private String videoScore5;
    private String documentScore1;
    private String documentScore2;
    private String documentScore3;
    private String documentScore4;
    private String score;
    private int id;

    public JudgeResearchEnrollAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        researchEnrollService = new ResearchEnrollService();
        researchScoreService = new ResearchScoreService();

        researchEnrollList = new ArrayList<ResearchEnroll>();
        researchScore = new ResearchScore();
        researchEnrollList();
    }


    public String researchEnrollList() {
        int judgeId = (int) session.get("judge");
        this.researchScore.setJudgeId(judgeId);
        researchEnrollList = researchEnrollService.getResearchEnrollListByDate(Date.valueOf("2021-07-01"));
        return SUCCESS;

    }

    public String scoreJSP() {
        if(researchEnrollList.size()==0) return NONE;
        int judgeId = (int) session.get("judge");
        researchEnroll = researchEnrollList.get(id - 1);
        int researchEnrollId = researchEnroll.getId();
        ArrayList<ResearchScore> researchScoresList = researchScoreService.getResearchScoreByJudgeIdAndResearchEnrollId(judgeId,researchEnrollId);
        if(researchScoresList.size() != 0){
            researchScore = researchScoresList.get(0);
            String comment = researchScore.getComment();
            String [] scores = comment.substring(comment.indexOf("[") + 1,comment.indexOf("]")).split(",");
            videoScore1 = scores[0];
            videoScore2 = scores[1];
            videoScore3 = scores[2];
            videoScore4 = scores[3];
            videoScore5 = scores[4];
            documentScore1 = scores[5];
            documentScore2 = scores[6];
            documentScore3 = scores[7];
            documentScore4 = scores [8];
            score = String.valueOf(researchScore.getScore());
            researchScore.setComment(comment.substring(comment.indexOf("]")+1));
        }
        String[] files = researchEnroll.getFilePath().split(";");
        file1 = files[0];
        file2 = files[1];
        file3 = files[2];
        file4 = files[3];
        file5 = files[4];
        file6 = files[5];
        file7 = files[6];
        file8 = files[7];
        return SUCCESS;
    }

    public String submitScore() {
        String comment = researchScore.getComment();
        String scores = "[" + videoScore1 + "," + videoScore2 + "," + videoScore3 + "," + videoScore4 + "," + videoScore5 + "," + documentScore1 + "," + documentScore2 + "," + documentScore3 + "," + documentScore4+ "]";
        researchScore.setComment(scores + comment);
        int judgeId = (int) session.get("judge");
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

    public String getFile6() {
        return file6;
    }

    public String getFile7() {
        return file7;
    }

    public String getFile8() {
        return file8;
    }

    public String getVideoScore1() {
        return videoScore1;
    }

    public String getVideoScore2() {
        return videoScore2;
    }

    public String getVideoScore3() {
        return videoScore3;
    }

    public String getVideoScore4() {
        return videoScore4;
    }

    public String getVideoScore5() {
        return videoScore5;
    }

    public String getDocumentScore1() {
        return documentScore1;
    }

    public String getDocumentScore2() {
        return documentScore2;
    }

    public String getDocumentScore3() {
        return documentScore3;
    }

    public String getDocumentScore4() {
        return documentScore4;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public void setFile3(String file3) {
        this.file3 = file3;
    }

    public void setFile4(String file4) {
        this.file4 = file4;
    }

    public void setFile5(String file5) {
        this.file5 = file5;
    }

    public void setFile6(String file6) {
        this.file6 = file6;
    }

    public void setFile7(String file7) {
        this.file7 = file7;
    }

    public void setFile8(String file8) {
        this.file8 = file8;
    }

    public void setVideoScore1(String videoScore1) {
        this.videoScore1 = videoScore1;
    }

    public void setVideoScore2(String videoScore2) {
        this.videoScore2 = videoScore2;
    }

    public void setVideoScore3(String videoScore3) {
        this.videoScore3 = videoScore3;
    }

    public void setVideoScore4(String videoScore4) {
        this.videoScore4 = videoScore4;
    }

    public void setVideoScore5(String videoScore5) {
        this.videoScore5 = videoScore5;
    }

    public void setDocumentScore1(String documentScore1) {
        this.documentScore1 = documentScore1;
    }

    public void setDocumentScore2(String documentScore2) {
        this.documentScore2 = documentScore2;
    }

    public void setDocumentScore3(String documentScore3) {
        this.documentScore3 = documentScore3;
    }

    public void setDocumentScore4(String documentScore4) {
        this.documentScore4 = documentScore4;
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
}
