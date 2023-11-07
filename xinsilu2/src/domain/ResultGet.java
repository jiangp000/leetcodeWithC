package domain;

/**
 * Created by widesui on 20/7/12.
 */
public class ResultGet {
    private String groupId;
    private String userId;
    private String username;
    private String title;
    private double videoScore;
    private double videoScore1;
    private double videoScore2;
    private double videoScore3;
    private double videoScore4;
    private double videoScore5;
    private double documentScore1;
    private double documentScore2;
    private double documentScore3;
    private double documentScore4;
    private double documentScore;
    private double score;
    private int times;  // 记录共有多少位评委已经评分

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getTimes() {
        return times;
    }

    public void setVideoScore1(double videoScore1) {
        this.videoScore1 = videoScore1;
    }

    public void setVideoScore2(double videoScore2) {
        this.videoScore2 = videoScore2;
    }

    public void setVideoScore3(double videoScore3) {
        this.videoScore3 = videoScore3;
    }

    public void setVideoScore4(double videoScore4) {
        this.videoScore4 = videoScore4;
    }

    public void setVideoScore5(double videoScore5) {
        this.videoScore5 = videoScore5;
    }

    public void setDocumentScore1(double documentScore1) {
        this.documentScore1 = documentScore1;
    }

    public void setDocumentScore2(double documentScore2) {
        this.documentScore2 = documentScore2;
    }

    public void setDocumentScore3(double documentScore3) {
        this.documentScore3 = documentScore3;
    }

    public void setDocumentScore4(double documentScore4) {
        this.documentScore4 = documentScore4;
    }

    public double getVideoScore1() {
        return videoScore1;
    }

    public double getVideoScore2() {
        return videoScore2;
    }

    public double getVideoScore3() {
        return videoScore3;
    }

    public double getVideoScore4() {
        return videoScore4;
    }

    public double getVideoScore5() {
        return videoScore5;
    }

    public double getDocumentScore1() {
        return documentScore1;
    }

    public double getDocumentScore2() {
        return documentScore2;
    }

    public double getDocumentScore3() {
        return documentScore3;
    }

    public double getDocumentScore4() {
        return documentScore4;
    }

    public double getVideoScore() {
        return videoScore;
    }

    public void setVideoScore(double videoScore) {
        this.videoScore = videoScore;
    }

    public void setDocumentScore(double documentScore) {
        this.documentScore = documentScore;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getDocumentScore() {
        return documentScore;
    }

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

    public double getScore() {
        return score;
    }

    public String toCSVStr() {
        return groupId + "," + userId + "\t," + username + "," + title + "," + String.valueOf(videoScore) + "," +
                String.valueOf(documentScore) + "," + String.valueOf(score) + "," + String.valueOf(times);
//        return userId + "," + username + "," + title + "," + String.valueOf(videoScore1) + "," +
//                String.valueOf(videoScore2) + "," + String.valueOf(videoScore3) + "," +
//                String.valueOf(videoScore4) + "," + String.valueOf(videoScore5) + "," +
//                String.valueOf(videoScore) + "," + String.valueOf(documentScore1) + "," +
//                String.valueOf(documentScore2) + "," + String.valueOf(documentScore3) + "," +
//                String.valueOf(documentScore4) + "," + String.valueOf(documentScore) + "," +
//                String.valueOf(score);
    }
}
