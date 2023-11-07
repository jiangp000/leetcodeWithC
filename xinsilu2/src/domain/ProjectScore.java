package domain;

/**
 * Created by jinggu on 2019/10/30.
 */

public class ProjectScore {
    int id;
    int projectEnrollId;
    int judgeId;
    int videoScore;
    int documentScore;
    int score;
    String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectEnrollId() {
        return projectEnrollId;
    }

    public void setProjectEnrollId(int projectEnrollId) {
        this.projectEnrollId = projectEnrollId;
    }

    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
