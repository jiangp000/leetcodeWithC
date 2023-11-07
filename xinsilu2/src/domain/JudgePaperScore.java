package domain;

/**
 * Created by jinggu on 2019/10/30.
 */

public class JudgePaperScore {
    int id;
    int researchEnrollId;
    int judgeId;
    int videoScore;
    int documentScore;
    double score;
    String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResearchEnrollId() {
        return researchEnrollId;
    }

    public void setResearchEnrollId(int researchEnrollId) {
        this.researchEnrollId = researchEnrollId;
    }

    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getVideoScore() {
        return videoScore;
    }

    public int getDocumentScore() {
        return documentScore;
    }

    public void setVideoScore(int videoScore) {
        this.videoScore = videoScore;
    }

    public void setDocumentScore(int documentScore) {
        this.documentScore = documentScore;
    }
}
