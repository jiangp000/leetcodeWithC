package domain;

/**
 * Created by widesui on 20/7/12.
 */
public class ResultScore {

    private String userId;
    private String username;
    private String title;
    private String projectNo;
    private String department;
    private String researchType;
    private double score;
    private int times;  // 记录共有多少位评委已经评分


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String toCSVStr() {
        return projectNo + "," + userId + "\t," + username + "," + title + "," + department + ","
                +  researchType + "," + String.valueOf(score) + "," + String.valueOf(times);
//        return userId + "," + username + "," + title + "," + String.valueOf(videoScore1) + "," +
//                String.valueOf(videoScore2) + "," + String.valueOf(videoScore3) + "," +
//                String.valueOf(videoScore4) + "," + String.valueOf(videoScore5) + "," +
//                String.valueOf(videoScore) + "," + String.valueOf(documentScore1) + "," +
//                String.valueOf(documentScore2) + "," + String.valueOf(documentScore3) + "," +
//                String.valueOf(documentScore4) + "," + String.valueOf(documentScore) + "," +
//                String.valueOf(score);
    }
}
