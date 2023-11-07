package domain;

/**
 * @author husky
 * @version 1.0
 * @date 2020/12/16 8:46
 */
public class JudgeScore {
    int id;
    int judgeEnrollId;
    int judgeId;
    int score;
    String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJudgeEnrollId() {
        return judgeEnrollId;
    }

    public void setProjectEnrollId(int projectEnrollId) {
        this.judgeEnrollId = projectEnrollId;
    }

    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
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
